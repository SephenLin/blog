package aode.ssm.blog.test;

import org.junit.Test;

/**
 * Created by 林进华 on 2017/7/30.
 */
public class phoneTest {
    @Test
    public void test1() throws Exception{
        String PostData = "account=ljh1449815705&password=jianchi147?.?&mobile=18344264526&content="+java.net.URLEncoder.encode("您的验证密码是：1852，如需帮助请联系客服。","utf-8");
        System.out.println(PostData);
        String ret = aode.ssm.blog.util.Send.SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx");
        System.out.println(ret);
    }
}
