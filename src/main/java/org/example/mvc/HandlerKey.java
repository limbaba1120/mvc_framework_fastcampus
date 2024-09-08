package org.example.mvc;

import java.util.Objects;

import org.example.reflection.annotation.RequestMethod;


public class HandlerKey {
	private String url;
	private RequestMethod requestMethod;

	public HandlerKey(RequestMethod requestMethod, String url) {
		this.requestMethod = requestMethod;
		this.url = url;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HandlerKey that = (HandlerKey) o;
		return Objects.equals(url, that.url) && requestMethod == that.requestMethod;
	}

	@Override
	public int hashCode() {
		return Objects.hash(url, requestMethod);
	}

	@Override
	public String toString() {
		return "HandlerKey{" +
			"url='" + url + '\'' +
			", requestMethod=" + requestMethod +
			'}';
	}
}