<%@ page import="lk.ijse.gdse71.dto.ComplaintDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/19/2025
  Time: 11:50 AM
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

    List<ComplaintDTO> userComplaintDTOS = (List<ComplaintDTO>) request.getAttribute("complaintDTOS");
    if (userComplaintDTOS == null) {
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

<div class="container-fluid overflow-hidden">
    <div class="row">
        <div class="col-md-2 sidebar d-none d-md-block">
            <a href="#" class="sidebar-brand d-flex align-items-center">
                <img src="<%= request.getContextPath() %>/assets/images/TrackNResolveIcon.png" alt="Profile" class="profile-img">
                <span>TrackNResolve</span>
            </a>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <%--<a class="nav-link active" href="employeeDashboard.jsp">--%>
                    <a class="nav-link" href="<%= request.getContextPath() %>/adminDashboard.jsp">
                        <i class="fas fa-tachometer-alt"></i>
                        Dashboard
                    </a>
                </li>
                <li class="nav-item">
                    <%--<a class="nav-link" href="viewComplaint.jsp">--%>
                    <a class="nav-link" href="<%= request.getContextPath() %>/api/v1/admin/complaint/list">
                        <i class="fas fa-clipboard-list"></i>
                        Complaint Log
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/api/v1/admin/update/complaint">
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
                            <a class="nav-link text-white" href="<%= request.getContextPath() %>/adminDashboard.jsp">
                                <i class="fas fa-tachometer-alt me-2"></i>
                                Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="<%= request.getContextPath() %>/api/v1/admin/complaint/list">
                                <i class="fas fa-clipboard-list me-2"></i>
                                Complaint Log
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="<%= request.getContextPath() %>/api/v1/admin/update/complaint">
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

            <div class="container-fluid px-4">
                <div class="expanded-form-container bg-white">
                    <div class="form-header-expanded">
                        <h2><i class="bi bi-file-earmark-text me-2"></i> Record Information</h2>
                        <p class="text-muted mb-2">Please fill in the details below</p>
                    </div>

                    <div class="table-responsive">
                        <form action="<%= request.getContextPath() %>/api/v1/admin/update/complaint" method="post">

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

                            <input type="hidden" name="complaint_id" value="<%= request.getParameter("complaint_id") != null ? request.getParameter("complaint_id") : "" %>">

                            <div class="mb-4">
                                <label for="title" class="form-label required-field">Title</label>
                                <%--<input type="text" class="form-control form-control-lg" id="title" name="title" placeholder="Enter title" required>--%>
                                <input type="text" class="form-control form-control-lg" id="title" name="title"
                                       value="<%= request.getParameter("title") != null ? request.getParameter("title") : "" %>"
                                       placeholder="Enter title" required>
                                <div class="form-text">A descriptive title for this record</div>
                            </div>

                            <div class="mb-4">
                                <label for="description" class="form-label required-field">Description</label>
                                <%--<textarea class="form-control" id="description" name="description" rows="3" placeholder="Enter detailed description" required></textarea>--%>
                                <textarea class="form-control" id="description" name="description" rows="3" placeholder="Enter detailed description" required><%=
                                request.getParameter("description") != null ? request.getParameter("description") : "" %></textarea>
                                <div class="form-text">Provide complete details about this record</div>
                            </div>

                            <div class="mb-4">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-select" id="status" name="status" aria-label="Complaint Status">
                                    <option value="Unresolved" <%= "Unresolved".equals(request.getParameter("status")) ? "selected" : "" %>>Unresolved</option>
                                    <option value="Resolved" <%= "Resolved".equals(request.getParameter("status")) ? "selected" : "" %>>Resolved</option>
                                </select>
                                <div class="form-text">Only admin can update the status</div>
                            </div>

                            <div class="mb-4">
                                <label for="admin_remarks" class="form-label">Admin Remarks</label>
                                <textarea class="form-control" id="admin_remarks" name="admin_remarks" rows="2" placeholder="Enter admin remarks (optional)"><%=
                                request.getParameter("admin_remarks") != null ? request.getParameter("admin_remarks") : "" %></textarea>
                                <div class="form-text">Only admin can update remarks</div>
                            </div>

                            <div class="d-flex justify-content-between mt-5">
                                <%--<button type="reset" class="btn btn-outline-secondary">
                                    <i class="bi bi-x-circle"></i> Cancel
                                </button>--%>
                                <a class="btn btn-outline-secondary me-2" href="<%= request.getContextPath() %>/api/v1/admin/update/complaint">
                                    <i class="bi bi-x-circle"></i> Cancel
                                </a>
                                <div>
                                    <%--<button type="reset" class="btn btn-warning me-2">
                                        <i class="bi bi-arrow-counterclockwise"></i> Reset
                                    </button>--%>
                                    <a class="btn btn-outline-secondary me-2" href="<%= request.getContextPath() %>/api/v1/admin/update/complaint">
                                        <i class="bi bi-arrow-counterclockwise"></i> Reset
                                    </a>
                                    <button type="submit" class="btn btn-primary">
                                        <i class="bi bi-save"></i> Update
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>

                <div class="expanded-form-container bg-white">
                    <div class="form-header-expanded">
                        <h2><i class="bi bi-file-earmark-text me-2"></i> Record List</h2>
                        <p class="text-muted mb-2">Here’s a list of unresolved complaints you’ve submitted</p>
                    </div>

                    <div class="table-responsive">
                        <table class="table expanded-table table-striped table-hover text-center">
                            <thead>
                            <tr class="text-center">
                                <th style="width: 4%;">#</th>
                                <th style="width: 15%;">Title</th>
                                <th style="width: 23%;">Description</th>
                                <th style="width: 12%;">Date Submitted</th>
                                <th style="width: 8%;">Status</th>
                                <th style="width: 20%;">Admin Remarks</th>
                                <th style="width: 18%;">Action</th>
                            </tr>
                            </thead>
                            <%--<tbody id="user-complaint-table">--%>
                            <tbody id="user-complaint-table" data-url="/api/v1/admin/update/complaint">
                            <% for (int i = 0; i < userComplaintDTOS.size(); i++) {
                                ComplaintDTO userComplaint = userComplaintDTOS.get(i);
                            %>
                            <tr>
                                <th scope="row"><%= i+1 %></th>
                                <td><%= userComplaint.getTitle() %></td>
                                <td style="word-break: break-word; max-width: 300px;"><%= userComplaint.getDescription() %></td>
                                <td><%= userComplaint.getDate_submitted() != null ? userComplaint.getDate_submitted().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "" %></td>
                                <td>
                                <span class="badge bg-<%= "Resolved".equals(userComplaint.getStatus()) ? "success" : "warning" %>">
                                    <%= userComplaint.getStatus() %>
                                </span>
                                </td>
                                <td style="word-break: break-word; max-width: 300px;"><%= (userComplaint.getAdmin_remarks() == null || userComplaint.getAdmin_remarks().trim().isEmpty()) ? "N/A" : userComplaint.getAdmin_remarks() %></td>
                                <td>
                                    <%--<button type="submit" class="btn btn-outline-warning btn-sm"> Update </button>--%>
                                    <form method="get" action="<%= request.getContextPath() %>/api/v1/admin/update/complaint" class="d-inline">
                                        <input type="hidden" name="complaint_id" value="<%= userComplaint.getComplaint_id() %>">
                                        <input type="hidden" name="title" value="<%= userComplaint.getTitle() %>">
                                        <input type="hidden" name="description" value="<%= userComplaint.getDescription() %>">
                                        <input type="hidden" name="status" value="<%= userComplaint.getStatus() %>">
                                        <input type="hidden" name="admin_remarks" value="<%= userComplaint.getAdmin_remarks() %>">
                                        <button type="submit" class="btn btn-outline-success btn-sm me-1"> Modify </button>
                                    </form>
                                    <form method="post" action="<%= request.getContextPath() %>/api/v1/delete/complaint" class="d-inline">
                                        <input type="hidden" name="complaint_id" value="<%= userComplaint.getComplaint_id() %>">
                                        <button type="submit" class="btn btn-outline-danger btn-sm"
                                                onclick="return confirm('Are you sure you want to delete this complaint?')"> Delete
                                        </button>
                                    </form>
                                </td>
                            </tr>
                            <% } %>

                            <% if (userComplaintDTOS.isEmpty()) { %>
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
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"crossorigin="anonymous"></script>
<script src="<%= request.getContextPath() %>/lib/jquery-3.7.1.min.js"></script>
<script src="<%= request.getContextPath() %>/js/validation.js"></script>

</body>
</html>
