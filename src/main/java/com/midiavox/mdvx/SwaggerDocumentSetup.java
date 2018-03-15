package com.midiavox.mdvx;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerDocumentSetup extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setTitle("MidiaVox, General API's");
		beanConfig.setDescription("Our Core API's that can be used for all sorts of stuff. Please Enjoy.");
		
		beanConfig.setSchemes(new String[] {"http"});
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/mdvx/services");
		beanConfig.setResourcePackage("com.midiavox.mdvx.services");
		
		beanConfig.setScan(true);
	}

}
