package com.bittech.tangshianalyze.crawler.common;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储清洗的数据
 * Author: secondriver
 * Created: 2019/3/17
 */
@ToString
public class DataSet {
    /**
     * Document Object Model
     *
     * data把DOM解析，清洗之后存储的数据
     * 比如：
     * 标题：送孟浩然之广陵
     * 作者：李白
     * 正文: XXX
     */
    private Map<String, Object> data = new HashMap<>();
    
    public void putData(String key, Object value) {
        this.data.put(key, value);
    }
    
    public Object getData(String key) {
        return this.data.get(key);
    }
    
    public Map<String, Object> getData() {
        return new HashMap<>(this.data);
    }
}
