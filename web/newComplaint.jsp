<%--
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
%>

<html>
<head>
    <title>TrackNResolve | JSP Web App</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/lib/normalize.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@tabler/icons-webfont@latest/dist/tabler-icons.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" type="image/ico" href="<%= request.getContextPath() %>/assets/images/TrackNResolveIcon.ico">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>

    <div class="container-fluid overflow-hidden">
        <div class="row">
            <div class="col-md-2 sidebar d-none d-md-block">
                <a href="#" class="sidebar-brand d-flex align-items-center">
                    <%--<img src="assets/images/TrackNResolveIcon.png" alt="Profile" class="profile-img">--%>
                        <img src="<%= request.getContextPath() %>/assets/images/TrackNResolveIcon.png" alt="Profile" class="profile-img">
                        <span>TrackNResolve</span>
                </a>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/employeeDashboard.jsp">
                            <i class="fas fa-tachometer-alt"></i>
                            Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
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
                        <a class="nav-link" href="<%= request.getContextPath() %>/api/v1/update/complaint">
                            <i class="fas fa-hourglass-half"></i>
                            Pending Actions
                        </a>
                    </li>
                    <li class="nav-item">
                        <%--<a class="nav-link" href="<%= request.getContextPath() %>/editUserComplaint.jsp">--%>
                        <a class="nav-link" href="<%= request.getContextPath() %>/login.jsp">
                            <i class="fas fa-sign-out-alt"></i>
                            Log Out
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
                                <a class="nav-link text-white" href="<%= request.getContextPath() %>/api/v1/update/complaint">
                                    <i class="fas fa-hourglass-half me-2"></i>
                                    Pending Actions
                                </a>
                            </li>
                            <li class="nav-item">
                                <%--<a class="nav-link" href="<%= request.getContextPath() %>/editUserComplaint.jsp">--%>
                                <a class="nav-link" href="<%= request.getContextPath() %>/login.jsp">
                                    <i class="fas fa-sign-out-alt"></i>
                                    Log Out
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="dashboard-header text-center">
                    <%--<h1><i class="fa-solid fa-circle-check me-2"></i>Complaint Submission</h1>--%>
                    <h1>Complaint Submission</h1>
                </div>

                <div class="container">
                    <div class="form-container bg-white">
                        <div class="form-header">
                            <h2><i class="bi bi-file-earmark-text"></i> Record Information</h2>
                            <p class="text-muted">Please fill in the details below</p>
                        </div>

                        <form action="<%= request.getContextPath() %>/api/v1/new/complaint" method="post">

                            <% if (request.getAttribute("error") != null) { %>
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                <%= request.getAttribute("error") %>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                            <% } %>

                            <% if (request.getAttribute("success") != null) { %>
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <%= request.getAttribute("success") %>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                            <% } %>

                            <div class="mb-4">
                                <label for="title" class="form-label required-field">Title</label>
                                <input type="text" class="form-control form-control-lg" id="title" name="title" placeholder="Enter title" required>
                                <div class="form-text">A descriptive title for this record</div>
                            </div>

                            <div class="mb-4">
                                <label for="description" class="form-label required-field">Description</label>
                                <textarea class="form-control" id="description" name="description" rows="3" placeholder="Enter detailed description" required></textarea>
                                <div class="form-text">Provide complete details about this record</div>
                            </div>

                            <div class="mb-4">
                                <label for="status" class="form-label">Status</label>
                                <input class="form-control" id="status" name="status" type="text" value="Queued" aria-label="readonly input example" readonly>
                                <div class="form-text">New Complaints are automatically set to 'Queued' status</div>
                            </div>

                            <div class="d-flex justify-content-between mt-5">
                                <button type="reset" class="btn btn-outline-secondary">
                                    <i class="bi bi-x-circle"></i> Cancel
                                </button>
                                <div>
                                    <button type="reset" class="btn btn-warning me-2">
                                        <i class="bi bi-arrow-counterclockwise"></i> Reset
                                    </button>
                                    <button type="submit" class="btn btn-primary">
                                        <i class="bi bi-save"></i> Submit
                                    </button>
                                </div>
                            </div>
                        </form>
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
