package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <b>AddCookiesServlet</b><br>
 * Servlet is needed to add cookies to the browser based on the selected kunta value in index.jsp
 * Redirects to the questions.jsp
 *
 */
@WebServlet("/add_cookies")
public class AddCookiesServlet extends HttpServlet {

	/**
	 * serialVersionUID is used to verify compatibility
	 * Created and calculated automatically if not specified
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @deprecated
	 * cookieCount was used as a debug variable
	 */
	@Deprecated
	private static int cookieCount;
	
	public AddCookiesServlet() {
		
	}
	/**
	 * @deprecated 
	 * Currently doPost is used to add cookies
	 * @param request
	 * @param response
	 * @exception ServletException
	 * @exception IOException 
	 */
	@Deprecated
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "Cookie"+(++cookieCount);
		String value = String.valueOf(System.currentTimeMillis());
		Cookie cookie = new Cookie(name, value);
		response.addCookie(cookie);
		response.getWriter().println("Cookies have been baked succesfully!");
	}
	/**
	 * doPost metgod gets the kunta variable from index.jsp.
	 * Adds the value to the browser cookies and redirects the user to the questions page.
	 * @param request
	 * @param response
	 * @exception ServletException
	 * @exception IOException
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String _kunta = request.getParameter("kunta");
    	System.out.println(_kunta);
    	Cookie cookie = new Cookie("Kunta", _kunta);
    	response.addCookie(cookie);
    	response.sendRedirect("/questions");
    }
	
}