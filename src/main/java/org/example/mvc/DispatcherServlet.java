package org.example.mvc;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.example.reflection.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	private List<HandlerMapping> handlerMappings;

	private List<ViewResolver> viewResolvers;

	private List<HandlerAdapter> handlerAdapters;

	@Override
	public void init() throws ServletException {
		RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
		rmhm.init();

		AnnotationHandlerMapping ahm = new AnnotationHandlerMapping("org.example.mvc");
		ahm.initialize();

		handlerMappings = List.of(rmhm, ahm);

		handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());
		viewResolvers = Collections.singletonList(new JspViewResolver());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {
		logger.info("[DispatcherServlet] Service Started");
		String requestURI = request.getRequestURI();
		RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());
		// handlermapping 을 통해서 handler 찾음 그리고 작업 위임

		try {
			Object handler = handlerMappings.stream()
				.filter(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)) != null)
				.map(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)))
				.findFirst()
				.orElseThrow(() -> new ServletException("No Handler for [" + requestMethod + ", " + requestURI + "]"));

			HandlerAdapter handlerAdapter = handlerAdapters.stream()
				.filter(ha -> ha.supports(handler))
				.findFirst()
				.orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));

			ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

			for (ViewResolver viewResolver : viewResolvers) {
				View view = viewResolver.resolveView(modelAndView.getViewName());
				view.render(modelAndView.getModel(), request, response);
			}

		} catch (Exception e) {
			logger.error("exception occured: [{}]", e.getMessage(), e);
			throw new ServletException(e);
		}
	}
}
