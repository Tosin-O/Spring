package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.SubjectDTO;
import com.example.studentmanagement.entity.Subject;
import com.example.studentmanagement.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public SubjectDTO saveSubject(SubjectDTO subjectDTO) {
        Subject subject = convertToEntity(subjectDTO);
        return convertToDTO(subjectRepository.save(subject));
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    private SubjectDTO convertToDTO(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        return subjectDTO;
    }

    private Subject convertToEntity(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setId(subjectDTO.getId());
        subject.setName(subjectDTO.getName());
        return subject;
    }
}