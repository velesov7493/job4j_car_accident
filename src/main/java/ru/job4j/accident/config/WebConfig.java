package ru.job4j.accident.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/styles/**")
			.addResourceLocations("/WEB-INF/template/css/");
		registry
			.addResourceHandler("/scripts/**")
			.addResourceLocations("/WEB-INF/template/js/");
		registry
			.addResourceHandler("/fonts/**")
			.addResourceLocations("/WEB-INF/template/fonts/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/views/");
		vr.setSuffix(".jsp");
		registry.viewResolver(vr);
	}
}
