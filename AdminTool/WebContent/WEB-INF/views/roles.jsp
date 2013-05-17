<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>


<fieldset><legend>Select Roles:</legend>

<table class="table" border="1" cellpadding="1" cellspacing="1" style="width: 500px;">
			<tbody>
				
				<c:forEach var="user" items="${sessionScope.roles}">
				<c:set var="count" value="0"></c:set>
				<c:set var="roleId" value="${user.key }"></c:set>	
				
				<tr>
					<td>
					<c:forEach var="role" items="${sessionScope.roles}">
					
						<c:if test="${role==roleId }">
						<c:set var="count" value="1"></c:set>
						<input type="checkbox" class="roleCheckbox" name="roleCheckbox" id="${user.getRoleId()}" value="${user.getRoleId()}" checked="checked" >
						</c:if>
				
					</c:forEach>
					<c:if test="${count==0}">
						<input type="checkbox" class="roleCheckbox" name="roleCheckbox" id="${user.getRoleId()}" value="${user.getRoleId()}">
					</c:if>
						</td>
					<td>
						<c:out value="${user.getRoleName()}"/></td>
				</tr>
				
				</c:forEach>
				
			</tbody>
		</table>
		<input type="button" value="save" id="save" onclick="save()">	
	</fieldset>
</body>
</html>