<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/13/2025
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>TrackNResolve | JSP Web App</title>
</head>
<body>
<diV class="container-fluid">
    <nav class="navbar sticky-top navbar-expand-md bg-light w-100" data-bs-theme="light">
        <div class="container-fluid">
            <a class="navbar-brand d-inline-block align-text-top" href="#"> WorkSync</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="btn btn-transparent me-2 border-0" id="home-manage-btn">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-transparent me-2 border-0" id="employee-manage-btn">Employees</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-transparent me-2 border-0" id="customer-manage-btn">Customers</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-transparent me-2 border-0" id="event-manage-btn">Events</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-transparent me-2 border-0" id="payment-manage-btn">Payments</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item text-nowrap">
                        <a class="btn btn-transparent me-2 border-0" id="logout-btn">Log Out</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>



    <div class="me-3">
        <h1 class="mt-5">Dashboard</h1>
        <p class="lead">Welcome to your dashboard!</p>
    </div>

</diV>

    <script src="lib/jquery-3.7.1.min.js"></script>
</body>
</html>
