package aode.ssm.blog.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PhoneUtil {

private static final String addr = "http://api.sms.cn/sms/";
private static final String userId = "tteesstt1";

/*
* 如uid是：test，登录密码是：123123
pwd=md5(123123test),即
pwd=b9887c5ebb23ebb294acab183ecf0769

*/
private static final String pwd = "89fd7beab064ea751929d81eb264fb7a";

private static final String encode = "utf8";

public static void send(String msgContent, String mobile) throws Exception {

//组建请求
String straddr = addr +
"?ac=send&uid="+userId+
"&pwd="+pwd+
"&mobile="+mobile+
"&template=100006"+
"&encode="+encode+
"&content={"+"\"code\":\"" + msgContent + "\"}" ;

StringBuffer sb = new StringBuffer(straddr);
System.out.println("URL:"+sb);

//发送请求
URL url = new URL(sb.toString());
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("POST");
BufferedReader in = new BufferedReader(new InputStreamReader(
url.openStream()));

//返回结果
String inputline = in.readLine();
System.out.println("Response:"+inputline);

}


public static void main(String[] args) {
try {
send("774873【篮球俱乐部】", "18344264526") ;
} catch (Exception e) {
e.printStackTrace();
}
}



}