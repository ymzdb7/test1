package com.winhands.base.orm.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.dialect.SQLServer2008Dialect;

/**
 * 
 * 根据DataSoure自动识别数据库类型 
 */
public class Hibernates {

    /**
     * Initialize the lazy property value.
     * 
     * e.g. Hibernates.initLazyProperty(user.getGroups());
     */
    public static void initLazyProperty(Object proxyedPropertyValue) {
        Hibernate.initialize(proxyedPropertyValue);
    }

    /**
     * 从DataSoure中取出connection, 根据connection的metadata中的jdbcUrl判断Dialect类型.
     * 
     * <p>
     * 仅支持Oracle, H2, MySql, PostgreSql, SQLServer，如需支持多数据库类型，请仿照此类自行编写
     * </p>
     * 
     */
    public static String getDialect(DataSource dataSource) {
        String jdbcUrl = getJdbcUrlFromDataSource(dataSource);

        // 根据jdbc url判断dialect
        if (jdbcUrl.contains(":h2:")) {
            return H2Dialect.class.getName();
        } else if (jdbcUrl.contains(":mysql:")) {
            return MySQL5InnoDBDialect.class.getName();
        } else if (jdbcUrl.contains(":oracle:")) {
            return Oracle10gDialect.class.getName();
        } else if (jdbcUrl.contains(":postgresql:")) {
            return PostgreSQL82Dialect.class.getName();
        } else if (jdbcUrl.contains(":sqlserver:")) {
            return SQLServer2008Dialect.class.getName();
        } else {
            throw new IllegalArgumentException("Unknown Database of " + jdbcUrl);
        }
    }

    /**
     * 根据dataSource获取url
     * 
     * @param dataSource
     * @return
     */
    private static String getJdbcUrlFromDataSource(DataSource dataSource) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            if (connection == null) {
                throw new IllegalStateException("Connection returned by DataSource [" + dataSource + "] was null");
            }
            return connection.getMetaData().getURL();
        } catch (SQLException e) {
            throw new RuntimeException("Could not get database url", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
