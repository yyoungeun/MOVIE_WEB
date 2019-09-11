package com.sunday.member.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.sunday.cmn.MessageVO;
/*
import com.hr.code.CodeDao;
import com.hr.code.CodeVO;*/
import com.sunday.cmn.StringUtil;
import com.sunday.member.dao.MemberVO;
import com.sunday.movie.domain.SearchVO;

/**
 * Servlet implementation class MemberController
 */
@WebServlet(description = "회원관리", urlPatterns = { "/login.do","/login.json" })
public class LoginControl extends HttpServlet {
	private final Logger LOG = Logger.getLogger(MemberService.class);
	
	private MemberService memberService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
    	memberService = new MemberService();
    }
    
    protected void do_find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.5 do_find");
    	MemberVO inVO = new MemberVO();
    	String userId = StringUtil.nvl(request.getParameter("user_id"),"");
    	String passwd = StringUtil.nvl(request.getParameter("passwd"),"");
    	String email = StringUtil.nvl(request.getParameter("find_email"),"");
    	
    	inVO.setUser_id(userId);
    	inVO.setPasswd(passwd);
    	inVO.setEmail(email);
    	
    	String msg ="";
    	
    	LOG.debug("03.2 param: " + inVO);
    	
    	MemberVO outVO = this.memberService.do_find(inVO);
    	LOG.debug("03.3 outVO: " + outVO);
    	
    	if(outVO.getUser_id() != ""){
    		
    	String host = "smtp.naver.com";
		String user = "1150amy@naver.com";
		String password = "@song7014";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(user,password);
			}
		});
		
		try{
			//4. Message클래스를 사용하여 수신자와 내용, 제목, 메시지를 작성한다.
			MimeMessage message = new MimeMessage(session);
			//4.1 from
			message.setFrom(new InternetAddress(user));
			//4.2 to
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(inVO.getEmail()));
			//4.3 제목
			message.setSubject("SUNDAY 회원 정보입니다.");
			//4.4 내용
			message.setText("회원님의 아이디는 "+ outVO.getUser_id() + " // " + "회원님의 비밀번호는 " + outVO.getPasswd());
			//5. Transport 클래스를 사용하여 작성한 메시지를 전달한다.
			Transport.send(message);
			
		}catch(MessagingException me){
			LOG.debug("====================================");
			LOG.debug("MessagingException: " + me.toString());
			LOG.debug("====================================");
		}
		LOG.debug("Send success.");	
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
	    	dispatcher.forward(request, response);
		
    	}else{
    		LOG.debug("fail");
    		//msg = "가입된 정보가 없습니다.";
    		
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/find_fail.jsp");
        	dispatcher.forward(request, response);
    		
    	}
    }
    
    protected void do_logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.4 do_logout: ");
    	
    	HttpSession httpSession = request.getSession();
    	if(null != httpSession){
    		LOG.debug("03.1 httpSession: " + httpSession);
    		httpSession.removeAttribute("user");
    		httpSession.removeAttribute("id");
    		httpSession.removeAttribute("passwd");
    		httpSession.removeAttribute("name");
    		httpSession.removeAttribute("email");
    		httpSession.removeAttribute("lvl");
    		httpSession.removeAttribute("reg_dt");
    		httpSession.removeAttribute("mod_dt");
    		
    		httpSession.invalidate();
    		LOG.debug("03.2 httpSession: " + httpSession);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        	dispatcher.forward(request, response);
    	}
    	
    }
    
    
    protected void do_login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_login: ");
    	MemberVO inVO = new MemberVO();
    	String userId = StringUtil.nvl(request.getParameter("user_id"),"");
    	String passwd = StringUtil.nvl(request.getParameter("passwd"),"");
    	inVO.setUser_id(userId);
    	inVO.setPasswd(passwd);
    	
    	LOG.debug("03.2 inVO: " + inVO);
    	int checkMsg = memberService.loginCheck(inVO);
    	MemberVO outVO = new MemberVO();
    	
    	if(checkMsg == 1){
    		outVO = memberService.do_selectOne(inVO);
    		//session
    		HttpSession session = request.getSession();
    		session.setAttribute("user", outVO);
    		session.setAttribute("id", outVO.getUser_id());
    		session.setAttribute("passwd", outVO.getPasswd());
    		session.setAttribute("name", outVO.getName());
    		session.setAttribute("email", outVO.getEmail());
    		session.setAttribute("lvl", outVO.getLvl());
    		session.setAttribute("reg_dt", outVO.getReg_dt());
        	session.setAttribute("mod_dt", outVO.getMod_dt());
    		
    		
    		LOG.debug("03.3 outVO: " + outVO);
    	}
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	String msg= "";
    	String gsonString ="";
    	
    	int result = this.memberService.loginCheck(inVO);
    	
    	HttpSession session = request.getSession(true);
    	LOG.debug("03.2 flag: " + result);
    
    	if(result == 1){
    		msg = inVO.getUser_id()+"가 로그인 되었습니다.";
    		LOG.debug(msg);
    	}else if(result == 2){
    		msg="아이디가 틀렸습니다.";
    		LOG.debug(msg);
    	}else{
    		msg="비밀번호가 틀렸습니다.";
    		LOG.debug(msg);
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(result),msg));
    	LOG.debug("03.4 gsonString: " + gsonString);
    	out.print(gsonString);
    }

    protected void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("02 doServiceHandler()");
    	request.setCharacterEncoding("UTF-8");
    	
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	LOG.debug("02.1 workDiv: " + workDiv);
    	
    	switch(workDiv){
    	
    		case "do_find":
    			do_find(request,response);
    		break;
    	
	    	case "do_login":
				do_login(request,response);
			break;
			
			case "do_logout":
				do_logout(request,response);
			break;
			
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doGet()");
		LOG.debug("01.1 memberService: " + memberService);
		doServiceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doPost()");
		doServiceHandler(request, response);
	}
}
