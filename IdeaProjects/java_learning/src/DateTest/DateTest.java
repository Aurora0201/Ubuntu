package DateTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) throws Exception {
        Date nowTime = new Date();

        //日期格式转换
        /*
        * z - Timezone
        * Z - Timezone + hour form
        *
        * y - Year
        * M - month
        * d - day
        *
        * E - Week
        *
        * H - Hour
        * m - minute
        * s - second
        *
        * S - mills
        * */

        SimpleDateFormat sdf = new SimpleDateFormat("z y年M月d日 E H时m分s秒 S毫秒");
        SimpleDateFormat sdf1 = new SimpleDateFormat("Z yy年M月d日 E H时m分s秒 S毫秒");
        //只有年份在少写y时会有区别
        System.out.println(sdf.format(nowTime));
        System.out.println(sdf1.format(nowTime));


        //String -> Date
        String time = "2002/02/01";
        SimpleDateFormat sdf2 = new SimpleDateFormat("y/M/d");
        Date stringToTime = sdf2.parse(time);
        System.out.println(stringToTime);
    }
}
