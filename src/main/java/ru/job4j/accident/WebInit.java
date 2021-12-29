package ru.job4j.accident;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.job4j.accident.config.WebConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInit implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ac =
			new AnnotationConfigWebApplicationContext();
		ac.register(WebConfig.class);
		ac.refresh();
		DispatcherServlet servlet = new DispatcherServlet(ac);
		ServletRegistration.Dynamic dispatcher =
			servletContext.addServlet("SpringRootController", servlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}
