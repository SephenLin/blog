package aode.ssm.blog.servlet;

import aode.ssm.blog.mapper.ArticleMapper;
import aode.ssm.blog.mapper.ImageMapper;
import aode.ssm.blog.mapper.TypeMapper;
import aode.ssm.blog.po.*;
import aode.ssm.blog.util.ArticleTypeUtil;
import aode.ssm.blog.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 林进华 on 2017/7/19.
 */
@Service
public class ArticleServlet extends BaseServlet {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    ImageMapper imageMapper;

    public int add_article(int user_id,Article article,String type) {
        // 文章添加
        User user = new User();
        user.setId(user_id);
        article.setUser(user);
        article.setTime(new Date());
        int number = articleMapper.add_article(article);
        // 文章类型添加
        String[] types = type.split(",");
        for(int i = 0;i < types.length; i++) {
            Type temp = new Type();
            temp.setLabel(types[i]);
            temp.setArticleNumber(1);
            if(typeMapper.selectLableByLabel(temp) != null) { // 如果文章类型已有，那么该类型文章数 + 1
                temp = typeMapper.selectLableByLabel(temp);
                System.out.println(temp.getArticleNumber() + "  *********************** tempId = " + temp.getId() + "   ****** articleId = " + article.getId());
                temp.setArticleNumber(temp.getArticleNumber() + 1);
                typeMapper.updateByPrimaryKey(temp);
                typeMapper.add_article_type(article.getId(),temp.getId());
            }else { // 没有则添加文章类型
                typeMapper.add_type(temp);
                System.out.println("*********************** tempId2 = " + temp.getId() + "   ****** articleId = " + article.getId());
                typeMapper.add_article_type(article.getId(),temp.getId());
            }
        }
        // 图片添加
        List<Image> images = imageMapper.selectByArticleIdEqualsNull();
        List<Image> temps = new ArrayList<Image>();
        for(Image image : images) {
            System.out.println(article.getContent().indexOf(image.getName()) + "*********************** image.name = " + image.getName() + "   ****** article.contain = " + article.getContent());
            if(article.getContent().indexOf(image.getName()) > -1) {
                image.setArticle(article);
                temps.add(image);
            }
        }
        if(!temps.isEmpty()) {
            imageMapper.updateByArticle(temps);
        }
        return number;
    }

    public int articleUpdateById(int user_id,Article article,String type) {
        // 文章预备删除，先存储进去list，因为下面的文章的添加会修改id
        int id = article.getId();
        System.out.println("预备前的文章的id = " + id);
        // 文章添加
        User user = new User();
        user.setId(user_id);
        article.setUser(user);
        article.setTime(new Date());
        int number = articleMapper.add_article(article);
        // 文章类型添加
        String[] types = type.split(",");
        for(int i = 0;i < types.length; i++) {
            Type temp = new Type();
            temp.setLabel(types[i]);
            temp.setArticleNumber(1);
            if(typeMapper.selectLableByLabel(temp) != null) { // 如果文章类型已有，那么该类型文章数 + 1
                temp = typeMapper.selectLableByLabel(temp);
                System.out.println(temp.getArticleNumber() + "  *********************** tempId = " + temp.getId() + "   ****** articleId = " + article.getId());
                temp.setArticleNumber(temp.getArticleNumber());
                typeMapper.updateByPrimaryKey(temp);
                typeMapper.add_article_type(article.getId(),temp.getId());
            }else { // 没有则添加文章类型
                typeMapper.add_type(temp);
                System.out.println("*********************** tempId2 = " + temp.getId() + "   ****** articleId = " + article.getId());
                typeMapper.add_article_type(article.getId(),temp.getId());
            }
        }
        // 图片添加

        List<Image> images = imageMapper.selectByArticleIdEqualsNull();
        List<Image> temps = new ArrayList<Image>();
        for(Image image : images) {
            System.out.println(article.getContent().indexOf(image.getName()) + "*********************** image.name = " + image.getName() + "   ****** article.contain = " + article.getContent());
            if(article.getContent().indexOf(image.getName()) > -1) {
                image.setArticle(article);
                temps.add(image);
            }
        }
        List<Image> images2 = imageMapper.selectAllByArticleId(article.getId());
        for(Image image : images2) {
            if(article.getContent().indexOf(image.getName()) > -1) {
                image.setArticle(article);
                temps.add(image);
            }
        }
        if(!temps.isEmpty()) {
            imageMapper.updateByArticle(temps);
        }
        // 删除原来的文章
        articleMapper.delectArticleByid(id);
        System.out.println("预备后的文章的id = " + id);
        return number;
    }

    public PageBean initPage(int id, PageBean pageBean) throws IOException {
        // 初始化nowPage，防止当前页数大于最后的页数
        pageBean = pageBean.init(pageBean.getSize(), pageBean.getNowPage(), articleMapper.selectAllNumber(id,pageBean), null);
        System.out.println(pageBean.toString());
        return pageBean;
    }

    public Map<String, Object> selectAllArticleByUserId(int id, PageBean pageBean) throws IOException {
        // 初始化nowPage，防止当前页数大于最后的页数
        pageBean = pageBean.init(pageBean.getSize(),pageBean.getNowPage(),articleMapper.selectAllNumber(id,pageBean),null);
        System.out.println(pageBean.toString());
        PageHelper.startPage(pageBean.getNowPage(),pageBean.getSize());
        List<Article> temps = articleMapper.selectAllArticleByUserId(id,pageBean);
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
                select = "<a style=\"text-decoration:none\" onClick=\"article_stop(this," + article.getId() + ")\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a>";
            }

            html = html + "<tr class=\"text-c\" id=\"temp\">" +
                    "<td><input type=\"checkbox\" value=\"" + article.getId() + "\" name=\"subChk\"></td>" +
                    "<td>" + i + "</td>" +
                    "<td class=\"text-l\"><u style=\"cursor:pointer\" class=\"text-primary\" onClick=\"article_show('查看','" +  article.getId() + "')\" title=\"查看\">" + article.getTitle() +"</u></td>" +
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
                    "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_edit('资讯编辑','" + article.getId() + "')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>" +
                    "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"article_del(this,'" + article.getId() + "')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a></td>" +
                    "</tr>";
                    i++;

        }
        pageBean.init(pageBean.getSize(),pageBean.getNowPage(),articleMapper.selectAllNumber(id,pageBean),articles);
        result.put("htmls",html);
        result.put("pageBean",pageBean);
        return result;
    }

    public Article selectArticleById(int id) {
        Article article = articleMapper.selectArticleById(id);
        List<Type> types = typeMapper.selectAllByArticleId(article.getId());
        article.setTypes(types);
        return article;
    }

    public Map<String,Object> delectArticlesById(int[] ids,int userId,PageBean pageBean) throws Exception {
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
            int k = articleMapper.delectArticlesById(articles);
            result = selectAllArticleByUserId(userId,pageBean);
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

    public String updateArticleAuditById(Article article) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String temp = "";
        Map<String,Object> result = new HashMap<String, Object>();
        if(article == null) {
            result.put("result",false);
        }else {
            articleMapper.updateArticleAuditById(article);
            result.put("result",true);
        }
        return objectMapper.writeValueAsString(result);
    }

}
