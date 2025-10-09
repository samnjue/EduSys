package com.api.edusys.services;

import com.api.edusys.dto.SubjectDTO;
import com.api.edusys.entities.Subject;
import com.api.edusys.entities.Teacher;
import com.api.edusys.repositories.SubjectRepository;
import com.api.edusys.repositories.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public SubjectService(SubjectRepository subjectRepository, TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Teacher teacher = teacherRepository.findById(subjectDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher with ID: " + subjectDTO.getTeacherId() + " not found"));

        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        subject.setTeacher(teacher);

        Subject savedSubject = subjectRepository.save(subject);
        return modelMapper.map(savedSubject, SubjectDTO.class);
    }

    private SubjectDTO toDTO(Subject subject) {
        SubjectDTO dto = modelMapper.map(subject, SubjectDTO.class); 

        if (subject.getTeacher() != null) {
            dto.setTeacherId(subject.getTeacher().getId());
        }

        dto.setStudentIds(subject.getStudents().stream().map(
            student -> student.getId()
        ).collect(Collectors.toSet()));
        return dto;
    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject with id: " + id + " not found"));
        return toDTO(subject);
    }

    public SubjectDTO updateSubject(Long id, SubjectDTO subjectDTO) {
        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject with id: " + id + " not found"));

        if (subjectDTO.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(subjectDTO.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher with ID: " + subjectDTO.getTeacherId() + " not found"));
            existingSubject.setTeacher(teacher);
        }

        existingSubject.setName(subjectDTO.getName());

        Subject updatedSubject = subjectRepository.save(existingSubject);
        return toDTO(updatedSubject);
    }

    public void deleteSubject(Long id) {
        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject with id: " + id + " not found"));
        subjectRepository.delete(existingSubject);
    }
}