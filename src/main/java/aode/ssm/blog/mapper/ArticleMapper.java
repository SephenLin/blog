package aode.ssm.blog.mapper;

import aode.ssm.blog.po.Article;
import aode.ssm.blog.util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 林进华 on 2017/7/19.
 */
import aode.ssm.blog.util.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface ArticleMapper extends MySqlMapper<Article>,Mapper<Article> {

    public int add_article(Article article);
    public List<Article> selectAllArticleByUserId(@Param("user_id")int id,@Param("pageBean")PageBean pageBean); // 用户查询符合类型的文章
    public List<Article> selectAllArticleByArticleType(@Param("articleType")String articleType,@Param("pageBean")PageBean pageBean); // 游客查询所有符合类型的文章
    public int delectArticlesById(List<Article> article);
    public int delectArticleByid(@Param("article_id")int id);
    public Article selectArticleById(@Param("id")int id);
    public int updateArticleAuditById(Article article);
    public int selectAllNumber(@Param("id")int id,@Param("pageBean")PageBean pageBean); // 用户查询文章总数
    public int selectAllNumberByArticleType(@Param("articleType")String articleType,@Param("pageBean")PageBean pageBean); // 游客查询文章总数
    public List<Article> randSelectArticleByArticleType(@Param("number")int number,@Param("article")Article article); // 通过文章类型随机选取文章
    public List<Article> selectAllArticleByArticleTraffics(@Param("articleType")String articleType);
    public List<Article> randSelectArticleByTraffics(@Param("number")int number); // 通过点击率随机选取精选文章
    public Article lookArticle(@Param("id")int id);
    public int updateTraffics(@Param("article")Article article); // 修改文章的访问量

}
