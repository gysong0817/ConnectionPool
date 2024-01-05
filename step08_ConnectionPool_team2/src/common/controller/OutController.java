package common.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emp.dao.EmpDAO;
import emp.dto.Emp;

@WebServlet("/out.do")
public class OutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		String url = "errors/error.jsp";
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		Emp emp = null;
		boolean result = false;
		
		try {
			emp = EmpDAO.getEmpByEmpno(empno);
			
			if(emp == null) {
				request.setAttribute("error", "존재하지 않는 사원");
				request.getRequestDispatcher(url).forward(request, response);
			} else {
				result = EmpDAO.deleteEmpByEmpno(empno);
			}
			
			if(result) {
				session.invalidate();
				session = null;
				
				url = "outComplete.jsp";
				response.sendRedirect(url);
				return;
			} else {
				request.setAttribute("error", "사원 삭제 실패");
				request.getRequestDispatcher(url).forward(request, response);
			}
			
		} catch (SQLException e) {
			request.setAttribute("error", "사원 삭제 실패");
			request.getRequestDispatcher(url).forward(request, response);
		}
		
	}
}
