package aode.ssm.blog.mapper;

import aode.ssm.blog.po.Comment;
import aode.ssm.blog.po.CommentCustom;
import aode.ssm.blog.util.PageBean;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.*;

import java.util.List;

/**
 * Created by 林进华 on 2017/8/4.
 */
public interface CommentMapper extends MySqlMapper<CommentCustom>,Mapper<CommentCustom> {

    public int selectNumberByArticleId(@Param("article_id")int id);
    public List<CommentCustom> selectAllCommentByArticleId(@Param("article_id")int id);
    public int insertComment(CommentCustom commentCustom);
    public int delectComment(@Param("article_id")int id);
    // 用户操作系列
    public int userSelectAllCommentNumber(@Param("user_id")int id,@Param("pageBean")PageBean pageBean);
    public List<CommentCustom> selectAllCommentByUserIdAndPage(@Param("user_id")int id,@Param("pageBean")PageBean pageBean);
    public int delectCommentsById(List<Comment> comments);

}
