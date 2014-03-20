<%@include file="taglib_includes.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Stories</title>
</head>
<body>
	<h1>1000 and 1 stories:</h1>
	<table border="1px">
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Description</th>
				<th>Points</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="story" items="${stories}">
				<tr>
					<td>${story.id}</td>
					<td>${story.title}</td>
					<td>${story.description}</td>
					<td>${story.estimatedPoints}</td>
					<td>
						<c:choose>
							<c:when test="${story.status == 0}">NEW</c:when>
							<c:when test="${story.status == 1}">EST.</c:when>
							<c:otherwise>DONE</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a href="<c:url value="/edit.do"/>?id=${story.id}"><button>Edit</button></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p/>
	<form:form action="saveStory.do"
		cssStyle="max-heigth:300px; background-color:lightblue;" method="post"
		commandName="newStory" id="registerForm"
		novalidate="novalidate">
		<b>Add Story</b>
		<table class="tableForm">

			<tr>
				<td  style="width: 150px;"><label>TITLE</label></td>
				<td><form:input id="title" name="title"
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
					<form:button>Save</form:button>
				</td>
			</tr>
		</table>
	</form:form>

	<p>
		<a href="<c:url value="/index.do"/>">Home</a>
		<a href="<c:url value="/weeks.do"/>">Weeks</a>
	</p>

</body>
</html>