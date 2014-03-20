<%@include file="taglib_includes.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Edit Story</title>
</head>
<body>
	<h1>Edit story:</h1>
	<p/>
	<form:form action="update.do"
		cssStyle="max-heigth:300px; background-color:lightblue;" method="post"
		commandName="story" id="registerForm"
		novalidate="novalidate">
		<table class="tableForm">

			<tr>
				<td  style="width: 150px;"><label>TITLE</label></td>
				<td><input id="id" name="id" path="id" type="hidden" value="${story.id}">
					<form:input id="title" name="title"
						cssStyle="width:400px;" path="title"></form:input></td>
			</tr>
			<tr>
				<td  style="width: 150px;"><label>DESCRIPTION</label></td>
				<td><form:input id="description"
						name="description" cssStyle="width:400px;"
						path="description"></form:input></td>
			</tr>
			<tr>
				<td  style="width: 150px;"><label>ESTIMATED POINTS</label></td>
				<td><form:input id="estimatedPoints" type="number"
						name="estimatedPoints" cssStyle="width:40px;"
						path="estimatedPoints"></form:input></td>
			</tr>
			<tr>
				<td  style="width: 150px;"><label>ASSIGNED DEVELOPER</label></td>
				<td><form:select id="developer"
						name="developer" cssStyle="width:400px;"
						path="assignedDeveloper.id">
						<c:forEach items="${developers}" var="developer">
							<form:option value="${developer.id}" label="${developer.name}"></form:option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td  style="width: 150px;"><label>STATUS</label></td>
				<td><form:select id="status"
						name="status" cssStyle="width:400px;"
						path="status">
						<form:option value="0" label="New"></form:option>
						<form:option value="1" label="Estimated"></form:option>
						<form:option value="2" label="Completed"></form:option>
					</form:select></td>
			</tr>
			<tr>
				<td colspan="2">
					<form:button>Update</form:button>
				</td>
			</tr>
		</table>
	</form:form>

	<p>
		<a href="<c:url value="/index.do"/>">Home</a>
		<a href="<c:url value="/stories.do"/>">Stories</a>
	</p>

</body>
</html>