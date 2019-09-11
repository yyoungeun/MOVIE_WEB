/**
 * @Class Name : MessageVO.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019. 7. 23.           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2019. 7. 23. 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 */
package com.sunday.cmn;

/**
 * @author sist
 *
 */
public class MessageVO extends DTO {
	private String msgId;
	private String msgContents;
	//msgId > 0: 성공
	//msgId <=0: 실패
	
	public MessageVO(){}
	
	public MessageVO(String msgId, String msgContents) {
		super();
		this.msgId = msgId;
		this.msgContents = msgContents;
	}
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getMsgContents() {
		return msgContents;
	}
	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}

	@Override
	public String toString() {
		return "MessageVO [msgId=" + msgId + ", msgContents=" + msgContents + ", toString()=" + super.toString() + "]";
	}
	
}
