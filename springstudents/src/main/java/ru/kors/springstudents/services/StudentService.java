package ru.kors.springstudents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kors.springstudents.models.Student;
import ru.kors.springstudents.models.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class StudentService implements StudentRepository  {

    private final List<Student> STUDENTS = new ArrayList<>();
    @Override
    public List<Student> findAllStudent() {
        return STUDENTS;
    }

    @Override
    public Student saveStudent(Student student) {
        STUDENTS.add(student);
        return student;
    }

    @Override
    public Student findByEmail(String email) {
        return STUDENTS.stream()
                .filter(element -> element.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Student updateStudent(Student student) {
        var studentIndex = IntStream.range(0, STUDENTS.size())
                .filter(ind -> STUDENTS.get(ind).getEmail().equals(student.getEmail()))
                .findFirst()
                .orElse(-1);
        if (studentIndex > -1) {
            STUDENTS.set(studentIndex, student);
            return student;
        }
        return null;
    }

    @Override
    public void deleteStudent(String email) {
        var student = findByEmail(email);
        if (student != null) {
            STUDENTS.remove(student);
        }
    }
    /*
    List<Student> findAllStudent();
    Student saveStudent(Student student);
    Student findByEmail(String email);
    Student updateStudent(Student student);
    void deleteStudent(String email);

     */
}