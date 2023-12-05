package com.multi.campus;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	
	//@Resource(name="dataSource-hikari")
	@Resource(name="dataSource-dbcp")
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory factory;
	
	@Test
	public void testMyBatis() {
		try(SqlSession ses=factory.openSession()){
			
			log.info("ses: "+ses);
			
			Connection con=ses.getConnection();
			
			log.info("con: "+con);
			
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testConnection() {
		try(Connection con=dataSource.getConnection()){
			log.info("con: "+con);
		}catch(Exception e) {
			log.error(e);
		}
	}//---------------------
	
	
	

}
