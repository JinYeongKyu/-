package controller.member;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.bbs.BBSDao;
import model.bbs.BBSDto;
import model.bbs.MemberDao;
import model.bbs.MemberDto;

@WebServlet("/Member/Withdrawal.do")
public class WithdrawalController extends HttpServlet {
	

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
        String id = req.getParameter("id");
        MemberDao dao = new MemberDao(getServletContext());
        MemberDto dto = dao.selectOne(id);
        dao.close();

        req.setAttribute("record", dto);
        req.getRequestDispatcher("/WEB-INF/member/JoinEdit.jsp").forward(req, resp);
        
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			String id = req.getParameter("id");
		
			MemberDao dao = new MemberDao(getServletContext());
	        MemberDto dto = new MemberDto();
			dto.setId(id);
			
			int deleteFlag=dao.delete(dto);
			dao.close();
			
			HttpSession session = req.getSession(false);

	        if (deleteFlag==1) {      
	            session.invalidate();
	        }
	        
			req.setAttribute("SUCCFAIL", deleteFlag);	    	
		    req.setAttribute("WHERE", "WITH");	    	
		    req.getRequestDispatcher("/WEB-INF/bbs/Message.jsp").forward(req, resp);
		
	}
	
}
