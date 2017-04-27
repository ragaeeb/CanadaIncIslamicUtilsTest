package com.canadainc.sunnah10;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class ShamelaJihadParserTest
{
	private static final String DB_PATH = "res/sunnah10/jihad.db";
	
	@Test
	public void testReadNarrations() throws IOException, SQLException
	{
		ShamelaJihadParser s = new ShamelaJihadParser();
		
		File[] all = new File("/Users/rhaq/workspace/resources/shamela/arabic/jihad").listFiles( new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		
		for (File f: all) {
			s.readNarrations(f);
		}
		
		Connection connection = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
		connection.setAutoCommit(false);
		
		System.out.println("Creating database...");			

		s.setConnection(connection);
		s.process( s.getNarrations() );

		connection.close();
		
	}
}