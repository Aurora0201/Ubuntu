package org.myspring.core;

public interface ApplicationContext {
    /**
     * return object by beanName
     * @param beanName the id of bean
     * @return instance of bean
     */
    Object getBean(String beanName);
}
