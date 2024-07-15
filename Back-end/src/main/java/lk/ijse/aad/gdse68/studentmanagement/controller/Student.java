package lk.ijse.aad.gdse68.studentmanagement.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.aad.gdse68.studentmanagement.dto.StudentDTO;
import lk.ijse.aad.gdse68.studentmanagement.util.Util;

import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/student")
public class Student extends HttpServlet {
    Connection connection;

    public static String SAVE_STUDENT = "INSERT INTO student (id, name, email, city, level) VALUES(?,?,?,?,?)";
    public static String GET_STUDENT = "SELECT * FROM student WHERE id=?";
    public static String UPDATE_STUDENT = "UPDATE student SET name=?, email=?, city=?, level=? WHERE id=?";
    public static String DELETE_STUDENT = "DELETE FROM student WHERE id=?";

    @Override
    public void init() throws ServletException {
        try {
            var dbClass = getServletContext().getInitParameter("db-class");
            var dbUrl = getServletContext().getInitParameter("db-url");
            var dbUsername = getServletContext().getInitParameter("db-username");
            var dbPassword = getServletContext().getInitParameter("db-password");
            Class.forName(dbClass);
            this. connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Save Student

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()) {
            // Object building of the JASON
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO student = jsonb.fromJson(req.getReader(), StudentDTO.class);
            student.setId(Util.generateId());

            // Save data in the DB
            PreparedStatement ps = connection.prepareStatement(SAVE_STUDENT);
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getCity());
            ps.setString(5, student.getLevel());

            if (ps.executeUpdate() != 0) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.write("Save Student Successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Save Student Failed");
            }

        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get Student

        try (var writer = resp.getWriter()) {
            StudentDTO studentDTO = new StudentDTO();
            Jsonb jsonb = JsonbBuilder.create();
            var studentId = req.getParameter("studentId");

            PreparedStatement ps = connection.prepareStatement(GET_STUDENT);
            ps.setString(1, studentId);
            ResultSet rst = ps.executeQuery();

            while (rst.next()) {
                studentDTO.setId(rst.getString("id"));
                studentDTO.setName(rst.getString("name"));
                studentDTO.setEmail(rst.getString("email"));
                studentDTO.setCity(rst.getString("city"));
                studentDTO.setLevel(rst.getString("level"));
            }
            resp.setContentType("application/json");
            jsonb.toJson(studentDTO, writer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Update Student

        try (var writer = resp.getWriter()) {
            var studentId = req.getParameter("studentId");
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO studentDTO = jsonb.fromJson(req.getReader(), StudentDTO.class);

            // SQL Process
            var ps = connection.prepareStatement(UPDATE_STUDENT);
            ps.setString(1, studentDTO.getName());
            ps.setString(2, studentDTO.getEmail());
            ps.setString(3, studentDTO.getCity());
            ps.setString(4, studentDTO.getLevel());
            ps.setString(5, studentId);
            if (ps.executeUpdate() != 0) {
                writer.write("Update Student Successfully");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // the written content output does not visible in front-end
            } else {
                writer.write("Update Student Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Delete Student

        try (var writer = resp.getWriter()) {
            var studentId = req.getParameter("studentId");
            var ps = connection.prepareStatement(DELETE_STUDENT);
            ps.setString(1, studentId);

            if (ps.executeUpdate() != 0) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                writer.write("Delete Student Failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}