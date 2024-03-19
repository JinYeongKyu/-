package controller.bbs;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bbs.BBSDao;
import model.bbs.BBSDto;


@WebServlet("/BBS/Write.do")
public class WriteController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        req.getRequestDispatcher("/WEB-INF/bbs/Write.jsp").forward(req, resp);
        
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        int affected;

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("USER-ID");
        
        BBSDao dao = new BBSDao(getServletContext());
        BBSDto dto = new BBSDto();
        
        dto.setContent(content);
        dto.setTitle(title);
        dto.setId(id);
        affected = dao.insert(dto);
        dao.close();
        
        req.setAttribute("SUCCFAIL", affected);   	
    	req.setAttribute("WHERE", "INS");    	
    	req.getRequestDispatcher("/WEB-INF/bbs/Message.jsp").forward(req, resp);

    }
	
}

