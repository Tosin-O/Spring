package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.TeacherDTO;
import com.example.studentmanagement.entity.Teacher;
import com.example.studentmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TeacherDTO getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public TeacherDTO saveTeacher(TeacherDTO teacherDTO) {
        if (teacherRepository.existsByEmail(teacherDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }
        Teacher teacher = convertToEntity(teacherDTO);
        return convertToDTO(teacherRepository.save(teacher));
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setName(teacher.getName());
        teacherDTO.setEmail(teacher.getEmail());
        return teacherDTO;
    }

    private Teacher convertToEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.getId());
        teacher.setName(teacherDTO.getName());
        teacher.setEmail(teacherDTO.getEmail());
        return teacher;
    }
}