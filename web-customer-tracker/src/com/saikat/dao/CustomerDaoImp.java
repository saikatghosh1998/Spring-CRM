package com.saikat.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.saikat.entity.Customer;

@Repository
public class CustomerDaoImp implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by firstName",Customer.class);
		
		//execute query & get result
		List<Customer> customer = theQuery.getResultList();
		
		//return the result		
		return customer;
	}

	@Override
	public void saveCustomer(Customer customer) {

	    //get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//save the customer
		currentSession.saveOrUpdate(customer);
		
		
	}

	@Override
	public Customer getCustomer(int id) {
		 //get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//get the customer
		Customer c = currentSession.get(Customer.class,id);
		
		return c;
	}

	@Override
	public void deleteCustomer(int id) {
		
		 //get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete the object by the id
		Query q = currentSession.createQuery("delete from Customer where id=:CustomerId");
		q.setParameter("CustomerId", id);
		q.executeUpdate();
		
		
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		//get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				 Query theQuery = null;
			        
			        //
			        // only search by name if theSearchName is not empty
			        //
			        if (theSearchName != null && theSearchName.trim().length() > 0) {
			        	  // search for firstName or lastName ... case insensitive
			            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
			            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

			        }
			        else {
			        	 // theSearchName is empty ... so just get all customers
			            theQuery =currentSession.createQuery("from Customer", Customer.class);            
			        }// execute query and get result list
			        List<Customer> customers = theQuery.getResultList();
	                
			        // return the results        
			        return customers;
	}

}
