/**
 * 
 */
package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.islamicutils.io.DBUtils;

/**
 * @author rhaq
 *
 */
public class SunnahGradeTableTest
{
	private static final String DB_PATH = "res/sunnah10/sunnah10_grades.db";
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		Class.forName("org.sqlite.JDBC"); // load the sqlite-JDBC driver using the current class loader
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		DBUtils.cleanUp(DB_PATH);
	}

	/**
	 * Test method for {@link com.canadainc.sunnah10.SunnahGradeTable#process(java.util.Map)}.
	 */
	@Test
	public void testProcess()
	{
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
			c.setAutoCommit(false);

			SunnahGradeTable sct = new SunnahGradeTable();
			sct.setLanguage("english");
			sct.setConnection(c);

			HashMap<Integer, Grade> grades = new HashMap<Integer, Grade>();
			grades.put(1, new Grade(1, "Sahih"));
			grades.put(2, new Grade(2, "Hasan"));
			sct.process(grades);

			PreparedStatement ps = c.prepareStatement("SELECT * FROM "+sct.getTableName()+" ORDER BY narration_id");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("narration_id") );
			assertEquals( 1, rs.getInt("author_id") );
			assertEquals( "Sahih", rs.getString("body") );
			assertNull( rs.getString("reference") );

			assertTrue( rs.next() );
			assertEquals( 2, rs.getInt("narration_id") );
			assertEquals( 2, rs.getInt("author_id") );
			assertEquals( "Hasan", rs.getString("body") );
			assertNull( rs.getString("reference") );

			rs.close();
			ps.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed!");
		}
	}

	/**
	 * Test method for {@link com.canadainc.sunnah10.SunnahGradeTable#getTableName()}.
	 */
	@Test
	public void testGetTableName() {
		assertEquals( "grades", new SunnahGradeTable().getTableName() );
	}
}