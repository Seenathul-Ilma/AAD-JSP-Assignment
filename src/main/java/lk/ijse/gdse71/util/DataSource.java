package lk.ijse.gdse71.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/15/2025 2:03 PM
 * Project: CMS
 * --------------------------------------------
 **/

@WebListener
public class DataSource implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //System.out.println("contextInitialized");

        //initialized the database here
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/complaintdb");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("1234");
        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxTotal(5);

        ServletContext servletContext = sce.getServletContext();            // put all connection (in pool) into this
        servletContext.setAttribute("dataSource", basicDataSource);   // Act as key to access the connection
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //System.out.println("contextDestroyed");

        //close the datasource here
        try {
            ServletContext servletContext = sce.getServletContext();
            BasicDataSource basicDataSource = (BasicDataSource) servletContext.getAttribute("dataSource");
            basicDataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
