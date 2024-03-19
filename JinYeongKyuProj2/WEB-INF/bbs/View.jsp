<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 50px">
    <div class="jumbotron" style="background-color: #A9BCF5;">
		<h1 style="color: #0B0B61;">${record.title }</h1>
	</div>	  
    <table class="table User table-light- table-hover">
	    <tr>
	        <th class="bg-light text-black" style="font-size: 1.2em;">${record.name}</th>
	    </tr>
	    <tr>
	        <td class="bg-light text-gray">
	            ${record.postDate}, 글번호 ${record.no}, 조회수 ${record.hitCount}
	        </td>
	    </tr>
	    <tr>
	        <td class="bg-light text-gray">${record.content}</td>
	    </tr>
	</table> 	  
	<div class="text-center d-flex justify-content-center">   	    	    
	    <c:if test="${sessionScope['USER-ID'] eq record.id}">
	    	<a href="<c:url value='/BBS/Edit.do?no=${record.no}'/>" class="btn btn-success mx-1">수정</a>
	    </c:if> 	
	    <a href="<c:url value='/BBS/List.do'/>" class="btn btn-success mx-1">목록</a>  
	    <c:if test="${sessionScope['USER-ID'] eq record.id}">
	        <form action="<c:url value='/BBS/Delete.do'/>" method="post" class="mx-1">
	            <input type="hidden" name="no" value="${record.no}">
	            <button type="submit" class="btn btn-danger" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
	        </form>   
	    </c:if> 		     
	</div>		
</div>
<%@ include file="/template/FooterMember.jsp" %>

            

