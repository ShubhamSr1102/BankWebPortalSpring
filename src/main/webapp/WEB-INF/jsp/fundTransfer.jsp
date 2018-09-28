<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
</body>
<div class=" mx-auto  m-5" align="center">
<div class="card" style="width: 50rem;">
<form action="fundTransferMethod" method="post">
  <div class="form-row">
    <div class="form-group col-md-6 ">
      <label >Sender's Account Id</label>
      <input type="number" class="form-control" name="fromAcc" value="${sessionScope.customer.account.accountId }" readonly="readonly">
    </div>
    <div class="form-group col-md-6">
      <label >Receiver's Account Id</label>
      <input type="number" class="form-control" name="toAcc" placeholder="Account Id" required="required">
    </div>
  </div>
  <div class="form-group">
    <label for="inputAddress">Amount</label>
    <input type="number" class="form-control" name="amount" placeholder="Amount to be transfered">
  </div>
  <button type="submit" class="btn btn-primary">Transfer</button>
</form>
</div>
<br>
<div class="card" style="width: 50rem;">
<c:if test = "${requestScope.success==true }">
    <div class="alert alert-success" role="alert">
    Money Transfered. Balance updated!
    </div>
    </c:if>
    <c:if test = "${requestScope.negativeamount==true }">
    <div class="alert alert-danger" role="alert">
    Invalid amount! Amount cannot be negative.
    </div>
    </c:if>
    <c:if test = "${requestScope.insufficientbalance==true }">
    <div class="alert alert-danger" role="alert">
    Insufficient balance!
    </div>
    </c:if>
    <c:if test = "${requestScope.accountnotfound==true }">
    <div class="alert alert-danger" role="alert">
    Invalid Recipient!
    </div>
    </c:if>
    </div>
</div>
</html>