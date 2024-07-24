package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.ClassDTO;
import com.example.studentmanagement.entity.Class;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.Subject;
import com.example.studentmanagement.entity.Teacher;
import com.example.studentmanagement.repository.ClassRepository;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    public ClassDTO saveClass(ClassDTO classDTO) {
        Class clazz = convertToEntity(classDTO);
        return convertToDTO(classRepository.save(clazz));
    }

    public List<ClassDTO> getClassesBySubject(Long subjectId) {
        return classRepository.findBySubjectId(subjectId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void enrollStudent(Long classId, Long studentId) {
        Class clazz = classRepository.findById(classId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (clazz != null && student != null) {
            clazz.getStudents().add(student);
            classRepository.save(clazz);
        }
    }

    public void dropStudent(Long classId, Long studentId) {
        Class clazz = classRepository.findById(classId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (clazz != null && student != null) {
            clazz.getStudents().remove(student);
            classRepository.save(clazz);
        }
    }

    private ClassDTO convertToDTO(Class clazz) {
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(clazz.getId());
        classDTO.setTeacherId(clazz.getTeacher().getId());
        classDTO.setSubjectId(clazz.getSubject().getId());
        classDTO.setStudentIds(clazz.getStudents().stream().map(Student::getId).collect(Collectors.toSet()));
        return classDTO;
    }

    private Class convertToEntity(ClassDTO classDTO) {
        Class clazz = new Class();
        clazz.setId(classDTO.getId());
        clazz.setTeacher(new Teacher());
        clazz.getTeacher().setId(classDTO.getTeacherId());
        clazz.setSubject(new Subject());
        clazz.getSubject().setId(classDTO.getSubjectId());
        clazz.setStudents(classDTO.getStudentIds().stream().map(id -> {
            Student student = new Student();
            student.setId(id);
            return student;
        }).collect(Collectors.toSet()));
        return clazz;
    }
}