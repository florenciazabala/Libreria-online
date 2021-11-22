package com.egg.library.web.controller;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.LoanVO;
import com.egg.library.domain.PictureVO;
import com.egg.library.domain.RolVO;
import com.egg.library.domain.service.CustomerService;
import com.egg.library.domain.service.LoanService;
import com.egg.library.domain.service.PictureService;
import com.egg.library.domain.service.UserService;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.exeptions.FieldInvalidException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private PictureService pictureService;


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

    public final String CUSTOMERS_UPLOADED_FOLDER ="src/main/resources/static/images/customers/";
    @PostMapping(value = "/save-signup")
    public RedirectView saveCustomer(@RequestParam Long document, @RequestParam String name, @RequestParam String lastName,
                                     @RequestParam String mail, @RequestParam String telephone,
                                     @RequestParam String username, @RequestParam String password,
                                     @RequestParam(required=false)@Value("${picture.value:@null}") MultipartFile picture, RedirectAttributes redirectAttributes){

        RedirectView redirectView = new RedirectView("/login");
        try{

            CustomerVO customerVO = customerService.create(document,name,lastName,telephone,username,mail,password, Collections.emptyList());
            if(picture != null && !picture.isEmpty()){
                PictureVO pictureVO = pictureService.createPicture(CUSTOMERS_UPLOADED_FOLDER,String.valueOf(customerVO.getId()),name.trim()+","+lastName.trim(),picture);
                customerService.updatePicture(pictureVO, customerVO);
            }
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

    @GetMapping("/profile")
    public ModelAndView profile(Principal principal, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("userProfile");

        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("error", flashMap.get("error"));
        }

        CustomerVO customerVO = customerService.findByUserMail(principal.getName());
        List<LoanVO> loansVO = customerVO.getLoans().entrySet().stream().map(l -> loanService.findById(l.getKey()))
                .collect(Collectors.toList());

        modelAndView.addObject("customer",customerVO);
        modelAndView.addObject("loans",loansVO);
        modelAndView.addObject("action","#");

        return modelAndView;
    }

    @GetMapping("/profile/update/{username}")
    public ModelAndView updateProfile(@PathVariable String username, Principal principal, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("userProfile");

        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("error", flashMap.get("error"));
        }

        CustomerVO customerVO = customerService.findByUserMail(principal.getName());
        List<LoanVO> loansVO = customerVO.getLoans().entrySet().stream().map(l -> loanService.findById(l.getKey()))
                .collect(Collectors.toList());

        modelAndView.addObject("customer",customerVO);
        modelAndView.addObject("loans",loansVO);
        modelAndView.addObject("action","/profile/save-profile");
        modelAndView.addObject("actionPicture","/profile/save-picture");
        return modelAndView;
    }

    @PostMapping(value = "/profile/save-profile")
    public RedirectView saveModificationsCustomer(@RequestParam Integer id, @RequestParam Long document, @RequestParam String name, @RequestParam String lastName,
                                                  @RequestParam String mail, @RequestParam String telephone,
                                                  @RequestParam("user.username") String username, @RequestParam String password, RedirectAttributes redirectAttributes){

        RedirectView redirectView = new RedirectView("/profile");
        System.out.println("Estoy en el post");
        try{
            customerService.update(id,document,name,lastName,mail,telephone);
            userService.update(username,mail,password);
            redirectAttributes.addFlashAttribute("success","Los cambios se han efectuado correctamente");

        }catch (FieldAlreadyExistException | FieldInvalidException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            /*
            redirectAttributes.addFlashAttribute("id", id);
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("lastName", lastName);
            redirectAttributes.addFlashAttribute("document", document);
            redirectAttributes.addFlashAttribute("mail", mail);
            redirectAttributes.addFlashAttribute("telephone", telephone);
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("password", password);*/
            redirectView.setUrl("/profile/update/"+username);
        }

        return redirectView;
    }

    @PostMapping(value = "/profile/save-picture")
    public RedirectView savePicture(@RequestParam Integer id,@RequestParam MultipartFile picture, RedirectAttributes redirectAttributes){

        RedirectView redirectView = new RedirectView("/profile");

        CustomerVO customerVO = customerService.findBId(id);

        try{

            PictureVO pictureVO = customerService.findBId(id).getPicture();
            if(picture != null){
                if(pictureVO == null){
                    pictureVO= pictureService.createPicture(CUSTOMERS_UPLOADED_FOLDER,String.valueOf(id),customerVO.getName().trim()+","+customerVO.getLastName().trim(),picture);
                }else{
                    pictureVO= pictureService.updatePicture(pictureVO,CUSTOMERS_UPLOADED_FOLDER,String.valueOf(id),customerVO.getName().trim()+","+customerVO.getLastName().trim(),picture);
                }
            }

            customerService.updatePicture(pictureVO, customerService.findBId(id));
            redirectAttributes.addFlashAttribute("success","Los cambios se han efectuado correctamente");

        }catch (FieldAlreadyExistException | FieldInvalidException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            redirectView.setUrl("/profile");
        }

        return redirectView;
    }


}
