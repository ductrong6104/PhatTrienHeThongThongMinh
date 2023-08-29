package com.example.WEBBANTHUOCTAY.dao;

import com.example.WEBBANTHUOCTAY.model.Loai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoaiBean {

    @Bean
    public CommandLineRunner init(LoaiResponsitory loaiResponsitory){
        return args->{
            Loai l1 = new Loai(1l,"loai 1");
            Loai l2 = new Loai(2l,"loai 2");
            loaiResponsitory.saveAll(List.of(l1,l2));
            List<Loai> listLoai = loaiResponsitory.findAll();
            for (Loai loai: listLoai){
                System.out.println(loai);
            }
        };
    }
}
