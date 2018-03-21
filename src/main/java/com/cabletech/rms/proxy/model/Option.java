package com.cabletech.rms.proxy.model;

/**
 * Created by liyong on 2018/3/19.
 */
public class Option {
    /**用于显示*/
    private String display;
    /**用于值*/
    private String value;
    /**
     * 构造方法
     */
    public Option() {

    }
    /**
     * 构造方法
     * @param value 值
     * @param display 显示项
     */
    public Option(String value, String display) {
        this.value = value;
        this.display = display;
    }

    /**
     * @return the display
     */
    public String getDisplay() {
        return display;
    }
    /**
     * @param display the display to set
     */
    public void setDisplay(String display) {
        this.display = display;
    }
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
