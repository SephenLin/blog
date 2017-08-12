package aode.ssm.blog.util;

import org.junit.Test;

/**
 * Created by 林进华 on 2017/7/30.
 */
public class PhoneUtil2 {
    public void phoneValidate(String phone,String message) throws Exception {
        String first = "account=ljh1449815705&password=jianchi147?.?&mobile=" + phone + "&content=";
        String PostData = first + java.net.URLEncoder.encode(message,"utf-8");
        System.out.println(PostData);
        String ret = aode.ssm.blog.util.Send.SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx");
        System.out.println(ret);
    }

}
