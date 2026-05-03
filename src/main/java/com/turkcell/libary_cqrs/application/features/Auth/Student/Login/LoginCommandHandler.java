package com.turkcell.libary_cqrs.application.features.Auth.Student.Login;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Student;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StudentJpaRepository;

@Service
public class LoginCommandHandler implements  CommandHandler<LoginCommand, String>{

    private final StudentJpaRepository studentJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginCommandHandler(StudentJpaRepository studentJpaRepository, PasswordEncoder passwordEncoder) {
        this.studentJpaRepository = studentJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String handle(LoginCommand command) {
        Student student = this.studentJpaRepository.findByEmail(command.email())
                                .orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST,"Kullanıcı bilgileri yanlış"));

        boolean passwordMatch = this.passwordEncoder.matches(command.password(), student.getPassword());

        if(!passwordMatch)
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Kullanıcı bilgileri yanlış");

        return "Giriş Başarılı";
    }

}
