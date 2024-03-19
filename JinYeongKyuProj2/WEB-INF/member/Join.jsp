<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/template/Top.jsp" %>    

	<div class="container mt-5">
	    <div class="jumbotron" style="background-color: #A9BCF5;">
		<h1 style="color: #0B0B61;">회원가입</h1>
	</div>

    <div class="card border mt-3">
        <div class="card-body">
            <form action="<c:url value="/Member/Join.do"/>" method="post">
            	 <div style="text-align: right; margin-bottom: 10px;">
       				 <span><span style="color: red; font-weight: bold">*</span> 필수 입력사항</span>
    			</div>
                <div class="form-group">
				    <label for="name" style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">이름<span style="color: red;">*</span></label>
				    <input type="text" class="form-control" id="name" name="name" value="${param.name}" placeholder="이름을 입력하세요">
				</div>

                <div class="form-group">
                    <label for="id" style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">아이디<span style="color: red;">*</span></label>
                    <input type="text" class="form-control" id="id" name="id" value="${param.id}" placeholder="아이디를 입력하세요">
                </div>

                <div class="form-group">
                    <label for="pwd" style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">비밀번호<span style="color: red;">*</span></label>
                    <input type="password" class="form-control" id="pwd" name="pwd" value="${param.pwd}" placeholder="비밀번호를 입력하세요">
                </div>

                <div class="form-group">
                    <label for="pwdcheck" style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">비밀번호 확인<span style="color: red;">*</span></label>
                    <input type="password" class="form-control" id="pwdcheck" name="pwdcheck" value="${param.pwdcheck}" placeholder="비밀번호를 한번 더 입력하세요">
                </div>

                <div class="form-group">
                    <label style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">성별<span style="color: red;">*</span></label>
                    <div class="form-check">
                        <input type="radio" class="form-check-input" name="gender" <c:if test="${param.gender=='M'}">checked</c:if> value="M" id="male1">
                        <label class="form-check-label" for="male1">남자</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" class="form-check-input" name="gender" <c:if test="${param.gender=='F'}">checked</c:if> value="F" id="female1">
                        <label class="form-check-label" for="female1">여자</label>
                    </div>
                </div>

                <div class="form-group">
                    <label style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">관심게임장르(복수선택가능)<span style="color: red;">*</span></label>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" name="inters" <c:if test="${param.inters == 'FPS'}">checked</c:if> value="FPS" id="FPS1">
                        <label class="form-check-label" for="FPS1">FPS</label>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" name="inters" <c:if test="${param.inters == 'AOS'}">checked</c:if> value="AOS" id="AOS1">
                        <label class="form-check-label" for="AOS1">AOS</label>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" name="inters" <c:if test="${param.inters == '오픈월드'}">checked</c:if> value="오픈월드" id="OPEN1">
                        <label class="form-check-label" for="OPEN1">오픈월드</label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="education" style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">카페 유입경로</label>
                    <select name="education" class="custom-select">
                       <option value="" selected>유입된 경로를 선택해주세요</option>                   
                        <option value="포털 사이트 검색" <c:if test="${record.education=='포털 사이트 검색'}">selected</c:if>>포털 사이트 검색</option>
                        <option value="유튜브 및 방송 유입" <c:if test="${record.education=='유튜브 및 방송 유입'}">selected</c:if>>유튜브 및 방송 유입</option>
                        <option value="지인 추천" <c:if test="${record.education=='지인 추천'}">selected</c:if>>지인 추천</option>
                        <option value="기타" <c:if test="${record.education=='기타'}">selected</c:if>>기타</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="selfintroduce" style="display: block; font-size: 1.2em; font-weight: bold; color: #2C3E50; margin-bottom: 8px;">카페에 가입하신 이유와 자기소개</label>
                    <textarea class="form-control" id="selfintroduce" rows="5" name="selfintroduce" placeholder="카페에 가입하신 이유와 자기소개를 간단히 작성해주세요">${param.selfintroduce}</textarea>
                </div>

                <button type="submit" class="btn btn-primary">확인</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="/template/Footer.jsp" %>