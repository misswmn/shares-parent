package com.shares.common.util.section;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/13
 * @description
 */
@Component
public class IntervalDictionary extends AbstractDictionary<Map<String, String>, Interval, String> {

    @Override
    List<Interval> init(Map<String, String> dictMap) {
        return dictMap.entrySet().stream()
                .map(entry -> new Interval(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getValue(List<Interval> sectionList, IValue value) {
        return sectionList.stream()
                .filter(s -> s.getConstantMap() != null)
                .filter(s -> s.getConstantMap().containsKey(value.getValue()))
                .map(s -> s.getConstantMap().get(value.getValue()))
                .findFirst().orElse(null);
    }

    public String getValue(Map<String, String> dictMap, String value) {
        List<Interval> sectionList = this.init(dictMap);
        String result = getValue(sectionList, () -> value);
        if (result != null) return result;
        Interval[] sections = sectionList.stream()
                .filter(s -> s.getConstantMap() == null)
                .toArray(Interval[]::new);
        IntervalSupport.qsort(sections, 0, sections.length - 1);
        return getValue(sections, 0, sections.length - 1, Integer.parseInt(value));
    }

    private String getValue(Interval[] sections, int min, int max, Integer value) {
        while (min <= max) {
            int middle = (min + max) / 2;
            if (sections[middle].getValue(value, true)) {
                min = middle + 1;
            } else {
                max = middle - 1;
            }
        }

        if (sections[max].getValue(value, false)) {
            return sections[max].getMessage();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, String> dictMap = new HashMap<>();
        {
            dictMap.put("(+,99]", "很低");
            dictMap.put("[100,199]", "低");
            dictMap.put("[200,299]", "中");
            dictMap.put("[300,399]", "中高");
            dictMap.put("[400,499]", "高");
            dictMap.put("[500,599)", "很高");
            dictMap.put("9990", "特殊值(极有可能)");
            dictMap.put("9991", "特殊值(没有可能)");
        }
        IntervalDictionary intervalDictionary = new IntervalDictionary();
        String result = intervalDictionary.getValue(dictMap, "-33");
        System.out.println(result);
    }
}
