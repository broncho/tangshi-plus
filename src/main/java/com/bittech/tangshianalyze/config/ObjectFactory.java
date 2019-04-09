package com.bittech.tangshianalyze.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.bittech.tangshianalyze.analyze.dao.AnalyzeDao;
import com.bittech.tangshianalyze.analyze.dao.impl.AnalyzeDaoImpl;
import com.bittech.tangshianalyze.analyze.service.AnalyzeService;
import com.bittech.tangshianalyze.analyze.service.impl.AnalyzeServiceImpl;
import com.bittech.tangshianalyze.crawler.Crawler;
import com.bittech.tangshianalyze.crawler.common.Page;
import com.bittech.tangshianalyze.crawler.parse.DataPageParse;
import com.bittech.tangshianalyze.crawler.parse.DocumentParse;
import com.bittech.tangshianalyze.crawler.pipeline.ConsolePipeline;
import com.bittech.tangshianalyze.crawler.pipeline.DatabasePipeline;
import com.bittech.tangshianalyze.web.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: secondriver
 * Created: 2019/3/23
 */
public final class ObjectFactory {
    
    private static final ObjectFactory instance = new ObjectFactory();
    
    private final Logger logger = LoggerFactory.getLogger(ObjectFactory.class);
    
    /**
     * 存放所有的对象
     */
    private final Map<Class, Object> objectHashMap = new HashMap<>();
    
    private ObjectFactory() {
        //1.初始化配置对象
        initConfigProperties();
        
        //2.数据源对象
        initDataSource();
        
        //3.爬虫对象
        initCrawler();
        
        //4.Web对象
        initWebController();
        
        
        //5.对象清单打印输出
        printObjectList();
    }
    
    private void initWebController() {
        DataSource dataSource = getObject(DataSource.class);
        AnalyzeDao analyzeDao = new AnalyzeDaoImpl(dataSource);
        AnalyzeService analyzeService = new AnalyzeServiceImpl(analyzeDao);
        WebController webController = new WebController(analyzeService);
        objectHashMap.put(WebController.class, webController);
    }
    
    private void initCrawler() {
        ConfigProperties configProperties = getObject(ConfigProperties.class);
        DataSource dataSource = getObject(DataSource.class);
        final Page page = new Page(
                configProperties.getCrawlerBase(),
                configProperties.getCrawlerPath(),
                configProperties.isCrawlerDetail()
        );
        Crawler crawler = new Crawler();
        crawler.addParse(new DocumentParse());
        crawler.addParse(new DataPageParse());
        if (configProperties.isEnableConsole()) {
            crawler.addPipeline(new ConsolePipeline());
        }
        crawler.addPipeline(new DatabasePipeline(dataSource));
        crawler.addPage(page);
        
        objectHashMap.put(Crawler.class, crawler);
    }
    
    private void initDataSource() {
        ConfigProperties configProperties = getObject(ConfigProperties.class);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(configProperties.getDbUsername());
        dataSource.setPassword(configProperties.getDbPassword());
        dataSource.setDriverClassName(configProperties.getDbDriverClass());
        dataSource.setUrl(configProperties.getDbUrl());
        objectHashMap.put(DataSource.class, dataSource);
    }
    
    private void initConfigProperties() {
        ConfigProperties configProperties = new ConfigProperties();
        objectHashMap.put(ConfigProperties.class, configProperties);
        
        logger.info("ConfigProperties info:\n{}", configProperties.toString());
        
    }
    
    
    public <T> T getObject(Class classz) {
        if (!objectHashMap.containsKey(classz)) {
            throw new IllegalArgumentException("Class " + classz.getName() + " not found Object");
        }
        return (T) objectHashMap.get(classz);
    }
    
    public static ObjectFactory getInstance() {
        return instance;
    }
    
    
    private void printObjectList() {
        logger.info("====== ObjectFactory List =====");
        for (Map.Entry<Class, Object> entry : objectHashMap.entrySet()) {
            logger.info(String.format("[%-5s] ==> [%s]", entry.getKey().getCanonicalName(),
                    entry.getValue().getClass().getCanonicalName()));
        }
        logger.info("================================");
    }
}
