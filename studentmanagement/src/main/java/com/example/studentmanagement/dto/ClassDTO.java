package com.example.studentmanagement.dto;

import lombok.Data;
import java.util.Set;

@Data
public class ClassDTO {
    private Long id;
    private Long teacherId;
    private Long subjectId;
    private Set<Long> studentIds;
}
