package com.jpierzchala.crm.controllers;

import com.jpierzchala.crm.database.Customer;
import com.jpierzchala.crm.database.service.inter.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    // inject the customer service
    private final CustomerService customerService;

    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("")
    public String showPage(Model model) {
        return "redirect:/list";
    }

    @GetMapping("list")
    private String listCustomers(Model theModel) {
        // get customers from the dao
        List<Customer> customers = this.customerService.getCustomers();
        // add customers to the model
        theModel.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("showFormForAdd")
    public String showFormForAdd(Model model) {

        // create model attribute to bind form data
        Customer customer = new Customer();

        model.addAttribute("newCustomer", customer);

        return "customer-form";
    }

    @PostMapping("saveCustomer")
    public String saveCustomer(@ModelAttribute("newCustomer") Customer newCustomer) {
        customerService.saveCustomer(newCustomer);
        return "redirect:/list";
    }

    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {
        // get the customer from our service
        Customer customer = customerService.getCustomer(customerId);
        // set customer as a model attribute to pre-populate the form
        model.addAttribute("newCustomer", customer);
        // send over to our form
        return "customer-form";
    }

    @GetMapping("deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/list";
    }
}
