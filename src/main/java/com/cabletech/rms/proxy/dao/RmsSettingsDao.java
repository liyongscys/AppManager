package com.cabletech.rms.proxy.dao;

/**
 * @author YuLeyuan
 *
 */
public interface RmsSettingsDao {
    String get(String key,String defaultValue);
    String put(String key,String value);
}
