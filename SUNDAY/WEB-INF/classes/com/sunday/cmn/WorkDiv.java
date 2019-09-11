package com.sunday.cmn;
import java.util.List;

public interface WorkDiv {

	/**
	 * 
	 * @Method Name  : do_insert
	 * @작성일   : 2019. 6. 25.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 등록 메소드
	 * @param dto
	 * @return
	 */
	public int do_insert(DTO dto);
	
	/**
	 * 
	 * @Method Name  : do_update
	 * @작성일   : 2019. 6. 25.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 수정 메소드
	 * @param dto
	 * @return
	 */
	public int do_update(DTO dto);
	
	/**
	 * 
	 * @Method Name  : do_delete
	 * @작성일   : 2019. 6. 25.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 삭제 메소드
	 * @param dto
	 * @return
	 */
	public int do_delete(DTO dto);
	
	/**
	 * 
	 * @Method Name  : do_selectOne
	 * @작성일   : 2019. 6. 25.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 단건조회 메소드
	 * @param dto
	 * @return DTO
	 */
	public DTO do_selectOne(DTO dto);
	
	/**
	 * 
	 * @Method Name  : do_retrieve
	 * @작성일   : 2019. 6. 25.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : 목록조회 메소드
	 * @param dto
	 * @return List<?>
	 */
	public List<?> do_retrieve(DTO dto);
}
