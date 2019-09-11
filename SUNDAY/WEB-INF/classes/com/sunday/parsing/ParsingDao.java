package com.sunday.parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sunday.cmn.ConnectionMaker;
import com.sunday.cmn.JDBCReturnReso;
import com.sunday.movie.domain.MovieVO;

public class ParsingDao {

	private Logger LOG = Logger.getLogger(ParsingDao.class);
	private List<MovieVO> list = new ArrayList<>();
	private ConnectionMaker connectionMaker;
	
	public ParsingDao(){
		connectionMaker = new ConnectionMaker();
	}
	
	/**
	 * 
	 * @Method Name  : getData
	 * @작성일   : 2019. 7. 9.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 영화 DB에서 parsing한 정보를 MovieVO로 저장하여 list에 추가
	 * @param URL
	 * @return List<MovieVO>
	 */
	public List<MovieVO> getData(URL url) {
		
		BufferedReader rd	   = null;
		HttpURLConnection conn = null;
		JsonParser jsonParser  = new JsonParser();
		
		try{
			//커넥션 생성
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			//데이터 읽어오기
			StringBuilder sb = new StringBuilder();
			String line;
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			
			//null이 아니면 한 줄을 읽어서 sb에 덧붙인다.
			while ((line = rd.readLine()) != null) { 
				sb.append(line); 
			} 
			
			//읽어 온 데이터를 Json오브젝트로 만든다.
			JsonObject object = (JsonObject) jsonParser.parse(sb.toString());
			
			//"data"를 파싱
			JsonArray jsonArray = (JsonArray) object.get("Data");
		
			//반복문으로 모든 정보 꺼내기
			for(int i=0; i<jsonArray.size(); i++){

				JsonObject obj = (JsonObject) jsonArray.get(i);
				JsonArray result = obj.get("Result").getAsJsonArray();
				
				for(int j=0; j<result.size(); j++){
					//파싱한 데이터를 담을 vo 생성
					MovieVO vo = new MovieVO();
					
					//"result"를 파싱
					JsonObject obj2 = (JsonObject) result.get(j);
					
					String DOCID = obj2.get("DOCID").getAsString();			//DOCID
					String title = obj2.get("title").getAsString();			//제목
					String titleEng = obj2.get("titleEng").getAsString();	//영제
					String prodYear = obj2.get("prodYear").getAsString();	//제작년도
					
					JsonArray director = obj2.get("director").getAsJsonArray();
					String directorNm = "";									//감독이름
					String directorId = "";									//감독코드
					
					JsonArray actor = obj2.get("actor").getAsJsonArray();
					String actorNm = "";									//배우이름
					String actorId = "";									//배우코드
					
					String nation = obj2.get("nation").getAsString();		//국가
					String plot = obj2.get("plot").getAsString();			//줄거리
					String keywords = obj2.get("keywords").getAsString();	//키워드
					String posters = obj2.get("posters").getAsString();		//포스터 주소
					String genre = obj2.get("genre").getAsString();			//장르
					
					JsonArray rating = obj2.get("rating").getAsJsonArray();
					String releaseDate = "";								//개봉일
					
					//배열 안의 배열은 새로운 오브젝트로 만들어서 또 파싱해준다.
					for(int x=0; x<director.size(); x++){
						JsonObject obj3 = (JsonObject) director.get(x);
						directorNm = obj3.get("directorNm").getAsString();
						directorId = obj3.get("directorId").getAsString();
					}
					
					for(int y=0; y<actor.size(); y++){
						JsonObject obj4 = (JsonObject) actor.get(y);
						actorNm = obj4.get("actorNm").getAsString();
						actorId = obj4.get("actorId").getAsString();
					}
					
					for(int z=0; z<rating.size(); z++){
						JsonObject obj5 = (JsonObject) rating.get(z);
						releaseDate = obj5.get("releaseDate").getAsString();
					}
					
					//vo에 가져온 정보들을 set하기
					vo.setDOCID(DOCID);
					vo.setTitle(title);
					vo.setTitleEng(titleEng);
					vo.setProdYear(prodYear);
					vo.setDirectorNm(directorNm);
					vo.setDirectorId(Integer.parseInt(directorId));
					vo.setActorNm(actorNm);
					vo.setActorId(Integer.parseInt(actorId));
					vo.setNation(nation);
					vo.setPlot(plot);
					vo.setKeywords(keywords);
					vo.setPosters(posters);
					vo.setGenre(genre);
					vo.setReleaseDate(releaseDate);
		
					//vo를 리스트에 추가
					list.add(vo);
				}
			}
			
		}catch(IOException e){
			LOG.debug("====================================");
			LOG.debug("IOException:"+e.getMessage());
			LOG.debug("====================================");
		}finally{
			//자원 반납
			try {
				rd.close();
				conn.disconnect();
			} catch (IOException e) {
				LOG.debug("====================================");
				LOG.debug("IOException:"+e.getMessage());
				LOG.debug("====================================");
			} 
		}
		return list;
	}
	
	/**
	 * 
	 * @Method Name  : do_insert
	 * @작성일   : 2019. 7. 11.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : MovieVO를 movie테이블에 insert
	 * @param MovieVO
	 * @return int
	 */
	public int do_insert(MovieVO vo){
		int flag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO movie ( 					\n");
		sb.append("     docid,          					\n");
		sb.append("     title,          					\n");
		sb.append("     title_eng,      					\n");
		sb.append("     genre,          					\n");
		sb.append("     director_nm,    					\n");
		sb.append("     director_id,    					\n");
		sb.append("     nation,         					\n");
		sb.append("     actor_nm,       					\n");
		sb.append("     actor_id,       					\n");
		sb.append("     keywords,       					\n");
		sb.append("     posters,        					\n");
		sb.append("     release_date,   					\n");
		sb.append("     prod_year,      					\n");
		sb.append("     rate,           					\n");
		sb.append("     plot,           					\n");
		sb.append("     reg_id,         					\n");
		sb.append("     reg_dt,         					\n");
		sb.append("     mod_id,         					\n");
		sb.append("     mod_dt          					\n");
		sb.append(" ) VALUES (          					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     TO_CHAR(SYSDATE, 'YYYY-MM-DD'),     \n");
		sb.append("     ?,              					\n");
		sb.append("     TO_CHAR(SYSDATE, 'YYYY-MM-DD')      \n");
		sb.append(" )                   					\n");
		
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
//			LOG.debug("================================");
//			LOG.debug("query:\n"+sb.toString());
//			LOG.debug("================================");
			
			pstmt.setString(1, vo.getDOCID());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getTitleEng());
			pstmt.setString(4, vo.getGenre());
			pstmt.setString(5, vo.getDirectorNm());
			pstmt.setInt(6, vo.getDirectorId());
			pstmt.setString(7, vo.getNation());
			pstmt.setString(8, vo.getActorNm());
			pstmt.setInt(9, vo.getActorId());
			pstmt.setString(10, vo.getKeywords());
			pstmt.setString(11, vo.getPosters());
			pstmt.setString(12, vo.getReleaseDate());
			pstmt.setString(13, vo.getProdYear());
			pstmt.setDouble(14, vo.getRate());
			pstmt.setString(15, vo.getPlot());
			pstmt.setString(16, vo.getRegId());
			pstmt.setString(17, vo.getModId());
			
			flag = pstmt.executeUpdate();
			
		}catch(SQLException e){
			LOG.debug("====================");
			LOG.debug("SQLException:"+e.getMessage());
			LOG.debug("등록실패:"+vo.toString());
			LOG.debug("====================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return flag;
	}

}