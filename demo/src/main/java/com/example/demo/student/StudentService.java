package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exists");
        }
        return studentRepository.getById(studentId);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email already exists");
        }
        studentRepository.save(student);
    }


    public Long deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
        return studentId;
    }

    @Transactional
    public Student updateStudent(Long studentId,
                              LocalDate dob,
                              String name,
                              String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exists"));

        if (name != null && name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email already exists");
            }
            student.setEmail(email);
        }

        if (dob != null && !Objects.equals(student.getDob(), dob)) {
            student.setDob(dob);
        }
        return student;
    }


}
