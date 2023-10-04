package com.springboot.controller;

import com.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(
                1,
                "Ashok",
                "Koda"
        );
        return student;
    }

    // http://localhost:8080/students
    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ravi", "Kumar"));
        students.add(new Student(2, "Ashok", "Leyland"));
        students.add(new Student(3, "John", "Sena"));
        students.add(new Student(4, "Jack", "Rose"));
        return students;
    }

    // http://localhost:8080/students/1/admin/admin
    @GetMapping("/students/{id}/{first-name}/{last-name}")
    public Student studentPathvariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        return new Student(studentId, firstName, lastName);
    }

    // http://localhost:8080/students/query?studentId=1&firstName=Ashok&lastName=Koda
    @GetMapping("/students/query")
    public Student studentRequestVariable(@RequestParam int studentId,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        return new Student(studentId, firstName, lastName);
    }

    // http://localhost:8080/students/create
    @PostMapping("/students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // http://localhost:8080/students/1/update
    @PutMapping("/students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // http://localhost:8080/students/3/delete
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId) {
        return "Student deleted successfully!.";
    }
}
