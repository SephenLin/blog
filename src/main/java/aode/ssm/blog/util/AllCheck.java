package aode.ssm.blog.util;

import java.io.IOException;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

/**
 * Created by 林进华 on 2017/7/1.
 */
public class AllCheck {

    public static boolean isMobileNO(String mobiles) {
        // 手机号码
        Pattern p = Pattern.compile("^((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---phone");
        return m.matches();
    }

    public static boolean isEmailNO(String email) {
        //电子邮件
        Pattern p = Pattern.compile("^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        Matcher m = p.matcher(email);
        System.out.println(m.matches() + "---email");
        return m.matches();
    }

    public static boolean isStudentNumberNO(String studentNumber) {
        //学号验证
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(studentNumber);
        System.out.println(m.matches() + "---number1");
        if(m.matches()) {
            Pattern q = Pattern.compile("^\\d{10,}$");
            Matcher r = p.matcher(studentNumber);
            System.out.println(m.matches() + "---number2");
            return r.matches() ;
        }
        return m.matches() ;

    }
}
