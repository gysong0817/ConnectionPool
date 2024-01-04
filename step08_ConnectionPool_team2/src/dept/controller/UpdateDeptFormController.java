package dept.controller; 

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.dao.DeptDAO;
import dept.dto.Dept;

@WebServlet("/updateDeptForm.do")
public class UpdateDeptFormController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "errors/error.jsp";
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		Dept dept = null;
		
		try {
			dept = DeptDAO.getDeptByDeptno(deptno);
			
			if(dept != null) {
				request.setAttribute("dept", dept);
				url = "dept/updateDept.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			} else {
				request.setAttribute("error", "존재하지 않는 부서");
				request.getRequestDispatcher(url).forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("error", "부서 정보 검색 실패");
			request.getRequestDispatcher(url).forward(request, response);
		}
		
	}
}
