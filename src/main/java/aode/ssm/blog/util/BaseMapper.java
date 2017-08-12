package aode.ssm.blog.util;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * Created by 林进华 on 2017/7/4.
 */
@Repository("TestMapper")
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
