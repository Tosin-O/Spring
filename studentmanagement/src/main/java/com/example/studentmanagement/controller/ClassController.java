package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.ClassDTO;
import com.example.studentmanagement.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping
    public ClassDTO saveClass(@RequestBody ClassDTO classDTO) {
        return classService.saveClass(classDTO);
    }

    @GetMapping("/subject/{subjectId}")
    public List<ClassDTO> getClassesBySubject(@PathVariable Long subjectId) {
        return classService.getClassesBySubject(subjectId);
    }

    @PostMapping("/{classId}/enroll/{studentId}")
    public void enrollStudent(@PathVariable Long classId, @PathVariable Long studentId) {
        classService.enrollStudent(classId, studentId);
    }

    @DeleteMapping("/{classId}/drop/{studentId}")
    public void dropStudent(@PathVariable Long classId, @PathVariable Long studentId) {
        classService.dropStudent(classId, studentId);
    }
}
