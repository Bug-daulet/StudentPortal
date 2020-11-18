
<script>
    $( document ).ready(function () {
        $.ajax({
            url: 'rest/club/listAll',
            type: 'GET',
            contentType: "application/json",
            success:
                function (data) {
                    $.each(data,  function (i) {
                            $("#listOfClubs").append("<li><a href='clubController.jsp?id="+data[i].id+"'>"
                                +" Name: " + data[i].name
                                +" Description: "+  data[i].description
                                + "<input type='hidden' name='id' value='" + data[i].id+"'>" + "</a></li>")
                        }
                    )
                }}
        )
        return false;
    });
</script>


<ul  id="listOfClubs"></ul>
<c:if test="${sessionScope.user.getRole().equals('admin')}">
    <a href="clubAdd.jsp?id=<%=request.getParameter("id")%>">Add Club</a>
</c:if>

