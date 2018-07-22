<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Registration</title>

<s:url var="url_css" value="/static/css/style.css" />

<link href="${url_css}" rel="stylesheet" type="text/css" />
</head>

<s:url var="url_bg" value="/static/images/bg.jpg" />
<body background="${url_bg}">
	<table border="1" width="80%" align="center">
		<tr>
			<td height="80px">
				<!-- This is for Header --> <jsp:include page="include/header.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td height="25px">
				<!-- This is for menu --> <jsp:include page="include/menu.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td height="350px" valign="top">
				<!-- This is for form -->
				<h3>Student Registration Form</h3> <c:if test="${err!=null}">
					<p class="error">${err}</p>
				</c:if> <s:url var="url_ssave" value="/student/save_student"></s:url> <f:form
					action="${url_ssave}" modelAttribute="command">
					<form:hidden path="studentId" />
					<table border="1">
						<tr>
							<td>First Name</td>
							<td><f:input path="firstName" /></td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td><f:input path="lastName" /></td>
						</tr>
						<tr>
							<td>Mobile</td>
							<td><f:input path="mobile" /></td>
						</tr>
						<tr>
							<td>Email</td>
							<td><f:input path="email" /></td>
						</tr>
						<tr>
							<td>College Id</td>
							<td><f:input path="collegeId" /></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><button>save</button></td>
						</tr>
					</table>
				</f:form>
			</td>
		</tr>

		<tr>
			<td height="25px">
				<!-- This is for footer --> <jsp:include page="include/footer.jsp"></jsp:include>
			</td>
		</tr>

	</table>
</body>

</html>