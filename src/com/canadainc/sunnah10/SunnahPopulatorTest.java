/**
 * 
 */
package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.islamicutils.io.DBUtils;

/**
 * @author rhaq
 *
 */
public class SunnahPopulatorTest
{
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
	public static void tearDownAfterClass() throws Exception {
		DBUtils.cleanUp("res/sunnah10/sunnah_english.db");
	}

	/**
	 * Test method for {@link com.canadainc.sunnah10.SunnahPopulator#process()}.
	 */
	@Test
	public void testProcess()
	{
		try {
			SunnahPopulator sp = new SunnahPopulator("english", "res/sunnah10");
			sp.process();
			
			PreparedStatement ps = sp.getConnection().prepareStatement("SELECT * FROM collections ORDER BY id");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("id") );
			assertFalse( rs.next() );

			ps = sp.getConnection().prepareStatement("SELECT * FROM books ORDER BY book_id");
			rs = ps.executeQuery();
			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("book_id") );
			assertFalse( rs.next() );
			
			ps = sp.getConnection().prepareStatement("SELECT * FROM chapters ORDER BY id");
			rs = ps.executeQuery();
			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("id") );
			assertTrue( rs.next() );
			assertEquals( 2, rs.getInt("id") );
			assertFalse( rs.next() );
			
			ps = sp.getConnection().prepareStatement("SELECT * FROM grades ORDER BY id");
			rs = ps.executeQuery();
			assertTrue( rs.next() );
			assertEquals( 803900, rs.getInt("narration_id") );
			assertTrue( rs.next() );
			assertEquals( 800010, rs.getInt("narration_id") );
			assertFalse( rs.next() );
			
			ps = sp.getConnection().prepareStatement("SELECT * FROM narrations ORDER BY id");
			rs = ps.executeQuery();
			assertTrue( rs.next() );
			assertEquals( 900010, rs.getInt("id") );
			assertTrue( rs.next() );
			assertEquals( 903900, rs.getInt("id") );
			assertFalse( rs.next() );
			
			rs.close();
			ps.close();
			ps.getConnection().close();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Failed!");
		}
	}
}