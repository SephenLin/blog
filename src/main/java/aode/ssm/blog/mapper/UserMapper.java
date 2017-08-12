package aode.ssm.blog.mapper;

import aode.ssm.blog.po.User;
import org.springframework.stereotype.Repository;

/**
 * Created by 林进华 on 2017/7/19.
 */
import aode.ssm.blog.util.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserMapper extends MySqlMapper<User>,Mapper<User> {

    public int insertUserByUser(User user);
    public List<User> selectUserByUser(User user);
    public int updateUser(User user);
    public int selectUserAudit(User user);
}
