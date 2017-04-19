package com.novelbio.portal.base.web;

public abstract class Transformer<O, T> {
	public abstract O rotate(T t);

	public abstract T transform(O o);
}
