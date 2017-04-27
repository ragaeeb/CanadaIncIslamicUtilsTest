package com.canadainc.sunnah10;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import com.canadainc.islamicutils.io.ShamelaReader;

public class SilsilaDaifParserTest
{
	private static final String DB_PATH = "res/sunnah10/sunnah_silsilah_daif.db";
	
	
	@Test
	public void testGetReadNarrations() throws IOException, SQLException
	{
		SilsilaDaifParser s = new SilsilaDaifParser();
		
		for (File f: ShamelaReader.getOrderedFiles("/Users/rhaq/workspace/java/CanadaIncIslamicUtils/res/sunnah10/shamela/silsila_daif")) {
			s.readNarrations(f);
		}
		
		Connection connection = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
		connection.setAutoCommit(false);
		
		System.out.println("Creating database...");			
		SilsilaSahihaTable sst = new SilsilaSahihaTable();
		sst.setConnection(connection);
		sst.process( s.getNarrations() );

		connection.close();
		
		//s.print();
		//Narration n = s.readNarrations( new File("res/sunnah10/shamela/50.txt") );
		
		//assertEquals("1", n.hadithNumber);
	}
}