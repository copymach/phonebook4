<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>폰북4 PhoneBook4</title>
</head>
<body>

	<h1>전화번호 리스트 [phonebook4]</h1>

	<P>
		<a href="http://localhost:8088/phonebook4/phone/writeForm">연락처 추가하기(절대경로 absolute path)</a> 
		<br>
		<!-- /WEB-INF/views/WEB-INF/views/writeForm.jsp.jsp -->
		 <a href="/phonebook4/phone/writeForm">연락처 추가하기(상대경로 relative path)</a>
	</p>
	<p>model2 방식으로 만든 전화번호부4</p>
	<p>입력한 정보 내역입니다.</p>
	<p>최근에 입력한 순서대로 보여줍니다.</p>

	<c:forEach items="${personList}" var="personVo">
		<table border="1">
			<tr>
				<td>이름(name)</td>
				<td>${personVo.name}</td>
			</tr>
			<tr>
				<td>핸드폰(Hp)</td>
				<td>${personVo.hp}</td>
			</tr>
			<tr>
				<td>회사(Company)</td>
				<td>${personVo.company}</td>
			</tr>
			<tr>
				<td><a href="/phonebook4/phone/updateForm?personId=${personVo.personId }">수정</a></td>
				<td><a href="/phonebook4/phone/delete?personId=${personVo.personId }">삭제</a></td>
			</tr>
		</table>
		<br>
	</c:forEach>
	<br>
	<a href="/phonebook4/phone/writeForm">전화번호 추가하기</a>

</body>
</html>