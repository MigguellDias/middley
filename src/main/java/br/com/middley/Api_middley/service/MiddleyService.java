package br.com.middley.Api_middley.service;

import br.com.middley.Api_middley.dto.LoginRequest;
import br.com.middley.Api_middley.model.Customer;
import br.com.middley.Api_middley.repository.MiddleyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiddleyService {

    MiddleyRepository middleyRepository;

    public MiddleyService(MiddleyRepository middleyRepository) {
        this.middleyRepository = middleyRepository;
    }

    public ResponseEntity<?> getCustomerByEmail(LoginRequest loginRequest){
        Optional<Customer> customer = middleyRepository.findByEmail(loginRequest.getEmail());
        if(customer.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "Email não econtrado"
            );
        }

        Customer customer1 = customer.get();

        if(!customer1.getPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "Senha incorreta"
            );
        }

        customer1.setPassword(null);
        return ResponseEntity.ok(customer1);
    }

    public void signCustomer(Customer customer) {
        middleyRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return middleyRepository.findAll();
    }
}
