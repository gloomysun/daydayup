package com.ly.springsecurity;

import com.ly.springsecurity.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityApplicationTests {
	@Autowired
	private UserDao userDao;

	@Test
	public void contextLoads() {

	}

}
