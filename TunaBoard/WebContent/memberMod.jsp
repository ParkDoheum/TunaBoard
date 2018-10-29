<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="common.css">
<title>홈</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<script>
	var msg = "${msg}";
	if(msg != "") {
		alert(msg);	
		clkFind();
	}
	
	function clkFind() {
		location.href= "memberFind";
	}
	
	function chkSubmit() {		
		var frm = document.frm;
				
		if(frm.custno.value == "") {
			alert("회원번호가 입력되지 않았습니다.");
			frm.custno.focus();
			return false;
			
		} else if (frm.custname.value == "") {
			alert("회원성명이 입력되지 않았습니다.");
			frm.custname.focus();
			return false;
			
		} else if (frm.phone.value == "") {
			alert("회원전화가 입력되지 않았습니다.");
			frm.phone.focus();
			return false;
			
		} else if (frm.address.value == "") {
			alert("회원주소가 입력되지 않았습니다.");
			frm.address.focus();
			return false;
			
		} else if (frm.joindate.value == "") {
			alert("가입일자가 입력되지 않았습니다.");
			frm.joindate.focus();
			return false;
			
		} else if (frm.grade.value == "") {
			alert("회원등급이 입력되지 않았습니다.");
			frm.grade.focus();
			return false;
			
		} else if (frm.city.value == "") {
			alert("도시코드가 입력되지 않았습니다.");
			frm.city.focus();
			return false;
		}
		return true;
	}
	
	
	</script>
	
<section class="container">
	<h3>홈쇼핑 회원 정보 수정</h3>
	<form name="frm" action="memberMod" method="post" onsubmit="return chkSubmit();">
		회원번호: <input type="text" name="custno" value="${vo.custno}" readonly><br>
		회원성명: <input type="text" name="custname" value="${vo.custname}"><br>
		회원전화: <input type="text" name="phone" value="${vo.phone}"><br>
		회원주소: <input type="text" name="address" value="${vo.address}"><br>
		가입일자: <input type="text" name="joindate" value="${vo.joindate}"><br>
		고객등급(A:VIP,B:일반,C:직원): <input type="text" name="grade" value="${vo.grade}" maxlength="1"><br>
		도시코드: <input type="text" name="city" value="${vo.city}" maxlength="2"><br>
		
		<input type="submit" value="수정">		
		<a href="memberFind"><input type="button" value="조회"></a>		
	</form>
	
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>