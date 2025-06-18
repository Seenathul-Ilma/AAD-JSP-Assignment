<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/13/2025
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%--<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession != null && currentSession.getAttribute("email") != null) {
        response.sendRedirect("employeeDashboard.jsp");
        return;
    }
%>--%>

<html>
<head>
    <title>Sign In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/lib/normalize.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@tabler/icons-webfont@latest/dist/tabler-icons.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

</head>
<body class="overflow-hidden">
    <div class="d-grid gap-2 col-12 mx-0 vh-100">
    <div class="row justify-content-center align-items-center">
        <div class="col-sm-4 mb-3 mb-sm-0 me-3">
            <h1 class="mt-0">Sign In</h1>
            <p class="lead">Login to your account</p>

            <form action="<%= request.getContextPath() %>/api/v1/signin" method="post">

                <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <%= request.getAttribute("error") %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <% } %>

                <div class="mb-3">
                    <label for="email" class="form-label">Your Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="JohnDoe@gmail.com" required>
                </div>

                <div class="mb-4">
                    <label for="password" class="form-label">Your Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="***********" required>
                </div>

                <button type="submit" id="user-signin-btn" class="btn btn-primary">Sign In</button>
                <a href="<%= request.getContextPath() %>/register.jsp" id="navigate-to-signup" class="btn btn-secondary">Register</a>
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
