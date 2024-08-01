package ru.kors.springstudents.controllers;

import org.springframework.web.bind.annotation.*;
import ru.kors.springstudents.models.Student;
import ru.kors.springstudents.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController  {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> fundAllStudent() {
        return service.findAllStudent();
    }

    @PostMapping("/save_student")
    public String saveStudent(@RequestBody Student student) {
        service.saveStudent(student);
        return "Student successfully saved";
    }

    @GetMapping("/{email}")
    public Student findByEmail(@PathVariable(value = "email") String email) {
        return service.findByEmail(email);
    }

    @PutMapping("/update_student")
    public Student updateStudent(@RequestBody Student student) {
        return service.updateStudent(student);
    }

    @DeleteMapping("/delete_mapping/{email}")
    public void deleteStudent(@PathVariable(value = "email") String email) {
        service.deleteStudent(email);
    }
}