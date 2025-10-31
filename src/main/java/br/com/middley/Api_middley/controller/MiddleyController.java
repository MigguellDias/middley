package br.com.middley.Api_middley.controller;

import br.com.middley.Api_middley.dto.LoginRequest;
import br.com.middley.Api_middley.model.Customer;
import br.com.middley.Api_middley.service.MiddleyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Customer> customer = middleyService.
                getCustomerByEmail(loginRequest.getEmail());
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "Email não econtrado"
            );
        }

        Customer customer1 = customer.get();

        if(!customer1.getPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "Senha incorreta");
        }

        customer1.setPassword(null);
        return ResponseEntity.ok(customer1);
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