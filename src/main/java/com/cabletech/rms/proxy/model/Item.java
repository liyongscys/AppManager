package com.cabletech.rms.proxy.model;

import java.io.Serializable;

/**
 * Created by liyong on 2018/3/19.
 */
public class Item implements Serializable {
    /**serialVersionUID*/
    private static final long serialVersionUID = 8733902050164512929L;
    /**主键*/
    private String id;
    /** 分类 */
    private String type;
    /** 显示名称 */
    private String name;
    /** 备用字段 */
    private String tag;
    /**状态*/
    private String state;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }
    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }


}
