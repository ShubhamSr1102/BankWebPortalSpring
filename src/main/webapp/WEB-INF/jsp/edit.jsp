<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>     
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<meta charset="ISO-8859-1">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<title>Edit Details</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="row">
<div class=" mx-auto  m-5">
<div class="card" style="width: 21rem;">
    <form:form action="updateProfile" class = "p-4" method="post" modelAttribute="customer">
  <div class="form-group">
    <form:label path="customerId" >Customer Id:</form:label>
    <form:input type="number" class="form-control" path="customerId" readonly="readonly" />
  </div>
  <div class="form-group">
    <form:label path="customerName">Username:</form:label>
    <form:input type="text" class="form-control" path="customerName" readonly="readonly"/>
  </div>
  <div class="form-group">
    <form:label path="customerEmail">Email address:</form:label>
    <form:input type="email" class="form-control" maxlength="21" path="customerEmail" />
  </div>
  <div class="form-group">
    <form:label path="customerAddress">Address:</form:label>
    <form:input type="text" class="form-control" maxlength="20" path="customerAddress"/>
  </div>
   <div class="form-group">
    <form:label path="customerDateOfBirth">Date-Of-Birth:</form:label>
    <form:input type="date" class="form-control" path="customerDateOfBirth"/>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
  <c:if test = "${requestScope.profileupdate==false }">
    <div class="alert alert-danger" role="alert">
    Something went wrong! Profile not updated.
    </div>
    </c:if>
</form:form>

</div>
</div>
</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>


</body>
</html>