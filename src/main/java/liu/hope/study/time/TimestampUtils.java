package liu.hope.study.time;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimestampUtils {


    public static long calNextTime(String unit, int period) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        if (StringUtils.isEmpty(unit)) {
            return zonedDateTime.toInstant().toEpochMilli();
        }
        switch (unit) {
            case "hour":
                return zonedDateTime.plus(period, ChronoUnit.HOURS).toInstant().toEpochMilli();
            case "day":
                return zonedDateTime.plus(period, ChronoUnit.DAYS).toInstant().toEpochMilli();
            case "week":
                return zonedDateTime.plus(period, ChronoUnit.WEEKS).toInstant().toEpochMilli();
            case "month":
                return zonedDateTime.plus(period, ChronoUnit.MONTHS).toInstant().toEpochMilli();
            case "year":
                return zonedDateTime.plus(period, ChronoUnit.YEARS).toInstant().toEpochMilli();
            default:
                return zonedDateTime.plus(period, ChronoUnit.MONTHS).toInstant().toEpochMilli();
        }
    }


    public static long calNextTime(Long from, String unit, int period) {
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(from).atZone(ZoneOffset.UTC);
        if (StringUtils.isEmpty(unit)) {
            return zonedDateTime.toInstant().toEpochMilli();
        }
        switch (unit) {
            case "day":
                return zonedDateTime.plus(period, ChronoUnit.DAYS).toInstant().toEpochMilli();
            case "week":
                return zonedDateTime.plus(period, ChronoUnit.WEEKS).toInstant().toEpochMilli();
            case "month":
                return zonedDateTime.plus(period, ChronoUnit.MONTHS).toInstant().toEpochMilli();
            case "year":
                return zonedDateTime.plus(period, ChronoUnit.YEARS).toInstant().toEpochMilli();
            default:
                return zonedDateTime.plus(period, ChronoUnit.MONTHS).toInstant().toEpochMilli();
        }
    }

    public static String formatEpochInMillis(long epochInMillis, ZoneId zoneId){
        if(epochInMillis == 0){
            epochInMillis = Instant.now(Clock.system(ZoneOffset.UTC)).toEpochMilli();
        }
        if(zoneId == null){
            zoneId = ZoneId.of("UTC");
        }
        Instant i = Instant.ofEpochMilli(epochInMillis);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(i, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return zonedDateTime.format(formatter);
    }

    public static long dateToBeginEpochInMillis(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).withHour(0).withMinute(0).withSecond(0).toInstant().toEpochMilli();
    }

    public static long dateToEndEpochInMillis(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).withHour(23).withMinute(23).withSecond(59).toInstant().toEpochMilli();
    }

    public static void main(String[] args) {

        System.out.println(TimestampUtils.calNextTime("", 1));
        System.out.println(TimestampUtils.calNextTime("month", 1));
        System.out.println(TimestampUtils.calNextTime("month", -1));
        System.out.println(TimestampUtils.dateToBeginEpochInMillis(new Date()));
        System.out.println(TimestampUtils.dateToEndEpochInMillis(new Date()));
    }

}
