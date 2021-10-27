package com.egg.library.web.controller;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/all")
    public ModelAndView showCustomers(){
        ModelAndView mav = new ModelAndView("customers");
        mav.addObject("customers",customerService.findAllCustomers());
        return mav;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView searchById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("customers");
        List<CustomerVO> customers = new ArrayList<>();
        customers.add(customerService.findBId(id));
        mav.addObject("customers",customers);
        return mav;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(){
        ModelAndView mav = new ModelAndView("formCustomer");
        mav.addObject("customer",new CustomerVO());
        mav.addObject("title","Crear cliente");
        mav.addObject("action","save");
        return mav;
    }
    @GetMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("formCustomer");
        mav.addObject("customer",customerService.findBId(id));
        mav.addObject("title","Modificar cliente");
        mav.addObject("action","saveModifications");
        return mav;
    }

    @PostMapping(value = "/save")
    public RedirectView saveCustomer(@RequestParam Long document,@RequestParam String name,@RequestParam String lastName,
                                     @RequestParam String mail, @RequestParam String telephone){
        customerService.create(document,name,lastName,mail,telephone);
        return new RedirectView("/customers/all");
    }

    @PostMapping(value = "/saveModifications")
    public RedirectView saveModificationsCustomer(@RequestParam Integer id,@RequestParam Long document,@RequestParam String name,@RequestParam String lastName,
                                     @RequestParam String mail, @RequestParam String telephone){
        customerService.update(id,document,name,lastName,mail,telephone);
        return new RedirectView("/customers/all");
    }

    @GetMapping(value = "/delete/{id}")
    public RedirectView delete(@PathVariable Integer id){
        customerService.delete(id);
        return new RedirectView("/customers/all");
    }

    @GetMapping(value = "/discharge/{id}")
    public RedirectView discharge(@PathVariable Integer id){
        customerService.discharged(id);
        return new RedirectView("/customers/all");
    }
}
