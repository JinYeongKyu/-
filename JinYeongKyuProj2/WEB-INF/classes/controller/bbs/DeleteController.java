package controller.bbs;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.bbs.BBSDao;
import model.bbs.BBSDto;

@WebServlet("/BBS/Delete.do")
public class DeleteController extends HttpServlet {
	

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
        String no = req.getParameter("no");
        BBSDao dao = new BBSDao(getServletContext());
        BBSDto dto = dao.selectOne(no);
        dao.close();

        req.setAttribute("record", dto);
        req.getRequestDispatcher("/WEB-INF/bbs/View.jsp").forward(req, resp);
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			String no = req.getParameter("no");
		
			BBSDao dao = new BBSDao(getServletContext());
	        BBSDto dto = new BBSDto();
			dto.setNo(no);
			
			int deleteFlag=dao.delete(dto);
			dao.close();
			
			req.setAttribute("SUCCFAIL", deleteFlag);	    	
		    req.setAttribute("WHERE", "DEL");	    	
		    req.getRequestDispatcher("/WEB-INF/bbs/Message.jsp").forward(req, resp);
		
	}
}
