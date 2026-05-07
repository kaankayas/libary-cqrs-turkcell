package com.turkcell.libary_cqrs.application.features.Auth.Staff.Login;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.core.security.jwt.JwtService;
import com.turkcell.libary_cqrs.domain.entities.Staff;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StaffJpaRepository;

@Service
public class LoginStaffCommandHandler implements CommandHandler<LoginStaffCommand, LoginResponse> {
    private final StaffJpaRepository staffJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginStaffCommandHandler(StaffJpaRepository staffJpaRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.staffJpaRepository = staffJpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @Override
    public LoginResponse handle(LoginStaffCommand command) {


        Staff staff = staffJpaRepository.findByEmail(command.email())
                .orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST, "Giriş bilgileri hatalı."));

        if (!passwordEncoder.matches(command.password(), staff.getPassword())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Giriş bilgileri hatalı.");
        }

       String jwt = jwtService.generate(staff.getId(), staff.getEmail());
        return new LoginResponse(jwt);    
    }
}
