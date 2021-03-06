package org.compiere.orm;

import org.idempiere.common.util.Language;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeUtil {
    /**
     * Truncate Day - D
     */
    public static final String TRUNC_DAY = "D";
    /**
     * Truncate Week - W
     */
    public static final String TRUNC_WEEK = "W";
    /**
     * Truncate Month - MM
     */
    public static final String TRUNC_MONTH = "MM";
    /**
     * Truncate Quarter - Q
     */
    public static final String TRUNC_QUARTER = "Q";
    /**
     * Truncate Year - Y
     */
    public static final String TRUNC_YEAR = "Y";

    /**
     * Get earliest time of a day (truncate)
     *
     * @param dayTime day and time
     * @return day with 00:00
     */
    public static Timestamp getDay(Timestamp dayTime) {
        if (dayTime == null) return getDay(System.currentTimeMillis());
        return getDay(dayTime.getTime());
    } //	getDay

    /**
     * Get earliest time of a day (truncate)
     *
     * @param time day and time
     * @return day with 00:00
     */
    public static Timestamp getDay(long time) {
        if (time == 0) time = System.currentTimeMillis();
        GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    } //	getDay

    /**
     * Return Day + offset (truncates)
     *
     * @param day    Day
     * @param offset day offset
     * @return Day + offset at 00:00
     */
    public static Timestamp addDays(Timestamp day, int offset) {
        if (offset == 0) {
            return day;
        }
        if (day == null) {
            day = new Timestamp(System.currentTimeMillis());
        }
        //
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (offset == 0) return new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, offset); // 	may have a problem with negative (before 1/1)
        return new Timestamp(cal.getTimeInMillis());
    } //	addDays

    /**
     * Get earliest time of a day (truncate)
     *
     * @param day   day 1..31
     * @param month month 1..12
     * @param year  year (if two diguts: < 50 is 2000; > 50 is 1900)
     * @return timestamp ** not too reliable
     */
    public static Timestamp getDay(int year, int month, int day) {
        if (year < 50) year += 2000;
        else if (year < 100) year += 1900;
        if (month < 1 || month > 12) throw new IllegalArgumentException("Invalid Month: " + month);
        if (day < 1 || day > 31) throw new IllegalArgumentException("Invalid Day: " + month);
        GregorianCalendar cal = new GregorianCalendar(year, month - 1, day);
        return new Timestamp(cal.getTimeInMillis());
    } //	getDay

    /**
     * Get Minimum of 2 digits
     *
     * @param no number
     * @return String
     */
    private static String get2digits(long no) {
        String s = String.valueOf(no);
        if (s.length() > 1) return s;
        return "0" + s;
    } //	get2digits

    /**
     * Format Elapsed Time
     *
     * @param elapsedMS time in ms
     * @return formatted time string 1'23:59:59.999 - d'hh:mm:ss.xxx
     */
    public static String formatElapsed(long elapsedMS1) {
        long elapsedMS = elapsedMS1;
        if (elapsedMS == 0) return "0";
        StringBuilder sb = new StringBuilder();
        if (elapsedMS < 0) {
            elapsedMS = -elapsedMS;
            sb.append("-");
        }
        //
        long miliSeconds = elapsedMS % 1000;
        elapsedMS = elapsedMS / 1000;
        long seconds = elapsedMS % 60;
        elapsedMS = elapsedMS / 60;
        long minutes = elapsedMS % 60;
        elapsedMS = elapsedMS / 60;
        long hours = elapsedMS % 24;
        long days = elapsedMS / 24;
        //
        if (days != 0) sb.append(days).append("'");
        //	hh
        if (hours != 0) sb.append(get2digits(hours)).append(":");
        else if (days != 0) sb.append("00:");
        //	mm
        if (minutes != 0) sb.append(get2digits(minutes)).append(":");
        else if (hours != 0 || days != 0) sb.append("00:");
        //	ss
        sb.append(get2digits(seconds)).append(".").append(miliSeconds);
        return sb.toString();
    } //	formatElapsed

    /**
     * Format Elapsed Time until now
     *
     * @param start start time
     * @return formatted time string 1'23:59:59.999
     */
    public static String formatElapsed(Timestamp start) {
        if (start == null) return "NoStartTime";
        long startTime = start.getTime();
        long endTime = System.currentTimeMillis();
        return formatElapsed(endTime - startTime);
    } //	formatElapsed

    /**
     * Get today (truncate)
     *
     * @return day with 00:00
     */
    public static Calendar getToday() {
        GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
        //	cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    } //	getToday

    /**
     * Get last date in month
     *
     * @param day day
     * @return last day with 00:00
     */
    public static Timestamp getMonthLastDay(Timestamp day1) {
        Timestamp day = day1;
        if (day == null) day = new Timestamp(System.currentTimeMillis());
        GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
        cal.setTimeInMillis(day.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //
        cal.add(Calendar.MONTH, 1); // 	next
        cal.set(Calendar.DAY_OF_MONTH, 1); // 	first
        cal.add(Calendar.DAY_OF_YEAR, -1); // 	previous
        return new Timestamp(cal.getTimeInMillis());
    } //	getNextDay

    /**
     * Get truncated day/time
     *
     * @param dayTime day
     * @param trunc   how to truncate TRUNC_*
     * @return next day with 00:00
     */
    public static Timestamp trunc(Timestamp dayTime, String trunc) {
        if (dayTime == null) dayTime = new Timestamp(System.currentTimeMillis());
        GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
        cal.setTimeInMillis(dayTime.getTime());
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        //	D
        cal.set(Calendar.HOUR_OF_DAY, 0);
        if (trunc == null || trunc.equals(TRUNC_DAY)) return new Timestamp(cal.getTimeInMillis());
        //	W
        if (trunc.equals(TRUNC_WEEK)) {
            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
            return new Timestamp(cal.getTimeInMillis());
        }
        // MM
        cal.set(Calendar.DAY_OF_MONTH, 1);
        if (trunc.equals(TRUNC_MONTH)) return new Timestamp(cal.getTimeInMillis());
        //	Q
        if (trunc.equals(TRUNC_QUARTER)) {
            int mm = cal.get(Calendar.MONTH);
            if (mm < 4) mm = 1;
            else if (mm < 7) mm = 4;
            else if (mm < 10) mm = 7;
            else mm = 10;
            cal.set(Calendar.MONTH, mm);
            return new Timestamp(cal.getTimeInMillis());
        }
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return new Timestamp(cal.getTimeInMillis());
    } //	trunc

    // ARHIPAC: TEO: ADDITION
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * [ ARHIPAC ] Gets calendar instance of given date
     *
     * @param date calendar initialization date; if null, the current date is used
     * @return calendar
     * @author Teo Sarca, SC ARHIPAC SERVICE SRL
     */
    public static Calendar getCalendar(Timestamp date) {
        GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
        if (date != null) {
            cal.setTimeInMillis(date.getTime());
        }
        return cal;
    }

    /**
     * [ ARHIPAC ] Get first date in month
     *
     * @param day day; if null current time will be used
     * @return first day of the month (time will be 00:00)
     */
    public static Timestamp getMonthFirstDay(Timestamp day) {
        if (day == null) day = new Timestamp(System.currentTimeMillis());
        Calendar cal = getCalendar(day);
        cal.setTimeInMillis(day.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //
        cal.set(Calendar.DAY_OF_MONTH, 1); // 	first
        return new Timestamp(cal.getTimeInMillis());
    } //	getMonthFirstDay

    /**
     * [ ARHIPAC ] Return Day + offset (truncates)
     *
     * @param day    Day; if null current time will be used
     * @param offset months offset
     * @return Teo Sarca, SC ARHIPAC SERVICE SRL
     */
    public static Timestamp addMonths(Timestamp day, int offset) {
        if (day == null) day = new Timestamp(System.currentTimeMillis());
        //
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (offset == 0) return new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.MONTH, offset);
        return new Timestamp(cal.getTimeInMillis());
    } //	addMonths

}
