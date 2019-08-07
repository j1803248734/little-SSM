package com.itheima.core.service;

import com.itheima.common.utils.Page;
import com.itheima.core.dao.CustomerDao;
import com.itheima.core.po.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;
    public Page<Customer> findCustomerList(Integer page, Integer rows, String custName, String custSource, String custIndustry, String custLevel) {
            //创建客户对象
            Customer customer = new Customer();
            //判断客户名称
            if(StringUtils.isNotBlank(custName))
            {
                customer.setCust_name(custName);
            }
            //判断客户信息来源
            if(StringUtils.isNotBlank(custSource))
            {
                customer.setCust_source(custSource);
            }
            //判断客户所述行业
            if(StringUtils.isNotBlank(custIndustry))
            {
                customer.setCust_industry(custIndustry);
            }
            //判断客户级别
            if(StringUtils.isNotBlank(custLevel))
            {
                customer.setCust_level(custLevel);
            }
            //当前页
            customer.setStart((page - 1) * rows);
            customer.setRows(rows);
            List<Customer> customers = customerDao.selectCustomerList(customer);
            Integer count = customerDao.selectCustomerListCount(customer);
            Page<Customer> result = new Page<>();
            result.setPage(page);
            result.setRows(customers);
            result.setSize(rows);
            result.setTotal(count);
            return result;
    }

    @Override
    public int createCustomer(Customer customer) {
        return customerDao.createCustomer(customer);
    }

    @Override
    public Customer getCustomerById(Integer cust_id) {
        Customer customer = customerDao.getCustomerById(cust_id);
        return customer;
    }

    @Override
    public int updateCustomer(Customer customer) {

        return customerDao.updateCustomer(customer);
    }

    @Override
    public int deleteCustomer(Integer id) {
        return customerDao.deleteCustomer(id);
    }
}
