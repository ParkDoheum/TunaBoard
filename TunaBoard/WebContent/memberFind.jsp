<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dbpkg.MemberVO" %>    
<%
	List<MemberVO> list = (List<MemberVO>)request.getAttribute("list"); 
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="common.css">
<title>홈</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<section class="container">
	<h3>회원목록조회/수정</h3>
	<table>
		<tr>
			<th>회원번호</th>
			<th>회원성명</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>가입일자</th>
			<th>고객등급</th>
			<th>거주지역</th>
		</tr>
		<%
			if (list != null && list.size() > 0) {
				for(MemberVO vo : list) {
		%>				
			<tr>
				<td>
					<a href="memberMod?custno=<%=vo.getCustno()%>"><%=vo.getCustno()%></a>
				</td>
				<td><%=vo.getCustname() %></td>
				<td><%=vo.getPhone() %></td>
				<td><%=vo.getAddress() %></td>
				<td><%=vo.getJoindate() %></td>
				<td><%=vo.getGrade() %></td>
				<td><%=vo.getCity() %></td>
			</tr>
		<%				
				}	
			}
		%>
	</table>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>