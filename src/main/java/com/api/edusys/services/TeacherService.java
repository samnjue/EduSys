package com.api.edusys.services;

import com.api.edusys.dto.TeacherDTO;
import com.api.edusys.entities.Teacher;
import com.api.edusys.repositories.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public TeacherService(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

   private Teacher toEntity(TeacherDTO dto) {
        return modelMapper.map(dto, Teacher.class);
   }

   private TeacherDTO toDTO(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDTO.class);
   }

   public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = toEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return toDTO(savedTeacher);
   }

   public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
   }

    public TeacherDTO getTeacherById(Long id) {
          Teacher teacher = teacherRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Teacher with id: " + id + " not found"));
         
          return toDTO(teacher);
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
          Teacher existingTeacher = teacherRepository.findById(id)
                                          .orElseThrow(() -> new RuntimeException("Teacher with id: " + id + " not found"));  
          
          existingTeacher.setName(teacherDTO.getName());
          existingTeacher.setEmail(teacherDTO.getEmail());
          existingTeacher.setDepartment(teacherDTO.getDepartment());

          Teacher updatedTeacher = teacherRepository.save(existingTeacher);
          return toDTO(updatedTeacher);
    }

    public void deleteTeacher(Long id) {
          Teacher existingTeacher = teacherRepository.findById(id)
                                          .orElseThrow(() -> new RuntimeException("Teacher with id: " + id + " not found"));  
          teacherRepository.delete(existingTeacher);
    }
}
