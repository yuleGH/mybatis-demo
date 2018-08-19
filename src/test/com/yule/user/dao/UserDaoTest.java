package com.yule.user.dao;

import com.yule.user.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class UserDaoTest {

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSessionFactory getSqlSessionFactory() {
        //单例
        if (sqlSessionFactory == null) {
            try {
                InputStream inputStream = Resources.getResourceAsStream("configuration.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * 初始化sqlSessionFactory，针对数据库的密码和用户名加密的情况
     * @throws IOException
     */
    public void initSqlSessionFactoryForEncodeDbInfo() throws IOException {
        //读入配置文件流
        InputStream inputStream = Resources.getResourceAsStream("configuration.xml");
        Reader reader = new InputStreamReader(inputStream);
        //读入属性文件
        Reader propertiesReader = new InputStreamReader(Resources.getResourceAsStream("config/system/db.properties"));
        Properties properties = new Properties();
        properties.load(propertiesReader);
        //解密
        properties.setProperty("username", decode(properties.get("username")));
        properties.setProperty("password", decode(properties.get("password")));
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);
    }

    /**
     * 一种解密方式
     * @param username
     * @return
     */
    private String decode(Object username) {
        return username.toString();
    }

    @Test
    public void test(){

    }

    @Test
    public void testQueryUserList() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.queryUserList();

        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    public void testQueryUserByName() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.queryUserByName("xiaoxiao");

        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void testQueryUserByName2(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("com.yule.user.dao.UserDao.queryUserByName", "xiaoxiao");

        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void testInsertUser(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);

        User user = new User("你说", "33");

        int i = mapper.insertUser(user);

        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();

        System.out.println(user);
    }

    @Test
    public void testUpdateUserById(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User("4", "xiao", "34");
        int count = userDao.updateUserById(user);

        user = userDao.queryUserById("4");
        sqlSession.commit();
        sqlSession.close();

        System.out.println(count);
        System.out.println(user);
    }

    @Test
    public void testDeleteUserById(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int count = userDao.deleteUserById("4");
        User user = userDao.queryUserById("4");
        sqlSession.commit();
        sqlSession.close();

        System.out.println(count);
        System.out.println(user);
    }

    @Test
    public void testQueryUserById(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.queryUserById("4");
        System.out.println(user);
        sqlSession.close();
    }


}