package aode.ssm.blog.servlet;

import aode.ssm.blog.po.*;
import aode.ssm.blog.util.ArticleTypeUtil;
import aode.ssm.blog.util.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.map.HashedMap;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 林进华 on 2017/7/19.
 */
@Service
public class ImageServlet extends BaseServlet {

    public PageBean initPage(int userId, PageBean pageBean) throws IOException {
        // 初始化nowPage，防止当前页数大于最后的页数
        pageBean.init(pageBean.getSize(),pageBean.getNowPage(),imageMapper.selectNumberByUserId(userId,pageBean),null);
        System.out.println("11111111总页数 : " + pageBean.getPageCount() + "结束的页数 :" + pageBean.getEndPageIndex() +
                " 当前的页数 : " + pageBean.getNowPage() + "*****  *** 每页的大小 ：" + pageBean.getSize() +
                "开始的页数 :" + pageBean.getStartPageIndex() + "中记录数 :" + pageBean.getRecordCount());
        return pageBean;
    }

        public Map selectAllByUserId(int userId, PageBean pageBean) {
            pageBean.init(pageBean.getSize(),pageBean.getNowPage(),imageMapper.selectNumberByUserId(userId,pageBean),null);
            PageHelper.startPage(pageBean.getNowPage(),pageBean.getSize());
            List<ImageCustom> temps = imageMapper.selectAllByUserId(userId,pageBean);
            List<ImageCustom> images = new ArrayList<ImageCustom>();
            Map<String,Object> result = new HashMap<String, Object>();
            String json = "";
            String templable = "";
            String tempaudit = "";
            String select = "";
            String html = ""; // 前台需要拼接的字符串
            ArticleTypeUtil articleTypeUtil = new ArticleTypeUtil();// 将数据库中的文章类型转换成String字符串
            int i = pageBean.getNowPage() * pageBean.getSize() - pageBean.getSize() + 1;
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");// 将从数据库取出来的时间格式化成想要的格式
            for(ImageCustom image : temps) {
                // 判断审核的状态
                if("2".equals(image.getArticle().getAudit()) || image.getArticle().getAudit() == null) {
                    tempaudit = "<span class=\"label label-defaunt radius\">已下架</span>";
                } else if("1".equals(image.getArticle().getAudit())) {
                    tempaudit = "<span class=\"label label-success radius\">已发布</span>";
                }else {
                    tempaudit = "<span class=\"label label-success radius\">审核中</span>";
                }

                if("2".equals(image.getArticle().getAudit()) || image.getArticle().getAudit() == null) {
                    select = "<a style=\"text-decoration:none\" onClick=\"picture_audit(this,'" +image.getArticle().getId() + "')\" href=\"javascript:;\" title=\"提交审核\"><i class=\"Hui-iconfont\">&#xe603;</i></a>";
                }else if("1".equals(image.getArticle().getAudit())) {
                    select = "<a style=\"text-decoration:none\" onClick=\"picture_stop(this,'" + image.getArticle().getId() + "')\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a>";
                }else {
                    select = "<a style=\"text-decoration:none\" onClick=\"picture_stop(this,'" + image.getArticle().getId() + "')\" href=\"javascript:;\" title=\"下架\"><i class=\"Hui-iconfont\">&#xe6de;</i></a>";
                }

                html = html + "<tr class=\"text-c\" id=\"temp\">" +
                        "<td><input type=\"checkbox\" value=\"" + image.getId() + "\" name=\"subChk\"></td>" +
                        "<td>" + i + "</td>" +
                        "<td class=\"text-l\"><u style=\"cursor:pointer\" class=\"text-primary\" onClick=\"article_show('查看','" +  image.getArticle().getId() + "')\" title=\"查看\">" + image.getArticle().getTitle() +"</u></td>" +
                        "<td>" +
                        "<li class=\"item\">" +
                            "<div class=\"portfoliobox\">" +
                                "<div class=\"picbox\"><a href=\"http://localhost:8080/blog/" + image.getUrl() + image.getName() + "\" data-lightbox=\"gallery\" data-title=\"卫生间1\"><img src=\"http://localhost:8080/blog/" + image.getUrl() + image.getName() + "\" height=\"110\" width=\"210\"></a></div>" +
                           "</div>"+ 
                        "</li>" +
                    "</td>" +
                        "<td>" + articleTypeUtil.returnStringToString(image.getArticle().getArticleType()) + "</td>" +
                        "<td>" + image.getArticle().getIntroduce() + "</td>" +
                        "<td>" + "2015-12-05" + "</td>" +
                        "<td class=\"td-status\">" +
                        tempaudit +
                        "</td>" +
                        "<td class=\"f-14 td-manage\"><input id=\"articleId\" type=\"hidden\" name=\"articleId\" value=\"" + image.getArticle().getId() + "\">" +
                        select +
                        "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"picture_edit('图库编辑','" + image.getId()+ "')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>" +
					    "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"picture_del(this,'" + image.getId() +"')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>" +
                        "</td>" +
                        "</tr>";
                i++;

            }
            pageBean.init(pageBean.getSize(),pageBean.getNowPage(),imageMapper.selectNumberByUserId(userId,pageBean),images);
            result.put("htmls",html);
            result.put("pageBean",pageBean);
            return result;
        }

    public ImageCustom selectImageById(int id) {
        return imageMapper.selectImageById(id);
    };

    public String deleteImageById(Image image) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> result = new HashMap<String,Object>();
        if(image.getId() == 0) {
            result.put("result",false);
            result.put("errorType","1"); //1代表 未知错误，不删除该行
            result.put("mesage","未知错误");
            return objectMapper.writeValueAsString(result);
        }else if(imageMapper.selectImageById(image.getId()) != null) {
            if(imageMapper.deleteByPrimaryKey(image) > 0) {
                result.put("result",true);
                return objectMapper.writeValueAsString(result);
            }else {
                result.put("result",false);
                result.put("errorType","1"); //1代表 未知错误，不删除该行
                result.put("mesage","删除失败，请稍后再试!!");
                return objectMapper.writeValueAsString(result);
            }
        }else {
            result.put("result",false);
            result.put("errorType","2"); //1代表 改图片不存在，删除该行
            result.put("mesage","该图片不存在，请确认后重试!");
            return objectMapper.writeValueAsString(result);
        }
    }

    public Map<String,Object> deleteImagesById(int[] ids,int userId,PageBean pageBean) throws Exception {
        System.out.println("**************** servlet 开始  ");
        Map<String,Object> result = new HashMap<String,Object>();
        List<Image> images = new ArrayList<Image>();
        if(ids.length > 0) {
            pageBean.init(pageBean.getSize(),pageBean.getNowPage(),// 初始化分页
                    pageBean.getRecordCount() - ids.length,null);
            for(int id : ids) {
                System.out.println("**************** id =   " + id);
                Image temp = new Image();
                temp.setId(id);
                System.out.println("**************** tempid =   " + temp.getId());
                images.add(temp);
            }
            int k = imageMapper.deleteImagesById(images);
            System.out.println("**************** deletnumber =   " + k);
            result = selectAllByUserId(userId,pageBean);
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
            result.put("errorType","1"); //1代表 未知错误，不删除该行
            result.put("pageBean",pageBean);
            result.put("mesage","未知错误");
            return result;
        }
    }

    // 其他

    // 图片审核
//    public String updateImageAuditById(Article article) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String temp = "";
//        Map<String,Object> result = new HashMap<String, Object>();
//        if(article == null) {
//            System.out.println("111111**************** article.ardit = null  ");
//            result.put("result",false);
//        }else {
//            System.out.println("22222222**************** articles.ardit =   " + article.getAudit());
//            imageMapper.updateImageAuditById(article);
//            result.put("result",true);
//        }
//        return objectMapper.writeValueAsString(result);
//    }

}
