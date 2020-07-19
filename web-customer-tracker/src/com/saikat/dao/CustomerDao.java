package com.saikat.dao;

import java.util.List;

import com.saikat.entity.Customer;

public interface CustomerDao {
	
 public List<Customer> getCustomers();

public void saveCustomer(Customer customer);

public Customer getCustomer(int id);

public void deleteCustomer(int id);

public List<Customer> searchCustomers(String theSearchName);

}
