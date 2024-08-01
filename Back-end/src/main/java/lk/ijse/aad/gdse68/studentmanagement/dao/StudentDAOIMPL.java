package lk.ijse.aad.gdse68.studentmanagement.dao;

import lk.ijse.aad.gdse68.studentmanagement.dto.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class StudentDAOIMPL implements StudentDAO {
    public static String SAVE_STUDENT = "INSERT INTO student (id, name, email, city, level) VALUES(?,?,?,?,?)";
    public static String GET_STUDENT = "SELECT * FROM student WHERE id=?";
    public static String UPDATE_STUDENT = "UPDATE student SET name=?, email=?, city=?, level=? WHERE id=?";
    public static String DELETE_STUDENT = "DELETE FROM student WHERE id=?";

    @Override
    public String saveStudent(StudentDTO studentDTO, Connection connection) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE_STUDENT);
            ps.setString(1, studentDTO.getId());
            ps.setString(2, studentDTO.getName());
            ps.setString(3, studentDTO.getEmail());
            ps.setString(4, studentDTO.getCity());
            ps.setString(5, studentDTO.getLevel());
            if (ps.executeUpdate() != 0) {
                return "Save Student Successfully";
            } else {
                return "Failed to Save Student";
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean deleteStudent(String id, Connection connection) throws SQLException, ClassNotFoundException {
        var ps = connection.prepareStatement(DELETE_STUDENT);
        ps.setString(1, id);
        return ps.executeUpdate() != 0;
    }

    @Override
    public boolean updateStudent(String id, StudentDTO studentDTO, Connection connection) throws SQLException, ClassNotFoundException {
        try {
            var ps = connection.prepareStatement(UPDATE_STUDENT);
            ps.setString(1, studentDTO.getName());
            ps.setString(2, studentDTO.getEmail());
            ps.setString(3, studentDTO.getCity());
            ps.setString(4, studentDTO.getLevel());
            ps.setString(5, id);
            return ps.executeUpdate() != 0;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public StudentDTO getStudent(String id, Connection connection) throws SQLException, ClassNotFoundException {
        try {
            StudentDTO studentDTO = new StudentDTO();
            var ps = connection.prepareStatement(GET_STUDENT);
            ps.setString(1, id);
            var rst = ps.executeQuery();
            while (rst.next()){
                studentDTO.setId(rst.getString("id"));
                studentDTO.setName(rst.getString("name"));
                studentDTO.setEmail(rst.getString("email"));
                studentDTO.setCity(rst.getString("city"));
                studentDTO.setLevel(rst.getString("level"));
            }
            return studentDTO;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
