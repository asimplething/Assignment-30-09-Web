package assignment.project.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

import assignment.project.dao.UserDao;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userEmailString = request.getParameter("email");
		String userPassString = request.getParameter("password");
		String rememberMeString = request.getParameter("remember-me");
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		CachedRowSet rowSet = UserDao.findUserByEmailAndPass(userEmailString, userPassString);
		try {
			if (rowSet == null || !rowSet.next()) {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login");
			} else {
				request.setAttribute("status", "success");
				session.setAttribute("email", rowSet.getString("Email"));
				session.setAttribute("rememberme", rememberMeString);
				dispatcher = request.getRequestDispatcher("index");
			}

		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("status", "unknow");
			dispatcher = request.getRequestDispatcher("login");
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}

}
