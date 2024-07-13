package lk.ijse.aad.gdse68.default_mapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/demo", loadOnStartup = 2)
public class Demo extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Hello Init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hello Get Method");
        System.out.println(Thread.currentThread().getName()); //different threads for different requests
        System.out.println("Hello Get Method");
    }

    @Override
    public void destroy() { //
        System.out.println("Destroyed..........");
    }
}
