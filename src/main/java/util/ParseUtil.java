package util;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class ParseUtil {
    private final static Map<String, LinkedBlockingQueue<SimpleDateFormat>> formatMapQueue = new HashMap<>();

    private ParseUtil() {
        throw new RuntimeException();
    }

    static String getUnescapedString(String str, JSONObject json) {
        return StringEscapeUtils.unescapeHtml4(getRawString(str, json));
    }

    public static String getRawString(String name, JSONObject json) {
        try {
            if (json.isNull(name)) {
                return null;
            } else {
                return json.getString(name);
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date getDate(String name, JSONObject json) {
        return getDate(name, json, "EEE MMM d HH:mm:ss z yyyy");
    }

    public static Date getDate(String name, JSONObject json, String format) {
        String dateStr = getUnescapedString(name, json);
        if ("null".equals(dateStr) || null == dateStr) {
            return null;
        } else {
            return getDate(dateStr, format);
        }
    }

    public static Date getDate(String dateString, String format) {
        LinkedBlockingQueue<SimpleDateFormat> simpleDateFormats = formatMapQueue.get(format);
        if (simpleDateFormats == null) {
            simpleDateFormats = new LinkedBlockingQueue<>();
            formatMapQueue.put(format, simpleDateFormats);
        }
        SimpleDateFormat sdf = simpleDateFormats.poll();
        if (null == sdf) {
            sdf = new SimpleDateFormat(format, Locale.US);
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        }
        try {
            return sdf.parse(dateString);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Unexpected date format(" + dateString + ") returned from twitter.com", pe);
        } finally {
            try {
                simpleDateFormats.put(sdf);
            } catch (InterruptedException ignore) {

            }
        }
    }
}