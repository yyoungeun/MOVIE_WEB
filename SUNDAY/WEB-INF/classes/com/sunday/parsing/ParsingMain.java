package com.sunday.parsing;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.movie.domain.MovieVO;


public class ParsingMain {

	public static void main(String[] args) {
		
		List<MovieVO> list = new ArrayList<>();
		Logger LOG = Logger.getLogger(ParsingMain.class);
		ParsingDao dao = new ParsingDao();
		URL url;

		//1.parse
		//총 건수72319건
		for(int i=0; i<72320; i=i+10){
			//파싱할 데이터 주소
			//startCount=i로 읽어오기 시작할 지점 설정
			String urlStr = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json.jsp?collection=kmdb_new&detail=Y&startCount="+i+"&ServiceKey=06PEZ2E4448ZA69F87VP"; 

			//파싱(예외 발생하면 그 페이지는 그냥 넘기고 continue)
			try {
				url = new URL(urlStr);
				list=dao.getData(url);
			} catch (Exception e) {
				LOG.debug("============================");
				LOG.debug("Exception:"+e.toString());
				LOG.debug("============================");
				continue; 
			}	
		}
		
		//2.insert
		int count = 0;
		for(int i=0; i<list.size(); i++){
			MovieVO vo = list.get(i);
			vo.setRegId("admin");
			int flag = dao.do_insert(vo);
			if(flag==1) count++; //등록성공한 건수 세기
		}
		
		LOG.debug("등록성공 건수:"+count);

	}

}
