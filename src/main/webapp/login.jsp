<%--
  Created by IntelliJ IDEA.
  User: adilb
  Date: 17.11.2020
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $( document ).ready(function () {
        $("#submit").click(function () {
            email = $("#email").val();
            password = $("#password").val();
            var log = {
                "email": email,
                "password": password
            }
            $.ajax({
                url: 'rest/auth',
                type: 'POST',
                data: JSON.stringify(log),
                contentType: "application/json",
                success:
                    function (data) {
                        if (data.status === "success") {
                            window.location.href = "dashboard.jsp";
                        } else {
                            alert(data.status);
                        }
                    },
                error:
                    function () {
                        alert("Error")
                    }
            });
            return false;
        });
    });


</script>
<body>
<form method="post">
    Email: <input type="text" id="email"><br>
    Password <input type="password" id="password" ><br>
    <input type="button" id="submit" value="Log in">
</form>

</body>
</html>
