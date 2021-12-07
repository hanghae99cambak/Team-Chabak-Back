package com.sparta.hanghaechabak.controller;

import com.sparta.hanghaechabak.security.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@ApiOperation(value = "홈으로 이동")
@Controller
public class HomeController {
    @GetMapping("/")
    public String go_home() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails==null){
            model.addAttribute("username","Guest");
        }
        else{
            model.addAttribute("username", userDetails.getUsername());
        }
        return "index";
    }
}
