<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 연락처 수정 </title>
</head>
<body>
	<h1>[Phonebook3]</h1>
	<h2>전화번호 수정폼</h2>
	<p>전화번호를 수정하려면 아래 항목을 기입하고 수정 버튼을 누르세요</p>

	<form action="/phonebook3/phone/update" method="get">
		이름(name) : <input type="text" name="name" value="${personVo.name }"> 
		<br> 
		핸드폰(hp) : <input type="text" name="hp" value="${personVo.hp }"> 
		<br>
		회사(company) <input type="text" name="company" value="${personVo.company }"> 
		<br> 
		저장ID <input type="text" name="personId" value="${personVo.personId }"> 
		<br> 
		히든처리할 액션은 생략 <!-- <input type="text" name="action" value="update"> -->
		<button type="submit">수정</button>
		<!-- http://localhost:8088/phonebook3/phone/update?name=2233&hp=33&company=33&id=34 -->
	</form>
	<br>
	<a href="http://localhost:8088/phonebook3/phone/list">리스트 (절대경로)</a>
	<br>
	<a href="/phonebook3/phone/list">리스트로 돌아가기 (상대경로)</a>
</body>
</html>