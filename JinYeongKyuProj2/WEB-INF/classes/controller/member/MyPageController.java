package controller.member;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bbs.BBSDao;

@WebServlet("/Member/MyPage.do")
public class MyPageController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
        req.getRequestDispatcher("/WEB-INF/member/MyPage.jsp").forward(req, resp);
        
	}
	
}
