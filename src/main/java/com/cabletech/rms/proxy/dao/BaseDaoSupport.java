package com.cabletech.rms.proxy.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.util.Assert.notNull;

/**
 * BaseDaoSupport
 *
 * @author YuLeyuan
 */
public abstract class BaseDaoSupport {
    private SqlSession sqlSession;

    @Autowired
    public final void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * Users should use this method to get a SqlSession to call its statement methods
     * This is SqlSession is managed by spring. Users should not commit/rollback/close it
     * because it will be automatically done.
     *
     * @return Spring managed thread safe SqlSession
     */
    public final SqlSession getSqlSession() {
        return this.sqlSession;
    }

    /**
     * {@inheritDoc}
     */
    protected void checkDaoConfig() {
        notNull(this.sqlSession, "Property 'sqlSessionFactory' are required");
    }
}
