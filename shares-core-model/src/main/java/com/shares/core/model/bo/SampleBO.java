package com.shares.core.model.bo;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/22
 * @description
 */
//@SpELAssert(value = "#isEven(count) && count < 10", applyIf = "enabled",
//        helpers = Helpers.class, message = "这是什么鬼")
public class SampleBO {

    private int count;
    private boolean enabled;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}