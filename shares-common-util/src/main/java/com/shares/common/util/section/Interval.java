package com.shares.common.util.section;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/4/20
 * @description 区间字典转换
 */
public class Interval {
    /**
     * 匹配区间字符串
     */
    private static final Pattern PATTERN = Pattern.compile("(\\[|\\()(\\+|\\d+)(,)(\\d+|\\+)(\\]|\\))");
    /**
     * 左区间[ or (
     */
    private String leftToken;
    /**
     * 左区间点
     */
    private String minValue;
    /**
     * 右区间点
     */
    private String maxValue;
    /**
     * 右区间] or )
     */
    private String rightToken;

    /**
     * 区间对应的值
     */
    private String message;

    /**
     * 特殊值
     */
    private Map<String, String> constantMap;

    public Interval(String string, String message) {
        string = string.replaceAll(" ", "");
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.find()) {
            this.leftToken = matcher.group(1);
            this.minValue = matcher.group(2);
            this.maxValue = matcher.group(4);
            this.rightToken = matcher.group(5);
            this.message = message;
        } else {
            if (this.constantMap == null) {
                this.constantMap = new HashMap<>();
            }
            constantMap.put(string, message);
        }
    }

    public String getLeftToken() {
        return leftToken;
    }

    public void setLeftToken(String leftToken) {
        this.leftToken = leftToken;
    }

    public String getMinValue() {
        return minValue == null ? null : ("+".equalsIgnoreCase(minValue) ? Integer.MIN_VALUE + "" : minValue);
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue == null ? null : ("+".equalsIgnoreCase(maxValue) ? Integer.MAX_VALUE + "" : maxValue);
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getRightToken() {
        return rightToken;
    }

    public void setRightToken(String rightToken) {
        this.rightToken = rightToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getConstantMap() {
        return constantMap;
    }

    public void setConstantMap(Map<String, String> constantMap) {
        this.constantMap = constantMap;
    }

    public boolean getValue(Integer value, boolean onlyLeft) {
        boolean left = false, right = false;
        switch (this.leftToken) {
            case "[":
                left = value >= Integer.parseInt(this.getMinValue());
                break;
            case "(":
                left = value > Integer.parseInt(this.getMinValue());
                break;
        }
        if (onlyLeft) {
            return left;
        }
        switch (this.rightToken) {
            case "]":
                right = value <= Integer.parseInt(this.getMaxValue());
                break;
            case ")":
                right = value < Integer.parseInt(this.getMaxValue());
                break;
        }
        return left && right;
    }
}
