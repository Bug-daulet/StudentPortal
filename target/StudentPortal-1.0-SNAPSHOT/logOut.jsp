<input type="button" id="logOut" value="Log Out">
<script>
    $( document ).ready(function () {
        $("#logOut").click(function () {
            $.ajax({
                url: 'rest/auth',
                type: 'GET',
                contentType: "application/json",
                success:
                    function (data) {
                        if (data.status === "success") {
                            window.location.href = "login.jsp";
                        }
                    }
            });
            return false;
        });
    });
</script>
