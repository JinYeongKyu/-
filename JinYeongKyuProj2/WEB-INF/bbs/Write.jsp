<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 50px">
    <div class="jumbotron" style="background-color: #A9BCF5;">
        <h1 style="color: #0B0B61;">카페 글쓰기</h1>
    </div>
    <!--jumbotron-->
    <form method="post" action="<c:url value="/BBS/Write.do"/>">
        <div class="form-group">
            <label for="title" style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">제목</label>
            <input type="text" class="form-control" placeholder="제목을 입력하세요" name="title"
                value="${param.title}">
        </div>
        <div class="form-group">
            <label for="title" style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">내용</label>
            <textarea class="form-control" rows="5" name="content" placeholder="내용을 입력하세요">${param.content}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">등록</button>
    </form>
</div>
<!--container-->
<%@ include file="/template/FooterMember.jsp"%>
