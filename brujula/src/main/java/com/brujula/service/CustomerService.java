package com.brujula.service;

import com.brujula.persistence.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService  {

    public List<Customer> findAll();

    public void save(Customer customer);

    public Optional<Customer> findCustomerById(Integer idCustomer);

    public void deleteCustomer(Integer idCustomer);
}
