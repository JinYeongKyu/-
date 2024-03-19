package controller.bbs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PagingUtil;
import model.bbs.BBSDao;
import model.bbs.BBSDto;

@WebServlet(urlPatterns = "/BBS/List.do",initParams = {@WebInitParam(name = "PAGE-SIZE",value = "4" ),@WebInitParam(name = "BLOCK-PAGE",value = "3" )})
public class ListController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BBSDao dao = new BBSDao(getServletContext());	
		
		Map map = new HashMap();	
		String searchColumn = req.getParameter("searchColumn");
	    String searchWord = req.getParameter("searchWord");
		//null 체크
		if (searchColumn != null && searchWord != null) {
	        map.put("searchColumn", searchColumn);
	        map.put("searchWord", searchWord);
	    }
		//페이징
		PagingUtil.setMapForPaging(map, dao, req, this);
		int totalRecordCount=Integer.parseInt(map.get(PagingUtil.TOTAL_RECORD_COUNT).toString());
		int pageSize=Integer.parseInt(map.get(PagingUtil.PAGE_SIZE).toString());
		int blockPage=Integer.parseInt(map.get(PagingUtil.BLOCK_PAGE).toString());
		int nowPage=Integer.parseInt(map.get(PagingUtil.NOWPAGE).toString());
		int totalPage=Integer.parseInt(map.get(PagingUtil.TOTAL_PAGE).toString());
		String pagingString=PagingUtil.pagingBootStrapStyle(totalRecordCount, pageSize, blockPage, nowPage, req.getContextPath()+"/BBS/List.do?");	
		
		List<BBSDto> records= dao.selectList(map);
		dao.close();
		//포워드
		req.setAttribute("records", records);
		req.setAttribute("paging", pagingString);
		req.getRequestDispatcher("/WEB-INF/bbs/List.jsp").forward(req, resp);
	}
	
}
