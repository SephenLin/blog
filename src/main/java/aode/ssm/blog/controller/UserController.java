package aode.ssm.blog.controller;

import aode.ssm.blog.po.Article;
import aode.ssm.blog.po.Image;
import aode.ssm.blog.po.ImageCustom;
import aode.ssm.blog.po.User;
import aode.ssm.blog.servlet.*;
import aode.ssm.blog.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 林进华 on 2017/7/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    ArticleServlet articleServlet;
    @Autowired
    ImageServlet imageServlet;
    @Autowired
    UserServlet userServlet;
    @Autowired
    CommentServlet commentServlet;
    @Autowired
    AdminServlet adminServlet;

    @RequestMapping("/indexUI")
    public String indexUI() {
        return "user/index";
    }

    @RequestMapping("/welcome")
    public String welcomeUI() {
        return "welcome";
    }

    // 文章操作系列

    @ResponseBody
    @RequestMapping(value="/article-list", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String articleList(Model model,PageBean pageBean,HttpSession session) throws IOException, ParseException {
        System.out.println(" 当前的页数 : " + pageBean.getNowPage() + "*****  *** 每页的大小 ：" + pageBean.getSize());
        ObjectMapper objectMapper = new ObjectMapper(); //json 数据
        User loginUser = (User) session.getAttribute("loginUser");
        int id = loginUser.getId();
        System.out.println(" 登录人的id : " + id + "*****  *** 每页的大小 ：" + pageBean.getSize());
        Map map = articleServlet.selectAllArticleByUserId(id,pageBean);
        return objectMapper.writeValueAsString(map);
    }

    @RequestMapping("/article-show")
    public String lookArticle(@RequestParam("id")int id,Model model) {
        model.addAttribute("article",adminServlet.lookArticleById(id));
        return "/admin/article-show";
    }

    @RequestMapping("/article-listUI")
    public String articleListUI(Model model,HttpSession session) throws IOException {
        User loginUser = (User) session.getAttribute("loginUser");
        int id = loginUser.getId();
        //在这里的size定义初始化时的条数
        PageBean pageBean = articleServlet.initPage(id,new PageBean(5,1,0,null));
        model.addAttribute("initPage",pageBean);
        // 初始化连续显示的分页数
        model.addAttribute("initPageSize",
                pageBean.getEndPageIndex() - pageBean.getStartPageIndex() > 3 ? 4 : pageBean.getEndPageIndex());
        return "user/article-list";
    }

    @RequestMapping("/article-updateUI")
    public String articleList(Model model,@RequestParam("article_id")int id) {
        model.addAttribute("article",articleServlet.selectArticleById(id));
        return "user/article-update";
    }

    @RequestMapping("/article-update")
    public void articleUpdate(Article article,@RequestParam("content1")String content,
                              @RequestParam("type")String types,HttpSession session) throws Exception {
        article.setContent(content);
        System.out.println(article.getEndTime().toString() + " 长度 : " + article.getContent().length() + "*****  *** 内容 ：" + article.getContent() + types);
        User user = (User) session.getAttribute("loginUser");
        articleServlet.articleUpdateById(user.getId(),article,types);
    }

    @ResponseBody
    @RequestMapping("/articles-delete")
    public String deleteArticles(HttpServletRequest request,HttpSession session,PageBean pageBean) throws Exception {
        String id = request.getParameter("ids");
        String[] temps = id.split(",");
        int[] ids = new int[temps.length];
        for(int i = 0; i < temps.length; i++) {
            ids[i] = Integer.parseInt(temps[i]);
        }
        User user = (User) session.getAttribute("userLogin");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(articleServlet.delectArticlesById(ids, 1,pageBean));
    }


    @RequestMapping("/article-addUI")
    public String articleAdd() {
        return "user/article-add";
    }

    @ResponseBody
    @RequestMapping(value="/article-audit", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String addImage(@RequestParam("id")int id,@RequestParam("audit")String audit) throws Exception {
        Article article = new Article();
        article.setId(id);
        article.setAudit(audit);
        return articleServlet.updateArticleAuditById(article);
    }

    @RequestMapping(value="/article-add")
    public String addImage(Article article, @RequestParam("content1")String content, @RequestParam("type")String types,
                           @RequestParam("startTime")Date startTime, @RequestParam("endTime")String endTime,
                           HttpSession session) throws Exception {
        article.setContent(content);
        User user = (User) session.getAttribute("loginUser");
        articleServlet.add_article(user.getId(),article,types);
        return "user/article-list";
    }

    // 文章操作系列结束

    // 2.博主修改资料或查看系列

    // 用户注销
    @RequestMapping(value="/loginOut")
    public String loginOut(HttpSession session) throws Exception {
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "redirect:/login.jsp";
    }

    // 用户查看
    @RequestMapping(value="/user-look")
    public String lookUser(@Param("id")int id,Model model,HttpSession session) throws Exception {
        User user = userServlet.selectUserById(id);
        if(user == null) {
            model.addAttribute("errorMassege","发生未知错误！");
            return "redirect:indexUI";
        }else {
            model.addAttribute("lookUser",user);
            return "user/userMassege";
        }
    }

    // 用户修改
    @ResponseBody
    @RequestMapping(value="/user-update", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String userUpdate(HttpSession session,User user,Model model,@Param("file") MultipartFile file,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        User temp = (User) session.getAttribute("loginUser");
        user.setTime(temp.getTime());
        user.setId(temp.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");// 将从数据库取出来的时间格式化成想要的格式
        String path = "resources\\headImage\\" + user.getName() + "\\" + dateFormater.format(new Date());
        Map<String,Object> map = userServlet.add_head(file,request, response,path,new User());
        User tempUser = (User) map.get("user");
        user.setHeadName(tempUser.getHeadName());
        user.setHeadUrl(tempUser.getHeadUrl());
        session.setAttribute("loginUser",user);
        return objectMapper.writeValueAsString(userServlet.updateUser(user));
    }

    // 2.博主修改资料或查看系列结束

    // 3.留言板系列
    @RequestMapping(value="/comment-listUI")
    public String commentListUI(HttpSession session,Model model) throws Exception {
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println("comment-list进来了");
        PageBean pageBean = commentServlet.userInitPage(loginUser.getId(),new PageBean(5,1,0,null));
        System.out.println(pageBean.toString());
        model.addAttribute("initPage",pageBean);
        // 初始化连续显示的分页数
        model.addAttribute("initPageSize",
                pageBean.getEndPageIndex() - pageBean.getStartPageIndex() > 3 ? 4 : pageBean.getEndPageIndex());
        return "user/comment-list";
    }

    @ResponseBody
    @RequestMapping(value="/comment-list", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String commentList(Model model,PageBean pageBean,HttpSession session) throws IOException, ParseException {
        System.out.println("comment-list进来了");
        System.out.println(pageBean.toString());
        ObjectMapper objectMapper = new ObjectMapper(); //json 数据
        User loginUser = (User) session.getAttribute("loginUser");
        int id = loginUser.getId();
        System.out.println(" 登录人的id : " + id + "*****  *** 每页的大小 ：" + pageBean.getSize());
        Map map = commentServlet.getAllCommentByUserId(id,pageBean);
        return objectMapper.writeValueAsString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/comments-delete", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String deleteComments(HttpServletRequest request,HttpSession session,PageBean pageBean) throws Exception {
        String id = request.getParameter("ids");
        String[] temps = id.split(",");
        int[] ids = new int[temps.length];
        for(int i = 0; i < temps.length; i++) {
            ids[i] = Integer.parseInt(temps[i]);
        }
        User user = (User) session.getAttribute("loginUser");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(commentServlet.delectCommentsById(ids, 1,pageBean));
    }

}
