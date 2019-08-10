package com.lpf.jdk8.date;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 测试jdk8的时间工具类
 *
 * @author lipengfei
 * @create 2019-05-29 17:57
 **/
public class TestJdk8Date {


    /**
     * 获取当前时间戳
     */
    @Test
    public void getCurrentTimeStamp() {
        Long sysMillSecond = System.currentTimeMillis();// 精确到毫秒
        Long millisecond = Instant.now().toEpochMilli();  // 精确到毫秒
        Long millisecond2 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long second = Instant.now().getEpochSecond();// 精确到秒
        Long second2 = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));

        System.out.println(sysMillSecond);
        System.out.println(millisecond);
        System.out.println(millisecond2);
        System.out.println(second);
        System.out.println(second2);
    }


    /**
     * 测试Date和LocalDateTime的转换是否正确
     */
    @Test
    public void testDateConvertToLocalDateTime() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = format.parse("2019-05-29 12:22:22");
        LocalDateTime localDateTime = DateUtils.dateConvertToLocalDateTime(date);
        Long localDateTimeSecond = localDateTime.toEpochSecond(ZoneOffset.of("+8"));
        Long dateSecond = date.toInstant().atOffset(ZoneOffset.of("+8")).toEpochSecond();
        Assert.assertTrue(dateSecond.equals(localDateTimeSecond));
    }

    /**
     * 测试timeStamp和LocalDateTime的转换是否正确
     */
    @Test
    public void testTimeStampConvertToLocalDateTime() throws ParseException {
        Long millisecond = Instant.now().toEpochMilli();  // 精确到毫秒
        Long second = Instant.now().getEpochSecond();// 精确到秒

        String dateStr = "2019-05-29 12:22:22:666";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        Date oldDate = DateUtils.localDateTimeConvertToDate(dateTime);

        long newTimestamp = DateUtils.datatimeToTimestamp(dateTime);
        long oldTimestamp = oldDate.getTime();

        System.out.println(newTimestamp);
        System.out.println(oldTimestamp);
    }

    /**
     * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
     */
    @Test
    public static Long convertTimeToLong(String time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse("2018-05-29 13:52:50", ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
