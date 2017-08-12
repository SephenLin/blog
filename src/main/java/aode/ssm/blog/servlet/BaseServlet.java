package aode.ssm.blog.servlet;

import aode.ssm.blog.mapper.ImageMapper;
import aode.ssm.blog.po.Admin;
import aode.ssm.blog.po.Image;
import aode.ssm.blog.po.ImageCustom;
import aode.ssm.blog.po.User;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 林进华 on 2017/7/19.
 */
public class BaseServlet {
    @Autowired
    ImageMapper imageMapper;

    // 返回 json
    public Map add_Image(MultipartFile file,
                            HttpServletRequest request, HttpServletResponse response, String path) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        String name = "";
        String uuidname = "";
        String roolPath = ""; //图片保存的工程,如：D:\aodeProject\target\blog\
        String realPath = ""; //图片保存的真实路径,如：D:\aodeProject\target\blog\resources\testImage\test
        ObjectMapper objectMapper = new ObjectMapper();
        Image image = new Image();
        try {
            name = file.getOriginalFilename();
            uuidname = this.getUUIDFileName(name);
            //图片保存的工程
            realPath = request.getServletContext().getRealPath(path);
            //真实路径
            roolPath = request.getSession().getServletContext().getRealPath("/");
            File file1 = new File(realPath);
            if (!file1.exists()){
                file1.mkdirs();
            }
            System.out.println("  ****************************  + realpath =  " + realPath + "roolpath = " + roolPath);
            image.setName(uuidname);
            image.setUrl(realPath.substring(realPath.lastIndexOf("blog\\") + 5, realPath.length()) + "\\");
            //得到文件的输入流
            InputStream in = new BufferedInputStream(file.getInputStream());
            //获得文件的输出流
            OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(realPath,uuidname)));
            IOUtils.copy(in,out);
            in.close();
            out.close();
            image.setAudit("2");
            //将图片信息传递到我的数据库当中
            int flag = imageMapper.insert(image);
            if(flag!=0){
                map.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
                map.put("url",realPath.substring(realPath.lastIndexOf("blog\\") + 4, realPath.length()) + "\\" + uuidname);         //能访问到你现在图片的路径
                map.put("title","");
                map.put("original",uuidname);
            }
        }catch(Exception e) {
            map.put("state", "文件上传失败!"); //在此处写上错误提示信息，这样当错误的时候就会显示此信息
            map.put("url","");
            map.put("title", "");
            map.put("original", "");
            e.printStackTrace();
            return map;
        }
        return map;
    }

    // 头像操作
    public Map add_head(MultipartFile file,
                         HttpServletRequest request, HttpServletResponse response, String path,Object object) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        String name = "";
        String uuidname = "";
        String roolPath = ""; //图片保存的工程,如：D:\aodeProject\target\blog\
        String realPath = ""; //图片保存的真实路径,如：D:\aodeProject\target\blog\resources\testImage\test
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        Admin admin = null;
        try {
            name = file.getOriginalFilename();
            uuidname = this.getUUIDFileName(name);
            //图片保存的工程
            realPath = request.getServletContext().getRealPath(path);
            //真实路径
            roolPath = request.getSession().getServletContext().getRealPath("/");
            File file1 = new File(realPath);
            if (!file1.exists()){
                file1.mkdirs();
            }
            System.out.println("  ****************************  + Headrealpath =  " + realPath + "Headroolpath = " + roolPath);
            if(object instanceof Admin) {
                admin = new Admin();
                admin.setHeadName(uuidname);
                admin.setHeadUrl(realPath.substring(realPath.lastIndexOf("blog\\") + 5, realPath.length()) + "\\");
            }else {
                user = new User();
                user.setHeadName(uuidname);
                user.setHeadUrl(realPath.substring(realPath.lastIndexOf("blog\\") + 5, realPath.length()) + "\\");
            }
            //得到文件的输入流
            InputStream in = new BufferedInputStream(file.getInputStream());
            //获得文件的输出流
            OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(realPath,uuidname)));
            IOUtils.copy(in,out);
            in.close();
            out.close();
            if(object instanceof Admin) {
                map.put("admin",admin);
            }else {
                map.put("user",user);
            }
        }catch(Exception e) {
            e.printStackTrace();
            return map;
        }
        return map;
    }
    // 返回response
    public void pictureUpdate(ImageCustom imageCustom,MultipartFile file,
                            HttpServletRequest request, HttpServletResponse response, String path) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        String uuidname = imageCustom.getName();
        String roolPath = ""; //图片保存的工程,如：D:\aodeProject\target\blog\
        String realPath = ""; //图片保存的真实路径,如：D:\aodeProject\target\blog\resources\testImage\test
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //图片保存的工程
            realPath = request.getServletContext().getRealPath(path);
            //真实路径
            roolPath = request.getSession().getServletContext().getRealPath("/");
            //1. 先删除文件
            File temp = new File(realPath + imageCustom.getName());
            if(temp.isFile()) {
                temp.delete();
            }
            //2. 添加文件
            File file1 = new File(realPath);
            if (!file1.exists()){
                file1.mkdirs();
            }
            System.out.println("  ****************************  + realpath =  " + realPath + "roolpath = " + roolPath);
            imageCustom.setName(uuidname);
            imageCustom.setUrl(realPath.substring(realPath.lastIndexOf("blog\\") + 5, realPath.length()) + "\\");
            //得到文件的输入流
            InputStream in = new BufferedInputStream(file.getInputStream());
            //获得文件的输出流
            OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(realPath,uuidname)));
            IOUtils.copy(in,out);
            in.close();
            out.close();
            //将图片信息传递到我的数据库当中
            int flag = imageMapper.updateImageById(imageCustom);
            if(flag!=0){
                response.getWriter().write(imageCustom.getName() + "上传成功,保存的路径:" + realPath);
            }else {
                response.getWriter().write(imageCustom.getName() + "上传失败,请检查操作是否正确");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getUUIDFileName(String fileName){
        UUID uuid = UUID.randomUUID();
        StringBuilder sb = new StringBuilder(100);
        sb.append(uuid.toString()).append(".").append(this.getExtName(fileName, '.'));
        return sb.toString();
    }

    //下面是我的两个方法，取的uuidname防止文件同名问题
    public String getExtName(String s, char split) {
        int i = s.lastIndexOf(split);
        int leg = s.length();
        return i > 0 ? (i + 1) == leg ? " " : s.substring(i+1, s.length()) : " ";
    }
}
