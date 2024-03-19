<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${WHERE == 'INS' }">
		<c:set var="successMessage" value="등록되었습니다"/>
		<c:set var="failuerMessage" value="입력 실패했습니다"/>
		<c:set var="successUrl" value="/BBS/List.do"/>
	</c:when>
	<c:when test="${WHERE == 'EDT' }">
		<c:set var="successMessage" value="수정되었습니다"/>
		<c:set var="failuerMessage" value="수정 실패했습니다"/>
		<c:set var="successUrl" value="/BBS/List.do"/>
	</c:when>
	<c:when test="${WHERE == 'JOIN' }">
		<c:set var="successMessage" value="환영합니다."/>
		<c:set var="failuerMessage" value="필수 사항 미체크 또는 중복된 아이디가 존재합니다"/>
		<c:set var="successUrl" value="/Member/Login.do"/>
	</c:when>
	<c:when test="${WHERE == 'CHECK' }">
		<c:set var="failuerMessage" value="비밀번호가 일치하지 않습니다. 비밀번호를 다시 한번 확인해주세요"/>
	</c:when>
	<c:when test="${WHERE == 'WITH' }">
		<c:set var="successMessage" value="탈퇴처리되었습니다."/>
		<c:set var="failuerMessage" value="작성한 게시글을 삭제한 뒤에 다시 시도해 주세요"/>
		<c:set var="successUrl" value="/Member/Login.do"/>
	</c:when>
	<c:otherwise>	
		<c:set var="successMessage" value="삭제되었습니다"/>
		<c:set var="failuerMessage" value="삭제 실패했습니다"/>
		<c:set var="successUrl" value="/BBS/List.do"/>	
	</c:otherwise>
</c:choose>

<script>
	<c:choose>
		<c:when test="${SUCCFAIL == 1 }">
			alert("${successMessage}");
			location.replace("<c:url value="${successUrl }"/>");
		</c:when>
		<c:when test="${SUCCFAIL == 0 }">
			alert("${failuerMessage}");
			history.back();
		</c:when>
	</c:choose>
</script>