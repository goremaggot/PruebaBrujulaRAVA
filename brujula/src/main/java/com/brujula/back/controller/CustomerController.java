package com.brujula.back.controller;

import com.brujula.back.dto.CustomerDTO;
import com.brujula.persistence.Customer;
import com.brujula.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    private CustomerDTO customerDTO;

    @Autowired
    protected CustomerService customerService;

    private ModelMapper modelMapper;


    @RequestMapping("/listCustomer")
    public String listCostumer(Model model) {
        List<Customer> customerList = customerService.findAll();
        modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<CustomerDTO>>(){}.getType();
        List<CustomerDTO> map = modelMapper.map(customerList, listType);
        model.addAttribute("customers", map);
       return "listCustomer";

    }

    @RequestMapping(value = "/customer")
    public String createCustomer(Model model) {
        customerDTO = new CustomerDTO();
        model.addAttribute("customer", customerDTO);
        model.addAttribute("add",true);
        return "customer";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String addCustomer( CustomerDTO customerDTO) {
        modelMapper = new ModelMapper();
        customerService.save(modelMapper.map(customerDTO, Customer.class));
        return "redirect:/listCustomer";
    }
    @RequestMapping(value="/customer/{id}/delete", method = RequestMethod.POST)
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/listCustomer";
    }

    @RequestMapping(value="/customer/{id}/edit")
    public String showEditCustomer(Model model, @PathVariable Integer id){
        customerDTO = new CustomerDTO();
        modelMapper = new ModelMapper();
        model.addAttribute("customer",modelMapper.map(customerService.findCustomerById(id).get(),CustomerDTO.class));
        model.addAttribute("add",false);
        return "customer";
    }

    @RequestMapping(value="/customer/{id}/edit", method = RequestMethod.POST)
    public String updateCustomer(CustomerDTO customerDTO ){
        customerService.save(modelMapper.map(customerDTO, Customer.class));
        return "redirect:/listCustomer";
    }
}


