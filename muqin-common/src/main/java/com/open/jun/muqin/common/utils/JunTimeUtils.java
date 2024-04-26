package com.open.jun.muqin.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 *@description: 时间工具类
 *         *** 1.本类提供了静态内部类，支持常见时间格式模板、时间相关字符、精度等信息，但是可能不够庞大，用户可以自定义格式等信息，但是建议使用标准格式
 *         *** 2.本类中默认时区是东八区，所有涉及转换、计算，如无具体说明，用户同样没有显示传入时区相关参数，都将使用该时区
 *@author: jun
 *version: 1.0
 *time: 2023/2/23 0:17
 **/
public final class JunTimeUtils {

    public static final ZoneOffset DEFAULT_ZONEOFFSET = ZoneOffset.ofHours(8);

    /****
     *@description: 时间工具类 - 时间格式模板内部类
     *              说明 ：
     *              1. 本类遵循标准的时间格式模板
     *                 1.1 yyyy 标识日所属年
     *                 1.2 YYYY 标识周所属年(需要注意，周所属年，当这一周跨年时，这一周时间都会返回新的一年)
     *                 1.3 MM 标识月份
     *                 1.4 mm 标识分钟
     *                 1.5 dd 标识月辖日 即几月几日
     *                 1.6 DD 标识年辖日 即当年第几天(需要注意，月辖日始终为2位，年辖日为3位)
     *                 1.7 HH 标识24小时制时
     *                 1.8 hh 标识12小时制时
     *                 1.9 ss 标识秒
     *                 2.0 SS 标识两位毫秒
     *                 2.1 SSS标识三位毫秒
     *@author: jun
     *version: 1.0
     *time: 2023/2/23 0:17
     **/
    public static final class Pattern {

        public static final String DEFAULT_SECOND = "yyyy-MM-dd HH:mm:ss";
        public static final String DEFAULT_SECOND_START_MM = "MM-dd-yyyy HH:mm:ss";
        public static final String DEFAULT_SECOND_SLASH = "yyyy/MM/dd HH:mm:ss";
        public static final String DEFAULT_SECOND_START_MM_SLASH = "MM/dd/yyyy HH:mm:ss";
        public static final String DEFAULT_SECOND_NUMBER = "yyyyMMddHHmmss";

        public static final String DEFAULT_MILLI_SECOND = "yyyy-MM-dd HH:mm:ss.SSS";
        public static final String DEFAULT_MILLI_SECOND_START_MM = "MM-dd-yyyy HH:mm:ss.SSS";
        public static final String DEFAULT_MILLI_SECOND_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";
        public static final String DEFAULT_MILLI_SECOND_START_MM_SLASH = "MM/dd/yyyy HH:mm:ss.SSS";
        public static final String DEFAULT_MILLI_SECOND_NUMBER = "yyyyMMddHHmmssSSS";

        public static final String DEFAULT_MINUTE = "yyyy-MM-dd HH:mm";
        public static final String DEFAULT_MINUTE_START_MM = "MM-dd-yyyy HH:mm";
        public static final String DEFAULT_MINUTE_SLASH = "yyyy/MM/dd HH:mm";
        public static final String DEFAULT_MINUTE_START_MM_SLASH = "MM/dd/yyyy HH:mm";
        public static final String DEFAULT_MINUTE_NUMBER = "yyyyMMddHHmm";


        public static final String DEFAULT_HOUR = "yyyy-MM-dd HH";
        public static final String DEFAULT_HOUR_START_MM = "MM-dd-yyyy HH";
        public static final String DEFAULT_HOUR_SLASH = "yyyy/MM/dd HH";
        public static final String DEFAULT_HOUR_START_MM_SLASH = "MM/dd/yyyy HH";
        public static final String DEFAULT_HOUR_NUMBER = "yyyyMMddHH";

        public static final String DEFAULT_DAY = "yyyy-MM-dd";
        public static final String DEFAULT_DAY_START_MM = "MM-dd-yyyy";
        public static final String DEFAULT_DAY_SLASH = "yyyy/MM/dd";
        public static final String DEFAULT_DAY_START_MM_SLASH = "MM/dd/yyyy";
        public static final String DEFAULT_DAY_NUMBER = "yyyyMMdd";

        public static final String DEFAULT_MONTH = "yyyy-MM";
        public static final String DEFAULT_MONTH_START_MM = "MM-yyyy";
        public static final String DEFAULT_MONTH_SLASH = "yyyy/MM";
        public static final String DEFAULT_MONTH_START_MM_SLASH = "MM/yyyy";
        public static final String DEFAULT_MONTH_NUMBER = "yyyyMM";

        public static final String DEFAULT_TIME_SECOND = "HH:mm:ss";
        public static final String DEFAULT_TIME_SECOND_NUMBER = "HHmmss";

        public static final String DEFAULT_TIME_MILLI_SECOND = "HH:mm:ss.SSS";

        public static final String DEFAULT_TIME_MINUTE = "HH:mm";

    }

    /****
     *@description: 时间工具类 - 时间常用连接符号
     *@author: jun
     *version: 1.0
     *time: 2023/2/23 0:17
     **/
    public static final class TimeCharacter {

        public static final String SLASH = "/";

        public static final String DASH = "-";

        public static final String COLON = ":";

        public static final String POINT = ".";

        public static final String CHAR_T = "T";

        public static final String CHAR_EMPTY = " ";

    }

    /****
     *@description: 时间工具类 - 时间单位 & 单位转换
     *              * 本类如无说明，所有及计算精度均为 时分秒天 计算精度毫秒 SSS
     *@author: jun
     *version: 1.0
     *time: 2023/2/23 0:17
     **/
    public static enum TimeUnit {

        SECOND("S", 1000, "秒"), MILLI_SECOND("SSS", 1, "毫秒"), MINUTE("m", 6000, "分钟"), HOUR("H", 60 * 6000, "小时"), DAY("D", 24 * 60 * 6000, "天"), MONTH("M", 0, "月"), YEAR("Y", 0, "年"),
        ;

        private String unit;

        private Integer rate;

        private String unitDesc;

        TimeUnit(String unit, Integer rate, String unitDesc) {
            this.unit = unit;
            this.rate = rate;
            this.unitDesc = unitDesc;
        }

        public Integer getRate() {
            return rate;
        }

        public String getUnit() {
            return unit;
        }

        public String getUnitDesc() {
            return unitDesc;
        }

        /**
         * 计算两个是个之间跨过了几天  左开右闭
         *
         * @param start
         * @param end
         * @return
         */
        public static Integer calDuringDay(LocalDateTime start, LocalDateTime end) {
            int startDayOfYear = start.getDayOfYear();
            int endDayOfYear = end.getDayOfYear();
            return endDayOfYear - startDayOfYear;
        }


    }

    /**
     * 计算当前时刻时间戳
     *
     * @return
     */
    public static Long culStempOnNow() {
        LocalDateTime now = LocalDateTime.now();
        long l = now.toInstant(DEFAULT_ZONEOFFSET).toEpochMilli();
        return l;
    }

    /**
     * 计算传入时刻时间戳
     *
     * @param localDateTime 时间参数
     * @return
     */
    public static Long culStemp(LocalDateTime localDateTime) {
        long l = localDateTime.toInstant(DEFAULT_ZONEOFFSET).toEpochMilli();
        return l;
    }

    /**
     * 计算传入时刻时间戳
     *
     * @param localDate 对象
     * @return
     */
    public static Long culStemp(LocalDate localDate) {
        long l = localDate.atStartOfDay(DEFAULT_ZONEOFFSET).toInstant().toEpochMilli();
        return l;
    }

    /**
     * 计算传入时刻时间戳
     *
     * @param date 时间日期对象
     * @return
     */
    public static Long culStemp(Date date) {
        return date.getTime();
    }

    /**
     * 计算传入时刻时间戳
     *
     * @param timeStr 时间字符串
     * @param pattern 时间格式
     * @return
     */
    public static Long culStemp(String timeStr, String pattern) {
        return culStemp(culLocalDateTime(timeStr, pattern));
    }

    /**
     * 计算 LocalDateTime
     *
     * @param timeStr 时间字符串
     * @return 使用默认格式 yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime culLocalDateTime(String timeStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(Pattern.DEFAULT_SECOND);
        return LocalDateTime.parse(timeStr, df);
    }

    /**
     * 计算 LocalDateTime
     *
     * @param timeStr 时间字符串
     * @param pattern 时间格式
     * @return
     */
    public static LocalDateTime culLocalDateTime(String timeStr, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(timeStr, df);
    }

    /**
     * 计算 LocalDateTime
     *
     * @param stemp 时间戳
     * @return
     */
    public static LocalDateTime culLocalDateTime(Long stemp) {

        return Instant.ofEpochMilli(stemp).atZone(DEFAULT_ZONEOFFSET).toLocalDateTime();

    }

    /**
     * 计算 LocalDateTime
     *
     * @param date 时间对象
     * @return
     */
    public static LocalDateTime culLocalDateTime(Date date) {

        return culLocalDateTime(culStemp(date));

    }

    /**
     * 计算 LocalDate
     *
     * @param timeStr 时间字符串
     * @param pattern 时间格式
     * @return
     */
    public static LocalDate culLocalDate(String timeStr, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(timeStr, df);
    }

    /**
     * 计算 LocalDate
     *
     * @param stemp 时间戳
     * @return
     */
    public static LocalDate culLocalDate(Long stemp) {

        return Instant.ofEpochMilli(stemp).atZone(DEFAULT_ZONEOFFSET).toLocalDate();

    }

    /**
     * 计算 LocalDate
     *
     * @param date 时间对象
     * @return
     */
    public static LocalDate culLocalDate(Date date) {

        return culLocalDate(culStemp(date));

    }

    /**
     * 计算Date
     *
     * @param timeStr 时间字符串
     * @return
     * @throws ParseException 使用默认格式 yyyy-MM-dd HH:mm:ss
     *                        不建议使用Date simpleDateFormat 非线程安全
     */
    @Deprecated
    public static Date culDate(String timeStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Pattern.DEFAULT_SECOND);
        return simpleDateFormat.parse(timeStr);
    }


    /**
     * 计算Date
     *
     * @param timeStr 时间字符串
     * @param pattern 时间格式
     * @return
     * @throws ParseException 不建议使用Date simpleDateFormat 非线程安全
     */
    @Deprecated
    public static Date culDate(String timeStr, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(timeStr);
    }

    /**
     * 计算Date
     *
     * @param stemp 时间戳
     * @return 不建议使用 Date
     */
    @Deprecated
    public static Date culDate(Long stemp) {
        return new Date(stemp);
    }

    /**
     * 计算Date
     *
     * @param localDate
     * @return 不建议使用 Date
     */
    @Deprecated
    public static Date culDate(LocalDate localDate) {
        return culDate(culStemp(localDate));
    }

    /**
     * 计算Date
     *
     * @param localDateTime
     * @return 不建议使用 Date
     */
    @Deprecated
    public static Date culDate(LocalDateTime localDateTime) {
        return culDate(culStemp(localDateTime));
    }

    /**
     * 时间戳转字符串
     *
     * @param stemp
     * @return 使用默认格式 yyyy-MM-dd HH:mm:ss
     */
    public static String culString(Long stemp) {
        LocalDateTime localDateTime = culLocalDateTime(stemp);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Pattern.DEFAULT_SECOND);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 计算格式为yyyyMM的数字时间
     * 不可以使用getYear和getMonth直接计算，否则计算目标值 202301会得到20231
     *
     * @param time
     * @return yyyyMM 格式时间 integer值
     */
    public static Integer culIntegerYYYYmmByLocalDateTime(LocalDateTime time) {
        String ym = culString(time, Pattern.DEFAULT_MONTH_NUMBER);
        return Integer.valueOf(ym);
    }

    /**
     * 计算格式为yyyyMM的数字时间
     * 不可以使用getYear和getMonth直接计算，否则计算目标值 202301会得到20231
     *
     * @param stemp 时间戳
     * @return yyyyMM 格式时间 integer值
     */
    public static Integer culIntegerYYYYmmByStemp(Long stemp) {
        LocalDateTime localDateTime = culLocalDateTime(stemp);
        return culIntegerYYYYmmByLocalDateTime(localDateTime);
    }

    /**
     * 时间转字符串
     *
     * @param stemp   时间戳
     * @param pattern 时间格式
     * @return
     */
    public static String culString(Long stemp, String pattern) {
        LocalDateTime localDateTime = culLocalDateTime(stemp);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 时间转字符串
     *
     * @param localDateTime
     * @return 使用默认格式 yyyy-MM-dd HH:mm:ss
     */
    public static String culString(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Pattern.DEFAULT_SECOND);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 时间转字符串
     *
     * @param localDateTime
     * @param pattern       时间格式
     * @return
     */
    public static String culString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 时间转字符串
     *
     * @param localDate
     * @return 使用默认格式 yyyy-MM-dd HH:mm:ss
     */
    public static String culString(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Pattern.DEFAULT_SECOND);
        return dateTimeFormatter.format(localDate);
    }

    /**
     * 时间转字符串
     *
     * @param localDate
     * @param pattern   时间格式
     * @return
     */
    public static String culString(LocalDate localDate, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(localDate);
    }

    /**
     * 时间转字符串
     *
     * @param date
     * @return 不建议使用Date
     */
    @Deprecated
    public static String culString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Pattern.DEFAULT_SECOND);
        return simpleDateFormat.format(date);
    }

    /**
     * 时间转字符串
     *
     * @param date
     * @param pattern 时间格式
     * @return 不建议使用Date
     */
    @Deprecated
    public static String culString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 计算 两个时间内所有yyyyMM格式时间列表
     *  参数会自动调换正确顺序
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    public static List<Integer> culYYYYmmListByYearMonthInteger(Integer start, Integer end) {

        if (start > end) {
            return culYYYYmmListByYearMonthInteger(end,start);
        }

        List<Integer> times = new ArrayList();

        while (start <= end) {
            times.add(start);
            start = culNextIntegerYYYYmmByInteger(start);
        }

        return times;

    }

    /**
     * 计算下个 yyyyMM 格式时间
     * @param start 开始时间
     * @return
     */
    private static Integer culNextIntegerYYYYmmByInteger(Integer start) {

        int month = start % 100;
        int year = start / 100;

        LocalDateTime of = LocalDateTime.of(year, month, 1, 0, 0);
        of = of.plusMonths(1);

        return culIntegerYYYYmmByLocalDateTime(of);

    }




}
