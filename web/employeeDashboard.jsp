<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/15/2025
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("email") == null) {
        System.out.println("Unauthorized Access.. Back to Sign In Page");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<%--<%
    HttpSession sessionCheck = request.getSession(false);
    if (sessionCheck == null || sessionCheck.getAttribute("email") == null) {
        System.out.println("Unauthorized Access.. Back to Sign In Page");
        response.sendRedirect("login.jsp");
        return;
    }
%>--%>


<html>
<head>
    <title>TrackNResolve | JSP Web App</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="lib/normalize.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@tabler/icons-webfont@latest/dist/tabler-icons.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" type="image/ico" href="assets/images/TrackNResolveIcon.ico">
    <link rel="stylesheet" href="css/styles.css">

</head>
<body>

    <div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar d-none d-md-block">
            <a href="#" class="sidebar-brand d-flex align-items-center">
                <img src="assets/images/TrackNResolveIcon.png" alt="Profile" class="profile-img">
                <span>TrackNResolve</span>
            </a>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="employeeDashboard.jsp">
                        <i class="fas fa-tachometer-alt"></i>
                        Dashboard
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="newComplaint.jsp">
                        <i class="fas fa-plus-circle"></i>
                        File A Complaint
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fas fa-clipboard-list"></i>
                        Complaint Log
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fas fa-hourglass-half"></i>
                        Pending Actions
                    </a>
                </li>
            </ul>
        </div>

        <div class="col-md-10 ms-sm-auto main-content">
            <nav class="navbar navbar-dark bg-dark d-md-none mb-4">
                <div class="container-fluid">
                    <div class="d-flex align-items-center">
                        <img src="assets/images/TrackNResolveIcon.png" alt="Profile" class="profile-img">
                    </div>
                    <a class="navbar-brand" href="#">TrackNResolve</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mobileNav">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>
            </nav>
            <div class="collapse d-md-none" id="mobileNav">
                <div class="bg-dark p-3 mb-4 rounded">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active text-white" href="employeeDashboard.jsp">
                                <i class="fas fa-tachometer-alt me-2"></i>
                                Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="newComplaint.jsp">
                                <i class="fas fa-plus-circle me-2"></i>
                                File A Complaint
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">
                                <i class="fas fa-clipboard-list me-2"></i>
                                Complaint Log
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">
                                <i class="fas fa-hourglass-half me-2"></i>
                                Pending Actions
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="dashboard-header text-center">
                <h1><i class="fas fa-tachometer-alt me-2"></i>Employee Dashboard</h1>
            </div>

            <%
                String name = (String) session.getAttribute("name");
                String role = (String) session.getAttribute("role");

                String capitalizedName = name != null && !name.isEmpty()
                        ? name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()
                        : "User Name";

                String capitalizedRole = role != null && !role.isEmpty()
                        ? role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase()
                        : "User Role";
            %>

            <div class="container">
                <div class="row mb-4">
                    <div class="col-md-6">
                        <div class="datetime-card">
                            <div class="employee-name"><%= capitalizedName %></div>
                            <div class="employee-role text-muted"><%= capitalizedRole %></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="datetime-card text-end">
                            <div class="datetime-value" id="currentDate">Monday, June 5, 2023</div>
                            <div class="datetime-value" id="currentTime">10:30:45 AM</div>
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="stat-card overview text-white">
                        <h5 class="mb-2"> TrackNResolve </h5>
                        <p>
                            The Complaint Management System helps you report and track issues easily. As a user, log in with your email and password, then submit new complaints by filling in the details. You can view, edit, or delete your unresolved complaints anytime. Admins have access to all complaints, update their status, add notes, and manage records. This system makes problem-solving faster and keeps communication clear.
                        </p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <div class="stat-card total-complaints text-center">
                            <div class="stat-icon">
                                <i class="fas fa-clipboard-list"></i>
                            </div>
                            <div class="stat-value" id="totalComplaints">147</div>
                            <div class="stat-label">Total Complaints Handled</div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="stat-card resolved-complaints text-center">
                            <div class="stat-icon">
                                <i class="fas fa-check-circle"></i>
                            </div>
                            <div class="stat-value" id="resolvedComplaints">132</div>
                            <div class="stat-label">Resolved Complaints</div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="stat-card pending-complaints text-center">
                            <div class="stat-icon">
                                <i class="fas fa-hourglass-half"></i>
                            </div>
                            <div class="stat-value" id="pendingComplaints">15</div>
                            <div class="stat-label">Pending Complaints</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"crossorigin="anonymous"></script>
    <script src="lib/jquery-3.7.1.min.js"></script>
    <script src="js/validation.js"></script>

</body>
</html>
