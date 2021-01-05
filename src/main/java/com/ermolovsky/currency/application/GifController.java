package com.ermolovsky.currency.application;


import com.ermolovsky.currency.application.service.GifService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GifController {
    public GifService gifService;

    public GifController(GifService gifService) {
        this.gifService = gifService;
    }

    @GetMapping("/")
    public String showGif(Model model){
        String tmpGif = gifService.getGif().getData().get(0).getImages().getOriginal().get("url");
        model.addAttribute("gifimage",tmpGif);
        return "index";
    }
}
