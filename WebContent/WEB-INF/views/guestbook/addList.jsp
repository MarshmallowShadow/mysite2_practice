<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.*"%>
<%@ page import="java.util.*"%>

<%
	List<GuestVo> gList = (List<GuestVo>)request.getAttribute("gList");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>방명록</title>
	
		<link href="./assets/css/mysite.css" rel="stylesheet" type="text/css">
		<link href="./assets/css/guestbook.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<div id="wrap">
	
			<div id="header" class="clearfix">
				<h1>
					<a href="./main?">MySite</a>
				</h1>
	
				<!-- 
				<ul>
					<li>황일영 님 안녕하세요^^</li>
					<li><a href="" class="btn_s">로그아웃</a></li>
					<li><a href="" class="btn_s">회원정보수정</a></li>
				</ul>
				-->	
				<ul>
					<li><a href="" class="btn_s">로그인</a></li>
					<li><a href="" class="btn_s">회원가입</a></li>
				</ul>
				
			</div>
			<!-- //header -->
	
			<div id="nav">
				<ul class="clearfix">
					<li><a href="">입사지원서</a></li>
					<li><a href="">게시판</a></li>
					<li><a href="">갤러리</a></li>
					<li><a href="">방명록</a></li>
				</ul>
			</div>
			<!-- //nav -->
		
			<div id="container" class="clearfix">
				<div id="aside">
					<h2>방명록</h2>
					<ul>
						<li>일반방명록</li>
						<li>ajax방명록</li>
					</ul>
				</div>
				<!-- //aside -->
	
				<div id="content">
					
					<div id="content-head" class="clearfix">
						<h3>일반방명록</h3>
						<div id="location">
							<ul>
								<li>홈</li>
								<li>방명록</li>
								<li class="last">일반방명록</li>
							</ul>
						</div>
					</div>
					<!-- //content-head -->
	
					<div id="guestbook">
						<form action="./gbc" method="post">
							<input type="hidden" name="action" value="add">
							<table id="guestAdd">
								<colgroup>
									<col style="width: 70px;">
									<col>
									<col style="width: 70px;">
									<col>
								</colgroup>
								<tbody>
									<tr>
										<th><label class="form-text" for="input-uname">이름</label></td>
										<td><input id="input-uname" type="text" name="name"></td>
										<th><label class="form-text" for="input-pass">패스워드</label></td>
										<td><input id="input-pass"type="password" name="password"></td>
									</tr>
									<tr>
										<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
									</tr>
									<tr class="button-area">
										<td colspan="4" class="text-center"><button type="submit">등록</button></td>
									</tr>
								</tbody>
								
							</table>
							<!-- //guestWrite -->
							<input type="hidden" name="action" value="add">
							
						</form>	
						
						<%for(int i=0; i<gList.size(); i++) { %>
							<table class="guestRead">
								<colgroup>
									<col style="width: 10%;">
									<col style="width: 40%;">
									<col style="width: 40%;">
									<col style="width: 10%;">
								</colgroup>
								<tr>
									<td><%=gList.get(i).getNo() %></td>
									<td><%=gList.get(i).getName() %></td>
									<td><%=gList.get(i).getRegDate() %></td>
									<td><a href="./gbc?action=deleteForm&no=<%=gList.get(i).getNo() %>">[삭제]</a></td>
								</tr>
								<tr>
									<td colspan=4 class="text-left"><%=gList.get(i).getContent() %></td>
								</tr>
							</table>
							<!-- //guestRead -->
						<%} %>			
					</div>
					<!-- //guestbook -->
				
				</div>
				<!-- //content  -->
			</div>
			<!-- //container  -->
	
			<div id="footer">
				Copyright ⓒ 2020 황일영. All right reserved
			</div>
			<!-- //footer -->
		</div>
		<!-- //wrap -->
	
	</body>

</html>