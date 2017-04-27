package com.canadainc.sunnah10;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SilsilaSaheehaParserTest
{
	private static final String DB_PATH = "res/sunnah10/sunnah_silsilah.db";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName("org.sqlite.JDBC"); // load the sqlite-JDBC driver using the current class loader
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//DBUtils.cleanUp(DB_PATH);
	}

	@Test
	public void testParse()
	{
		File[] all = new File("/Users/rhaq/workspace/java/CanadaIncIslamicUtils/res/sunnah10/silsila_ar").listFiles( new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		
		//all = new File[]{ new File("/Users/rhaq/workspace/java/CanadaIncIslamicUtils/res/sunnah10/silsila_ar/silsila_186.txt") };
		
		SilsilaSaheehaParser ipp = new SilsilaSaheehaParser();
		try {
			ArrayList<Narration> narrations = new ArrayList<Narration>();
			for (File f: all) {
				System.out.println("Processing..."+f.getName());
				narrations.addAll( ipp.parse(f) );
			}

			Connection connection = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
			connection.setAutoCommit(false);
			
			System.out.println("Creating database...");			
			SilsilaSahihaTable sst = new SilsilaSahihaTable();
			sst.setConnection(connection);
			sst.process(narrations);

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}