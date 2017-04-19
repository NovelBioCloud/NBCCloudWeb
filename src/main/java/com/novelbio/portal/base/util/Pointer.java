package com.novelbio.portal.base.util;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 指针，用来保存临时数据value
 * 
 * @author renyaoxiang
 * @date 2017年4月12日
 * 
 * @param <T>
 */
public class Pointer<T> {
	T value;

	private Pointer() {

	}

	private Pointer(Consumer<Pointer<T>> consumer) {
		consumer.accept(this);
	}

	private Pointer(T value) {
		this.value = value;
	}

	public void set(T value) {
		this.value = value;
	}

	public T get() {
		return this.value;
	}

	public Pointer<T> tryCatch(Consumer<Pointer<T>> consumer, Consumer<Pointer<T>> onSuccess,
			Consumer<Pointer<T>> onError) {
		try {
			consumer.accept(this);
		} catch (Exception e) {
			onError.accept(this);
		}
		onSuccess.accept(this);
		return this;
	}

	public Pointer<T> when(@SuppressWarnings("unchecked") Branch<T>... branchs) {
		boolean result = false;
		for (Branch<T> branch : branchs) {
			result = branch.mapping.apply(value);
			if (result) {
				branch.action.accept(this);
				break;
			}
		}
		return this;
	}

	public static <T> Pointer<T> create() {
		return new Pointer<T>();
	}

	public static <T> Pointer<T> create(T value) {
		return new Pointer<T>(value);
	}

	public static <T> Pointer<T> create(Consumer<Pointer<T>> consumer) {
		return new Pointer<T>(consumer);
	}

	public static class Branch<T> {
		private Function<T, Boolean> mapping;
		private Consumer<Pointer<T>> action;

		public Branch(Function<T, Boolean> check, Consumer<Pointer<T>> action) {
			this.mapping = check;
			this.action = action;
		}

	}

	public static <T> Branch<T> newBranch(Function<T, Boolean> check, Consumer<Pointer<T>> consumer) {
		return new Branch<T>(check, consumer);
	}
}
