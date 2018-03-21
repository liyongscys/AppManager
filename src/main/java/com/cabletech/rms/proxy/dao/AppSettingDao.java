package com.cabletech.rms.proxy.dao;

import java.util.List;
import java.util.Map;

import com.cabletech.rms.proxy.model.Option;
import com.cabletech.rms.proxy.model.SettingItem;

/**
 * @author YuLeyuan
 */
public interface AppSettingDao {
    /**
     * insert
     *
     * @param setting setting
     */
    void insert(SettingItem setting);

    /**
     * update
     *
     * @param setting setting
     */
    void update(SettingItem setting);

    /**
     * delete
     *
     * @param name name
     */
    void delete(String name);

    /**
     * find
     *
     * @param name name
     * @return
     */
    SettingItem find(String name);

    /**
     * getValue
     *
     * @param name name
     * @return
     */
    String getValue(String name);

    /**
     * list
     *
     * @return
     */
    List<SettingItem> list();

    /**
     * @return
     */
    Map<String, SettingItem> listAsMap();

    /**
     * @param name
     * @return
     */
    List<Option> getOptions(String name);
}
