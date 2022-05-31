package com.brujula.service;

import com.brujula.persistence.Customer;
import com.brujula.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Customer customer) {
        repository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerById(Integer idCustomer) {
        return repository.findById(idCustomer);
    }

    @Override
    public void deleteCustomer(Integer idCustomer) {
        Optional<Customer>findById = findCustomerById(idCustomer);
        if(findById.isPresent()) {
            repository.delete(findById.get());
        }
    }
}

