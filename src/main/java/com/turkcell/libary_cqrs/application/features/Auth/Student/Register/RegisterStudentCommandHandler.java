package com.turkcell.libary_cqrs.application.features.Auth.Student.Register;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Student;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StudentJpaRepository;

@Service
public class RegisterStudentCommandHandler implements CommandHandler<RegisterCommand,UUID>{

    private final StudentJpaRepository studentJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterStudentCommandHandler(StudentJpaRepository studentJpaRepository, PasswordEncoder passwordEncoder) {
        this.studentJpaRepository = studentJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UUID handle(RegisterCommand command) {
        Optional<Student> student = studentJpaRepository.findByEmail(command.email());

        if(student.isPresent())
             throw new BusinessException(HttpStatus.CONFLICT, "Bu emailde zaten kayıt var");

        Student newStudent = new Student();

        newStudent.setName(command.name());
        newStudent.setSurname(command.surname());
        newStudent.setEmail(command.email());

        String encodedPassword = this.passwordEncoder.encode(command.password());
        newStudent.setPassword(encodedPassword);
                                               
        studentJpaRepository.save(newStudent);

        return newStudent.getId();
        
    }

}
