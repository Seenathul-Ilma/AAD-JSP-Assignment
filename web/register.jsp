<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/13/2025
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/lib/normalize.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@tabler/icons-webfont@latest/dist/tabler-icons.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body class="overflow-hidden">
    <div class="d-grid gap-2 col-12 mx-0 vh-100">
        <div class="row justify-content-center align-items-center">
            <div class="col-sm-4 mb-3 mb-sm-0 me-3">
                <h1 class="mt-0">Sign Up</h1>
                <p class="lead">Create your account to get started.</p>

                <form action="<%= request.getContextPath() %>/api/v1/signup" method="post">
                    <!-- <form action="api/v1/signup" method="post"> -->

                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("error") %>
                    </div>
                    <% } %>

                    <div class="mb-3">
                        <label for="name" class="form-label">Your Name</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="John Doe" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Role</label>
                        <select class="form-select" name="role" required>
                            <option value="" disabled selected>Select your role</option>
                            <option value="admin">Admin</option>
                            <option value="employee">Employee</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Your Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="JohnDoe@gmail.com" required>
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label">New Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="***********" required>
                    </div>

                    <button type="submit" id="user-signup-btn" class="btn btn-primary">Sign Up</button>
                    <a href="<%= request.getContextPath() %>/login.jsp" id="navigate-to-signin" class="btn btn-secondary">Sign In</a>

                </form>
            </div>

            <div class="col-sm-6 mb-3 mb-sm-0">
                <img src="<%= request.getContextPath() %>/assets/images/image.jpg" alt="Page Image" class="img-fluid rounded">
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"crossorigin="anonymous"></script>
    <script src="<%= request.getContextPath() %>/lib/jquery-3.7.1.min.js"></script>
    <script src="<%= request.getContextPath() %>/js/validation.js"></script>


</body>
</html>
