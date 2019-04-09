package com.bittech.tangshianalyze.crawler.parse;

import com.bittech.tangshianalyze.crawler.common.Page;

/**
 * Author: secondriver
 * Created: 2019/3/17
 */
public interface Parse {
    
    /**
     * 解析页面
     *
     * @param page
     */
    void parse(final Page page);
}
