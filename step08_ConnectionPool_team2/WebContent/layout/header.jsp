<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<header>
  <h1>Servlet / JSP</h1>
  <p>Dept Practice Header</p>
  <div align="right">
  		<c:if test="${not empty sessionScope.userId}">
  			<span style="font-size:12pt;">${sessionScope.userName} 님 환영합니다.</span>
	  		<span style="font-size:12pt;"><input type="button" value="로그아웃" onclick="location.href='logout.do'"></span>
		</c:if>
		<c:if test="${not empty sessionScope.userId}">
		    <span style="font-size:12pt;">
		        <input type="button" value="회원정보" onclick="location.href='information?userId=${sessionScope.userId}'">
		    </span>
		</c:if>
	</div>
</header>
