package com.bittech.tangshianalyze.analyze.service.impl;

import com.bittech.tangshianalyze.analyze.dao.AnalyzeDao;
import com.bittech.tangshianalyze.analyze.entity.PoetryInfo;
import com.bittech.tangshianalyze.analyze.model.AuthorCount;
import com.bittech.tangshianalyze.analyze.model.WordCount;
import com.bittech.tangshianalyze.analyze.service.AnalyzeService;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Author: secondriver
 * Created: 2019/3/23
 */
public class AnalyzeServiceImpl implements AnalyzeService {
    
    private final AnalyzeDao analyzeDao;
    
    public AnalyzeServiceImpl(AnalyzeDao analyzeDao) {
        this.analyzeDao = analyzeDao;
    }
    
    @Override
    public List<AuthorCount> analyzeAuthorCount() {
        //此处结果并未排序
        //排序方式
        //1. DAO层SQL排序
        //2. Service层进行数据排序
        List<AuthorCount> authorCounts = analyzeDao.analyzeAuthorCount();
        //此处是按照count升序
        authorCounts.sort(
                Comparator.comparing(AuthorCount::getCount));
        return authorCounts;
    }
    
    @Override
    public List<WordCount> analyzeWordCloud() {
        //1.查询出所有的数据
        //2.取出 title content
        //3.分词 过滤 /w  null 空  len<2
        //4.统计 k-v  k是词 v是词频
        Map<String, Integer> map = new HashMap<>();
        List<PoetryInfo> poetryInfos = analyzeDao.queryAllPoetryInfo();
        for (PoetryInfo poetryInfo : poetryInfos) {
            List<Term> terms = new ArrayList<>();
            String title = poetryInfo.getTitle();
            String content = poetryInfo.getContent();
            terms.addAll(NlpAnalysis.parse(title).getTerms());
            terms.addAll(NlpAnalysis.parse(content).getTerms());
            
            Iterator<Term> iterator = terms.iterator();
            while (iterator.hasNext()) {
                Term term = iterator.next();
                //词性的过滤
                if (term.getNatureStr() == null || term.getNatureStr().equals("w")) {
                    iterator.remove();
                    continue;
                }
                //词的过滤
                if (term.getRealName().length() < 2) {
                    iterator.remove();
                    continue;
                }
                //统计
                String realName = term.getRealName();
                int count;
                if (map.containsKey(realName)) {
                    count = map.get(realName) + 1;
                } else {
                    count = 1;
                }
                map.put(realName, count);
            }
        }
        List<WordCount> wordCounts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            WordCount wordCount = new WordCount();
            wordCount.setCount(entry.getValue());
            wordCount.setWord(entry.getKey());
            wordCounts.add(wordCount);
        }
        return wordCounts;
    }
}
