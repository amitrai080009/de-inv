<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cascading Dropdown</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#country').change(function(){
                var countryId = $(this).val();
                $.ajax({
                    url: 'getStates',
                    type: 'POST',
                    data: {countryId: countryId},
                    success: function(data) {
                        var states = $('#state');
                        states.empty();
                        states.append('<option value="">Select State</option>');
                        $.each(data, function(index, state) {
                            states.append('<option value="'+state.id+'">'+state.name+'</option>');
                        });
                    }
                });
            });

            $('#state').change(function(){
                var stateId = $(this).val();
                $.ajax({
                    url: 'getCities',
                    type: 'POST',
                    data: {stateId: stateId},
                    success: function(data) {
                        var cities = $('#city');
                        cities.empty();
                        cities.append('<option value="">Select City</option>');
                        $.each(data, function(index, city) {
                            cities.append('<option value="'+city.id+'">'+city.name+' - '+city.UDISE+'</option>');
                        });
                    }
                });
            });
        });
    </script>
</head>
<body>
    <s:form action="getCountries" method="post">
        <s:select id="country" name="country" list="countries" listKey="id" listValue="name" headerKey="" headerValue="Select Country"/>
        <select id="state" name="state">
            <option value="">Select State</option>
        </select>
        <select id="city" name="city">
            <option value="">Select City</option>
        </select>
    </s:form>
</body>
</html>
