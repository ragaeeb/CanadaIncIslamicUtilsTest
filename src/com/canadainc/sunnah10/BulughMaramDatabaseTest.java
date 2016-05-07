package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author rhaq
 *
 */
public class BulughMaramDatabaseTest
{
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName("org.sqlite.JDBC"); // load the sqlite-JDBC driver using the current class loader
	}


	/**
	 * Test method for {@link com.canadainc.sunnah10.BulughMaramDatabase#process()}.
	 */
	@Test
	public void testProcessArabic()
	{
		try
		{
			BulughMaramDatabase bmd = new BulughMaramDatabase("res/sunnah10/static_bulugh.db");
			bmd.setLanguage("arabic");
			List<Narration> narrations = bmd.process();
			bmd.close();

			assertEquals( 3, narrations.size() );
			assertEquals( new Narration(0, "F", 1, 2, "Q", "128", 2102001, 1, "X"), narrations.get(0) );
			assertEquals( new Narration(0, null, 1, 2, "Q", "128", 2102002, 2, "Y"), narrations.get(1) );
			assertEquals( new Narration(0, null, 1, 7, "R", "606", 2110004, 1, "A"), narrations.get(2) );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Test method for {@link com.canadainc.sunnah10.BulughMaramDatabase#process()}.
	 */
	@Test
	public void testProcessEnglish()
	{
		try
		{
			BulughMaramDatabase bmd = new BulughMaramDatabase("res/sunnah10/static_bulugh.db");
			bmd.setLanguage("english");
			List<Narration> narrations = bmd.process();
			bmd.close();

			assertEquals( 3, narrations.size() );
			assertEquals( new Narration(2102001, "The times of prayer", 1, 2, "Prayer", "163", 2102001, 1, "Sunrise"), narrations.get(0) );
			assertEquals( new Narration(2102002, "The times of prayer", 1, 2, "Prayer", "164", 2102002, 2, "Muslim"), narrations.get(1) );
			assertEquals( new Narration(2110004, "Conditions of Business Transactions and Those which are Forbidden", 1, 7, "Business Transactions", "802", 2110004, 1, "Halal"), narrations.get(2) );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}