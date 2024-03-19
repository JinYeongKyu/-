package controller.member;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bbs.BBSDao;

@WebServlet("/Member/Login.do")
public class LoginController extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
        req.getRequestDispatcher("/WEB-INF/member/Login.jsp").forward(req, resp);
        
    }
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
		String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        BBSDao dao = new BBSDao(getServletContext());
        boolean isMember = dao.isMember(id, pwd);
        dao.close();

        if (isMember) {
            req.getSession().setAttribute("USER-ID", id);
            req.getSession().setAttribute("USER-PWD", pwd);
            req.getRequestDispatcher("/WEB-INF/member/MyPage.jsp").forward(req, resp);
        } 
        else {
            req.setAttribute("NOT-LOGIN", "아이디 또는 비밀번호를 잘못 입력했습니다. 다시 확인해주세요.");
            req.getRequestDispatcher("/WEB-INF/member/Login.jsp").forward(req, resp);
        }
        
    }
	
}
