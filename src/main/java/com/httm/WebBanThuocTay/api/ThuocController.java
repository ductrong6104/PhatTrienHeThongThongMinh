package com.httm.WebBanThuocTay.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("thuoc")
public class ThuocController {
    @GetMapping("/danhSachThuoc")
    public ModelAndView getAll(){
        ModelAndView view = new ModelAndView();
        view.setViewName("index.html");
        return view;
    }
}
