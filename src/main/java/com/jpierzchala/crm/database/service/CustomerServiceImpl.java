package com.jpierzchala.crm.database.service;

import com.jpierzchala.crm.database.Customer;
import com.jpierzchala.crm.database.dao.CustomerRepository;
import com.jpierzchala.crm.database.service.inter.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer newCustomer) {
        customerRepository.save(newCustomer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int customerId) {
        // Optional is a container that can contain null or the object itself
        Optional<Customer> queryResult = customerRepository.findById(customerId);
        // if null return new Customer(), otherwise return the Customer
        return queryResult.orElseGet(Customer::new);
    }

    @Override
    @Transactional
    public void deleteCustomer(int customerId) {
        customerRepository.deleteById(customerId);
    }
}
