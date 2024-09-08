package org.example.calculator;

import java.util.List;

public class Courses {

	private final List<Course> courses;

	public Courses(List<Course> courses) {
		this.courses = courses;
	}

	public double multiplyCreditAndCoursesGrade() {
		return courses.stream()
			.mapToDouble(Course::multiplyCreditAndCoursesGrade)
			.sum();

	}

	public int calculateTotalCompletedCredit() {
		return courses.stream()
			.mapToInt(Course::getCredit)
			.sum();
	}
}
