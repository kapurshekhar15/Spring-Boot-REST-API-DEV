package net.javaguides.springbootrestapi.controller;

import net.javaguides.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "Shekhar", "Kapur");
//        return new ResponseEntity<>(student, HttpStatus.OK);
//        return ResponseEntity.ok(student);

        // passing header to the response entity...


        return ResponseEntity.ok().
                header("custome-header", "ramesh-kumar")
                .body(student);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> list1 = new ArrayList<Student>();
        list1.add(new Student(1, "Shekhar", "Kapur"));
        list1.add(new Student(2, "Ramesh", "Kapur"));
        list1.add(new Student(3, "Shayam", "Kapur"));
        list1.add(new Student(4, "Zohair", "Kapur"));

        return ResponseEntity.ok(list1);
    }


    // Spring Boot REST API with path variable
    // {id} - URI template variable
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentid,
                                                       @PathVariable("first-name") String firstName,
                                                       @PathVariable("last-name") String lastName) {

        Student student = new Student(studentid, firstName, lastName);

        return ResponseEntity.ok(student);
    }


    // Spring Boot REST API with query param
    // http://localhost:8080/students/query?id=5&firstName=Akash&lastName=Patel
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestParam(@RequestParam int id,
                                                       @RequestParam String firstName,
                                                       @RequestParam String lastName
    ) {

        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);

    }


    // Spring boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }


    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }


    // Spring boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return ResponseEntity.ok("student deleted successfully");
    }

}
