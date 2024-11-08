package edu.kh.test.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.test.member.dto.Customer;
import edu.kh.test.member.mapper.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService{

	 @Autowired
	 private CustomerMapper mapper;
	
	@Override
	public int updateCustomer(Customer customer) {
		return mapper.updateCustomer(customer);
	}
}
