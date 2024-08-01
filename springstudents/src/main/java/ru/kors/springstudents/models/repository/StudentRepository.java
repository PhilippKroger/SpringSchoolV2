package ru.kors.springstudents.models.repository;

import org.springframework.stereotype.Repository;
import ru.kors.springstudents.models.Student;

import java.util.List;

@Repository
public interface StudentRepository {
    List<Student> findAllStudent();
    Student saveStudent(Student student);
    Student findByEmail(String email);
    Student updateStudent(Student student);
    void deleteStudent(String email);
}
