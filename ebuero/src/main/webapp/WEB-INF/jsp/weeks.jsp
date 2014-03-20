<%@include file="taglib_includes.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Weekly plan</title>
<style type="text/css">
	div {
		border: 1px solid black;
		background-color: lightblue;
		width: 640px;
	}
	table {
		border: none;
		width: 100%;
	}
</style>
</head>
<body>
	<h1>Weekly plans:</h1>
	<c:forEach var="week" items="${weeks}">
		<div>
			<table>
				<tbody>
					<tr>
						<td><b>Week</b></td><td/><td><b>${week.points} points / ${total} max points</b></td>
					</tr>
					<tr></tr>
					<c:forEach var="story" items="${week.stories}">
						<tr>
							<td width="50%">Story: <b>${story.title}</b></td><td width="10%"/><td>Points:  <b>${story.estimatedPoints}</b></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<p/>
	</c:forEach>
	<p>
		<a href="${pageContext.request.contextPath}/index.do">Home</a>
	</p>

</body>
</html>