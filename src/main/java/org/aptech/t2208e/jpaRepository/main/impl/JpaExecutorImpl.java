package org.aptech.t2208e.jpaRepository.main.impl;

import org.aptech.t2208e.jpaRepository.annotation.Entity;
import org.aptech.t2208e.jpaRepository.main.JpaExecutor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class JpaExecutorImpl<T> implements JpaExecutor<T> {
    private Class<T> clazz;
    private String  className;
    public String tableName;
    Field[] fields;

    public static void main(String[] args) {

    }
    public JpaExecutorImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.className = clazz.getSimpleName();
        // get mapping of tableName
        this.tableName = clazz.getAnnotation(Entity.class) != null ?
                clazz.getAnnotation(Entity.class).tableName()
                : className;
        this.fields = clazz.getDeclaredFields();
        System.err.println(this.className);
        System.err.println(this.tableName);
        System.err.println(this.fields);
    }

    @Override
    public List<T> findAll() {
        String sql  = "select * from %table_name%;";
        String trueSql  = sql.replace("%table_name%", this.tableName);
        System.err.println(trueSql);



        return List.of();
    }

    @Override
    public Optional<T> findById(Number id) {
        return Optional.empty();
    }
}
