package smscls;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SMStoCustArea
 */
public class SMStoCustArea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SMStoCustArea() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("Msr debug - 1");
		HttpSession ses=request.getSession();
		String oddays=request.getParameter("oddays");
		String area_lang=request.getParameter("area_lang");
		ses.setAttribute("oddays",oddays);
		ses.setAttribute("area_lang",area_lang);
		RequestDispatcher rd=request.getRequestDispatcher("/SMStoCustSelectArea.jsp");
		System.out.println("before farwording....");
		rd.forward(request, response);


	}

}
