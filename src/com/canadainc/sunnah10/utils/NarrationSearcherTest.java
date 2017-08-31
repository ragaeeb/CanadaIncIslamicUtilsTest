package com.canadainc.sunnah10.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Before;
import org.junit.Test;

public class NarrationSearcherTest
{
	private static Connection c;

	@Before
	public void setUpBeforeClass() throws Exception
	{
		Class.forName( org.sqlite.JDBC.class.getCanonicalName() ); // load the sqlite-JDBC driver using the current class loader
		c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/collections_source.db");
	}

	@Test
	public void testPrompt() throws Exception
	{
		NarrationSearcher n = new NarrationSearcher();
		n.prompt(c);
	}
}