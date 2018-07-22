<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Studnet List</title>

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
				<h3>Stundent List</h3>
				<c:if test="${param.act eq 'sv'}">
					<p class="success">Student Saved Successfully. </p>
				</c:if>
				<c:if test="${param.act eq 'del'}">
					<p class="success">Studnet Deleted Successfully!</p>
				</c:if>
				
				<table border="1" cellpadding="3" >
					<tr> 
						<th>SR</th>
						<th>FIRST NAME</th>
						<th>LAST Name</th>
						<th>MOBILE</th>
						<th>EMAIL</th>
						<th>COLLEGE ID</th>
						<th>ACTION</th>
					</tr>
					<c:if test="${empty studentList }">
						<tr > 
							<td align="center" colspan=8 class="error"> No Records Present. </td>
						</tr>
					</c:if>
					<c:forEach var="c" items="${studentList}" varStatus="st">
						<tr> 
						<td>${st.count}</td>
						<td>${c.firstName}</td>
						<td>${c.lastName}</td>
						<td>${c.mobile}</td>
						<td>${c.email}</td>
						<td>${c.collegeId}</td>
						<s:url var="url_del"  value="/student/del_student">
							<s:param name="sid" value="${c.studentId}"/>
						</s:url>
						<s:url var="url_edit"  value="/student/edit_student">
							<s:param name="sid" value="${c.studentId}"/>
						</s:url>
						<td> <a href="${url_edit}">Edit</a> | <a href="${url_del}">Delete</a> </td>
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