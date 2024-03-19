<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <script src="https://kit.fontawesome.com/0b4621b427.js" crossorigin="anonymous"></script>
    <title></title>
    <style>
        /*점보트론 세로폭 및 마진바툼 줄이기*/
        .jumbotron{
            padding-top:2rem;
            padding-bottom:1rem;            
            margin-bottom: .5rem;
            
            border-top-left-radius:0;
            border-top-right-radius:0;
        }
    </style>
</head>
<body>
	<!-- 네비게이션 바  -->
	<!--상단 고정-->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">   
      <span class="navbar-text">
        영규의 두번째 프로젝트
      </span>
      
      <!-- Toggler/collapsibe Button -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
       
        <!-- Links -->
        <ul class="navbar-nav mr-3">
      
          <c:if test="${empty sessionScope['USER-ID']}">
			   <li class="nav-item">
			       <a class="nav-link" href="${pageContext.request.contextPath}/Member/Join.do">회원가입 <i class="fas fa-user-plus"></i></a>
			   </li>
		  </c:if>
		  
		  <c:if test="${not empty sessionScope['USER-ID']}">
			  <span class="navbar-text">
	        	${sessionScope['USER-ID']}님 환영합니다
	      	  </span>
		  </c:if>
		  
		  <c:if test="${not empty sessionScope['USER-ID']}">
		  	   <li class="nav-item">
			       <a class="nav-link" href="${pageContext.request.contextPath}/Member/MyPage.do">마이페이지 <i class="fas fa-user"></i></a>
			   </li>
		  	   <li class="nav-item">
			       <a class="nav-link" href="${pageContext.request.contextPath}/Member/JoinEdit.do">회원정보수정 <i class="fas fa-user-cog"></i></a>
			   </li>			   			  
		  </c:if>
					                  
          <c:if test="${empty sessionScope['USER-ID']}">
	          <li class="nav-item">
	            <a class="nav-link" href="${pageContext.request.contextPath}/Member/Login.do">로그인 <i class="fa fa-user-o"></i></a>
	          </li>
          </c:if>
          
          <c:if test="${not empty sessionScope['USER-ID']}">
	          <li class="nav-item">
	            <a class="nav-link " href="${pageContext.request.contextPath}/Member/Logout.do">로그아웃 <i class="fa fa-user-minus"></i></a>
	          </li>
          </c:if>  
            
          <li class="nav-item">
            <a class="nav-link " href="${pageContext.request.contextPath}/BBS/List.do">게시판 <i class='fas fa-edit'></i></a>
          </li>     
        </ul>
      </div>
    </nav>