package assignment.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import assignment.project.dao.UserDao;

/**
 * Servlet implementation class RegisterService
 */
@WebServlet("/RegisterService")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		try {
			String rePassword = request.getParameter("re_password");
			String password = request.getParameter("password");
			if (rePassword == null || rePassword == "" || !rePassword.equals(password)) {
				request.setAttribute("status", "nopassword");
				dispatcher = request.getRequestDispatcher("register");
				dispatcher.forward(request, response);
				return;
			}
			String email = request.getParameter("email");
			CachedRowSet rowSet = UserDao.findUserByEmail(email);
			// check databse is connected
			if (rowSet == null) {
				request.setAttribute("status", "nodatabase");
				dispatcher = request.getRequestDispatcher("register");
				return;
			}
			//check if there is a registered email or not
			if (rowSet != null && rowSet.next()) {
				request.setAttribute("status", "existedemail");
				dispatcher = request.getRequestDispatcher("register");
				return;
			}
			
			String uname = request.getParameter("name");
			String stringTime = java.time.LocalDate.now().toString();

			boolean insertResult = UserDao.insertAccount(uname, email, password, stringTime);
			if (insertResult) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("login");
			} else {
				request.setAttribute("status", "nodatabase");
				dispatcher = request.getRequestDispatcher("register");
			}
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("status", "unknow");
			e.printStackTrace();
		} finally {
			dispatcher.forward(request, response);
		}
	}
}
