package aode.ssm.blog.servlet;

import aode.ssm.blog.mapper.ArticleMapper;
import aode.ssm.blog.mapper.ImageMapper;
import aode.ssm.blog.mapper.TypeMapper;
import aode.ssm.blog.po.Article;
import aode.ssm.blog.po.Type;
import aode.ssm.blog.util.ArticleTypeUtil;
import aode.ssm.blog.util.Compare;
import aode.ssm.blog.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 林进华 on 2017/8/2.
 */
@Service
public class VisitorArticleServlet extends BaseServlet {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    ImageMapper imageMapper;

    public PageBean initPage(String articleType,PageBean pageBean) throws IOException {
        // 初始化nowPage，防止当前页数大于最后的页数
        pageBean = pageBean.init(pageBean.getSize(), pageBean.getNowPage(),
                articleMapper.selectAllNumberByArticleType(articleType,pageBean), null);
        System.out.println(pageBean.toString());
        return pageBean;
    }

    public List<Article> selectArticleByTraffics(String articleType) {
        return articleMapper.selectAllArticleByArticleTraffics(articleType);
    }

    public Map<String, Object> selectAllArticleByArticleType(String articleType,PageBean pageBean) throws IOException {
        // 初始化nowPage，防止当前页数大于最后的页数
        pageBean = pageBean.init(pageBean.getSize(), pageBean.getNowPage(),
                articleMapper.selectAllNumberByArticleType(articleType,pageBean), null);
        System.out.println(pageBean.toString());
        PageHelper.startPage(pageBean.getNowPage(),pageBean.getSize());
        List<Article> temps = articleMapper.selectAllArticleByArticleType(articleType,pageBean);
        System.out.println("结果中数据的大小 ：" + temps.size());
        List<Article> articles = new ArrayList<Article>();
        Map<String,Object> result = new HashMap<String, Object>();
        String html = ""; // 前台需要拼接的字符串
        String html2 = ""; // 拼接文章的标签
        ArticleTypeUtil articleTypeUtil = new ArticleTypeUtil(); // 将数据库中的文章类型转换成String字符串
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");// 将从数据库取出来的时间格式化成想要的格式
        for(Article article : temps) {

            for(Type type : article.getTypes()) {
                html2 = "<a href=\"//localhost:8080/blog/visitor/blog-look.action?id=" + article.getId() + "\">" + type.getLabel() + "</a>" +
                        "," + html2;
                    }

        html = "<div id=\"temp\"><h3><a target=\"_blank\" href=\"http://localhost:8080/blog/visitor/blog-look.action?id=" + article.getId() + "\">" + article.getTitle() + "</a></h3>" +
               "<figure><img src=\"http://localhost:8080/blog/" + article.getImages().get(0).getUrl() + article.getImages().get(0).getName() + "\"></figure>" +
               "<ul>" +
               "<p>" + article.getIntroduce() + "</p>" +
               "<a target=\"_blank\" href=\"http://localhost:8080/blog/visitor/blog-look.action?id=" + article.getId() + "\" class=\"readmore\">阅读全文&gt;&gt;</a>" +
               "</ul>" +
               "<p class=\"dateview\">" +
               "<span>" + dateFormater.format(article.getTime()) + "</span>" +
               "<span>作者：" + article.getUser().getAccount() + "</span>" +
               "<span>文章类型：[<a href=\"//localhost:8080/blog/visitor/article-listUI.action?articleType=" + article.getId() + "\">" + articleTypeUtil.returnStringToString(article.getArticleType()) + "</a>]</span>" +
               "<span>标签：[" +
                html2 +
                "]</span>" +
               "</p></div>" + html;
            html2 = "";
        }
        pageBean.init(pageBean.getSize(),pageBean.getNowPage(),articleMapper.selectAllNumberByArticleType(articleType,pageBean),articles);
        result.put("htmls",html);
        result.put("pageBean",pageBean);
        return result;
    }

    // 初始化博客页面
    public Map<String,Object> initBlog() throws IOException {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Article> topArticles = new ArrayList<Article>(); // 顶部的文章
        List<Article> leftArticles = new ArrayList<Article>(); // 顶部的文章
        Article leftArticle = new Article(); // 左边的文章
        List<Article> rightArticles = new ArrayList<Article>(); // 右边的文章
        List<Article> buttonArticles = new ArrayList<Article>(); // 底部的文章
        Article articleType = new Article();
        Compare compare = new Compare();
        if(compare.compareTime(new Date(),"06:00:00","12:00:00")){
            articleType.setArticleType("4");
            topArticles = articleMapper.randSelectArticleByArticleType(4,articleType);
        }else if(compare.compareTime(new Date(),"12:00:00","15:00:00")){
            articleType.setArticleType("5");
            topArticles = articleMapper.randSelectArticleByArticleType(4,articleType);
        }else if(compare.compareTime(new Date(),"15:00:00","18:00:00")){
            articleType.setArticleType("6");
            topArticles = articleMapper.randSelectArticleByArticleType(4,articleType);
        }else if(compare.compareTime(new Date(),"18:00:00","24:00:00")) {
            articleType.setArticleType("7");
            topArticles = articleMapper.randSelectArticleByArticleType(4,articleType);
        }else {
            articleType.setArticleType("8");
            topArticles = articleMapper.randSelectArticleByArticleType(4,articleType);
        }
        map.put("topArticles",topArticles);
        articleType.setArticleType("1");
        leftArticles = articleMapper.randSelectArticleByArticleType(1,articleType);
        if(leftArticles.size() > 0) {
            leftArticle = leftArticles.get(0);
        }
        map.put("leftArticle",leftArticle);
        articleType.setArticleType(null);
        rightArticles = articleMapper.randSelectArticleByTraffics(5);
        map.put("rightArticles",rightArticles);
        articleType.setArticleType("2");
        buttonArticles = articleMapper.randSelectArticleByArticleType(3,articleType);
        map.put("buttonArticles",buttonArticles);
        return map;
    }

    // 游客查看文章
    public Map<String,Object> lookArticle(int articleId) {
        Map<String,Object> map = new HashMap<String, Object>();
        Article article = articleMapper.lookArticle(articleId);
        if(article == null) {
            map.put("result",false);
            map.put("message","该文章作者已删除！");
        }else {
            article.setTraffics(article.getTraffics() + 1);
            articleMapper.updateTraffics(article); // 修改访问量
            System.out.println("文章类型 : " + article.getArticleType());
            Article articleType = new Article();
            articleType.setArticleType(article.getArticleType());
            map.put("sameArticles",articleMapper.randSelectArticleByArticleType(5,articleType));
            map.put("nevoArticles",articleMapper.randSelectArticleByTraffics(5));
            map.put("result",true);
            map.put("article",article);
        }
        return map;
    }

}
