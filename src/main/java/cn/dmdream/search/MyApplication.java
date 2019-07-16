package cn.dmdream.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"cn.dmdream.*"})
@MapperScan("cn.dmdream.dao")
public class MyApplication extends SpringBootServletInitializer{
	
	// main
	// java -jar pyg-springboot-demo.jar
	public static void main(String[] args) {
		// 启动入口
		// 1,自动加载内置tomcat服务器环境
		// 2,开启项目
		SpringApplication.run(MyApplication.class, args);

		// 融入其他技术:
		// 1,导入springboot融入技术坐标
		// 2,application.proerties约定配置

		// 开发一个命令:
		// cmd abc 导入数据库数据导入索引库
		// cmd abd 查询数据库同步静态页面
	}
	
    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
    
}
