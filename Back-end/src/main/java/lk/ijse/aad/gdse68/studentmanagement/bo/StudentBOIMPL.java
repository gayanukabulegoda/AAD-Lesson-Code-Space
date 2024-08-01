package lk.ijse.aad.gdse68.studentmanagement.bo;

import lk.ijse.aad.gdse68.studentmanagement.dao.StudentDAOIMPL;
import lk.ijse.aad.gdse68.studentmanagement.dto.StudentDTO;

import java.sql.Connection;

public class StudentBOIMPL implements StudentBO {

    @Override
    public String saveStudent(StudentDTO student, Connection connection) throws Exception {
        var studentDAOIMPL = new StudentDAOIMPL();
        return studentDAOIMPL.saveStudent(student, connection);
    }

    @Override
    public boolean deleteStudent(String id, Connection connection) throws Exception {
        var studentDAOIMPL = new StudentDAOIMPL();
        return studentDAOIMPL.deleteStudent(id, connection);
    }

    @Override
    public boolean updateStudent(String id, StudentDTO student, Connection connection) throws Exception {
        var studentDAOIMPL = new StudentDAOIMPL();
        return studentDAOIMPL.updateStudent(id, student, connection);
    }

    @Override
    public StudentDTO getStudent(String id, Connection connection) throws Exception {
        var studentDAOIMPL = new StudentDAOIMPL();
        return studentDAOIMPL.getStudent(id, connection);
    }
}
