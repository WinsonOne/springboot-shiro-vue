package xyz.winson.one;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 * @author Winson One
 * @date 2019年09月25日 19:10
 */
@SpringBootApplication
@MapperScan("xyz.winson.one.mapper")
public class SpringbootShiroVueBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootShiroVueBackendApplication.class, args);
	}

}
