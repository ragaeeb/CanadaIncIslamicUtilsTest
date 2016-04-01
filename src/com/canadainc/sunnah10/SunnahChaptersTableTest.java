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
public class SunnahChaptersTableTest
{
	private static final String DB_PATH = "res/sunnah10/sunnah10_chapters.db";

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
		DBUtils.cleanUp(DB_PATH);
	}

	/**
	 * Test method for {@link com.canadainc.sunnah10.SunnahChaptersTable#process(java.util.Collection)}.
	 */
	@Test
	public void testProcess()
	{
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
			c.setAutoCommit(false);

			SunnahPrimaryTable<Chapter> sct = new SunnahChaptersTable();
			sct.setLanguage("english");
			sct.setConnection(c);

			ArrayList<Chapter> all = new ArrayList<Chapter>();
			Chapter firstChapter = new Chapter("Intro", 1);
			Chapter secondChapter = new Chapter("Second", 2);

			all.add(firstChapter);
			all.add(secondChapter);
			sct.process(all);

			PreparedStatement ps = c.prepareStatement("SELECT * FROM "+sct.getTableName()+" ORDER BY id");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( firstChapter.title, rs.getString("title") );
			assertEquals( 1, rs.getInt("id") );
			assertEquals( firstChapter.number, rs.getInt("number") );
			assertEquals( 1, sct.getIdFor(firstChapter) );

			assertTrue( rs.next() );
			assertEquals( secondChapter.title, rs.getString("title") );
			assertEquals( 2, rs.getInt("id") );
			assertEquals( secondChapter.number, rs.getInt("number") );
			assertEquals( 2, sct.getIdFor(secondChapter) );

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
	 * Test method for {@link com.canadainc.sunnah10.SunnahChaptersTable#getTableName()}.
	 */
	@Test
	public void testGetTableName() {
		assertEquals( "chapters", new SunnahChaptersTable().getTableName() );
	}
}