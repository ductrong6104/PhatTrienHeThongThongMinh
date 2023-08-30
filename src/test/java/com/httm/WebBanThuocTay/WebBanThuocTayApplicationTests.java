package com.httm.WebBanThuocTay;

import com.httm.WebBanThuocTay.dao.ThuocRepository;
import com.httm.WebBanThuocTay.model.Thuoc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
class WebBanThuocTayApplicationTests {

	@Autowired
	private ThuocRepository thuocRepository;
	@Test
	void contextLoads() {
		Thuoc t1 = new Thuoc("anonyxin");
		Thuoc t2 = new Thuoc("xyanmol");
		thuocRepository.saveAll(List.of(t1,t2));
		List<Thuoc> listThuoc = thuocRepository.findAll();
		for (Thuoc t: listThuoc){
			System.out.println(t);
		}
		thuocRepository.deleteAll();
	}

}
