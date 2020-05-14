package com.example.springboothello;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
class SpringbootHelloApplicationTests {
	private static final Logger LOG = LoggerFactory.getLogger(SpringbootHelloApplicationTests.class);

	@Test
	void contextLoads() {
	}

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeAll
	public static void beforeAll(){
		LOG.info("beforeAll");
	}

	@BeforeEach
	public void beforeEach(){
		LOG.info("beforeEach");  //mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@AfterEach
	public void afterEach(){
		LOG.info("afterEach");
	}

	@AfterAll
	public static void afterAll(){
		LOG.info("afterAll");
	}

	@Test
	public void testTwo() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/hello")
				.param("searchPhrase","ABC")          //传参
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);  //请求类型 JSON
		MvcResult mvcResult = mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())     //期望的结果状态 200
				.andDo(MockMvcResultHandlers.print())                 //添加ResultHandler结果处理器，比如调试时 打印结果(print方法)到控制台
				.andReturn();                                         //返回验证成功后的MvcResult；用于自定义验证/下一步的异步处理；
		int status = mvcResult.getResponse().getStatus();                 //得到返回代码
		String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
		LOG.info("status:" + status + ",content:" + content);
	}

}
