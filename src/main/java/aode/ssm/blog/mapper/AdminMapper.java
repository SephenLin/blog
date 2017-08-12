package aode.ssm.blog.mapper;

import aode.ssm.blog.po.Admin;
import aode.ssm.blog.po.Article;
import aode.ssm.blog.po.User;
import aode.ssm.blog.util.PageBean;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * Created by 林进华 on 2017/8/6.
 */
public interface AdminMapper extends MySqlMapper<Admin>,Mapper<Admin> {

    // 文章操作系列
    public List<Article> selectAllArticle(@Param("pageBean")PageBean pageBean);
    public Article lookArticle(@Param("article_id")int id);
    public int delectArticlesById(List<Article> articles);
    public int selectAllCountNumber(@Param("pageBean")PageBean pageBean);
    public int updateArticleAuditById(Article article);

    // 用户操作系列
    public List<User> selectAllUser(@Param("pageBean")PageBean pageBean);
    public User selectUserByUserId(@Param("id")int id);
    public int delectUsersById(List<User> users);
    public int selectAllUserNumber(@Param("pageBean")PageBean pageBean);
    public int updateUserPassword(@Param("user") User user);
    public int insertUserAudit(@Param("user") User user);
    public int deleteUserAudit(@Param("userId") int userId);

    // 管理员操作系列
    public List<Admin> loginAdmin(@Param("admin") Admin admin);
    public int updateAdmin(@Param("admin")Admin admin);
    public Admin selectAdmin(@Param("admin")Admin admin);
}
