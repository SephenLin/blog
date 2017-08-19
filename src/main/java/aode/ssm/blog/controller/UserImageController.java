package aode.ssm.blog.controller;
import aode.ssm.blog.po.Article;
import aode.ssm.blog.po.Image;
import aode.ssm.blog.po.ImageCustom;
import aode.ssm.blog.po.User;
import aode.ssm.blog.servlet.ArticleServlet;
import aode.ssm.blog.servlet.ImageServlet;
import aode.ssm.blog.util.PageBean;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by 林进华 on 2017/7/18.
 */
@Controller
@RequestMapping("/image")
public class UserImageController {

    @Autowired
    ImageServlet imageServlet;
    @Autowired
    ArticleServlet articleServlet;

    @ResponseBody
    @RequestMapping(value = "/testImage",method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String testImage(@RequestParam(value = "upfile", required = false) MultipartFile file,
                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String separator = File.separator;
        // windows下正斜杠/和反斜杠都是可以的
        // linux下只认正斜杠，为了保证跨平台性，不建议使用反斜杠（在java程序中是转义字符，用\来表示反斜
        Map<String,Object> map = imageServlet.add_Image(file,request,response,"resources" + separator + "testImage" + separator + "test");
        return objectMapper.writeValueAsString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/image-add",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addImage(@RequestParam(value = "upfile", required = false) MultipartFile file,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String separator = File.separator;
        // windows下正斜杠/和反斜杠都是可以的
        // linux下只认正斜杠，为了保证跨平台性，不建议使用反斜杠（在java程序中是转义字符，用\来表示反斜
        Map<String,Object> map = imageServlet.add_Image(file,request,response,"resources" + separator + "testImage" + separator + "test");
        return objectMapper.writeValueAsString(map);
    }

    // 修改功能
    @RequestMapping(value="/image-updateUI")
    public String pictureUpdateUI(Model model, @RequestParam("id")int id) throws Exception {
        ImageCustom imageCustom = imageServlet.selectImageById(id);
        model.addAttribute("image",imageCustom);
        return "user/picture-update";
    }

    @RequestMapping("/image-update")
    public void UpdateImage(@RequestParam(value = "file", required = false) MultipartFile file,
                              @RequestParam(value = "id", required = false) int id,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        ImageCustom imageCustom = imageServlet.selectImageById(id);
        String separator = File.separator;
        // windows下正斜杠/和反斜杠都是可以的
        // linux下只认正斜杠，为了保证跨平台性，不建议使用反斜杠（在java程序中是转义字符，用\来表示反斜
        imageServlet.pictureUpdate(imageCustom,file,request,response,"resources" + separator + "testImage" + separator + "test");
    }

    // 删除功能

    @ResponseBody
    @RequestMapping(value = "/images-delete",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String deleteImages(HttpServletRequest request,HttpSession session,PageBean pageBean) throws Exception {
        String id = request.getParameter("ids");
        System.out.println("**************** + " + id);
        String[] temps = id.split(",");
        int[] ids = new int[temps.length];
        for(int i = 0; i < temps.length; i++) {
            ids[i] = Integer.parseInt(temps[i]);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        User user = (User) session.getAttribute("loginUser");
        return objectMapper.writeValueAsString(imageServlet.deleteImagesById(ids,user.getId(),pageBean));
    }

    // 列表功能
    @ResponseBody
    @RequestMapping(value="/image-list",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String pictureList(HttpSession session, Model model, PageBean pageBean) throws Exception {
        System.out.println(" 当前的页数 : " + pageBean.getNowPage() + "*****  *** 每页的大小 ：" + pageBean.getSize());
        ObjectMapper objectMapper = new ObjectMapper(); //json 数据
        User user = (User) session.getAttribute("loginUser");
        int id = user.getId();
        Map map = imageServlet.selectAllByUserId(id,pageBean);
        return objectMapper.writeValueAsString(map);
    }

    @RequestMapping(value="/image-listUI")
    public String articleListUI(Model model,HttpSession session) throws IOException {
        User user = (User) session.getAttribute("loginUser");
        //在这里的size定义初始化时的条数
        PageBean pageBean = imageServlet.initPage(user.getId(),new PageBean(5,1,0,null));
        model.addAttribute("initPage",pageBean);
        // 初始化连续显示的分页数
        model.addAttribute("initPageSize",
                pageBean.getEndPageIndex() - pageBean.getStartPageIndex() > 3 ? 4 : pageBean.getEndPageIndex());
        return "user/picture-list";
    }

    //修改审核状态
    @ResponseBody
    @RequestMapping(value="/image-audit")
    public String addImage(@RequestParam("id")int id,@RequestParam("audit")String audit) throws Exception {
        Article article = new Article();
        article.setId(id);
        article.setAudit(audit);
        return articleServlet.updateArticleAuditById(article);
    }
}
