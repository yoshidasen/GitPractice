package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
/**
 * Servlet implementation class Kadai1601RegisterConfirmServlet
 */
@WebServlet("/Kadai1601RegisterConfirmServlet")
public class Kadai1601RegisterConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kadai1601RegisterConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int id = 0;
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		int gender = Integer.parseInt(request.getParameter("gender"));
		int phone_number = Integer.parseInt(request.getParameter("phone_number"));
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		Member member = new Member(id,name, age, gender, phone_number, mail, password, null);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("input_data", member);
		
		String view = "WEB-INF/view/Kadai1601Confirm.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
