package com.yule.mybatis.objectfactory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * @author yule
 * @date 2018/8/12 19:34
 */
public class MyObjectFactory extends DefaultObjectFactory {

    private Logger logger = LoggerFactory.getLogger(MyObjectFactory.class);

    private static final long serialVersionUID = -8855120656740914948L;

    @Override
    public void setProperties(Properties properties) {
        logger.info("使用我自己的对象工厂 定制属性");
        super.setProperties(properties);
    }

    @Override
    public <T> T create(Class<T> type) {
        logger.info("使用我自己的对象工厂 构建单个对象");
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        logger.info("使用我自己的对象工厂 构建对象列表");
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public <T> boolean isCollection(Class<T> aClass) {
        return false;
    }
}
