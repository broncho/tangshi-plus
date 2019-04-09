package com.bittech.tangshianalyze.analyze.dao;

import com.bittech.tangshianalyze.analyze.entity.PoetryInfo;
import com.bittech.tangshianalyze.analyze.model.AuthorCount;

import java.util.List;

/**
 * Author: secondriver
 * Created: 2019/3/23
 */
public interface AnalyzeDao {
    
    /**
     * 分析唐诗中作者的创作数量
     *
     * @return
     */
    List<AuthorCount> analyzeAuthorCount();
    
    
    /**
     * 查询所有的诗文，提供给业务层进行分析
     *
     * @return
     */
    List<PoetryInfo> queryAllPoetryInfo();
    
}
