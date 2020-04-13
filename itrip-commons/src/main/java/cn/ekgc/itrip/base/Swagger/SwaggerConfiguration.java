package cn.ekgc.itrip.base.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
	@Bean
	public Docket createRestDoket(){
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(apiInfo());
		return docket;
	}

	/**
	 * <b>用于创建Swagger文档页面相关信息的对象</b>
	 * @return
	 */
    private ApiInfo apiInfo(){
    	ApiInfo apiInfo = new ApiInfoBuilder()
    	         //设置生成文档
    	         .title("爱旅行 | 项目文档后端")
			    //设定版本号
			     .version("1.0.0")
	           //设定联系人
	             .contact(new Contact("叶", "","1139817353@qq.com"))
    	         .build();
    	return apiInfo;

    }
}
