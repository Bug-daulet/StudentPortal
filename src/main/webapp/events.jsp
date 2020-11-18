
<script>
    $( document ).ready(function () {
        $.ajax({
            url: 'rest/events/listAll',
            type: 'GET',
            contentType: "application/json",
            success:
                function (data) {
                    $.each(data,  function (i) {
                        $("#listOfEvents").append("<li><a href='eventController.jsp?id="+data[i].id+"'>"
                            +" Title: " + data[i].title
                            +" Description: "+  data[i].description
                            +" Date: " + new Date(data[i].date)
                            +" Club ID:" + data[i].clubID + "<input type='hidden' name='id' value='" + data[i].id+"'>" + "</a></li>")
                        }
                    )
                }}
        )
        return false;
    });
</script>

<ul  id="listOfEvents"></ul>
<c:if test="${sessionScope.user.getRole().equals('admin')}">
    <a href="eventAdd.jsp?id=<%=request.getParameter("id")%>">Add Event</a>
</c:if>

