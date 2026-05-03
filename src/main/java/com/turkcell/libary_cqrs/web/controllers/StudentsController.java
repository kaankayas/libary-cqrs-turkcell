package com.turkcell.libary_cqrs.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.libary_cqrs.application.features.Auth.Student.Login.LoginCommand;
import com.turkcell.libary_cqrs.application.features.Auth.Student.Register.RegisterCommand;
import com.turkcell.libary_cqrs.application.features.Student.Query.Search.SearchByNameQuery;
import com.turkcell.libary_cqrs.application.features.Student.Query.Search.SearchByNameQueryResponse;
import com.turkcell.libary_cqrs.application.features.Student.Query.GetAll.GetAllStudentQuery;
import com.turkcell.libary_cqrs.application.features.Student.Query.GetAll.GetAllStudentQueryResponse;
import com.turkcell.libary_cqrs.core.mediator.Mediator;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/student")
public class StudentsController {
    private final Mediator mediator;

    public StudentsController(Mediator mediator) {
        this.mediator = mediator;
    }

   @PostMapping("/register") 
    public ResponseEntity<UUID>registerMethod(@Valid @RequestBody RegisterCommand command) {

       var response = mediator.send(command); 
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }

   @GetMapping("/search")
   public ResponseEntity<SearchByNameQueryResponse> search(@RequestParam(required = false) String search,
                                                           @RequestParam(defaultValue = "1") int page, 
                                                           @RequestParam(defaultValue = "10") int size) {
       var response = mediator.send(new SearchByNameQuery(search, page, size));
       return ResponseEntity.ok(response);
   }

   @PostMapping("login")
   public ResponseEntity<String> login(@RequestBody @Valid LoginCommand command) {
      var response = mediator.send(command); 
         return ResponseEntity.ok(response);
   }

   @GetMapping("/getAll")
   public ResponseEntity<GetAllStudentQueryResponse> getAll(@RequestParam(defaultValue = "1") int page, 
                                                            @RequestParam(defaultValue = "10") int size) {
       var response = mediator.send(new GetAllStudentQuery(page, size));
       return ResponseEntity.ok(response);
   }
   
}
