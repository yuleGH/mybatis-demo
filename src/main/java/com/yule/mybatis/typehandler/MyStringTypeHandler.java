package com.yule.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yule
 * @date 2018/8/12 17:52
 */
@MappedTypes({String.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyStringTypeHandler implements TypeHandler<String> {
    private static Logger logger = LoggerFactory.getLogger(MyStringTypeHandler.class);

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        logger.info("使用我的 typeHandler");
        preparedStatement.setString(i, s);
    }

    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        logger.info("使用我的 typeHandler，ResultSet 列名获取字符串");
        return resultSet.getString(s);
    }

    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        logger.info("使用我的 typeHandler，ResultSet 下标获取字符串");
        return resultSet.getString(i);
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        logger.info("使用我的 typeHandler，CallableStatement 下标获取字符串");
        return callableStatement.getString(i);
    }

}
