package aode.ssm.blog.mapper;

import aode.ssm.blog.po.Type;
import aode.ssm.blog.po.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * Created by 林进华 on 2017/7/20.
 */
public interface TypeMapper extends MySqlMapper<Type>,Mapper<Type> {

    public Type selectLableByLabel(Type type);
    public int add_type(Type type);
    public int add_article_type(@Param("article_id")int article_id,@Param("type_id")int type_id);
    public List<Type> selectAllByArticleId(@Param("article_id")int id);

}
