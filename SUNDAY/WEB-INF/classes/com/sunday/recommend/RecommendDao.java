package com.sunday.recommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.cmn.ConnectionMaker;
import com.sunday.cmn.DTO;
import com.sunday.cmn.JDBCReturnReso;
import com.sunday.cmn.WorkDiv;

public class RecommendDao implements WorkDiv {
	
	// Logger 형식의 변수를 생성하고, 그 안에 클래스 이름을 지정해 클래스에서 log4j를 사용할 수 있게 함
	private final Logger LOG = Logger.getLogger(RecommendDao.class);
	
	// ConnectionMaker 클래스의 참조 변수 선언
	private ConnectionMaker connectionMaker;
	
	/**기본 생성자*/
	public RecommendDao() {
		// ConnectionMaker 클래스의 객체 생성해서 참조 변수에 저장
		connectionMaker = new ConnectionMaker();
		
	}//--RecommendDao()
	
	/**4개의 추천코드가 일치하는 영화 정보를 출력하는 메소드*/
	public List<?> do_retrieve(DTO dto) { // 조회할 객체를 매개변수로 받음
		
		RecommendVO vo = (RecommendVO) dto; // DTO 타입의 매개변수를 RecommendVO 타입의 변수에 저장
		List<RecommendVO> list=new ArrayList<>(); // 검색된 영화 데이터 모두를 담을(영화 각각은 객체) ArrayList 생성
		
		Connection conn = null; // 커넥션 객체를 담을 변수
		PreparedStatement pstmt = null; // PreparedStatement 객체를 반환받을 변수(쿼리문 가짐)
		ResultSet rs = null; // 쿼리의 실행 결과를 반환받을 변수(ResultSet 객체에 담아서 리턴)
		
		// 검색 조건(WHERE) 쿼리문
		StringBuilder sbWhere = new StringBuilder();
		
		// 추천 코드의 값에 따라 쿼리문을 다르게 입력
		if(vo.getRecM() > 0 || vo.getRecW() > 0 || vo.getRecT() > 0 || vo.getRecP() > 0) { // 추천 코드 변수에 하나라도 값이 있으면
			
			if(vo.getRecM() > 0) { // 기분 코드에 값이 있으면
				sbWhere.append("AND rec_m = ? \n"); // 기분 코드에 따라 검색하는 쿼리문을 StringBuilder에 담음
			}
			if(vo.getRecW() > 0) { // 날씨 코드에 값이 있으면
				sbWhere.append("AND rec_w = ? \n"); // 날씨 코드에 따라 검색하는 쿼리문을 StringBuilder에 담음
			}
			if(vo.getRecT() > 0) { // 시간 코드에 값이 있으면
				sbWhere.append("AND rec_t = ? \n"); // 시간 코드에 따라 검색하는 쿼리문을 StringBuilder에 담음
			}
			if(vo.getRecP() > 0) { // 사람 코드에 값이 있으면
				sbWhere.append("AND rec_p = ? \n"); // 사람 코드에 따라 검색하는 쿼리문을 StringBuilder에 담음
			}
			
		}//--if
		
		// 메인 쿼리문
		// StringBuilder 객체 생성 -> 문자열을 담는 역할
		StringBuilder sb = new StringBuilder();
		// 검색어로 검색된 테이블 전체 조회하는데 페이징으로 잘라서 반환 + 크로스 조인으로 전체 검색된 데이터 수도 같이 반환
		sb.append("SELECT T1.rnum                                                   \n"); 
		sb.append(" 	,T1.rec_m                                                   \n");
		sb.append(" 	,T1.rec_w                                                   \n"); 
		sb.append(" 	,T1.rec_t                                                   \n"); 
		sb.append(" 	,T1.rec_p                                                   \n"); 
		sb.append(" 	,T1.docid                                                   \n"); 
		
		sb.append(" 	,T1.title                                                   \n"); 
		sb.append(" 	,T1.title_eng                                               \n"); 
		sb.append(" 	,T1.genre                                                   \n"); 
		sb.append(" 	,T1.director_nm                                             \n"); 
		sb.append(" 	,T1.director_id                                             \n"); 
		
		sb.append(" 	,T1.nation                                                  \n"); 
		sb.append(" 	,T1.actor_nm                                                \n"); 
		sb.append(" 	,T1.actor_id                                                \n"); 
		sb.append(" 	,T1.keywords                                                \n"); 
//		sb.append(" 	,T1.posters                                                 \n"); 
		sb.append("           ,CASE                                        		    \n");
		sb.append("            WHEN T1.posters LIKE '%|%' THEN substr(T1.posters, 1, instr(T1.posters, '|')-1) \n");
		sb.append("            ELSE T1.posters                                      \n");
		sb.append("            END as posters                                       \n");
		
		sb.append(" 	,T1.release_date                                            \n"); 
		sb.append(" 	,T1.prod_year                                               \n"); 
		sb.append(" 	,T1.rate                                                    \n"); 
		sb.append(" 	,T1.plot                                                    \n"); 
		sb.append(" 	,T1.reg_id                                                  \n"); 
		
		sb.append(" 	,T1.reg_dt                                                  \n"); 
		sb.append(" 	,T1.mod_id                                                  \n"); 
		sb.append(" 	,T1.mod_dt                                                  \n"); 
		sb.append(" 	,T1.now_showing                                             \n");                                                         
		sb.append(" 	,T2.*                                                       \n"); 
		
		sb.append("FROM (                                                             \n");
		sb.append("SELECT B.*                                                         \n");
		sb.append("FROM(                                                              \n");
		sb.append("	SELECT ROWNUM AS rnum, A.*                                        \n");
		sb.append("	FROM(                                                             \n");
		sb.append("		SELECT mo.*, r.rec_m, r.rec_w, r.rec_t, r.rec_p   \n");
		sb.append("		FROM recommend r, movie mo                                    \n");
		sb.append("        WHERE r.docid = mo.docid                                   \n");
		sb.append("		--SEARCH CONDITION                                            \n");
		//-------------------------------------------------------------------------------
		// 가상 테이블 A 쿼리문에 검색 조건 끼워넣기
		if(vo.getRecM() > 0 || vo.getRecW() > 0 || vo.getRecT() > 0 || vo.getRecP() > 0) { // 추천 코드 변수에 하나라도 값이 있으면
			sb.append(sbWhere.toString()); // 검색어를 포함한 검색 조건(WHERE) 쿼리문을 끼워넣음
		}
		//-------------------------------------------------------------------------------
		//sb.append("		ORDER BY mo.rate DESC                                       \n");
		sb.append("	)A                                                                           \n");
		sb.append("	WHERE ROWNUM <= (9 *(?-1)+9))B                                     \n");
		sb.append("WHERE B.rnum >= (9*(?-1)+1)                                          \n");
		sb.append(" )T1                                                                          \n");
		sb.append(" CROSS JOIN   --조건에 따른 조회 데이터 카운트                                          \n");     
		sb.append(" (                                                                            \n");
		sb.append(" SELECT COUNT(*) total_cnt                                                    \n");
		sb.append("		FROM recommend r, movie mo                                               \n");
		sb.append("        WHERE r.docid = mo.docid                                              \n");
		sb.append(" --SEARCH CONDITION                                                           \n");
		//-------------------------------------------------------------------------------
		// 가상 테이블 T2에 검색 조건 끼워넣기: 검색어로 검색된 데이터 수를 반환하게 됨
		if(vo.getRecM() > 0 || vo.getRecW() > 0 || vo.getRecT() > 0 || vo.getRecP() > 0) { // 추천 코드 변수에 하나라도 값이 있으면
			sb.append(sbWhere.toString()); // 검색어를 포함한 검색 조건(WHERE) 쿼리문을 끼워넣음
		}
		//-------------------------------------------------------------------------------
		sb.append("		ORDER BY mo.rate DESC                                       \n");
		sb.append( " )T2 ");
		
		// 전체 쿼리문 출력
		LOG.debug("0. sql \n:"+sb.toString()); 
		
		try{
			conn = new ConnectionMaker().getConnection(); // 커넥션 객체를 반환받음
			pstmt = conn.prepareStatement(sb.toString()); // prepareStatement 객체를 생성하면서  버퍼의 쿼리문을 저장
			
			//--------각 추천 코드 값에 따른 쿼리문의 물음표 값 입력---------
			if(vo.getRecM() > 0 || vo.getRecW() > 0 || vo.getRecT() > 0 || vo.getRecP() > 0) { // 추천 코드 변수에 하나라도 값이 있으면
				
				// 추천 코드 1~4
				// PAGE_NUM
				// PAGE_NUM
				// 추천 코드 1~4
				
				// 2차원 배열 만들기: 각 코드에 대한 값: 1~4, 코드: 10~40 (기분-날씨-시간-사람 순서)
				int[][] arr = {{vo.getRecM(), 10}, {vo.getRecW(), 20}, {vo.getRecT(), 30}, {vo.getRecP(), 40}};
				int cnt = 0; // 값이 있는 코드의 수를 카운트
				
				for(int i = 0; i < arr.length; i++) {
					
					if(arr[i][0] > 0) { // 각 코드에 대한 값이 있으면
						
						cnt += 1; // 카운트
						
						//System.out.println("cnt: " + cnt);
					}
				}
				
				//System.out.println("cnt: " + cnt);
				
				// 추천 코드 쿼리문 
				for(int i = 0; i < arr.length; i++) {
					
					//System.out.println("i: " + i);
					
					if(arr[i][0] != 0) { // 각 코드에 대한 값이 있으면
						
						if(arr[i][1] == 10) { // 기분 코드일 경우 
							
							pstmt.setInt(i+1, vo.getRecM()); // i+1번째 물음표를 기분 코드의 값으로 set
							//System.out.println("i+1: " + (i+1));
							
							switch (cnt) { // 값이 있는 코드의 수에 따라
								case 1:
									pstmt.setInt(i+4, vo.getRecM()); // i+4번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+4: " + (i+4));
									break;
								case 2:
									pstmt.setInt(i+5, vo.getRecM()); // i+5번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+5: " + (i+5));
									break;
								case 3:
									pstmt.setInt(i+6, vo.getRecM()); // i+6번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+6: " + (i+6));
									break;
								case 4:
									pstmt.setInt(i+7, vo.getRecM()); // i+7번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+7: " + (i+7));
									break;
	
								default:
									break;
							}//--switch
	
						}
						else if(arr[i][1] == 20) { // 날씨 코드일 경우 
							
							// 앞선 순서의 코드에 값이 없었을 경우, 쿼리문의 물음표(?) 순서를 맞추기 위해  i의 카운트를 빼줌
							int tmp = 0;
							for(int j = 1; j <= i; j++) {
								
								
								if(arr[i-j][0] == 0) {
									tmp++;
								}
							}
							i -= tmp;
							
							pstmt.setInt(i+1, vo.getRecW()); // i+1번째 물음표를 날씨 코드의 값으로 set
							//System.out.println("i+1: " + (i+1));
							
							switch (cnt) {
								case 1:
									pstmt.setInt(i+4, vo.getRecW()); // i+4번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+4: " + (i+4));
									break;
								case 2:
									pstmt.setInt(i+5, vo.getRecW()); // i+5번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+5: " + (i+5));
									break;
								case 3:
									pstmt.setInt(i+6, vo.getRecW()); // i+6번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+6: " + (i+6));
									break;
								case 4:
									pstmt.setInt(i+7, vo.getRecW()); // i+7번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+7: " + (i+7));
									break;
	
								default:
									break;
							}//--switch
							
							// 다시 i의 카운트를 원래대로 되돌림
							i += tmp;
					
						}
						else if(arr[i][1] == 30) { // 시간 코드일 경우 
							
							// 앞선 순서의 코드에 값이 없었을 경우, 쿼리문의 물음표(?) 순서를 맞추기 위해  i의 카운트를 빼줌
							int tmp = 0;
							for(int j = 1; j <= i; j++) {
								
								
								if(arr[i-j][0] == 0) {
									tmp++;
								}
							}
							i -= tmp;
							
							pstmt.setInt(i+1, vo.getRecT()); // i+1번째 물음표를 시간 코드의 값으로 set
							//System.out.println("i+1: " + (i+1));
							
							switch (cnt) {
								case 1:
									pstmt.setInt(i+4, vo.getRecT()); // i+4번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+4: " + (i+4));
									break;
								case 2:
									pstmt.setInt(i+5, vo.getRecT()); // i+5번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+5: " + (i+5));
									break;
								case 3:
									pstmt.setInt(i+6, vo.getRecT()); // i+6번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+6: " + (i+6));
									break;
								case 4:
									pstmt.setInt(i+7, vo.getRecT()); // i+7번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+7: " + (i+7));
									break;
	
								default:
									break;
							}//--switch
							
							// 다시 i의 카운트를 원래대로 되돌림
							i += tmp;
			
						}
						else if(arr[i][1] == 40) { // 사람 코드일 경우 
							
							// 앞선 순서의 코드에 값이 없었을 경우, 쿼리문의 물음표(?) 순서를 맞추기 위해  i의 카운트를 빼줌
							int tmp = 0;
							for(int j = 1; j <= i; j++) {
								
								
								if(arr[i-j][0] == 0) {
									tmp++;
								}
							}
							i -= tmp;
							
							pstmt.setInt(i+1, vo.getRecP()); // i+1번째 물음표를 사람 코드의 값으로 set
							//System.out.println("i+1: " + (i+1));
							
							switch (cnt) {
								case 1:
									pstmt.setInt(i+4, vo.getRecP()); // i+4번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+4: " + (i+4));
									break;
								case 2:
									pstmt.setInt(i+5, vo.getRecP()); // i+5번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+5: " + (i+5));
									break;
								case 3:
									pstmt.setInt(i+6, vo.getRecP()); // i+6번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+6: " + (i+6));
									break;
								case 4:
									pstmt.setInt(i+7, vo.getRecP()); // i+7번째 물음표를 날씨 코드의 값으로 set
									//System.out.println("i+7: " + (i+7));
									break;
	
								default:
									break;
							}//--switch
							
							// 다시 i의 카운트롤 원래대로 되돌림
							i += tmp;
						}
			
					}//--if
					
				}//--for
				
				// 페이징 쿼리문
				for(int i = 0; i < 4; i++) { // 각 추천 코드 값이 있는 경우 1~4
					
					// 추천 코드 쿼리문의 수에 따라 해당 번째 물음표에 페이지 값을 set
					if(cnt == i+1) { 
						pstmt.setInt(i+2, vo.getPageNum());
						pstmt.setInt(i+3, vo.getPageNum());
						//System.out.println("i+2: " + (i+2));
						//System.out.println("i+3: " + (i+3));
					}
				
				}//--for
			
			}//--if(1)
			
			// 추천 코드 조건 쿼리가 없는 경우 -> 페이징만
			else {
				
				// PAGE_NUM
				// PAGE_NUM
				pstmt.setInt(1, vo.getPageNum());
				pstmt.setInt(2, vo.getPageNum());
				
			}//--else(1)
			
			// (매개변수로 받음) 조회할 객체의 추천 코드를 출력해봄
			LOG.debug("1.==========================");
			LOG.debug("1.rec: " + vo.getRecM());
			LOG.debug("1.rec: " + vo.getRecW());
			LOG.debug("1.rec: " + vo.getRecT());
			LOG.debug("1.rec: " + vo.getRecP());
			LOG.debug("1.==========================");
			
			rs = pstmt.executeQuery(); // SELECT 쿼리를 실행하여, 쿼리 실행 결과값을 담은 ResultSet 객체를 반환
			// ResultSet.next(): 처음에 첫번 째 행 이전에 커서가 위치하기 때문에, 첫 번째 행에 저장된 데이터를 읽으려면  next() 메소드를 사용하여 커서를 이동해야함
			//                 : 커서의 다음 행이 존재하는 경우 true를 리턴하고, 커서를 그 행으로 이동시킴
			//   			   : 커서는 순차적으로 이동하고, 마지막 행에 도달하면 false를 리턴
			
			// rs에 쿼리 실행 결과가 있어서 다음 행이 존재하는 경우
			while(rs.next()) {
		
				// 영화 하나에 대한 데이터를 담을 RecommendVO 객체를 생성
				RecommendVO outVO = new RecommendVO();
				// 쿼리문으로 검색된 객체에서 추출한 데이터에서 값을 뽑아내어 OutVo의 변수 값으로 설정
				outVO.setRecM(rs.getInt("rec_m"));
				outVO.setRecW(rs.getInt("rec_w"));
				outVO.setRecT(rs.getInt("rec_t"));
				outVO.setRecP(rs.getInt("rec_p"));
				
				outVO.setDOCID(rs.getString("docid"));
				outVO.setTitle(rs.getString("title"));
				outVO.setTitleEng(rs.getString("title_eng"));
				outVO.setGenre(rs.getString("genre"));
				outVO.setDirectorNm(rs.getString("director_nm"));
				outVO.setDirectorId(rs.getInt("director_id"));
				outVO.setNation(rs.getString("nation"));
				outVO.setActorNm(rs.getString("actor_nm"));
				outVO.setActorId(rs.getInt("actor_id"));
				outVO.setKeywords(rs.getString("keywords"));
				outVO.setPosters(rs.getString("posters"));
				outVO.setReleaseDate(rs.getString("release_date"));
				outVO.setProdYear(rs.getString("prod_year"));
				outVO.setRate(rs.getDouble("rate"));
				outVO.setPlot(rs.getString("plot"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setNowShowing(rs.getString("now_showing"));
				outVO.setTotal(rs.getInt("total_cnt"));
				outVO.setNum(rs.getInt("rnum"));
				
				list.add(outVO); // 영화 하나에 대한 정보가 담겨 있는 객체를 리스트에 더함
			}
			
		} catch(SQLException e) {
			LOG.debug("====================");
			LOG.debug("SQLException=" + e.getMessage());
			LOG.debug("====================");
		}finally {
			// 자원 반납 클래스에 있는 메소드를 불러와서 자원반납 시킴
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
			
		}
		
		return list; // 검색된 영화 전체를 객체 리스트로 반환
		
	}//--do_retrieve

	public int do_insert(DTO dto) {return 0;}
	public int do_update(DTO dto) {return 0;}
	public int do_delete(DTO dto) {return 0;}
	public DTO do_selectOne(DTO dto) {return null;}
	
}//--class
