package com.egg.library.web.controller;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.LoanVO;
import com.egg.library.domain.service.CustomerService;
import com.egg.library.domain.service.LoanService;
import com.egg.library.domain.service.UserService;
import com.egg.library.exeptions.FieldAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false)String logout, Principal principal, HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("login");

        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (error != null) {
            modelAndView.addObject("error", "Usuario o contraseña incorrectos");
        }

        if (logout != null) {
            modelAndView.addObject("logout", "Ha salido correctamente de la plataforma");
        }
        if(flashMap != null){
            modelAndView.addObject("success", flashMap.get("success"));
        }

        if (principal != null) {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping(value = "/signup")
    public ModelAndView signup(HttpServletRequest request,Principal principal){
        ModelAndView modelAndView = new ModelAndView("signup");
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

        if (principal != null) {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @PostMapping(value = "/save-signup")
    public RedirectView saveCustomer(@RequestParam Long document, @RequestParam String name, @RequestParam String lastName,
                                     @RequestParam String mail, @RequestParam String telephone,
                                     @RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes){

        RedirectView redirectView = new RedirectView("/login");
        try{

            customerService.create(document,name,lastName,telephone,username,mail,password, Collections.emptyList());
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

            redirectView.setUrl("/signup");
        }

        return redirectView;
    }

    public String getUsername(){
        Object userLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = ((UserDetails)userLogged).getPassword();
        return ((UserDetails)userLogged).getUsername();
    }

    @GetMapping("/profile/{username}")
    public ModelAndView profile(@PathVariable String username, Principal principal){
        ModelAndView modelAndView = new ModelAndView("userProfile");

        CustomerVO customerVO = customerService.findByUserMail(principal.getName());
        List<LoanVO> loansVO = customerVO.getLoans().entrySet().stream().map(l -> loanService.findById(l.getKey()))
                .collect(Collectors.toList());

        modelAndView.addObject("customer",customerVO);
        modelAndView.addObject("loans",loansVO);

        return modelAndView;
    }

}
