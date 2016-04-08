/**
 * 
 */
package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.islamicutils.io.DBUtils;

/**
 * @author rhaq
 *
 */
public class SunnahCollectionsTableTest
{
	private static final String DB_PATH = "res/sunnah10/sunnah10_collections.db";

	@BeforeClass
	public static void begin() throws Exception {
		Class.forName("org.sqlite.JDBC"); // load the sqlite-JDBC driver using the current class loader
	}

	/**
	 * Test method for {@link com.canadainc.sunnah10.SunnahCollectionsTable#process(java.util.Collection)}.
	 */
	@Test
	public void testProcess()
	{
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
			c.setAutoCommit(false);

			SunnahPrimaryTable<String> sct = new SunnahCollectionsTable();
			sct.setLanguage("english");
			sct.setConnection(c);

			ArrayList<String> all = new ArrayList<String>();
			all.add("bukhari");
			all.add("adab");
			sct.process(all);

			PreparedStatement ps = c.prepareStatement("SELECT * FROM "+sct.getTableName()+" ORDER BY id");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( "Adab Al-Mufrad", rs.getString("name") );
			assertEquals( 2, rs.getInt("id") );
			assertEquals( 2, sct.getIdFor("adab") );
			assertEquals( 109, rs.getInt("author") );

			assertTrue( rs.next() );
			assertEquals( 3, rs.getInt("id") );
			assertEquals( 3, sct.getIdFor("bukhari") );
			assertEquals( 109, rs.getInt("author") );

			assertFalse( rs.next() );

			rs.close();
			ps.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed!");
		}
	}

	/**
	 * Test method for {@link com.canadainc.sunnah10.SunnahCollectionsTable#getTableName()}.
	 */
	@Test
	public void testGetTableName() {
		assertEquals( "collections", new SunnahCollectionsTable().getTableName() );
	}

	@AfterClass
	public static void tearDownClass() {
		DBUtils.cleanUp(DB_PATH);
	}
}
