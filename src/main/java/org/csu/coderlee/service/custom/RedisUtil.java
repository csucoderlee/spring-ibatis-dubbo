package org.csu.coderlee.service.custom;

/**
 * @author by bixi.lx
 * @created on 2018 03 18 23:42
 */
public interface RedisUtil {

    void get();
    void set();
    void cache();
    void flush();
    void touch();
}
