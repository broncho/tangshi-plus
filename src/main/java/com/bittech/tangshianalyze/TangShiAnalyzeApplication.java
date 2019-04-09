package com.bittech.tangshianalyze;


import com.bittech.tangshianalyze.config.ObjectFactory;
import com.bittech.tangshianalyze.crawler.Crawler;
import com.bittech.tangshianalyze.web.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 唐诗分析程序的主类
 * Author: secondriver
 * Created: 2019/3/10
 * Description: 比特科技，只为更好的你；你只管学习，其它交给我。
 */
public class TangShiAnalyzeApplication {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TangShiAnalyzeApplication.class);
    
    public static void main(String[] args) {
        
        WebController webController = ObjectFactory.getInstance().getObject(WebController.class);
        
        //运行了web服务，提供接口
        LOGGER.info("Web Server launch ...");
        webController.launch();
        
        //启动爬虫
        if (args.length == 1 && args[0].equals("run-crawler")) {
            Crawler crawler = ObjectFactory.getInstance().getObject(Crawler.class);
            LOGGER.info("Crawler started ...");
            crawler.start();
        }
    }
}
