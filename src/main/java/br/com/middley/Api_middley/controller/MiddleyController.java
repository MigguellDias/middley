package br.com.middley.Api_middley.controller;

import br.com.middley.Api_middley.dto.LoginRequest;
import br.com.middley.Api_middley.model.Customer;
import br.com.middley.Api_middley.service.MiddleyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class MiddleyController {

    MiddleyService middleyService;

    public MiddleyController(MiddleyService middleyService) {
        this.middleyService = middleyService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return middleyService.getCustomerByEmail(loginRequest);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signCustomer(@RequestBody Customer customer) {
        middleyService.signCustomer(customer);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(){
        return middleyService.getAllCustomers();
    }
}