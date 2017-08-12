package aode.ssm.blog.servlet;

import aode.ssm.blog.mapper.UserMapper;
import aode.ssm.blog.po.User;
import aode.ssm.blog.util.*;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 林进华 on 2017/7/30.
 */
@Service
public class UserServlet extends BaseServlet {

    @Autowired
    UserMapper userMapper ;

    public Map getValidateMassege(String phone,String massegeStart,String massegeEnd) {
        Map<String, java.lang.Object> map = new HashMap<String,  java.lang.Object>();
        PhoneUtil2 phoneUtil2 = new PhoneUtil2();
        // 获取6位随机数
        String n = null;
        Random r;
        for (int i = 0; i < 6; i++) {
            r = new Random();
            n = (r.nextInt(9) + 1) + "" + n;
            System.out.println(n);
        }
        try {
            phoneUtil2.phoneValidate(phone,massegeStart + n + massegeEnd);
            map.put("result",true);
            map.put("validateMassege",n);
            return map;
        } catch (Exception e) {
            map.put("result",false);
            map.put("validateMassege",n);
            e.printStackTrace();
            return map;
        }
    }
    //注册
    public int register(User user) {
        user.setTime(new Date());
        int i = userMapper.insertUserByUser(user);
        return i;
    }

    // 找回密码，通过邮箱
    public Map<String, java.lang.Object> sendEmailForPassword(User user) {
        Map<String, java.lang.Object> map = new HashMap<String, java.lang.Object>();
        EmailUtil2 emailUtil2 = new EmailUtil2();
        User temp = null;
        List<User> users = null;
        System.out.println("**********" + user.getName() + user.getAccount() + user.getEmail());
        users = userMapper.selectUserByUser(user);
        if(users.size() > 1) {
            map.put("result",false);
            map.put("message","发生了未知错误，请稍后再试！");
        }else {
            temp = users.get(0);
            if(temp != null) {
                emailUtil2.hashCode("1449815705@qq.com","taoqbbehsruvjfhg",
                        temp.getEmail(),"<table>" +
                                "<tr><td colspan=\"3\">&nbsp;&nbsp;账号信息&nbsp;&nbsp;</td></tr>" +
                                "<tr><td>&nbsp;&nbsp;名字&nbsp;&nbsp;</td><td>&nbsp;&nbsp;账号&nbsp;&nbsp;</td><td>&nbsp;&nbsp;密码&nbsp;&nbsp;</td></tr>" +
                                "<tr><td>&nbsp;&nbsp;" + temp.getName() + "&nbsp;&nbsp;</td><td>&nbsp;&nbsp;" + temp.getAccount() + "&nbsp;&nbsp;</td><td>&nbsp;&nbsp;" + temp.getPassword() + "&nbsp;&nbsp;</td></tr>" +
                                "</table>");
                map.put("result",true);
                map.put("message","邮箱发送成功，请注意接收！");
            }else {
                map.put("result",false);
                map.put("message","邮箱发送失败，请仔细检查所填信息！");
            }
        }
        return map;
    }

    // 用户登录
    public Map<String, java.lang.Object> login1(User user) {
        Map<String, java.lang.Object> map = new HashMap<String, java.lang.Object>();
        User temp = null;
        List<User> users = null;
        users = userMapper.selectUserByUser(user);
        if(users.size() > 1) {
            map.put("result",false);
            map.put("message","发生了未知错误，请稍后再试！");
        }else {
            if(users.size() == 0) {
                map.put("result",false);
                map.put("message","登录失败，请重新输入！");
            }else {
                temp = users.get(0);
                if(temp != null) {
                    int i = userMapper.selectUserAudit(temp);
                    if(i > 0) {
                        map.put("result",false);
                        map.put("message","该账号非法使用，已被冻结，请找管理员联系！");
                    }else {
                        map.put("result",true);
                        map.put("user",temp);
                        map.put("message","登录成功，稍后进入博客个人页面！");
                    }
                }else {
                    map.put("result",false);
                    map.put("message","登录失败，请重新输入！");
                }
            }
        }
        return map;
    }

    // 修改个人信息
    public Map<String, java.lang.Object> updateUser(User user) {
        Map<String, java.lang.Object> map = new HashMap<String, java.lang.Object>();
        int i = userMapper.updateUser(user);
        System.out.println("************" + i);
        System.out.println("************" + user.toString());
        if(i > 0) {
            user = userMapper.selectUserByUser(user).get(0);
            map.put("result",true);
            map.put("message","修改成功！");
            map.put("updateUser",user);
        }else {
            map.put("result",false);
            map.put("message","修改失败！");
        }
        return map;
    }

    // 查看个人信息
    public User selectUserById(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }


}
