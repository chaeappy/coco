<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
</head>
<body>
	<div></div>
	<div id="page">
		<div id="top"></div>
		<div class="ctr-p" id="two">
			<img src="<c:url value="/resources/img/logo.png" />" />
		</div>
		<div class="ctr-p" id="bottom">
			<button class="button" id="logBtn" onclick="loginPage()">로그인</button>
			<button class="button" onclick="signUpPage()">회원가입</button>
			<button class="button">ID / PW 찾기</button>
		</div>
	</div>
</body>
<script type="text/javascript">
	function loginPage() {
		location.href = "login";
	}

	function signUpPage() {
		location.href = "signUp"
	}


</script>
</html>
