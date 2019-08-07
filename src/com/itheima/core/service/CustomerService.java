package com.itheima.core.service;

import com.itheima.common.utils.Page;
import com.itheima.core.po.Customer;

public interface CustomerService {
    public Page<Customer> findCustomerList(Integer page , Integer rows , String custName , String custSource , String custIndustry , String custLevel);
    public int createCustomer(Customer customer);
    public Customer getCustomerById(Integer cust_id);
    public int updateCustomer(Customer customer);

    public int deleteCustomer(Integer id);
}
