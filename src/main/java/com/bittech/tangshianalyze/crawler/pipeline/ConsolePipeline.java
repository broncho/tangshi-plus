package com.bittech.tangshianalyze.crawler.pipeline;

import com.bittech.tangshianalyze.crawler.common.Page;

import java.util.Map;

/**
 * Author: secondriver
 * Created: 2019/3/17
 */
public class ConsolePipeline implements Pipeline {
    @Override
    public void pipeline(final Page page) {
        Map<String, Object> data = page.getDataSet().getData();
        System.out.println(data);
    }
}
