<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/Top.jsp"%>
<div class="container" style="margin-top:50px">
    <div class="jumbotron" style="background-color: #A9BCF5;">
        <h1 style="color: #0B0B61;">마이페이지 <small>${sessionScope['USER-ID']}</small></h1>
    </div><!--jumbotron-->
    <table class="table table-bordered table-striped">
	    <tr>
	        <th class="bg-light text-black" style="font-size: 1.2em;">가입한 카페</th>
	    </tr>
	    <tr>
	        <td class="bg-light text-gray">
	            <a class="nav-link " href="${pageContext.request.contextPath}/BBS/List.do" style="color: black;">스트리머 남극빙수의 공식 팬카페</a>
	        </td>
	    </tr>
	</table>
</div><!--container-->
<%@ include file="/template/Footer.jsp"%>