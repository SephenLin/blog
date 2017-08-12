package aode.ssm.blog.controller;

import aode.ssm.blog.po.CommentCustom;
import aode.ssm.blog.servlet.AdminServlet;
import aode.ssm.blog.servlet.CommentServlet;
import aode.ssm.blog.util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 林进华 on 2017/8/4.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentServlet commentServlet;
    @Autowired
    AdminServlet adminServlet;

    @ResponseBody
    @RequestMapping(value="/add-comment", method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String addComment(CommentCustom commentCustom, PageBean pageBean, HttpSession httpSession) throws  Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> map = commentServlet.addComment(commentCustom,pageBean);
        System.out.println(commentCustom.toString());
        return objectMapper.writeValueAsString(map);
    }

    @ResponseBody
    @RequestMapping(value="/comment-list", method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String commentList(CommentCustom commentCustom, PageBean pageBean) throws  Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> map = commentServlet.getAllCommentAndReplay(commentCustom.getTopicAid(),pageBean);
        System.out.println(commentCustom.toString());
        return objectMapper.writeValueAsString(map);
    }

    @RequestMapping("/user-show")
    public String lookUser(@RequestParam("id")int id, Model model) {
        model.addAttribute("user",adminServlet.lookUserById(id));
        return "/admin/user-show";
    }

}
