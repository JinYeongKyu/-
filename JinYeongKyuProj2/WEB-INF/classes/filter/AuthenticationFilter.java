package filter;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/BBS/List.do")
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req =(HttpServletRequest)request;			
		Object checkLogin=req.getSession().getAttribute("USER-ID");
		
		if(checkLogin==null) {
			HttpServletResponse resp=(HttpServletResponse)response;
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out= resp.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 게시판을 이용해주세요')");
			out.println("location.replace('"+req.getContextPath()+"/Member/Login.do')");
			out.println("</script>");
			return;
		}
		chain.doFilter(request, response);
			
	}

}
