package com.springboot.controller;

import com.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students/api/")
public class StudentController {

    // http://localhost:8080/students/api/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "Ashok",
                "Koda"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header", "Ashok")
                .body(student);
    }

    // http://localhost:8080/students/api/allstudents
    @GetMapping("allstudents")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ravi", "Kumar"));
        students.add(new Student(2, "Ashok", "Leyland"));
        students.add(new Student(3, "John", "Sena"));
        students.add(new Student(4, "Jack", "Rose"));
        return ResponseEntity.ok(students);
    }

    // http://localhost:8080/students/api/1/admin/administrator
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathvariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // http://localhost:8080/students/api/query?studentId=1&firstName=Harish&lastName=Koda
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int studentId,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // http://localhost:8080/students/api/create
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // http://localhost:8080/students/api/1/update
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // http://localhost:8080/students/api/1/delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        return ResponseEntity.ok("Student deleted successfully!.");
    }
}
