package com.sunday.cmn;

import org.apache.log4j.Logger;

public class StringUtil {

	private static final Logger LOG = Logger.getLogger(StringUtil.class);
	
	/**
	 * String이 null이면 defaultValue로 대체
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static String nvl(Object str, String defaultValue){
		String retStr = "";
		
		if(str == null || str.equals("")){
			retStr = defaultValue;
		}else{
			retStr = str.toString().trim();
		}
		return retStr;
	}
	
	/**
	 * 페이지 렌더링
	 * @param maxNum
	 * @param currPageNo
	 * @param rowPerPage
	 * @param bottomCount
	 * @param url
	 * @param scriptName
	 * @return
	 */
	public static String renderPaging(int maxNum, int currPageNo, int rowPerPage, int bottomCount, String url, String scriptName){
		
		/**총 페이지*/
		int maxPageNo = ((maxNum-1)/rowPerPage)+1;
		
		/**시작 페이지*/
		int startPageNo = ((currPageNo-1)/bottomCount)*bottomCount+1; 
		
		/**마지막 페이지*/
		int endPageNo = ((currPageNo-1)/bottomCount+1)*bottomCount;
		
		/**현재 블럭*/
		int nowBlockNo = ((currPageNo-1)/bottomCount)+1;
		
		/**총 블럭 개수*/
		int maxBlockNo = ((maxNum-1)/bottomCount)+1;
		
		/**반복 변수*/
		int inx = 0;
		
		StringBuilder html = new StringBuilder();
		
		if(currPageNo>maxPageNo){
			return ""; //현재 페이지 번호가 총 페이지 번호보다 크면 번호를 표시하지 않고 공백으로 처리
		}
		
		html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> \n");
		html.append("<tr> \n");
		html.append("<td align=\"center\"> \n");
		//paging----------------------------------------
		
		//<< &laquo;
		if(nowBlockNo>1 && nowBlockNo<=maxBlockNo){
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',1);\" >");
			html.append("&laquo; ");
			html.append("</a> \n");
		}	
		
		//<
		if(startPageNo > bottomCount){
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+(startPageNo-1)+");\" >");
			html.append("< ");
			html.append("</a> \n");
		}
		
		//1 2 ... 10
		for(inx=startPageNo; inx<=maxPageNo && inx<=endPageNo; inx++){
			if(inx==currPageNo){
				html.append("<b>"+inx+"</b> &nbsp;&nbsp;");
			}else{
				html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+inx+");\" >");
				html.append(inx);
				html.append("</a>&nbsp;&nbsp; \n");
			}
		}
		
		//>
		if(maxPageNo>=inx){
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+((nowBlockNo*bottomCount)+1)+");\" >");
			html.append("> ");
			html.append("</a> \n");
		}
		
		//>> &raquo;
		if(maxPageNo>=inx){
			html.append("<a href=\"javascript:"+scriptName+"('"+url+"',"+maxPageNo+");\" >");
			html.append("&raquo; ");
			html.append("</a> \n");
		}

		//paging----------------------------------------
		
		html.append("</td> \n");
		html.append("</tr> \n");
		html.append("</table>");
		
		LOG.debug("=======================");
		LOG.debug(html.toString());
		LOG.debug("=======================");
				
		return html.toString();
	}
}
