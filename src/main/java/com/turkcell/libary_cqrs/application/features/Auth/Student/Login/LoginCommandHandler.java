package com.turkcell.libary_cqrs.application.features.Auth.Student.Login;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.core.security.jwt.JwtService;
import com.turkcell.libary_cqrs.domain.entities.Student;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StudentJpaRepository;

@Service
public class LoginCommandHandler implements  CommandHandler<LoginCommand, LoginResponse>{

    private final StudentJpaRepository studentJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginCommandHandler(StudentJpaRepository studentJpaRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService) 
    {
        this.studentJpaRepository = studentJpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }



    @Override
    public LoginResponse handle(LoginCommand command) {
        Student student = this.studentJpaRepository.findByEmail(command.email())
                                .orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST,"Kullanıcı bilgileri yanlış"));

        boolean passwordMatch = this.passwordEncoder.matches(command.password(), student.getPassword());

        if(!passwordMatch)
            throw new BusinessException(HttpStatus.BAD_REQUEST,"Kullanıcı bilgileri yanlış");

        String jwt = jwtService.generate(student.getId(), student.getEmail());
        return new LoginResponse(jwt);    
        }

}
