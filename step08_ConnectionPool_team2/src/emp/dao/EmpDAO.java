package emp.dao; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.util.DBUtil;
import dept.dto.Dept;
import emp.dto.Emp;

public class EmpDAO {
	// getEmpByEmpnoAndEname
	// Query : SELECT * FROM emp WHERE empno = ? AND ename = ?
	public static Emp getEmpByEmpnoAndEname(int empno, String ename) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Emp emp = null;
		
		String sql = "SELECT * FROM emp WHERE empno = ? AND ename = ?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empno);
			pstmt.setString(2, ename);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				emp = new Emp(
								rset.getInt(1),			// empno
								rset.getString(2),		// ename
								rset.getString(3),		// job
								rset.getInt(4),			// mgr
								rset.getDate(5),	// hiredate
								rset.getFloat(6),		// sal
								rset.getInt(7),			// comm
								rset.getInt(8)			// deptno
						);
			}
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		
		return emp;
	}
	
	public static Emp getEmpByEmpno(int empno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Emp emp = null;
		
		String sql = "SELECT * FROM emp WHERE empno = ?";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				emp = new Emp(
							rset.getInt(1),
							rset.getString(2),
							rset.getString(3),
							rset.getInt(4),
							rset.getDate(5),
							rset.getFloat(6),
							rset.getInt(7),
							rset.getInt(8));
			}
			
		} finally {
			DBUtil.close(rset, pstmt, con);
		}
		
		
		return emp;
		
	}
	
	public static boolean insertEmp(Emp emp) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String sql = "INSERT INTO emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setDate(5, emp.getHiredate());
			pstmt.setFloat(6, emp.getSal());
			pstmt.setInt(7, emp.getComm());
			pstmt.setInt(8, emp.getDeptno());
			
			result = pstmt.executeUpdate();
			
			if(result != 0) {
				return true;
			}
			
		} finally {
			DBUtil.close(pstmt, con);
		}
		
		return false;
	}
	
}
