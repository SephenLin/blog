package aode.ssm.blog.mapper;

import aode.ssm.blog.po.Image;
import aode.ssm.blog.po.ImageCustom;
import aode.ssm.blog.po.User;
import aode.ssm.blog.util.BaseMapper;
import aode.ssm.blog.util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 林进华 on 2017/7/18.
 */
import aode.ssm.blog.util.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface ImageMapper extends MySqlMapper<Image>,Mapper<Image> {

    public List<Image> selectByArticleIdEqualsNull();
    public int updateByArticle(List<Image> image);
    public List<ImageCustom> selectAllByUserId(@Param("user_id")int id,@Param("pageBean")PageBean pageBean);
    public List<Image> selectAllByArticleId(@Param("article_id")int id);
    public int selectNumberByUserId(@Param("user_id")int id,@Param("pageBean")PageBean pageBean);
    public int updateImageById(ImageCustom imageCustom);
    //public int updateImageAuditById(Image image);
    public ImageCustom selectImageById(@Param("id")int id);
    public int deleteImagesById(List<Image> image);
}
