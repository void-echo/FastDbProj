package com.echo.fastdbproj;

import com.echo.fastdbproj.dao.CustomerDao;
import com.echo.fastdbproj.entity.Customer;
import com.echo.fastdbproj.service.MainService;
import com.echo.fastdbproj.service.impl.MainServiceImpl;
import com.echo.fastdbproj.util.JsonProvider;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FastDbProjApplicationTests {

	MainService mainService;

	@Resource
	CustomerDao customerDao;

	@Test
	void contextLoads() {
		Customer customer = customerDao.queryById("void-echo");
		System.out.println(new JsonProvider().toJson(customer));
	}

	@Autowired
	public void setMainService(MainService service) {
		this.mainService = service;
	}
}
