package aode.ssm.blog.mapper;

import aode.ssm.blog.po.ReplyCustom;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * Created by 林进华 on 2017/8/4.
 */
public interface ReplyMapper extends MySqlMapper<ReplyCustom>,Mapper<ReplyCustom> {
}
