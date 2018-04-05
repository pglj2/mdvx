package com.midiavox.mdvx;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SwaggerDocumentSetup extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setTitle("MidiaVox");
		//beanConfig.setDescription("");
		beanConfig.setSchemes(new String[]{"http"});
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/mdvx/services");
		
		beanConfig.setResourcePackage("com.midiavox.mdvx.services");
		beanConfig.setScan(true);
	}

}
