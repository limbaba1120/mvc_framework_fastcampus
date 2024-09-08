package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.mvc.annotation.RequestMapping;
import org.example.reflection.annotation.RequestMethod;

@org.example.mvc.annotation.Controller
public class HomeController implements Controller{
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "home";
	}
}
