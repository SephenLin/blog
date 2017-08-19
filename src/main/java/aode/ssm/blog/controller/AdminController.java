package aode.ssm.blog.controller;

import aode.ssm.blog.po.Admin;
import aode.ssm.blog.po.Article;
import aode.ssm.blog.po.User;
import aode.ssm.blog.servlet.AdminServlet;
import aode.ssm.blog.servlet.ArticleServlet;
import aode.ssm.blog.util.PageBean;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 林进华 on 2017/8/6.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServlet adminServlet;
    @Autowired
    ArticleServlet articleServlet;

    // 跳转到管理员登录页面
    @RequestMapping("/admin/loginUI")
    public String loginUI() {
        return "admin/login";
    }

    @RequestMapping("/welcome")
    public String welcomeUI() {
        return "welcome";
    }

    @RequestMapping("/indexUI")
    public String indexUI() {
        return "admin/index";
    }

    @RequestMapping("/user-delUI")
    public String user_delUI() {
        return "admin/user-del";
    }

    @RequestMapping("/system-articleTypeUI")
    public String system_article_typeUI() {
        return "admin/system-articleType";
    }

    @RequestMapping("/system-logUI")
    public String system_logUI() {
        return "admin/system-log";
    }

    // 修改管理员信息

    // 跳转管理员信息页面
    @RequestMapping("/admin-updateUI")
    public String system_updateUI() {
        return "admin/adminMassege";
    }

    // 管理员修改自己信息
    @ResponseBody
    @RequestMapping(value="/admin-update", method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String userUpdate(HttpSession session, Admin admin, Model model, @Param("file") MultipartFile file,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Admin temp = (Admin) session.getAttribute("loginAdmin");
        admin.setId(temp.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");// 将从数据库取出来的时间格式化成想要的格式
        String path = "resources\\admin\\headImage\\" + admin.getName() + "\\" + dateFormater.format(new Date());
        Map<String,Object> map = adminServlet.add_head(file,request, response,path,new Admin());
        Admin tempAdmin = (Admin) map.get("admin");
        System.out.println(tempAdmin.toString());
        admin.setHeadName(tempAdmin.getHeadName());
        admin.setHeadUrl(tempAdmin.getHeadUrl());
        session.setAttribute("loginAdmin",admin);
        return objectMapper.writeValueAsString(adminServlet.updateAdmin(admin));
    }

    @RequestMapping(value="/loginOut")
    public String loginOut(HttpSession session) throws Exception {
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "/admin/login";
    }
    // 修改管理员信息end

    // 管理员操作文章系列

    @RequestMapping("/article-show")
    public String lookArticle(@RequestParam("id")int id,Model model) {
        model.addAttribute("article",adminServlet.lookArticleById(id));
        return "/admin/article-show";
    }

    @ResponseBody
    @RequestMapping(value="/article-list", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String articleList(Model model, PageBean pageBean, HttpSession session) throws IOException, ParseException {
        System.out.println(pageBean.toString());
        ObjectMapper objectMapper = new ObjectMapper(); //json 数据
        Map map = adminServlet.selectAllArticle(pageBean);
        return objectMapper.writeValueAsString(map);
    }

    @RequestMapping("/article-listUI")
    public String articleListUI(Model model,HttpSession session) throws IOException {
        PageBean pageBean = adminServlet.initPage(new PageBean(5,1,0,null),new Article());
        model.addAttribute("initPage",pageBean);
        // 初始化连续显示的分页数
        model.addAttribute("initPageSize",
                pageBean.getEndPageIndex() - pageBean.getStartPageIndex() > 3 ? 4 : pageBean.getEndPageIndex());
        return "admin/article-list";
    }

    @ResponseBody
    @RequestMapping(value="/article-audit", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String addImage(@RequestParam("id")int id, @RequestParam("audit")String audit) throws Exception {
        Article article = new Article();
        article.setId(id);
        article.setAudit(audit);
        return articleServlet.updateArticleAuditById(article);
    }

    @ResponseBody
    @RequestMapping(value = "/articles-delete", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String deleteArticles(HttpServletRequest request,HttpSession session,PageBean pageBean) throws Exception {
        String id = request.getParameter("ids");
        String[] temps = id.split(",");
        int[] ids = new int[temps.length];
        for(int i = 0; i < temps.length; i++) {
            ids[i] = Integer.parseInt(temps[i]);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(adminServlet.delectArticlesById(ids,pageBean));
    }

    // 管理员操作文章系列end

    // 管路员管理用户界面

    @RequestMapping("/user-listUI")
    public String userListUI(HttpSession session,Model model) throws Exception {
        PageBean pageBean = adminServlet.initPage(new PageBean(5,1,0,null),new User());
        model.addAttribute("initPage",pageBean);
        // 初始化连续显示的分页数
        model.addAttribute("initPageSize",
                pageBean.getEndPageIndex() - pageBean.getStartPageIndex() > 3 ? 4 : pageBean.getEndPageIndex());
        System.out.println("user-listUI  -------->------>" + pageBean.toString());
        return "admin/user-list";
    }

    // 导入excel表
    @ResponseBody
    @RequestMapping(value = "/user_export_excel", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String user_export_excel(HttpSession session,Model model,HttpServletRequest request) throws Exception {
        adminServlet.user_export_excel(request);
        Map<String,Object> map = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        map.put("excelUrl","Excel的存储位置为aodeProject\target\\blog\resources");
        return objectMapper.writeValueAsString(map);
    }

    // 下载excel表
    @RequestMapping(value="/download-user-excel")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam("filename") String filename,Model model)throws Exception {
        // windows下正斜杠/和反斜杠都是可以的
        // linux下只认正斜杠，为了保证跨平台性，不建议使用反斜杠（在java程序中是转义字符，用\来表示反斜
        String separator = File.separator;
        //下载文件路径
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");// 将从数据库取出来的时间格式化成想要的格式
        String path = request.getServletContext().getRealPath("resources" + separator + "userExcel" + separator + dateFormater.format(new Date()));
        File file = new File(path + separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value="/user-list", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String userList(Model model, PageBean pageBean, HttpSession session) throws Exception {
        System.out.println(pageBean.toString());
        ObjectMapper objectMapper = new ObjectMapper(); //json 数据
        Map map = adminServlet.selectAllUser(pageBean);
        // session.setAttribute("tempPageBean",map.get("pageBean")); // 用于下面用户修改密码时更新本页的用户页面，在《userPassword》这里使用
        return objectMapper.writeValueAsString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/users-delete", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String deleteUsers(HttpServletRequest request,HttpSession session,PageBean pageBean) throws Exception {
        String id = request.getParameter("ids");
        String[] temps = id.split(",");
        int[] ids = new int[temps.length];
        for(int i = 0; i < temps.length; i++) {
            ids[i] = Integer.parseInt(temps[i]);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(adminServlet.delectUsersById(ids,pageBean));
    }

    @RequestMapping("/user-show")
    public String lookUser(@RequestParam("id")int id,Model model) {
        model.addAttribute("user",adminServlet.lookUserById(id));
        return "/user/user-show";
    }

    // 转到用户密码页面
    @RequestMapping("/user-passwordUI")
    public String userPasswordUI(@RequestParam("id")int id,Model model) {
        model.addAttribute("user",adminServlet.lookUserById(id));
        return "/admin/user-password";
    }

    // 修改用户密码
    @RequestMapping("/user-password")
    public String userPassword(User user,Model model,HttpSession session) throws Exception {
        boolean result = adminServlet.updateUserPassword(user);
        return "admin/user-list";
    }

    // 禁止用户登录
    @ResponseBody
    @RequestMapping(value = "/user-audit-stop", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String userStop(@RequestParam("user_audit_id")String id,Model model) throws Exception {
        Map<String,Object> map = adminServlet.insertUserAudit(Integer.parseInt(id));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    // 允许用户登录
    @ResponseBody
    @RequestMapping(value = "/user-audit-start", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String userStart(@RequestParam("user_audit_id")String id,Model model) throws Exception {
        Map<String,Object> map = adminServlet.deleteUserAudit(Integer.parseInt(id));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }
}
