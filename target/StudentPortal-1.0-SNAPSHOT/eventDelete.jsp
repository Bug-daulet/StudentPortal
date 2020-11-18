<%--
  Created by IntelliJ IDEA.
  User: adilb
  Date: 18.11.2020
  Time: 6:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $( document ).ready(function () {
        $("#submit").click(function() {
            $.ajax({
                url: 'rest/events/<%=request.getParameter("id")%>',
                type: 'DELETE',
                contentType: "application/json",
                success:
                    function (data) {
                        if (data.status === "success") {
                            window.location.href = "dashboard.jsp";
                        } else {
                            $("error").text("Error")
                        }
                    }
            });
        })

    });

</script>
    <p id="error"></p>
<h1>Are you sure to delete</h1>
<input type="button" id="submit" value="Delete Event">
</body>
</html>
