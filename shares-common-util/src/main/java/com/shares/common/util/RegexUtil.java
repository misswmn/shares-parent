package com.shares.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangmn
 * @version 1.0
 * @date 2016/12/30
 * @description
 */
public class RegexUtil {

    private static final Pattern patternParam = Pattern.compile("\\{([\\w]+)\\}");

    /**
     * @param src    json字段串
     * @param params
     * @return 将{"key":"value"}转换为{"key":"值"}
     */
    public static String matchValue(String src, Map<?, ?> params) {
        for (Matcher matcher = patternParam.matcher(src); matcher.find(); ) {
            int sub = matcher.groupCount();
            String n = matcher.group(sub).trim();
            String val = String.valueOf(params.get(matcher.group(sub).trim()));
            src = src.replace("{" + n + "}", val);
        }
        return src;
    }

    /**
     * @param src    json字段串
     * @param object
     * @return 将{"key":"value"}转换为{"key":"值"}
     */
    public static String matchValue(String src, Object object) {
        Map<?, ?> params = JsonUtils.jsonToMap(JsonUtils.objectToJson(object));
        for (Matcher matcher = patternParam.matcher(src); matcher.find(); ) {
            int sub = matcher.groupCount();
            String n = matcher.group(sub).trim();
            String val = String.valueOf(params.get(matcher.group(sub).trim()));
            src = src.replace("{" + n + "}", val);
        }
        return src;
    }

    public static void main(String[] args) {
        String result = "将于{time}到货，请{to}查收.";
        Map<String, String> params = new HashMap<>();
        params.put("time", "2017-05-05");
        params.put("to", "张三");
        String target = matchValue(result, params);
        System.out.println(target);
    }
}
