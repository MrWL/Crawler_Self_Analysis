package com.peng.spider;

import java.awt.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ApiUse {
	public static void main(String[] args) throws Exception {
		//ģ��chorme�������������������޸�BrowserVersion.����
		WebClient  webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page=webClient.getPage("http://www.yanyulin.info");
		//�������ָ��������ϻ�ȡ��ǩhed������
		//HtmlDivision div=(HtmlDivision)page.getElementById("hed");
		//System.out.println(div.asXml());
		//ͬ�����Դ�ӡ��hed������,//div��//��ʾ���������ĵ��е�div,������Щdiv
		//����list�У�Ȼ���ȡ��һ��div
		//final HtmlDivision div = (HtmlDivision) page.getByXPath("//div").get(0);
		//System.out.println(div.asXml());
		java.util.List<HtmlAnchor> achList=page.getAnchors();
		for(HtmlAnchor ach:achList){
			System.out.println(ach.getHrefAttribute());
		}
	    webClient.closeAllWindows();		
		
		
	}
}
