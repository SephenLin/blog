package aode.ssm.blog.servlet;

import aode.ssm.blog.mapper.ArticleMapper;
import aode.ssm.blog.mapper.CommentMapper;
import aode.ssm.blog.mapper.ImageMapper;
import aode.ssm.blog.mapper.TypeMapper;
import aode.ssm.blog.po.Article;
import aode.ssm.blog.po.Comment;
import aode.ssm.blog.po.CommentCustom;
import aode.ssm.blog.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by 林进华 on 2017/8/4.
 */
@Service
public class CommentServlet extends BaseServlet {

    @Autowired
    CommentMapper commentMapper;
    // 游客操作系列
    public Map<String,Object> addComment(CommentCustom commentCustom,PageBean pageBean) {
        System.out.println("addComment进来了！" + commentCustom.toString());
        commentCustom.setTime(new Date());
        int i = commentMapper.insertComment(commentCustom);
        System.out.println("添加之后评论的id :" + commentCustom.getId());
        Map<String,Object> map = new HashMap<String, Object>();
        if(i > 0) {
            map.put("result",true);
            map.put("message","回复成功！");
            System.out.println("回显可以了!");
        }else {
            map.put("result",false);
            map.put("message","回复失败，请稍后再试！");
        }
        Map<String,Object> temp = getAllCommentAndReplay(commentCustom.getTopicAid(),pageBean);
        map.put("comments",temp.get("comments"));
        map.put("pageBean",temp.get("pageBean"));
        return map;
    }

    public String commentHtml(CommentCustom commentCustom) {
        String htmls = "<div id=\"temp\"><article class=\"comment\">" +
                "<header class=\"clearfix\">" +
                "<img src=\"http://localhost:8080/blog/" + commentCustom.getFromUser().getHeadUrl() + commentCustom.getFromUser().getHeadName() + "\" alt=\"A Smart Guy\" class=\"avatar\">" +
                "<div class=\"meta\">" +
                "<h3><a href=\"#\">" + commentCustom.getFromUser().getName() + "</a></h3>" +
                "<span class=\"date\">" +
                commentCustom.getTime() +
                "</span>" +
                "<span class=\"separator\">" +
                "-" +
                "</span>" +
                "<a href=\"#create-comment\" class=\"reply-link\">Reply</a>" +
                "</div>" +
                "</header>" +
                "<div class=\"body\">" +
                commentCustom.getContent() +
                "</div>" +
                "</article> </div>";
        return htmls;
    }

    public String replayHtml(CommentCustom commentCustom) {
        String htmls = "<div id=\"temp\"><article class=\"comment reply\">" +
                "<header class=\"clearfix\">" +
                "<img src=\"http://localhost:8080/blog/" + commentCustom.getFromUser().getHeadUrl() + commentCustom.getFromUser().getHeadName() + "\" alt=\"A Smart Guy\" class=\"avatar\">" +
                "<div class=\"meta\">" +
                "<h3><a href=\"#\">" + commentCustom.getFromUser().getName() + "</a></h3>" +
                "<span class=\"date\">" +
                commentCustom.getTime() +
                "</span>" +
                "<span class=\"separator\">" +
                "-" +
                "</span>" +
                "<a href=\"#create-comment\" class=\"reply-link\">Reply</a>" +
                "</div>" +
                "</header>" +
                "<div class=\"body\">" +
                commentCustom.getContent() +
                "</div>" +
                "</article> </div>";
        return htmls;
    }

    // order 是序号
    public String userHtml(int order,CommentCustom commentCustom) {
        String userHtml = "<tr class=\"text-c\" id=\"temp\">" +
                                "<td><input type=\"checkbox\" value=\"" + commentCustom.getId() + "\" name=\"checkComment\"></td>" +
                                "<td>" + order + "</td>" +
                                "<td>" + commentCustom.getArticle().getTitle() + "</td>" +
                                "<td><a href=\"javascript:;\" onclick=\"member_show('" + commentCustom.getFromUser().getName() + "的资料','" + commentCustom.getFromUser().getId() + "','360','400')\"><i class=\"avatar size-L radius\"><img alt=\"\" src=\"http://localhost:8080/blog/" + commentCustom.getFromUser().getHeadUrl() + commentCustom.getFromUser().getHeadName() + "\"></i></a></td>" +
                                "<td class=\"text-l\"><div class=\"c-999 f-12\">" +
                                       "<span class=\"ml-20\">" + commentCustom.getFromUser().getName() +"</span> <span class=\"ml-20\"><time>" + commentCustom.getTime() + "</time></span> <span class=\"ml-20\">" + "******" + "</span> <span class=\"ml-20\">" + commentCustom.getFromUser().getEmail() + "</span></div>" +
                                    "<div>" + commentCustom.getContent() + "</div>" +
                                "</td>" +
                                "<td class=\"td-manage\"> <a title=\"删除\" href=\"javascript:;\" onclick=\"member_del(this,'" + commentCustom.getId() + "')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td>" +
                            "</tr>";
        return userHtml;
    }


    // 分页操作------- 针对客户浏览文章而看到的分页
    public PageBean initPage(int articleId,PageBean pageBean) throws IOException {
        // 初始化nowPage，防止当前页数大于最后的页数
        pageBean = pageBean.init(pageBean.getSize(), pageBean.getNowPage(),commentMapper.selectNumberByArticleId(articleId),null);
        System.out.println(pageBean.toString());
        return pageBean;
    }

    public Map<String,Object> getAllCommentAndReplay(int articleId,PageBean pageBean) {
        pageBean = pageBean.init(pageBean.getSize(), pageBean.getNowPage(),commentMapper.selectNumberByArticleId(articleId),null);
        Map<String,Object> map = new HashMap<String, Object>();
        String htmls = "";
        String replays = "";
        PageHelper.startPage(pageBean.getNowPage(),pageBean.getSize());
        List<CommentCustom> temps = commentMapper.selectAllCommentByArticleId(articleId);
        for(CommentCustom temp : temps) {
            htmls = commentHtml(temp) + replays + htmls;
        }
        map.put("comments",htmls);
        map.put("pageBean",pageBean);
        return map;
    }

    // 游客操作系列 end

    // 用户操作系列
    // 分页操作

    public PageBean userInitPage(int userId,PageBean pageBean) throws IOException {
        // 初始化nowPage，防止当前页数大于最后的页数
        pageBean = pageBean.init(pageBean.getSize(), pageBean.getNowPage(),commentMapper.userSelectAllCommentNumber(userId,pageBean),null);
        System.out.println(pageBean.toString());
        return pageBean;
    }

    public Map<String,Object> getAllCommentByUserId(int userId,PageBean pageBean) {
        pageBean = pageBean.init(pageBean.getSize(), pageBean.getNowPage(),commentMapper.userSelectAllCommentNumber(userId,pageBean),null);
        Map<String,Object> map = new HashMap<String, Object>();
        String htmls = "";
        String replays = "";
        int i = 1;  // 呈现给用户的序号
        PageHelper.startPage(pageBean.getNowPage(),pageBean.getSize());
        System.out.println(pageBean.toString());
        List<CommentCustom> temps = commentMapper.selectAllCommentByUserIdAndPage(userId,pageBean);
        for(CommentCustom temp : temps) {
            System.out.println(temp.toString());
            htmls = htmls + userHtml(i,temp) + replays;
            i++;
        }
        map.put("comments",htmls);
        map.put("pageBean",pageBean);
        return map;
    }

    public Map<String,Object> delectCommentsById(int[] ids,int userId,PageBean pageBean) throws Exception {
        List<Comment> comments = new ArrayList<Comment>();
        Map<String,Object> result = null;
        if(ids.length > 0) {
            pageBean.init(pageBean.getSize(),pageBean.getNowPage(),// 初始化分页
                    pageBean.getRecordCount() - ids.length,null);
            for(int id : ids) {
                Comment temp = new Comment();
                temp.setId(id);
                comments.add(temp);
            }
            int k = commentMapper.delectCommentsById(comments);
            result = getAllCommentByUserId(userId,pageBean);
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



}
