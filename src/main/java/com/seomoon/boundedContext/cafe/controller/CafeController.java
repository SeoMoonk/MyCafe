package com.seomoon.boundedContext.cafe.controller;

import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafe.model.form.CafeCreateForm;
import com.seomoon.boundedContext.cafe.service.CafeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafe")
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String cafeCreateForm(CafeCreateForm cafeCreateForm) {

        return "view/cafe/createForm";
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createCafe(@Valid CafeCreateForm cafeCreateForm,
                             BindingResult bindingResult,
                             Principal principal) {

        String loginId = principal.getName();

        Map<String, String> createResultMap = cafeService.createCafe(cafeCreateForm, loginId);

        if(createResultMap.get("code").startsWith("F")){

            String failCode = createResultMap.get("code");
            String failMsg = createResultMap.get("msg");

            bindingResult.reject("global.error", failCode + ": " + failMsg);

            return "view/cafe/createForm";
        }

        return "redirect:/";
    }

    @GetMapping("/detail")
    public String getCafeDetail(@RequestParam(value = "id") Long cafeId, Model model) {

        Map<String, Object> getCafeMap = cafeService.getCafeById(cafeId);

        if(getCafeMap.get("code").toString().startsWith("F")){
            return "/error";
        } else{
            Cafe result = (Cafe) getCafeMap.get("result");
            model.addAttribute("cafe", result);

            return "view/cafe/cafeDetail";
        }
    }

}
