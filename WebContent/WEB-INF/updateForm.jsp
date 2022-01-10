<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="com.javaex.vo.PersonVo" %>
<%@ page import="com.javaex.dao.PhoneDao" %>
    
<%	
	//해당 인물의 코드만 받기위한 파라미터
	String str = request.getParameter ("id");
	int id = Integer.parseInt(str);
	
	//phoneDao의 getPerson사용해보기
	PhoneDao phoneDao = new PhoneDao();
	
	
%>  
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>[Phonebook2]</h1>
	
	<h2>전화번호 수정 폼(form)</h2>
	
	<p>전화번호를 수정하려면<br>
	   아래에 항목을 수정하고 "수정" 버튼을 누르세요.
	</p>
	
	<form action="/phonebook2/pbc" method="get">
   		이름(name) : <input type="text" name="name" value="<%=phoneDao.getPerson(id).getName()%>"><br>
   		핸드폰(hp) : <input type="text" name="hp" value="<%=phoneDao.getPerson(id).getHp()%>"><br>
   		회사(company) : <input type="text" name="company" value="<%=phoneDao.getPerson(id).getCompany()%>"><br>
   		코드(id) : <input type=text name="id" value="<%=id %>">  <br>
   <input type=text name="action" value="update">
   <button type="submit">수정</button>
   </form>
   
	

</body>
</html>