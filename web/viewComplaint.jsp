<%@ page import="lk.ijse.gdse71.model.UserComplaintList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/16/2025
  Time: 8:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    if (session.getAttribute("email") == null) {
        System.out.println("Unauthorized Access.. Back to Sign In Page");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    List<UserComplaintList> userComplaintList = (List<UserComplaintList>) request.getAttribute("userComplaintList");
    if (userComplaintList == null) {
        //response.sendRedirect(request.getContextPath() + "/employeeDashboard.jsp");
        System.out.println("Complaint table is empty..!");
        return;
    }
%>

<html>
<head>
    <title>TrackNResolve | JSP Web App</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/lib/normalize.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@tabler/icons-webfont@latest/dist/tabler-icons.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" href="<%= request.getContextPath() %>/assets/images/TrackNResolveIcon.ico" type="image/ico">

</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar d-none d-md-block">
            <a href="#" class="sidebar-brand d-flex align-items-center">
                <img src="<%= request.getContextPath() %>/assets/images/TrackNResolveIcon.png" alt="Profile" class="profile-img">
                <span>TrackNResolve</span>
            </a>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <%--<a class="nav-link active" href="employeeDashboard.jsp">--%>
                    <a class="nav-link" href="<%= request.getContextPath() %>/employeeDashboard.jsp">
                        <i class="fas fa-tachometer-alt"></i>
                        Dashboard
                    </a>
                </li>
                <li class="nav-item">
                    <%--<a class="nav-link" href="newComplaint.jsp">--%>
                    <a class="nav-link" href="<%= request.getContextPath() %>/newComplaint.jsp">
                        <i class="fas fa-plus-circle"></i>
                        File A Complaint
                    </a>
                </li>
                <li class="nav-item">
                    <%--<a class="nav-link" href="viewComplaint.jsp">--%>
                    <a class="nav-link" href="<%= request.getContextPath() %>/api/v1/user/complaint/list">
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
                        <%--<img src="assets/images/TrackNResolveIcon.png" alt="Profile" class="profile-img">--%>
                        <img src="<%= request.getContextPath() %>/assets/images/TrackNResolveIcon.png" alt="Profile" class="profile-img">
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
                            <a class="nav-link text-white" href="<%= request.getContextPath() %>/employeeDashboard.jsp">
                                <i class="fas fa-tachometer-alt me-2"></i>
                                Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="<%= request.getContextPath() %>/newComplaint.jsp">
                                <i class="fas fa-plus-circle me-2"></i>
                                File A Complaint
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="<%= request.getContextPath() %>/api/v1/user/complaint/list">
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
                <%--<h1><i class="fa-solid fa-circle-check me-2"></i>Reported Complaints</h1>--%>
                <h1>Reported Complaints</h1>
            </div>

            <div class="container">

                <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger">
                    <%= request.getAttribute("error") %>
                </div>
                <% } %>

                <div class="form-container bg-white">
                    <div class="form-header">
                        <h2><i class="bi bi-file-earmark-text"></i> Record Information</h2>
                        <p class="text-muted">Here’s a list of complaints you’ve submitted</p>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Title</th>
                                <th scope="col">Description</th>
                                <th scope="col">Date Submitted</th>
                                <th scope="col">Status</th>
                                <th scope="col">Admin Remarks</th>
                            </tr>
                        </thead>
                        <%--<tbody id="user-complaint-table">--%>
                        <tbody id="user-complaint-table" data-url="/api/v1/user/complaint/list">
                        <% for (int i = 0; i < userComplaintList.size(); i++) {
                                UserComplaintList userComplaint = userComplaintList.get(i);
                            %>
                            <tr>
                                <th scope="row"><%= i+1 %></th>
                                <td><%= userComplaint.getTitle() %></td>
                                <td><%= userComplaint.getDescription() %></td>
                                <td><%= userComplaint.getDate_submitted() %></td>
                                <td>
                                    <span class="badge bg-<%= "Resolved".equals(userComplaint.getStatus()) ? "success" : "warning" %>">
                                        <%= userComplaint.getStatus() %>
                                    </span>
                                </td>
                                <td><%= (userComplaint.getAdmin_remarks() == null || userComplaint.getAdmin_remarks().trim().isEmpty()) ? "N/A" : userComplaint.getAdmin_remarks() %></td>
                            </tr>
                            <% } %>

                            <% if (userComplaintList.isEmpty()) { %>
                            <tr>
                                <td colspan="6" class="text-center">No complaints found</td>
                            </tr>
                            <% } %>

                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"crossorigin="anonymous"></script>
    <script src="<%= request.getContextPath() %>/lib/jquery-3.7.1.min.js"></script>
    <script src="<%= request.getContextPath() %>/js/validation.js"></script>

</body>
</html>
