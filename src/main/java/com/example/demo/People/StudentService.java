package com.example.demo.People;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                new Student("Zoksh", "123456", "Ziad@yahoo.com", "01030560441")
        );
    }
}
