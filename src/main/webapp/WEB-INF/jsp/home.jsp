<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.table-bordered td, .table-bordered th{
    border-color: black !important;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
<div class="jumbotron">
  <h1 class="display-4">Hello, ${sessionScope.customer.customerName}!</h1>
  <p class="lead">Welcome to our bank. We hope you will have a great experience here.</p>
  <hr class="my-4">
  <p>Thank you for your faith!</p>
</div>
<div class="row m-0">
    <div class="col mt-5 ml-1 mr-0 mb-5">

        <div class="table-responsive">
            <div class="m-0">
                <table class="table table-bordered m-0" style="background-color: #E0E0E3; padding:150px;   ">
                <thread>
                <tr>
                <p align="center"><font size="5" color="black">Customer Details</font></p>
                </tr>
                </thread>
                    <thead>
                    <tr>
                        <th scope="col"><h5 >Customer ID</h5></th>
                        <th scope="col"><h5 >Name</h5></th>
                        <th scope="col"><h5 >DOB</h5></th>
                        <th scope="col"><h5 >Email</h5></th>
                        <th scope="col"><h5 >Address</h5></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row"><h5 >${sessionScope.customer.customerId}</h5></th>
                        <td><h5 >${sessionScope.customer.customerName}</h5></td>
                        <td><h5 >${sessionScope.customer.customerDateOfBirth}</h5></td>
                        <td style="font-size: 14px"><h5 >${sessionScope.customer.customerEmail}</h5></td>
                        <td style="font-size: 14px"><h5 >${sessionScope.customer.customerAddress}</h5></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col mt-5 ml-0">
        <div class="table-responsive">
            <div class="m-0">
                <table class="table table-bordered m-0" style="background-color: #E0E0E3; padding:150px;   ">
                <thread>
                <tr>
                <p align="center"><font size="5" color="black">Account Details</font></p>
                </tr>
                </thread>
                    <thead>
                    <tr>
                        <th scope="col"><h5 >Account ID</h5></th>
                        <th scope="col"><h5 >Account Type</h5></th>
<!--                         <th scope="col"><h5 >Balance</h5></th> -->
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row"><h5 >${sessionScope.customer.account.accountId}</h5></th>
                        <td><h5 >${sessionScope.customer.account.accountType}</h5></td>
<%--                         <td><h5 >${sessionScope.customer.account.balance}</h5></td> --%>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>