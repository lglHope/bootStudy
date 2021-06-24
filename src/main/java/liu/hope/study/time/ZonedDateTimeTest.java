package liu.hope.study.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Date;

import static java.time.temporal.ChronoField.*;

public class ZonedDateTimeTest {

    private static final String DTAE_TIME_PATTERN= "yyyy-MM-dd'T'HH:mm:ss";

    public static void main(String[] args) {
        long now = new Date().getTime();
        Date date_5min = new Date(now - 5 * 60 * 1000);
        Date date_10min = new Date(now - 10 * 60 * 1000);
//        System.out.println(Instant.now().atZone(ZoneOffset.UTC).plusMinutes(5).toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        // 2018-06-08T10:34:56+08:00
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("+08:00"));
        System.out.println(zonedDateTime.format(formatter) + zonedDateTime.getOffset());
        System.out.println(getCurrentUTCDateTime(new Date()));

        longToInstant(System.currentTimeMillis());

        System.out.println(LocalDateTime.now().atZone(ZoneId.of("+07:00")).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm")));
        System.out.println(LocalDateTime.ofInstant(Instant.now(), Clock.system(ZoneId.of("+7")).getZone()).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm")));
    }


    /**
     * 获取当前日期的UTC格式时间   格式: 2018-06-08T10:34:56+08:00
     * @param date
     * @return
     */
    public static String getCurrentUTCDateTime(Date date) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("+08:00"));
        String startTime = zonedDateTime.format(DateTimeFormatter.ofPattern(DTAE_TIME_PATTERN));
        String zoneId = zonedDateTime.toOffsetDateTime().getOffset().getId();
        return (startTime + zoneId);
    }

    public static void longToInstant(Long mill) {
        System.out.println(mill);
        Date date = new Date(mill);
        System.out.println(date);
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        System.out.println(zonedDateTime.format(formatter));
        System.out.println(date.toInstant().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
        System.out.println(date.toInstant().toEpochMilli());
    }

}
