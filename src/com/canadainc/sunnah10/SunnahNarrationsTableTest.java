/**
 * 
 */
package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.islamicutils.io.DBUtils;

/**
 * @author rhaq
 *
 */
public class SunnahNarrationsTableTest
{
	private static final String DB_PATH = "res/sunnah10/sunnah10_narrations.db";

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
	 * Test method for {@link com.canadainc.sunnah10.SunnahNarrationsTable#process(java.util.Map)}.
	 */
	@Test
	public void testProcess()
	{
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
			c.setAutoCommit(false);

			SunnahNarrationsTable sct = new SunnahNarrationsTable(  new StubSunnahCollectionsTable(), new StubSunnahChaptersTable() );
			sct.setLanguage("english");
			sct.setConnection(c);

			ArrayList<Narration> bukhari = new ArrayList<Narration>();
			bukhari.add( new Narration(1, "FirstChapter", 1, 1, "Introduction", "1", 90, 1, "Sample", 5) );

			ArrayList<Narration> adab = new ArrayList<Narration>();
			adab.add( new Narration(2, "LastChapter", 2, 2, "Conclusion", "2", 91, 2, "Something") );

			Map<String, Collection<Narration>> collectionToBooks = new HashMap<String, Collection<Narration>>();
			collectionToBooks.put("adab", adab);
			collectionToBooks.put("bukhari", bukhari);

			sct.process(collectionToBooks);

			PreparedStatement ps = c.prepareStatement("SELECT * FROM "+sct.getTableName()+" ORDER BY id");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("chapter_id") );
			assertEquals( 1, rs.getInt("collection_id") );
			assertEquals( 1, rs.getInt("book_id") );
			assertEquals( 1, rs.getInt("in_book_number") );
			assertEquals( "1", rs.getString("hadith_number") );
			assertEquals( "Sample", rs.getString("body") );
			assertEquals( 5, rs.getInt("translator_id") );

			assertTrue( rs.next() );
			assertEquals( 2, rs.getInt("chapter_id") );
			assertEquals( 2, rs.getInt("collection_id") );
			assertEquals( 2, rs.getInt("book_id") );
			assertEquals( 2, rs.getInt("in_book_number") );
			assertEquals( "2", rs.getString("hadith_number") );
			assertEquals( "Something", rs.getString("body") );
			assertEquals( 0, rs.getInt("translator_id") );

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
	 * Test method for {@link com.canadainc.sunnah10.SunnahNarrationsTable#getTableName()}.
	 */
	@Test
	public void testGetTableName() {
		assertEquals( "narrations", new SunnahNarrationsTable(null,null).getTableName() );
	}


	private class StubSunnahChaptersTable implements SunnahPrimaryTable<Chapter>
	{
		@Override
		public void setConnection(Connection c) {}

		@Override
		public String getTableName() {
			return null;
		}

		@Override
		public void setLanguage(String language) {}

		@Override
		public int getIdFor(Chapter x) {
			return x.number;
		}

		@Override
		public void process(Collection<Chapter> elements) throws SQLException {}	
	}
}