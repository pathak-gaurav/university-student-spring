<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<c:url var="url_logout" value="/logout" />

<c:if test="${sesssionScope.userId==null && sessionScope.role == null}">
	<s:url var="url_login" value="/index" />
	<s:url var="url_Reg" value="/user/home_user_form" />
	<a href="${url_login}">Login</a> | <a href="${url_Reg}">User Registration</a> | <a href="#">About</a>
</c:if>

<c:if test="${sessionScope.userId!=null && sessionScope.role == 1}">
	<s:url var="url_ahome" value="/admin/dashboard" />
	<s:url var="url_addCol" value="/college/college_form" />
	<s:url var="url_alistCol" value="/college/clist" />
	<s:url var="url_addUser" value="/user/user_form" />
	<s:url var="url_alistUser" value="/user/ulist" />
	<s:url var="url_addStudent" value="/student/student_form" />
	<s:url var="url_alistStudent" value="/student/slist" />
	<s:url var="url_addMarksheet" value="/marksheet/marksheet_form" />
	<s:url var="url_alistMarksheet" value="/marksheet/mlist" /> 
	<s:url var="url_aMeritList" value="/marksheet/merit_list" />
	<a href="${url_ahome}">Home</a> | <a href="${url_addCol}">College Registration</a> | 
	<a href="${url_alistCol}">List College</a> | <a href="${url_addUser}">Add User</a> | <a href="${url_alistUser}">List
		User</a> | <a href="${url_addStudent}">Add Student</a> | <a href="${url_alistStudent}">List Student</a> | <a href="${url_addMarksheet}">Add Marksheet</a> |  <a href="${url_alistMarksheet}">List Marksheet</a>  <br> | <a href="${url_aMeritList}">Merit List</a> | <a
		href="${url_logout}">Logout</a>
</c:if>

<c:if test="${sessionScope.userId!=null && sessionScope.role == 2}">
	<s:url var="url_uhome" value="/user/dashboard" />
	<a href="${url_uhome}">Home</a> | <a href="#">About</a> | <a
		href="${url_logout}">Logout</a>
</c:if>

<c:if test="${sessionScope.userId!=null && sessionScope.role == 3}">
	<s:url var="url_shome" value="/student/dashboard" />
	<s:url var="url_smyMarksheet" value="/marksheet/my_marks" /> 
	<s:url var="url_smyProfile" value="/user/my_profile" />
	<a href="${url_uhome}">Home</a> | <a href="${url_smyMarksheet}">Get Marksheet</a> | <a href="${url_smyProfile}">My Profile</a> | <a href="#">About</a> | <a
		href="${url_logout}">Logout</a>
</c:if>

<c:if test="${sessionScope.userId!=null && sessionScope.role == 4}">
	<s:url var="url_khome" value="/kiosk/dashboard" />
	<s:url var="url_klistMarksheet" value="/marksheet/mlist" />
	<s:url var="url_klist" value="/kiosk/clist" />
	<a href="${url_uhome}">Home</a> | <a href="${url_cform}">Add
		Contact</a> | <a href="${url_clist}">List Marksheet</a> | <a href="#">My Profile</a> |<a href="#">About</a> | <a
		href="${url_logout}">Logout</a>
</c:if>

<c:if test="${sessionScope.userId!=null && sessionScope.role == 5}">
	<s:url var="url_chome" value="/college/dashboard" />
	<s:url var="url_addStudent" value="/college/student_byCollege" />
	<s:url var="url_clistStudent" value="/college/college_students" />
	<s:url var="url_clistMarksheet" value="/college/college_student_marksheet" />
	<s:url var="url_smyProfile" value="/user/my_profile" />
	<a href="${url_chome}">Home</a> | <a href="${url_addStudent}">Add
		Student</a> | <a href="${url_clistStudent}">Student List</a> | <a href="${url_clistMarksheet}">Student's Marksheet</a> | <a href="${url_smyProfile}">College Profile</a> | <a
		href="${url_logout}">Logout</a>
</c:if>
