package com.vivo.soft.excel.springexceldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement//开启默认事务管理
@SpringBootApplication
@ServletComponentScan // 扫描使用注解方式的servlet
public class SpringExcelDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExcelDemoApplication.class, args);
	}
}
