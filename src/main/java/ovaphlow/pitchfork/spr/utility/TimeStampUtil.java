package ovaphlow.pitchfork.spr.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampUtil {

        public long gettimeStemp(String time, String format) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!"".equals(format)) {
                simpleDateFormat = new SimpleDateFormat(format);
            }

            long timeStemp = 0;
            try {

                Date date = simpleDateFormat.parse(time);

                timeStemp = date.getTime();

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return timeStemp;
        }



}
