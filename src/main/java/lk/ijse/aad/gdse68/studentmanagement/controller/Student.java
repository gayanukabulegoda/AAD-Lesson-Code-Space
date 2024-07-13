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
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/student")
public class Student extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Todo: Save Student

        /*var id = req.getParameter("id");
        var name = req.getParameter("name");
        var email = req.getParameter("email");
        var level = req.getParameter("level");

        System.out.println(id);
        System.out.println(name);
        System.out.println(email);
        System.out.println(level);*/

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        /*BufferedReader reader = req.getReader(); // to read request
        StringBuilder sb = new StringBuilder(); // to create string
        reader.lines().forEach(line -> sb.append(line).append("\n")); // read line by line & after a new line move to a new line
        System.out.println(sb);*/

        // PROCESS the JSON
        /*JsonReader reader = Json.createReader(req.getReader()); // gains the JSON reader
        JsonObject jsonObject = reader.readObject();  // process and read the JSON object
        String email = jsonObject.getString("email"); // need to give the key as the string to get the value
        System.out.println(email);*/

        // Optional -> JSON array processing
        /*JsonReader reader = Json.createReader(req.getReader());
        JsonArray jsonArray = reader.readArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.getJsonObject(i);
            System.out.println(jsonObject.getString("name"));
            System.out.println(jsonObject.getString("email"));
        }*/

        // send data to client (FRONT-END)
        /*PrintWriter writer = resp.getWriter(); // gains the writer
        writer.write(email); // write the email to front end*/

        // Object building of the JASON

        Jsonb jsonb = JsonbBuilder.create(); // jason is build up and assigned to jsonb type reference
        // PrintWriter writer = resp.getWriter();
        StudentDTO student = jsonb.fromJson(req.getReader(), StudentDTO.class); // from the gained JSON Via front-end (in-coming JSON via front end)
        student.setId(Util.generateId());
        // writer.write(jsonb.toJson(student.toString()));

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
