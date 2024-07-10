<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>Live Search</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#searchBox').on('keyup', function(){
                var keyword = $(this).val();
                $.ajax({
                    url: 'search',
                    type: 'POST',
                    data: {keyword: keyword},
                    success: function(data) {
                        $('#results').empty();
                        $.each(data, function(index, item) {
                            $('#results').append('<div>' + item + '</div>');
                        });
                    }
                });
            });
        });
    </script>
</head>
<body>
    <h1>Live Search</h1>
    <input type="text" id="searchBox" placeholder="Search...">
    <div id="results"></div>
</body>
</html>
