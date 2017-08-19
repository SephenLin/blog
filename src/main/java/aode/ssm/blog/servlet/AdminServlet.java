package aode.ssm.blog.servlet;

import aode.ssm.blog.mapper.AdminMapper;
import aode.ssm.blog.mapper.ArticleMapper;
import aode.ssm.blog.mapper.TypeMapper;
import aode.ssm.blog.po.*;
import aode.ssm.blog.util.ArticleTypeUtil;
import aode.ssm.blog.util.ExcelExport;
import aode.ssm.blog.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 林进华 on 2017/8/6.
 */
@Service
public class AdminServlet extends BaseServlet {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    ArticleMapper articleMapper;
    private int id;

    // 管理员登录
    public Map<String, Object> loginAdmin(Admin admin) {
        Map<String, java.lang.Object> map = new HashMap<String, Object>();
        Admin temp = null;
        List<Admin> admins = null;
        admins = adminMapper.loginAdmin(admin);
        if(admins.size() > 1) {
            map.put("result",false);
            map.put("message","发生了未知错误，请稍后再试！");
        }else {
            if(admins.size() == 0) {
                map.put("result",false);
                map.put("message","登录失败，请重新输入！");
            }else {
                temp = admins.get(0);
                if(temp != null) {
                    map.put("result",true);
                    map.put("admin",temp);
                    map.put("message","登录成功，稍后进入博客个人页面！");
                }else {
                    map.put("result",false);
                    map.put("message","登录失败，请重新输入！");
                }
            }
        }
        return map;
    }

    // 修改管理员个人信息

    public Map<String, java.lang.Object> updateAdmin(Admin admin) {
        Map<String, java.lang.Object> map = new HashMap<String, java.lang.Object>();
        int i = adminMapper.updateAdmin(admin);
        System.out.println("updateAdmin修改的记录数为 ： " + i);
        System.out.println("admin修改后的信息为 ：" + admin.toString());
        if(i > 0) {
            admin = adminMapper.selectAdmin(admin);
            map.put("result",true);
            map.put("message","修改成功！");
            map.put("updateAdmin",admin);
        }else {
            map.put("result",false);
            map.put("message","修改失败！");
        }
        return map;
    }
    // 修改管理员个人信息end

    // 公共部分
    public PageBean initPage(PageBean pageBean,Object object) throws IOException {
        System.out.println("管理员中的各种列表进来了");
        // 初始化nowPage，防止当前页数大于最后的页数
        if(object instanceof Article) {
            pageBean = pageBean.init(pageBean.getSize(), pageBean.getNowPage(), adminMapper.selectAllCountNumber(pageBean), null);
            System.out.println("initPage --------> Article ------>" + pageBean.toString());
        }else if(object instanceof User) {
            pageBean = pageBean.init(pageBean.getSize(),pageBean.getNowPage(),adminMapper.selectAllUserNumber(pageBean),null);
            System.out.println("initPage --------> User ------>" + pageBean.toString());
        }
        return pageBean;
    }

    // 文章列表

    public Map<String, Object> selectAllArticle(PageBean pageBean) throws IOException {
        // 初始化nowPage，防止当前页数大于最后的页数
        pageBean = pageBean.init(pageBean.getSize(),pageBean.getNowPage(), adminMapper.selectAllCountNumber(pageBean),null);
        System.out.println(pageBean.toString());
        PageHelper.startPage(pageBean.getNowPage(),pageBean.getSize());
        List<Article> temps = adminMapper.selectAllArticle(pageBean);
        List<Article> articles = new ArrayList<Article>();
        Map<String,Object> result = new HashMap<String, Object>();
        String json = "";
        String templable = "";
        String tempaudit = "";
        String select = "";
        String html = ""; // 前台需要拼接的字符串
        ArticleTypeUtil articleTypeUtil = new ArticleTypeUtil();// 将数据库中的文章类型转换成String字符串
        int i = pageBean.getNowPage() * pageBean.getSize() - pageBean.getSize() + 1;
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");// 将从数据库取出来的时间格式化成想要的格式
        for(Article article : temps) {
            List<Type> types = typeMapper.selectAllByArticleId(article.getId());
            article.setTypes(types);
            articles.add(article);
            for(Type type : types) {
                templable = type.getLabel() + ",";
            }
            // 判断审核的状态
            if("2".equals(article.getAudit()) || article.getAudit() == null) {
                tempaudit = "<span class=\"label label-defaunt radius\">已下架</span>";
            } else if("1".equals(article.getAudit())) {
                tempaudit = "<span class=\"label label-success radius\">已发布</span>";
            }else {
                tempaudit = "<span class=\"label label-success radius\">审核中</span>";
            }

            if("2".equals(article.getAudit()) || article.getAudit() == null) {
                select = "<a style=\"text-decoration:none\" onClick=\"article_audit(this," +article.getId() + ")\" href=\"javascript:;\" title=\"提交审核\"><i class=\"Hui-iconfont\">&#xe603;</i></a>";
            }else if("1".equals(article.getAudit())) {
                select = "<a style=\"text-decoration:none\" onClick=\"article_stop(this," + article.getId() + ")\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a>";
            }else {
                select = "<a style=\"text-decoration:none\" onClick=\"article_start(this," + article.getId() + ")\" href=\"javascript:;\" title=\"通过审核\"><i class=\"Hui-iconfont\">&#xe6de;</i></a>";
            }

            html = html + "<tr class=\"text-c\" id=\"temp\">" +
                    "<td><input type=\"checkbox\" value=\"" + article.getId() + "\" name=\"subChk\"></td>" +
                    "<td>" + i + "</td>" +
                    "<td class=\"text-l\"><u style=\"cursor:pointer\" class=\"text-primary\" onClick=\"article_show('查看'," +  article.getId() + ")\" title=\"查看\">" + article.getTitle() +"</u></td>" +
                    "<td>" + article.getIntroduce() + "</td>" +
                    "<td>" +articleTypeUtil.returnStringToString(article.getArticleType()) + "</td>" +
                    "<td>" + templable + "</td>" +
                    "<td>" + dateFormater.format(article.getTime()) + "</td>" +
                    "<td>" + dateFormater.format(article.getStartTime()) + "</td>" +
                    "<td>" + dateFormater.format(article.getEndTime()) + "</td>" +
                    "<td>" + article.getTraffics() + "</td>" +
                    "<td class=\"td-status\">" +
                    tempaudit +
                    "</td>" +
                    "<td class=\"f-14 td-manage\"><input id=\"articleId\" type=\"hidden\" name=\"articleId\" value=\"" + article.getId() + "\">" +
                    select +
                    "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_del(this,'" + article.getId() + "')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td>" +
                    "</tr>";
            i++;
        }
        pageBean.init(pageBean.getSize(),pageBean.getNowPage(), adminMapper.selectAllCountNumber(pageBean),articles);
        result.put("htmls",html);
        result.put("pageBean",pageBean);
        return result;
    }

    public Map<String,Object> delectArticlesById(int[] ids,PageBean pageBean) throws Exception {
        List<Article> articles = new ArrayList<Article>();
        Map<String,Object> result = null;
        if(ids.length > 0) {
            pageBean.init(pageBean.getSize(),pageBean.getNowPage(),// 初始化分页
                    pageBean.getRecordCount() - ids.length,null);
            for(int id : ids) {
                Article temp = new Article();
                temp.setId(id);
                articles.add(temp);
            }
            int k = adminMapper.delectArticlesById(articles);
            result = selectAllArticle(pageBean);
            if(k > 0) {
                result.put("result",true);
                return result;
            }else {
                result.put("result",false);
                result.put("errorType","1"); //1代表 未知错误，不删除该行
                result.put("mesage","删除失败，请稍后再试!!");
                return result;
            }
        }else {
            result.put("result",false);
            result.put("pageBean",pageBean);
            result.put("errorType","1"); //1代表 未知错误，不删除该行
            result.put("mesage","未知错误");
            return result;
        }
    }

    public Article lookArticleById(int id) {
        return adminMapper.lookArticle(id);
    }

    // 文章列表end

    // 管理员管理用户

    public Map<String,Object> selectAllUser(PageBean pageBean) {
        pageBean = pageBean.init(pageBean.getSize(),pageBean.getNowPage(),
                adminMapper.selectAllUserNumber(pageBean),null);
        System.out.println("selectAllUser 1 次 --------> User ------>" + pageBean.toString());
        System.out.println(pageBean.toString());
        PageHelper.startPage(pageBean.getNowPage(),pageBean.getSize());
        List<User> users = adminMapper.selectAllUser(pageBean);
        Map<String,Object> result = new HashMap<String, Object>();
        System.out.println("后台管理用户查询的条数 : " + users.size());
        String htmls = "";
        if(users.size() > 0) {
            int i = 1;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for(User user : users) {
                htmls = htmls + "<tr id=\"temp\"  class=\"text-c\">" +
                        "<td><input type=\"checkbox\" id= \"subChk\" value=\"" + user.getId() + "\" name=\"subChk\"></td>" +
                        "<td>" + i + "</td>" +
                        "<td><img class=\"avatar size-XL l\" src=\"http://localhost:8080/blog/" + user.getHeadUrl() + user.getHeadName() + "\"></td>" +
                        "<td>" +user.getName() + "</td>" +
                        "<td><u style=\"cursor:pointer\" class=\"text-primary\" onclick=\"member_show('查看" + user.getName() + "的资料','" +user.getId()+ "','360','400')\">" + user.getAccount() + "</u></td>" +
                        "<td>" + user.getPassword() + "</td>" +
                        "<td>" + user.getPhone() + "</td>" +
                        "<td>" + user.getEmail() + "</td>" +
                        "<td>" + user.getLevel() + "</td>" +
                        "<td class=\"text-l\">" + user.getTraffics() + "</td>" +
                        "<td>" + simpleDateFormat.format(user.getTime()) + "</td>" +
                        "<td class=\"td-status\"><span class=\"label label-success radius\">已启用</span></td>" +
                        "<td class=\"td-manage\">" +
                        "<a style=\"text-decoration:none\" onClick=\"member_stop(this,'" + user.getId() + "')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a> " +
                        "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"change_password('修改密码','" + user.getId() + "','600','270')\" href=\"javascript:;\" title=\"修改密码\"><i class=\"Hui-iconfont\">&#xe63f;</i></a> " +
                        "<a title=\"删除\" href=\"javascript:;\" onclick=\"user_del(this,'" + user.getId() + "')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td>" +
                        "</tr>";
                i++;
            }
            pageBean.init(pageBean.getSize(),pageBean.getNowPage(),adminMapper.selectAllUserNumber(pageBean),users);
            System.out.println("selectAllUser 2 次 --------> User ------>" + pageBean.toString());
            result.put("htmls",htmls);
            result.put("pageBean",pageBean);
        }
        return result;
    }

    public Map<String,Object> delectUsersById(int[] ids,PageBean pageBean) throws Exception {
        List<User> users = new ArrayList<User>();
        Map<String,Object> result = null;
        if(ids.length > 0) {
            pageBean.init(pageBean.getSize(),pageBean.getNowPage(),// 初始化分页
                    pageBean.getRecordCount() - ids.length,null);
            for(int id : ids) {
                User temp = new User();
                temp.setId(id);
                users.add(temp);
            }
            int k = adminMapper.delectUsersById(users);
            result = selectAllUser(pageBean);
            if(k > 0) {
                result.put("result",true);
                return result;
            }else {
                result.put("result",false);
                result.put("errorType","1"); //1代表 未知错误，不删除该行
                result.put("mesage","删除失败，请稍后再试!!");
                return result;
            }
        }else {
            result.put("result",false);
            result.put("pageBean",pageBean);
            result.put("errorType","1"); //1代表 未知错误，不删除该行
            result.put("mesage","未知错误");
            return result;
        }
    }

    public Map<String,Object> user_export_excel(HttpServletRequest request) throws Exception {
        // 1.创造文件夹和文件---start

        //1.1 创造存储的文件夹   按时间----start

        // windows下正斜杠/和反斜杠都是可以的
        // linux下只认正斜杠，为了保证跨平台性，不建议使用反斜杠（在java程序中是转义字符，用\来表示反斜
        String separator = File.separator;
        //真实路径
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");// 将从数据库取出来的时间格式化成想要的格式
        String realPath = request.getServletContext().getRealPath("resources" + separator + "userExcel" + separator + dateFormater.format(new Date()));
        File file1 = new File(realPath);
        if (!file1.exists()){
            file1.mkdirs();
        }
        //1.1 创造存储的文件夹   按时间----end

        // 1.2 创造文件 ----start

        File file2 = new File(realPath + separator + "testExcel.xls") ; // 文件夹
				if(!file2.exists()) {
					file2.createNewFile() ;
				}else {
					file2.delete();
					file2.createNewFile();
				}
        // 1.2 创造文件 ----end

        // 1.创造文件夹和文件---end

        List<User> users = adminMapper.selectAllUser(new PageBean());
        ExcelExport excelExport = new ExcelExport();
        excelExport.user_export_excel(users,file2);
        return null;
    }

    public User lookUserById(int id) {
        User user = null;
        user = adminMapper.selectUserByUserId(id);
        user.setArtice(articleMapper.selectAllArticleByUserId(id,new PageBean()));
        return user;
    }

    public boolean updateUserPassword(User user) {
        int i = adminMapper.updateUserPassword(user);
        if(i > 0) {
            return true;
        }else {
            return false;
        }
    }

    public Map<String,Object> insertUserAudit(int user_audit_id) {
        Map<String,Object> map = new HashMap<String, Object>();
        User user = adminMapper.selectUserByUserId(user_audit_id);
        System.out.println(user.toString());
        int i = adminMapper.insertUserAudit(user);
        if(i > 0) {
            map.put("result",true);
        }else {
            map.put("result",false);
            map.put("message","用户不存在！");
        }
        return map;
    }

    public Map<String,Object> deleteUserAudit(int user_audit_id) {
        Map<String,Object> map = new HashMap<String, Object>();
        int i = adminMapper.deleteUserAudit(user_audit_id);
        if(i > 0) {
            map.put("result",true);
        }else {
            map.put("result",false);
            map.put("message","用户不存在！");
        }
        return map;
    }

}
