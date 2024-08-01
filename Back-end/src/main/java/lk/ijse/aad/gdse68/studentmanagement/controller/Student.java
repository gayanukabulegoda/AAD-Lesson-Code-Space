package lk.ijse.aad.gdse68.studentmanagement.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.aad.gdse68.studentmanagement.bo.StudentBOIMPL;
import lk.ijse.aad.gdse68.studentmanagement.dto.StudentDTO;
import lk.ijse.aad.gdse68.studentmanagement.util.Util;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/student", loadOnStartup = 3)
public class Student extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(Student.class);
    Connection connection;

    @Override
    public void init() throws ServletException {
        logger.info("Init Method Invoked");
        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/studentManagementPortal");
            this.connection = pool.getConnection();
            logger.debug("Connection Initialized", this.connection);

        } catch ( SQLException | NamingException e) {
            logger.error("DB connection not init" );
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Request not matched with the criteria");
        }
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var studentBOIMPL = new StudentBOIMPL();
            StudentDTO student = jsonb.fromJson(req.getReader(), StudentDTO.class);
            logger.info("Invoke generateId()");
            student.setId(Util.generateId());
            //Save data in the DB
            writer.write(studentBOIMPL.saveStudent(student,connection));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var studentBOIMPL = new StudentBOIMPL();
            Jsonb jsonb = JsonbBuilder.create();
            //DB Process
            var studentId = req.getParameter("studentId");;
            resp.setContentType("application/json");
            jsonb.toJson(studentBOIMPL.getStudent(studentId,connection),writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            var studentBOIMPL = new StudentBOIMPL();
            var studentId = req.getParameter("studentId");
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO student = jsonb.fromJson(req.getReader(), StudentDTO.class);
            if (studentBOIMPL.updateStudent(studentId, student, connection)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                writer.write("Update failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            var studentId = req.getParameter("studentId");
            var studentBOIMPL = new StudentBOIMPL();
            if(studentBOIMPL.deleteStudent(studentId,connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Delete failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
