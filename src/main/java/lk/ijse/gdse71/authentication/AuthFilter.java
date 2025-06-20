package lk.ijse.gdse71.authentication;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/17/2025 12:05 AM
 * Project: CMS
 * --------------------------------------------
 **/

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String email = (session != null) ? (String) session.getAttribute("email") : null;

        // Allow these pages/resources without authentication
        String uri = req.getRequestURI();
        boolean isAllowedRequest = uri.endsWith("login.jsp") ||
                uri.contains("signin") ||
                uri.endsWith("register.jsp") ||
                uri.contains("register") ||
                uri.contains("signup") ||
                uri.contains("css") ||
                uri.contains("js") ||
                uri.contains("assets") ||
                uri.contains("fonts");

        if (email == null && !isAllowedRequest) {
            System.out.println("[AuthFilter] Blocked unauthorized access to: " + uri);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        chain.doFilter(request, response);

    }
}

