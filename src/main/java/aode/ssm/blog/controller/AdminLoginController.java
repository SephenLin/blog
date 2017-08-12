package aode.ssm.blog.controller;

import aode.ssm.blog.po.Admin;
import aode.ssm.blog.po.User;
import aode.ssm.blog.servlet.AdminServlet;
import aode.ssm.blog.servlet.ArticleServlet;
import aode.ssm.blog.servlet.ImageServlet;
import aode.ssm.blog.servlet.UserServlet;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by 林进华 on 2017/8/1.
 */
@Controller
@RequestMapping("/adminLoginController")
public class AdminLoginController {

    @Autowired
    AdminServlet adminServlet;

    // 跳转到管理员登录页面
    @RequestMapping("/admin/loginUI")
    public String loginUI() {
        return "admin/login";
    }

    // 从后台获取数据，检查验证是否通过
    @ResponseBody
    @RequestMapping(value="/login1",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String login1(Admin admin, HttpSession session) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> map = adminServlet.loginAdmin(admin);
        Admin tempAdmin = (Admin) map.get("admin");
        session.setAttribute("loginAdmin",tempAdmin);
        return objectMapper.writeValueAsString(map);
    }

    // 验证通过后进入管理员页面
    @RequestMapping(value="/login2",method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String login2() throws Exception {
        return "/admin/index";
    }
}
