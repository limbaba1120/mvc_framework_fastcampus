package org.example.mvc;

import java.util.HashMap;
import java.util.Map;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.ForwardController;
import org.example.mvc.controller.HomeController;
import org.example.mvc.controller.UserCreateController;
import org.example.mvc.controller.UserListController;
import org.example.reflection.annotation.RequestMethod;

public class RequestMappingHandlerMapping implements HandlerMapping{
	// key: /users value: UserController 실행해줘
	private Map<HandlerKey, Controller> mappings = new HashMap<>();

	void init() {
	   	// mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
		mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
		mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
		mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());


	}

	@Override
	public Controller findHandler(HandlerKey handlerKey) {
		return mappings.get(handlerKey);
	}

}
