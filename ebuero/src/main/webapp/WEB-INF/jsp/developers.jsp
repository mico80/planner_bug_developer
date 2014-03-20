<%@include file="taglib_includes.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Developers</title>
</head>
<body>
	<h1>The developers army:</h1>
	<p/>
	<table border="1px">
		<thead>
			<tr>
				<th width="20%">ID</th>
				<th width="55%">Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="developer" items="${developers}">
				<tr>
					<td>${developer.id}</td>
					<td>${developer.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<p/>
	<form:form action="saveDeveloper.do"
		cssStyle="max-heigth:300px;background-color:lightblue;" method="post"
		commandName="newDeveloper" id="registerForm"
		novalidate="novalidate">
		<b>Add Developer</b>
		<table>
			<tr>
				<td>
					<label>NAME</label></td>
				<td><form:input id="name" name="name"
						cssStyle="width:400px;" path="name"></form:input></td>
			</tr>
			<tr>
				<td colspan="2">
					<form:button>Save</form:button>
				</td>
			</tr>
		</table>
	</form:form>

	<p>
		<a href="${pageContext.request.contextPath}/index.do">Home</a>
	</p>

</body>
</html>