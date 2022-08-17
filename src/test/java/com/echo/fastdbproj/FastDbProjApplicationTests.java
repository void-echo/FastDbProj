package com.echo.fastdbproj;

import com.echo.fastdbproj.service.MainService;
import com.echo.fastdbproj.service.impl.MainServiceImpl;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FastDbProjApplicationTests {

	MainService mainService;
	@Test
	void contextLoads() {
		mainService.updateCustomerPlace("123123", 123.123, 123.123);
		mainService.updateDriverPlace("D123", 123.12, 123.14);
		mainService.updateDriverPlace("D124", 12.12, 123.14);
		mainService.updateDriverPlace("D125", 120.12, 123.14);
		mainService.updateDriverPlace("D126", 121.12, 123.14);
		List<String> chiKaiKuRuMasOfKyaKu = mainService.getChiKaiKuRuMasOfKyaKu(
				"123123", 123.123, 123.123);
		chiKaiKuRuMasOfKyaKu.forEach(System.out::println);
	}

	@Autowired
	public void setMainService(MainService service) {
		this.mainService = service;
	}
}
