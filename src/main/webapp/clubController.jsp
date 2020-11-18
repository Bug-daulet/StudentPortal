<%--
  Created by IntelliJ IDEA.
  User: adilb
  Date: 17.11.2020
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
<head>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $( document ).ready(function () {
        $.ajax({
            url: 'rest/club/' + <%=request.getParameter("id")%>,
            type: 'GET',
            contentType: "application/json",
            success:
                function (data) {
                    $("#listOfClubs").append("<li>"
                        +" Name: " + data.name
                        +" Description: "+  data.description
                        +"</li>")
                }}
        )
        return false;
    });
</script>
<body>
<h1>Hi</h1>
id = <%=request.getParameter("id")%>
<a href="clubUpdate.jsp?id=<%=request.getParameter("id")%>">Update</a>
<a href="clubDelete.jsp?id=<%=request.getParameter("id")%>">Delete</a>

<ul  id="listOfClubs"></ul>
<c:if test="${sessionScope.user.getRole().equals('admin')}">
    <a href="clubUpdate.jsp?id=<%=request.getParameter("id")%>">Update</a>
    <a href="clubDelete.jsp?id=<%=request.getParameter("id")%>">Delete</a>
</c:if>

</body>
</html>
