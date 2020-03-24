package com.ezoinc.raulqueiroz.calculatrice.api.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ezoinc.raulqueiroz.calculatrice.api.controller.CalculatriceController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Value("${api.inf.versao}")
	private String versao;
	
	@Value("${api.inf.descricao}")
	private String descricao;
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())
          .apis(RequestHandlerSelectors.basePackage(CalculatriceController.class.getPackage().getName()))            
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(getApiInfo());
    }
	
	@SuppressWarnings("rawtypes")
	public ApiInfo getApiInfo() {
		StringVendorExtension vendorExtension = new StringVendorExtension("", "");
		List<VendorExtension> vendorExtensions = new ArrayList<>();
	    vendorExtensions.add(vendorExtension);
		return new ApiInfo("API Ezo Inc", descricao, versao, "http://blog.ezoqc.com/", new Contact("Ezo Inc", "http://blog.ezoqc.com/", " info@ezoqc.com"), "http://blog.ezoqc.com/", "http://blog.ezoqc.com/", vendorExtensions);
	}
	

}
