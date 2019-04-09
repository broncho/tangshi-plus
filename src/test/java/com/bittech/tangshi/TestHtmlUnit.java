package com.bittech.tangshi;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

/**
 * Author: secondriver
 * Created: 2019/3/17
 */
public class TestHtmlUnit {
    
    public static void main(String[] args) {
        
        try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            
            //
            webClient.getOptions().setJavaScriptEnabled(false);
            
            HtmlPage htmlPage = webClient.getPage("https://so.gushiwen.org/shiwenv_eeb217f8cb2d.aspx");
            
            HtmlElement body = htmlPage.getBody();
            
            //标题
            String titlePath = "//div[@class='cont']/h1/text()";
            DomText titleDom = (DomText) body.getByXPath(titlePath).get(0);
            String title = titleDom.asText();
            
            
            //朝代
            String dynastyPath = "//div[@class='cont']/p/a[1]";
            
            HtmlAnchor dynastyDom = (HtmlAnchor) body.getByXPath(dynastyPath).get(0);
            String dynasty = dynastyDom.asText();
            
            //作者
            String authorPath = "//div[@class='cont']/p/a[2]";
            HtmlAnchor authorDom = (HtmlAnchor) body.getByXPath(authorPath).get(0);
            String author = authorDom.asText();
            
            //正文
            String contentPath = "//div[@class='cont']/div[@class='contson']";
            HtmlDivision contentDom = (HtmlDivision) body.getByXPath(contentPath).get(0);
            String content = contentDom.asText();
            
            
         
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}
