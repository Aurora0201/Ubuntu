package com.framework.spring.dao;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ListAndSet {
    private List<String> name;
    private Set<Woman> women;
    private Map<String, String> map;
    private Properties properties;

    @Override
    public String toString() {
        return "ListAndSet{" +
                "name=" + name +
                ", women=" + women +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public void setWomen(Set<Woman> women) {
        this.women = women;
    }
}
