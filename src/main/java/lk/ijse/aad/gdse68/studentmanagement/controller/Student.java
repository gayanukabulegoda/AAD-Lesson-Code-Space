package lk.ijse.aad.gdse68.studentmanagement.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.aad.gdse68.studentmanagement.dto.StudentDTO;
import lk.ijse.aad.gdse68.studentmanagement.util.Util;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/student", loadOnStartup = 3)
public class Student extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        String initParameter = getServletContext().getInitParameter("myContext");
        System.out.println(initParameter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Todo: Save Student

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        // Object building of the JASON
        Jsonb jsonb = JsonbBuilder.create(); // jason is build up and assigned to jsonb type reference
        StudentDTO student = jsonb.fromJson(req.getReader(), StudentDTO.class); // from the gained JSON Via front-end (in-coming JSON via front end)
        student.setId(Util.generateId());

        // create response
        resp.setContentType("application/json"); // acknowledge that JSON to from back backend to front end we will give a JSON type content
        jsonb.toJson(student, resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Todo: Get Student
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Todo: Update Student
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Todo: Delete Student
    }

}
