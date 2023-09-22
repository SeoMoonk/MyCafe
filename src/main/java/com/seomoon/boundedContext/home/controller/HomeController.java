package com.seomoon.boundedContext.home.controller;

import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import com.seomoon.boundedContext.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CafeService cafeService;

    @GetMapping("/")
    public String home(Model model) {

        List<Cafe> allCafeList = cafeService.getAllCafeList();

        model.addAttribute("cafeList", allCafeList);

        return "/view/index";
    }

}
