package changeInformation;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.dao.EmpDAO;
import emp.dto.Emp;

@WebServlet("/getEmp.do")
public class GetEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "errors/error.jsp";
		
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		Emp emp = null;
		
		try {			
			emp = EmpDAO.getEmpByEmpno(empno);
			
			
			if (emp != null) {
				request.setAttribute("emp", emp);
				url = "emp/getEmp.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}else
				request.setAttribute("error", "존재하지 않는 부서");
				request.getRequestDispatcher(url).forward(request, response);			
		} catch (SQLException e) {
			request.setAttribute("error", "부서 정보 실패");
			request.getRequestDispatcher(url).forward(request, response);
		}
		
	}

}
