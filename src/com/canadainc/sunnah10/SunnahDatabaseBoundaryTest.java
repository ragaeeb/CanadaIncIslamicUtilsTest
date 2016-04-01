/**
 * 
 */
package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.islamicutils.io.DBUtils;

/**
 * @author rhaq
 *
 */
public class SunnahDatabaseBoundaryTest
{
	private static final String DB_PATH = "res/sunnah10/sunnah10_english_test.db";

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
	 * Test method for {@link com.canadainc.sunnah10.SunnahDatabaseBoundary#process(java.util.Map, java.util.Collection, java.util.Map, java.util.Map)}.
	 */
	@Test
	public void testProcess()
	{
		try {
			SunnahDatabaseBoundary sct = new SunnahDatabaseBoundary(DB_PATH);
			sct.setLanguage("english");

			HashSet<Book> bukhariBooks = new HashSet<Book>(2);
			bukhariBooks.add( new Book(1, "Intro") );
			Map<String, Set<Book>> collectionToBooks = new HashMap<String, Set<Book>>();
			collectionToBooks.put("bukhari", bukhariBooks);

			ArrayList<Chapter> all = new ArrayList<Chapter>();
			Chapter firstChapter = new Chapter("Intro", 1);
			all.add(firstChapter);

			HashMap<Integer, Grade> grades = new HashMap<Integer, Grade>();
			grades.put(1, new Grade(1, "Sahih"));

			ArrayList<Narration> bukhari = new ArrayList<Narration>();
			bukhari.add( new Narration(1, "FirstChapter", 1, 1, "Introduction", "1", 90, 1, "Sample", 5) );
			Map<String, Collection<Narration>> collectionToNarrations = new HashMap<String, Collection<Narration>>();
			collectionToNarrations.put("bukhari", bukhari);

			sct.process(collectionToBooks, all, grades, collectionToNarrations);
			PreparedStatement ps = sct.getConnection().prepareStatement("SELECT * FROM collections ORDER BY id");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );
			assertEquals( 3, rs.getInt("id") );
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
