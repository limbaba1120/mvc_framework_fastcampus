package org.example.reflection;

import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.example.reflection.annotation.Controller;
import org.example.reflection.annotation.Service;
import org.example.reflection.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTest {

	private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

	@Test
	void controllerScan() {
		// 해당 package 밑에 Controller Annotation이 붙어있는 걸 찾아서 Bean에 등록
		Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class));

		logger.debug("beans: [{}]", beans);
	}

	@Test
	void serviceScan() {
		// 해당 package 밑에 Controller Annotation이 붙어있는 걸 찾아서 Bean에 등록
		Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Service.class));

		logger.debug("beans: [{}]", beans);
	}

	private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
		Reflections reflections = new Reflections("org.example.reflection");

		Set<Class<?>> beans = new HashSet<>();
		annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
		return beans;
	}

	@Test
	void showClass() {
		Class<User> clazz = User.class;
		logger.debug(clazz.getName());

		logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
		logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
		logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
	}

	@Test
	void load() throws ClassNotFoundException {
		// 1번째 방법
		Class<User> clazz = User.class;

		// 2번째 방법
		User user = new User("serverwizard", "임건우");
		Class<? extends User> clazz2 = user.getClass();

		// 3번째 방법
		Class<?> clazz3 = Class.forName("org.example.reflection.model.User");

		logger.debug("clazz1: [{}]", clazz);
		logger.debug("clazz2: [{}]", clazz2);
		logger.debug("clazz3: [{}]", clazz3);

		assertThat(clazz == clazz2).isTrue();
		assertThat(clazz2 == clazz3).isTrue();
		assertThat(clazz == clazz3).isTrue();
	}
}
