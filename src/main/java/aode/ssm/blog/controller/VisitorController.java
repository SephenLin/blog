package aode.ssm.blog.controller;

import aode.ssm.blog.po.Article;
import aode.ssm.blog.po.User;
import aode.ssm.blog.servlet.ArticleServlet;
import aode.ssm.blog.servlet.CommentServlet;
import aode.ssm.blog.servlet.VisitorArticleServlet;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 林进华 on 2017/7/19.
 */
@Controller
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    ArticleServlet articleServlet;
    @Autowired
    CommentServlet commentServlet;
    @Autowired
    VisitorArticleServlet visitorArticleServlet;

    @RequestMapping(value="/blog-init")
    public String blogInit(HttpSession session) throws Exception {
        Map<String,Object> map = visitorArticleServlet.initBlog();
        session.setAttribute("topArticles",map.get("topArticles"));
        session.setAttribute("leftArticle",map.get("leftArticle"));
        session.setAttribute("rightArticles",map.get("rightArticles"));
        session.setAttribute("buttonArticles",map.get("buttonArticles"));
        return "redirect:/index.jsp";
    }

    @RequestMapping(value="/blog-look")
    public String lookArticle(RedirectAttributes redirectAttributes, HttpSession session,
                              HttpServletRequest request, @RequestParam("id")int id) throws Exception {
        session.removeAttribute("message");
        Map<String,Object> map = visitorArticleServlet.lookArticle(id);
        Boolean result = (Boolean) map.get("result");
        if(result) {
            session.setAttribute("article",map.get("article"));
            session.setAttribute("nevoArticles",map.get("nevoArticles")); // 精选文章
            session.setAttribute("sameArticles",map.get("sameArticles")); // 类型相同的文章

            // 初始化文章评论的分页
            PageBean pageBean = commentServlet.initPage(id,new PageBean(3,1,0,null));
            session.setAttribute("initPage",pageBean);
            // 初始化连续显示的分页数
            session.setAttribute("initPageSize",
                    pageBean.getEndPageIndex() - pageBean.getStartPageIndex() > 3 ? 4 : pageBean.getEndPageIndex());
            return "redirect:/single.jsp";
        }else {
            redirectAttributes.addFlashAttribute("message",map.get("message"));
            return "redirect:/index.jsp";
        }

    }

    // 分页操作

    @ResponseBody
    @RequestMapping(value="/article-list", method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String articleList(@Param("articleType") String articleType,Model model,
    PageBean pageBean, HttpSession session) throws IOException, ParseException {
        System.out.println(" 当前的页数 : " + pageBean.getNowPage() + "*****  *** 每页的大小 ：" + pageBean.getSize());
        ObjectMapper objectMapper = new ObjectMapper(); //json 数据
        Map map = visitorArticleServlet.selectAllArticleByArticleType(articleType,pageBean);
        return objectMapper.writeValueAsString(map);
    }

    @RequestMapping("/article-listUI")
    public String articleListUI(@RequestParam("articleType")String articleType,HttpSession session) throws IOException {
        System.out.println("article-listUI进来了");
        System.out.println("articleType :" + articleType == null ? true : articleType);
        //在这里的size定义初始化时的条数
        PageBean pageBean = visitorArticleServlet.initPage(articleType,new PageBean(6,1,0,null));
        session.setAttribute("initPage",pageBean);
        session.setAttribute("articleType",articleType);
        session.setAttribute("goodArticles",visitorArticleServlet.selectArticleByTraffics(articleType));
        // 初始化连续显示的分页数
        session.setAttribute("initPageSize",
                pageBean.getEndPageIndex() - pageBean.getStartPageIndex() > 3 ? 4 : pageBean.getEndPageIndex());
        System.out.println("visitor- article-listUI -end");
        return "redirect:/articleType.jsp";
    }

}
