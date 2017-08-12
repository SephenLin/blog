package aode.ssm.blog.util;

/**
 * Created by 林进华 on 2017/8/9.
 */
public class ArticleTypeUtil {

    private String articleTypeName;

    public String returnStringToString(String i) {
        if(i.equals("1")) {
           return "治愈";
        }else if(i.equals("2")) {
            return "技术";
        }else if(i.equals("3")) {
            return "心情";
        }else if(i.equals("4")) {
            return "清晨";
        }else if(i.equals("5")) {
            return "中午";
        }else if(i.equals("6")) {
            return "下午";
        }else if(i.equals("7")) {
            return "夜晚";
        }else {
            return "深夜";
        }
    }

}
