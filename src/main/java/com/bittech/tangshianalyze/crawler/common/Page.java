package com.bittech.tangshianalyze.crawler.common;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: secondriver
 * Created: 2019/3/17
 */
@Data
public class Page {
    
    /**
     * 数据网站的根地址
     * 比如：https://so.gushiwen.org
     */
    private final String base;
    
    /**
     * 具体网页的路径
     * 比如：/shiwenv_45c396367f59.aspx
     */
    private final String path;
    
    
    /**
     * 网页DOM对象
     */
    private HtmlPage htmlPage;
    
    
    /**
     * 标识网页是否是详情页
     */
    private final boolean detail;
    
    /**
     * 子页面对象集合
     */
    private Set<Page> subPage = new HashSet<>();
    
    /**
     * 数据对象
     */
    private DataSet dataSet = new DataSet();
    
    public String getUrl() {
        return this.base + this.path;
    }
    
}
