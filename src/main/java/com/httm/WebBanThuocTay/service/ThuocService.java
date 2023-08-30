package com.httm.WebBanThuocTay.service;

import com.httm.WebBanThuocTay.dao.ThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThuocService {
    @Autowired
    private final ThuocRepository thuocRepository;

    public ThuocService(ThuocRepository thuocRepository) {
        this.thuocRepository = thuocRepository;
    }

}
