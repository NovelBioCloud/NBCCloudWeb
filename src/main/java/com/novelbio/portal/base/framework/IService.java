package com.novelbio.portal.base.framework;

import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public interface IService<T, K extends Serializable, Dao> {

	void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
