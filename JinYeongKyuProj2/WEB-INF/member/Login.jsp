<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/Top.jsp" %>
<style>
    body {
        background-color: #EFF2FB;
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        height: 100vh;
        margin: 0;
    }
</style>
<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="login-box bg-light text-black p-4 rounded-lg" style="width: 50%; height: 60%;">
        <h1 class="text-center mb-4">카페 로그인</h1>
        <span class="font-weight-bold text-danger">${empty requestScope['NOT-LOGIN'] ? '' : requestScope['NOT-LOGIN']}</span>
        <form class="form-signin d-flex flex-column align-items-center" action="${pageContext.request.contextPath}/Member/Login.do" method="POST">
            <label for="id" class="sr-only">아이디</label>
            <input type="text" id="id" name="id" class="form-control my-2" placeholder="아이디" required autofocus
                value="${empty param.id ? '' : param.id}" />
            <label for="pwd" class="sr-only">비밀번호</label>
            <input type="password" id="pwd" name="pwd" class="form-control my-2" placeholder="비밀번호" required
                value="${empty param.pwd ? '' : param.pwd}" />
            <button class="btn btn-success my-2" type="submit" style="width: 25%;">로그인</button>
        </form>
         <div class="mt-3 text-center">
            <p>계정이 없으신가요? <a href="${pageContext.request.contextPath}/Member/Join.do">회원가입</a></p>
        </div>
    </div>
</div>
<!--container-->
<%@ include file="/template/Footer.jsp" %>