package com.framework.spring.bean;

import org.springframework.beans.factory.FactoryBean;

public class InstanceStarFactory implements FactoryBean<Star> {
    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }

    @Override
    public Star getObject() throws Exception {
        return new Star();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
