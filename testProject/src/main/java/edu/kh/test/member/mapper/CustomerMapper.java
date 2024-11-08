package edu.kh.test.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.test.member.dto.Customer;

@Mapper
public interface CustomerMapper {
	 int updateCustomer(Customer customer);
}
