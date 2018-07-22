<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Marksheet List</title>

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
				<h3>Marksheet List</h3>
				<c:if test="${param.act eq 'sv'}">
					<p class="success">Marksheet Saved Successfully. </p>
				</c:if>
				<c:if test="${param.act eq 'del'}">
					<p class="success">Marksheet Deleted Successfully!</p>
				</c:if>
				
				<table border="1" cellpadding="3" >
					<tr> 
						<th>SR</th>
						<th>ROLLNO</th>
						<th>STUDNET ID</th>
						<th>NAME</th>
						<th>PHYSICS</th>
						<th>CHEMISTRY</th>
						<th>MATHS</th>
					</tr>
					<c:if test="${empty marksheetList }">
						<tr > 
							<td align="center" colspan=8 class="error"> No Records Present. </td>
						</tr>
					</c:if>
					<c:forEach var="c" items="${marksheetList}" varStatus="st">
						<tr> 
						<td>${st.count}</td>
						<td>${c.rollno}</td>
						<td>${c.studentId}</td>
						<td>${c.name}</td>
						<td>${c.physics}</td>
						<td>${c.chemistry}</td>
						<td>${c.maths}</td>
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