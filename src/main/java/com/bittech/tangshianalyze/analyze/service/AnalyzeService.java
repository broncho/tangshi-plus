package com.bittech.tangshianalyze.analyze.service;

import com.bittech.tangshianalyze.analyze.model.AuthorCount;
import com.bittech.tangshianalyze.analyze.model.WordCount;

import java.util.List;

/**
 * Author: secondriver
 * Created: 2019/3/23
 */
public interface AnalyzeService {
    
    /**
     * 分析唐诗中作者的创作数量
     *
     * @return
     */
    List<AuthorCount> analyzeAuthorCount();
    
    /**
     * 词云分析
     *
     * @return
     */
    List<WordCount> analyzeWordCloud();
    
}
