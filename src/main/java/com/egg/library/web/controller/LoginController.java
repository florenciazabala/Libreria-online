package com.egg.library.web.controller;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.service.CustomerService;
import com.egg.library.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false)String logout, Principal principal){

        ModelAndView modelAndView = new ModelAndView("login");

        if (error != null) {
            modelAndView.addObject("error", "Usuario o contraseña inválida");
        }

        if (logout != null) {
            modelAndView.addObject("logout", "Ha salido correctamente de la plataforma");
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
            modelAndView.addObject("success", flashMap.get("exito"));
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

            customerService.create(document,name,lastName,mail,telephone,userService.create(username,mail,password));
            redirectAttributes.addFlashAttribute("success","Se ha registrado correctamente");

        }catch (Exception e){
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


}
