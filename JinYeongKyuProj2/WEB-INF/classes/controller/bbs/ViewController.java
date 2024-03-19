package controller.bbs;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bbs.BBSDao;
import model.bbs.BBSDto;

@WebServlet("/BBS/View.do")
public class ViewController extends HttpServlet {
	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
        String no = req.getParameter("no");

        BBSDao dao = new BBSDao(getServletContext());
        
        dao.updateHitCount(no);
        BBSDto record = dao.selectOne(no);
        dao.close();
        //내용 줄바꿈
        record.setContent(record.getContent().replace("\r\n", "<br/>"));
        //포워드
        req.setAttribute("record", record);
        req.getRequestDispatcher("/WEB-INF/bbs/View.jsp").forward(req, resp);
        
	    }
	 
}
