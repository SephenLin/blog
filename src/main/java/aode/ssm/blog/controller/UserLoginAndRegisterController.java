package aode.ssm.blog.controller;

import aode.ssm.blog.po.User;
import aode.ssm.blog.servlet.ArticleServlet;
import aode.ssm.blog.servlet.ImageServlet;
import aode.ssm.blog.servlet.UserServlet;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/userLoginAndRegisterController")
public class UserLoginAndRegisterController {

    @Autowired
    ArticleServlet articleServlet;
    @Autowired
    ImageServlet imageServlet;
    @Autowired
    UserServlet userServlet;

    // 用户注册板块4
    // 1.获取验证码
    @ResponseBody
    @RequestMapping(value="/register-validate",method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String getRegisterValidate(HttpSession session, String phone) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("************phone = " + phone);
        Map<String,Object> map = userServlet.getValidateMassege(phone,"您的验证密码是：","，如需帮助请联系客服。");
        session.setAttribute("validateMap",map);
        return objectMapper.writeValueAsString(map);
    }

    // 2.验证账号是否存在
    @ResponseBody
    @RequestMapping(value="/register-checkAccount",method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String checkAccount(String account) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> map = userServlet.checkAccount(account);
        return objectMapper.writeValueAsString(map);
    }

    @RequestMapping(value="/register",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String register(HttpSession session, User user, @Param("file") MultipartFile file,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");// 将从数据库取出来的时间格式化成想要的格式
        String path = "resources\\headImage\\" + user.getName() + "\\" + dateFormater.format(new Date());
        Map<String,Object> map = userServlet.add_head(file,request, response,path,new User());
        User tempUser = (User) map.get("user");
        user.setHeadName(tempUser.getHeadName());
        user.setHeadUrl(tempUser.getHeadUrl());
        int i = userServlet.register(user);
        if(i > 0) {
            return "redirect:/login.jsp";
        }else {
            return "redirect:/register.jsp";
        }
    }

    @ResponseBody
    @RequestMapping(value="/sendEmailForPassword",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String sendEmailForPassword(User user) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> map = userServlet.sendEmailForPassword(user);
        return objectMapper.writeValueAsString(map);
    }

    // 从后台获取数据，检查验证是否通过
    @ResponseBody
    @RequestMapping(value="/login1",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String login1(User user,HttpSession session) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> map = userServlet.login1(user);
        User tempUser = (User) map.get("user");
        session.setAttribute("loginUser",tempUser);
        User tempUser2 = (User) session.getAttribute("loginUser");
        return objectMapper.writeValueAsString(map);
    }

    // 验证通过后进入个人博客页面
    @RequestMapping(value="/login2",method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String login2() throws Exception {
        return "redirect:/user/indexUI";
    }
}
