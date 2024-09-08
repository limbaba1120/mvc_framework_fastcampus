package org.example.di.bean;

import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.example.di.annotation.Controller;
import org.example.di.annotation.Service;
import org.example.di.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

class BeanFactoryTest {
	private Reflections reflections;

	private BeanFactory beanFactory;

	@BeforeEach
	void setUp() {
		reflections = new Reflections("org.example.di");
		// UserController, UserService
		Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class);
		beanFactory = new BeanFactory(preInstantiatedClazz);
	}

	private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
		Set<Class<?>> beans = new HashSet<>();

		for (Class<? extends Annotation> annotation : annotations) {
			beans.addAll(reflections.getTypesAnnotatedWith(annotation));
		}

		return beans;
	}

	@Test
	void diTest() {
		UserController userController = beanFactory.getBean(UserController.class);

		assertThat(userController).isNotNull();
		assertThat(userController.getUserService()).isNotNull();
	}
}