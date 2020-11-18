<%@ page import="java.util.Date" %>
<%@ page import="rest.Model.User" %>

<%
    User user = (User) request.getSession().getAttribute("user");

%>
<h1>Name:<%=user.getFirstName()%> <%=user.getLastName()%></h1> <br>
<b> Creation time of the Session: <b> <%=new Date(request.getSession().getCreationTime())%> <br>
    Last access time: <%=new Date(request.getSession().getLastAccessedTime())%>


