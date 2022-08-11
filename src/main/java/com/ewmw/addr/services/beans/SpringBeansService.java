package com.ewmw.addr.services.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import javax.management.ReflectionException;

@Service
public class SpringBeansService {
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    public <T> T instantiateNew(Class<T> clazz) throws Exception {
        if (! ReflectionHelper.hasParameterlessPublicConstructor(clazz))
            throw new Exception("Provided type: " + clazz.getName() + " has no empty constructor");

        T newInstance = clazz.newInstance();

        autowireCapableBeanFactory.autowireBean(newInstance);

        return newInstance;
    }
}
