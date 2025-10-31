package br.com.middley.Api_middley.repository;

import br.com.middley.Api_middley.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MiddleyRepository extends JpaRepository <Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
