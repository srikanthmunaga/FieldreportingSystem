package smscls;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class a1
 */
public class Pleasewait1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pleasewait1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setAttribute("username", request.getAttribute("name"));
		HttpSession ses=request.getSession(false);
		String huma_id[] = request.getParameterValues("huma_id");

		System.out.println(huma_id.length);
		String areaList = "''";
		String unitList = "''";
		int areacount=huma_id.length;
		for (String name : huma_id) {
			if (name != null)
				areaList += ",'" + name + "'";
		}	
		ses.setAttribute("areacount",areacount);
		//ses.setAttribute("oddays",oddays);
		ses.setAttribute("areaList", areaList);

		RequestDispatcher rd=request.getRequestDispatcher("/pleasewait.jsp");
		System.out.println("before farwording....");
		rd.forward(request, response);
		System.out.println("after farwording....");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
