<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/template/Top.jsp" />
<div class="container" style="margin-top: 50px">
	<div class="jumbotron" style="background-color: #A9BCF5;">
    	<h1 style="color: #0B0B61;">남극빙수<small style="font-size: 70%;"> 공식 팬카페</small></h1>
	</div>
	<div class="text-right mb-2">
		<a href="<c:url value="/BBS/Write.do"/>"
			class="btn btn-success"><i class="fas fa-pencil-alt"></i> 글쓰기</a>
	</div>
	<table class="table User table-striped table-hover table-border text-center">
		<thead>
			<tr>
				<th class="col-1">번호</th>
				<th>제목</th>
				<th class="col-2">작성자</th>
				<th class="col-1">조회수</th>
				<th class="col-2">작성일</th>
			</tr>
		</thead>
		<tbody class="table-sm">
			<c:if test="${empty records }" var="isEmpty">
					<tr>
						<td colspan="6">등록된 게시글이 없습니다.</td>
					</tr>
				</c:if>		
			<c:if test="${not isEmpty}">
				<c:forEach var="record" items="${records }" >
					<tr>
						<td>${record.no}</td>
						<td class="text-left"><a href="<c:url value="/BBS/View.do?no=${record.no}"/>"><span><span style="color: black;">${record.title}</span></a></td>
						<td>${record.name}</td>
						<td>${record.hitCount}</td>
						<td>${record.postDate}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	${paging}
	<form class="form-inline justify-content-center" method="post">
		<select class="form-control" name="searchColumn">
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="name">작성자</option>
		</select> 
		<input type="text" class="form-control mx-2 my-2"
			placeholder="검색어를 입력하세요" name="searchWord" />
		<button type="submit" class="btn btn-primary">검색</button>
	</form> 
</div>
<%@ include file="/template/FooterMember.jsp" %>

