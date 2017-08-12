package aode.ssm.blog.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 林进华 on 2017/7/2.
 */
@Component
public class TimeConverter implements Converter<String, Date> {

    public TimeConverter() {
        System.out.println("**************转换器TimeConverter启动成功******************");
    }

    public Date convert(String source) {
        if(source != null) {//如果从浏览器传入字符串不等于""开始转换
            source = source.trim();//去除前后空格
            if (source.equals("")) {
                source = null;
            }
        }
        try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         return dateFormat.parse(source);
            } catch (ParseException e) {
            e.printStackTrace();
           }
        return null ;
    }
}
