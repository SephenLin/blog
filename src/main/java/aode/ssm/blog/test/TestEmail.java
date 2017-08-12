package aode.ssm.blog.test;

import aode.ssm.blog.util.EmailUtil;
import aode.ssm.blog.util.EmailUtil2;
import org.junit.Test;

/**
 * Created by 林进华 on 2017/7/30.
 */
public class TestEmail {
@Test
    public void test1() {




        EmailUtil2 emailUtil = new EmailUtil2();
        emailUtil.hashCode("1449815705@qq.com","taoqbbehsruvjfhg","1449815705@qq.com","<table></table>");
    }

}
