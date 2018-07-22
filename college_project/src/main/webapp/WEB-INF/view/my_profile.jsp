<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Profile</title>

<s:url var="url_css" value="/static/css/style.css" />

<link href="${url_css}" rel="stylesheet" type="text/css" />
</head>

<s:url var="url_bg" value="/static/images/bg.jpg" />
<body background="${url_bg}">
	<table border="1" width="100%" align="center">
		<tr>
			<td height="80px">
				<!-- This is for Header -->
				<jsp:include page="include/header.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td height="25px">
				<!-- This is for menu -->
				<jsp:include page="include/menu.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td height="350px" valign="top">
				<!-- This is for form -->
				<h3>Profile</h3>
				<c:if test="${param.act eq 'sv'}">
					<p class="success">Marksheet Saved Successfully. </p>
				</c:if>
				<c:if test="${param.act eq 'del'}">
					<p class="success">Marksheet Deleted Successfully!</p>
				</c:if>
				
				<table border="1" cellpadding="3" >
					<tr> 
						<th>FIRST NAME</th>
						<th>LAST NAME</th>
						<th>USERNAME</th>
						<th>MOBILE</th>
						<th>GENDER</th>
					</tr>
					<c:if test="${empty userList }">
						<tr > 
							<td align="center" colspan=8 class="error"> No Records Present. </td>
						</tr>
					</c:if>
					
					<c:forEach var="c" items="${userList}" varStatus="st">
						<tr> 
						<td>${c.firstName}</td>
						<td>${c.lastName}</td>
						<td>${c.login}</td>
						<td>${c.mobile}</td>
						<td>${c.gender}</td>
						</tr>
					</c:forEach>


				</table>
			</td>
		</tr>
		
		<tr>
			<td height="25px">
				<!-- This is for footer -->
				<jsp:include page="include/footer.jsp"></jsp:include>
			</td>
		</tr>

	</table>
</body>

</html>