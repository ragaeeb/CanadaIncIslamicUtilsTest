package com.canadainc.sunnah10.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.islamicutils.io.DBUtils;

public class FileSystemCollectorTest
{
	@Test
	public void testCollect() throws IOException
	{
		FileSystemCollector fsc = new FileSystemCollector("res/sunnah10/shamela/sunan_abu_dawud");
		fsc.collect();
		File[] data = fsc.getResult();
		assertEquals(2, data.length);
		assertEquals("129.txt", data[0].getName());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName( org.sqlite.JDBC.class.getCanonicalName() ); // load the sqlite-JDBC driver using the current class loader
	}

	//@Test
	public void writeToDB() throws Exception
	{
		String fileName = UUID.randomUUID().toString()+".db";
		Connection c = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);

			FileSystemCollector fsc = new FileSystemCollector("res/sunnah10/shamela/sunan_abu_dawud");
			fsc.collect();
			fsc.writeToDB(c);

			PreparedStatement ps = c.prepareStatement("SELECT * FROM sunan_abu_dawud ORDER BY id");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertNull( rs.getString("path") );
			assertEquals( 1, rs.getInt("id") );
			assertEquals( "129.txt", rs.getString("file_name") );
			assertTrue( rs.getString("json").contains("pid") );
			assertTrue( rs.getString("json").contains("cid") );

			assertTrue( rs.next() );
			assertNull( rs.getString("path") );
			assertEquals( 2, rs.getInt("id") );

			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (c != null) {
				c.close();
			}

			DBUtils.cleanUp(fileName);
		}
	}

	@Test
	public void portCollections() throws Exception
	{
		Connection c = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/collections_source.db");

			String[] toPort = new String[]{"bayaan_tabayeen", "kabaair"};

			for (String collection: toPort)
			{
				FileSystemCollector fsc = new FileSystemCollector("/Users/rhaq/workspace/resources/"+collection);
				fsc.collect();
				fsc.writeToDB(c);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (c != null) {
				c.close();
			}
		}
	}
}