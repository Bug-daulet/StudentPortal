
<script>
    $( document ).ready(function () {
        $.ajax({
            url: 'rest/news/listAll',
            type: 'GET',
            contentType: "application/json",
            success:
                function (data) {
                    $.each(data,  function (i) {
                            $("#listOfNews").append("<li><a href='newsController.jsp?id="+data[i].id+"'>"
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


<ul  id="listOfNews"></ul>

<c:if test="${sessionScope.user.getRole().equals('admin')}">
    <a href="newsAdd.jsp?id=<%=request.getParameter("id")%>">Add News</a>
</c:if>

