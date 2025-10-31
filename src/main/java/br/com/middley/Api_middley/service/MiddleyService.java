package br.com.middley.Api_middley.service;

import br.com.middley.Api_middley.model.Customer;
import br.com.middley.Api_middley.repository.MiddleyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiddleyService {

    MiddleyRepository middleyRepository;

    public MiddleyService(MiddleyRepository middleyRepository) {
        this.middleyRepository = middleyRepository;
    }

    public Optional<Customer> getCustomerByEmail(String email){
        return middleyRepository.findByEmail(email);
    }

    public void signCustomer(Customer customer) {
        middleyRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return middleyRepository.findAll();
    }
}
