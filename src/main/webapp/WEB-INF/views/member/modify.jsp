<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보 수정</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<h1>회원정보 수정</h1>
		<form action="/member/modify.do" method="POST">
		<fieldset>
			<legend>정보 수정</legend>
			<ul>
				<li>
					<label>아이디</label>				
					<input type="text" name="memberId" value="${memberId }">
				</li>
				<li>
					<label>비밀번호 *</label>				
					<input type="password" name="memberPw" >
				</li>
				<li>
					<label>이름</label>				
					<input type="text" name="memberName" value="${memberName }" >
				</li>
				<li>
					<label>나이</label>				
					<input type="text" name="memberAge" value="${memberAge }" >
				</li>
				<li>
					<label>성별</label>				
					남<input type="radio" name="memberGender" value="M" >
					여<input type="radio" name="memberGender" value="F" >
				</li>
				<li>
					<label>이메일</label>				
					<input type="text" name="memberEmail" value="${memberEmail }">
				</li>
				<li>
					<label>전화번호</label>				
					<input type="text" name="memberPhone" value=${memberPhone }>
				</li>
				<li>
					<label>주소</label>				
					<input type="text" name="memberAddress" value="${memberAddress }">
				</li>
				<li>
					<label>취미</label>				
					<input type="text" name="memberHobby" value="${memberHobby }">
				</li>
			</ul>	
		</fieldset>
		<div>
				<input type="submit" value="수정">
				<input type="reset" value="취소">
			</div>
		</form>
	</body>
</html>