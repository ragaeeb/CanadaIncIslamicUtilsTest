package com.canadainc.sunnah10.utils;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlainTextParserTest
{
	private static Connection c;
	private PlainTextParser p;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName( org.sqlite.JDBC.class.getCanonicalName() ); // load the sqlite-JDBC driver using the current class loader
		c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/translations.db");
	}
	
	
	@Before
	public void setUp() throws Exception {
		p = new PlainTextParser();
	}


	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		c.close();
	}

	@Test
	public void testRead() throws Exception
	{
		p.read("res/sunnah10/jaami.txt");
		
		assertEquals( 208, p.getNarrations().size() );
		
		p.write(c);
	}
}