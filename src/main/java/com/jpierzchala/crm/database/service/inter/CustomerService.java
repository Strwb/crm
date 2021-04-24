package com.jpierzchala.crm.database.service.inter;

import com.jpierzchala.crm.database.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    void saveCustomer(Customer newCustomer);

    Customer getCustomer(int customerId);

    void deleteCustomer(int customerId);
}
