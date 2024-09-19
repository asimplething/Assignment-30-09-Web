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
 * Servlet implementation class ForgotPassService
 */
@WebServlet("/ForgotPassService")
public class ForgotPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEmailString = request.getParameter("email");
		RequestDispatcher dispatcher = null;
		
		try {
			CachedRowSet rowSet = UserDao.findUserByEmail(userEmailString);
			if (rowSet == null)
			{
				request.setAttribute("status", "nodatabase");
			}
			else if (!rowSet.next()) {
				request.setAttribute("status", "failed");
			} 
			else {
				request.setAttribute("status", "success");
				request.setAttribute("password", rowSet.getString("Password"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("status", "unknow");
			e.printStackTrace();
		} finally {
			dispatcher = request.getRequestDispatcher("restorepassword");
			dispatcher.forward(request, response);
		}
	}

}
