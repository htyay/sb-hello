## 1.1 hello 

Spring Boot的主要优点：

- 为所有Spring开发者更快的入门
- 开箱即用，提供各种默认配置来简化项目配置
- 内嵌式容器简化Web项目
- 没有冗余代码生成和XML配置的要求

#### a 创建基础项目

方式一 : 在线Spring Initializr生成  https://start.spring.io/ ,下载项目zip文件,解压导入idea

方式二: idea-->新建 project --> Spring Initializr --> 默认mvn项目 + 版本sb 2.2.6

方式三: 一二不好用 可直接手动创建项目,或者复制项目

#### b 创建hello接口

```java
@RestController
public class HelloController {    
	@RequestMapping("/hello")    
	public String index() {       
		return "Hello World";    
	}
}
```

#### c 单元测试 junit 5

```java
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@BeforeEach
	public void beforeEach(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
		System.out.println("status:" + status + ",content:" + content);
	}
```

