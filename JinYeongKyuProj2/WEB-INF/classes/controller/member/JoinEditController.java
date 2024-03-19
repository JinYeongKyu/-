package controller.member;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bbs.BBSDao;
import model.bbs.BBSDto;
import model.bbs.MemberDao;
import model.bbs.MemberDto;

@WebServlet("/Member/JoinEdit.do")
public class JoinEditController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String id = (String) req.getSession().getAttribute("USER-ID");
		        
        MemberDao dao = new MemberDao(getServletContext());
        MemberDto dto = dao.selectOne(id);      
        //관심사항 체크 
        String[] inters = dto.getInters().split(",");
        Set<String> selectedInters = new HashSet(Arrays.asList(inters));
        Map<String, Boolean> intersMap = new HashMap();
        intersMap.put("FPS", selectedInters.contains("FPS"));
        intersMap.put("AOS", selectedInters.contains("AOS"));
        intersMap.put("오픈월드", selectedInters.contains("오픈월드"));
        dao.close();
        //포워드
        req.setAttribute("record", dto);
        req.setAttribute("intersMap", intersMap);
        req.getRequestDispatcher("/WEB-INF/member/JoinEdit.jsp").forward(req, resp);
        
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        int affected;

        String pwd = req.getParameter("pwd");
        String pwdcheck = req.getParameter("pwdcheck");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String gender = req.getParameter("gender");       
        String education = req.getParameter("education");
        String selfintroduce = req.getParameter("selfintroduce");
        String[] inter = req.getParameterValues("inters");
        //널 체크
        if(inter==null) {
        	req.setAttribute("SUCCFAIL", 0);
        	req.setAttribute("WHERE", "JOIN");
        	req.getRequestDispatcher("/WEB-INF/bbs/Message.jsp").forward(req, resp);
        }   
        
        String inters = Arrays.stream(inter).collect(Collectors.joining(","));   
        
        MemberDao dao = new MemberDao(getServletContext());
        MemberDto dto = new MemberDto();
                
        dto.setPwd(pwd);
        dto.setName(name);
        dto.setId(id);
        dto.setGender(gender);
        dto.setInters(inters);
        dto.setEducation(education);
        dto.setSelfintroduce(selfintroduce);      
        affected = dao.update(dto);
        dao.close();
        //비밀번호 확인
        if(!pwd.equals(pwdcheck)) {
        	req.setAttribute("SUCCFAIL", 0);
        	req.setAttribute("WHERE", "CHECK");
        	req.getRequestDispatcher("/WEB-INF/bbs/Message.jsp").forward(req, resp);
        }
        else {
	        req.setAttribute("SUCCFAIL", affected);  	
	    	req.setAttribute("WHERE", "EDT");  	
	    	req.getRequestDispatcher("/WEB-INF/bbs/Message.jsp").forward(req, resp);
        }

    }
	
}