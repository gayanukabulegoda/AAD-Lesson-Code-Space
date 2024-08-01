package lk.ijse.aad.gdse68.studentmanagement.dao;

import lk.ijse.aad.gdse68.studentmanagement.dto.StudentDTO;

import java.sql.Connection;

public sealed interface StudentDAO permits StudentDAOIMPL  {
    String saveStudent(StudentDTO student, Connection connection) throws Exception;
    boolean deleteStudent(String id, Connection connection) throws Exception;
    boolean updateStudent(String id,StudentDTO student,Connection connection) throws Exception;
    StudentDTO getStudent(String id,Connection connection) throws Exception;
}
