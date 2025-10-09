package com.api.edusys.services;

import com.api.edusys.dto.StudentDTO;
import com.api.edusys.entities.Student;
import com.api.edusys.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {


    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    private Student toEntity(StudentDTO dto) {
        return modelMapper.map(dto, Student.class);
    }

    private StudentDTO toDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return toDTO(savedStudent);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Student with id: " + id + " not found"));
       
        return toDTO(student);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                                        .orElseThrow(() -> new RuntimeException("Student with id: " + id + " not found"));  
        
        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setAge(studentDTO.getAge());

        Student updatedStudent = studentRepository.save(existingStudent);
        return toDTO(updatedStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student with id: " + id + " not found");
        }
        studentRepository.deleteById(id);
    }
}