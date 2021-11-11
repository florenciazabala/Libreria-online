package com.egg.library.web.controller;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.LoanVO;
import com.egg.library.domain.RolVO;
import com.egg.library.domain.UserVO;
import com.egg.library.domain.service.CustomerService;
import com.egg.library.domain.service.RolService;
import com.egg.library.domain.service.UserService;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.exeptions.FieldInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private RolService rolService;

    @GetMapping(value = "/all")
    public ModelAndView showCustomers(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("customers");

        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null){
            modelAndView.addObject("success", flashMap.get("success"));
        }

        modelAndView.addObject("customers",customerService.findAllCustomers());
        return modelAndView;
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
    public ModelAndView create(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("formCustomer");

        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("error", flashMap.get("error"));
            modelAndView.addObject("name", flashMap.get("name"));
            modelAndView.addObject("lastName", flashMap.get("lastName"));
            modelAndView.addObject("document", flashMap.get("document"));
            modelAndView.addObject("mail", flashMap.get("mail"));
            modelAndView.addObject("telephone", flashMap.get("telephone"));
            modelAndView.addObject("username", flashMap.get("username"));
            modelAndView.addObject("password", flashMap.get("password"));
        }

        modelAndView.addObject("roles",rolService.findAll());
        modelAndView.addObject("title","Crear cliente");
        modelAndView.addObject("action","save");
        return modelAndView;
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable Integer id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("formCustomer");

        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("error", flashMap.get("error"));
        }
        CustomerVO customerVO = customerService.findBId(id);

        modelAndView.addObject("id", customerVO.getId());
        modelAndView.addObject("name", customerVO.getName());
        modelAndView.addObject("lastName", customerVO.getLastName());
        modelAndView.addObject("document", customerVO.getDocument());
        modelAndView.addObject("mail", customerVO.getMail());
        modelAndView.addObject("telephone", customerVO.getTelephone());
        modelAndView.addObject("username", customerVO.getUser().getUsername());
        //modelAndView.addObject("password", customerVO.getUser().getPassword());
        modelAndView.addObject("rolesUser", customerVO.getUser().getRoles());

        modelAndView.addObject("roles",rolService.findAll());
        modelAndView.addObject("title","Modificar cliente");
        modelAndView.addObject("action","saveModifications");
        return modelAndView;
    }

    @PostMapping(value = "/save")
    public RedirectView saveCustomer(@RequestParam Long document, @RequestParam String name, @RequestParam String lastName,
                                     @RequestParam String mail, @RequestParam String telephone,
                                     @RequestParam String username, @RequestParam String password,
                                     @RequestParam("rolChecked") List<String> roles, RedirectAttributes redirectAttributes){
        RedirectView redirectView = new RedirectView("/customers/all");
        try{

            List<RolVO> rolesVO = Collections.emptyList();
            if(roles != null){
                rolesVO = roles.stream().map(rol -> rolService.findByRole(rol)).collect(Collectors.toList());
            }

            customerService.create(document,name,lastName,telephone,username,mail,password,rolesVO);
            redirectAttributes.addFlashAttribute("success","Se ha registrado correctamente");

        }catch (FieldAlreadyExistException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("lastName", lastName);
            redirectAttributes.addFlashAttribute("document", document);
            redirectAttributes.addFlashAttribute("mail", mail);
            redirectAttributes.addFlashAttribute("telephone", telephone);
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("password", password);

            redirectView.setUrl("/customers/create");
        }

        return redirectView;
    }

    @PostMapping(value = "/saveModifications")
    public RedirectView saveModificationsCustomer(@RequestParam Integer id,@RequestParam Long document, @RequestParam String name, @RequestParam String lastName,
                                                  @RequestParam String mail, @RequestParam String telephone,
                                                  @RequestParam String username, @RequestParam String password, @RequestParam("rolChecked") List<String> roles,
                                                  RedirectAttributes redirectAttributes){

        RedirectView redirectView = new RedirectView("/customers/all");
        System.out.println("Id: "+id);
        try{
            List<RolVO> rolesVO = Collections.emptyList();
            if(roles != null){
                rolesVO = roles.stream().map(rol -> rolService.findByRole(rol)).collect(Collectors.toList());
            }
            customerService.update(id,document,name,lastName,mail,telephone);
            userService.update(username,mail,password,rolesVO);
            //customerService.create(document,name,lastName,mail,telephone,userService.create(username,mail,password, Collections.emptyList()));
            redirectAttributes.addFlashAttribute("success","Los cambios se han efectuado correctamente");

        }catch (FieldAlreadyExistException | FieldInvalidException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            redirectAttributes.addFlashAttribute("id", id);
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("lastName", lastName);
            redirectAttributes.addFlashAttribute("document", document);
            redirectAttributes.addFlashAttribute("mail", mail);
            redirectAttributes.addFlashAttribute("telephone", telephone);
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("password", password);

            redirectView.setUrl("/customers/update/"+id);
        }

        return redirectView;
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
