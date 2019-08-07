package com.itheima.core.web.controller;

import com.itheima.common.utils.Page;
import com.itheima.core.po.BaseDict;
import com.itheima.core.po.Customer;
import com.itheima.core.po.User;
import com.itheima.core.service.BaseDictService;
import com.itheima.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BaseDictService baseDictService;
    @Value("${customer.from.type}")
    private String FROM_TYPE;
    @Value("${customer.industry.type}")
    private String INDUSTRY_TYPE;
    @Value("${customer.level.type}")
    private String LEVEL_TYPE;
    @RequestMapping(value = "/customer/list.action")
    public String List(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows , String custName ,
                       String custSource , String custIndustry , String custLevel , Model model)
    {
        Page<Customer> customers = customerService.findCustomerList(page , rows , custName , custSource , custIndustry , custLevel);
        model.addAttribute("page",customers);
        List<BaseDict> fromType = baseDictService.findBaseDictByTypeCode(FROM_TYPE);
        List<BaseDict> industryType = baseDictService.findBaseDictByTypeCode(INDUSTRY_TYPE);
        List<BaseDict> levelType = baseDictService.findBaseDictByTypeCode(LEVEL_TYPE);
        model.addAttribute("fromType", fromType);
        model.addAttribute("industryType",industryType);
        model.addAttribute("levelType",levelType);
        model.addAttribute("custName", custName);
        model.addAttribute("custSource",custSource);
        model.addAttribute("custIndustry",custIndustry);
        model.addAttribute("custLevel",custLevel);
        return "customer";
    }
    @RequestMapping("/customer/create.action")
    @ResponseBody   //以特定的格式写入Response的body区域
    public String customerCreate(Customer customer , HttpSession session)
    {
        User user = (User) session.getAttribute("USER_SESSION");
        customer.setCust_user_id(user.getCust_id());
        Date date = new Date();
        //得到一个timestamp格式的时间，存入mysql的时间格式为yyyy/MM/dd HH:mm:ss
        Timestamp timestamp = new Timestamp(date.getTime());
        customer.setCust_createtime(timestamp);
        int result = customerService.createCustomer(customer);
        if(result>0)
        {
            return "OK";
        }
        else
        {
            return "FAIL";
        }

    }
    @RequestMapping("/customer/getCustomerById.action")
    @ResponseBody
    public Customer getCustomerById(Integer id)
    {
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }
    @RequestMapping("/customer/update.action")
    @ResponseBody
    public String customerUpdate(Customer customer)
    {
        int result =  customerService.updateCustomer(customer);
        if(result>0)
        {
            return "OK";
        }
        else
        {
            return "FAIL";
        }
    }
    @RequestMapping("/customer/delete.action")
    @ResponseBody
    public String customerDelete(Integer id)
    {
        int result =  customerService.deleteCustomer(id);
        if(result>0)
        {
            return "OK";
        }
        else
        {
            return "FAIL";
        }
    }
}
