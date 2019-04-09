package com.bittech.tangshianalyze.crawler.pipeline;

import com.bittech.tangshianalyze.crawler.common.Page;

/**
 * Author: secondriver
 * Created: 2019/3/17
 */
public interface Pipeline {
    
    /**
     * 管道处理page中的数据
     *
     * @param page
     */
    void pipeline(final Page page);
}
