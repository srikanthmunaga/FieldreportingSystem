package frs_cls;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public final class Sessioncheck extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// //System.out.println("inside the doPost method");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control", "no-cache"); 
		
		String data = "";
		// //System.out.println("inside the doPost method of session check");
		 ////System.out.println("inside the session check and user="+((HttpServletRequest) request).getSession().getAttribute("user"));

		if (((HttpServletRequest) request).getSession().getAttribute("username") == null) {
			data = "Sorry the current session is expired";
			// response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to
			// login page.
		} else // if (((HttpServletRequest)
				// request).getSession().getAttribute("user") != null)
		{
			data = "OKThe current session is not expired";
		}
		data = data.replace("null", "");
		out.println(data);// mandatery...here works as return responseObject;
	}
}
