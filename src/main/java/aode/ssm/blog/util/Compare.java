package aode.ssm.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 林进华 on 2017/8/6.
 */
public class Compare {

    public boolean compareTime(Date nowTime,String startTime,String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(nowTime); //2017-08-06 11:53:54
        // 截取当前时间时分秒 转成整型
        int  tempDate=Integer.parseInt(nowDate.substring(11, 13) + nowDate.substring(14, 16) + nowDate.substring(17, 19));
        // 截取开始时间时分秒  转成整型
        int  tempDateBegin=Integer.parseInt(startTime.substring(0, 2) + startTime.substring(3, 5) + startTime.substring(6, 8));
        // 截取结束时间时分秒  转成整型
        int  tempDateEnd=Integer.parseInt(endTime.substring(0, 2) + endTime.substring(3, 5) + endTime.substring(6, 8));

        if ((tempDate >= tempDateBegin && tempDate <= tempDateEnd)) {
            return true;
        } else {
            return false;
        }
    }

}
