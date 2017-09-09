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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.common.io.DBUtils;

/**
 * @author rhaq
 *
 */
public class SunnahBooksTableTest
{
	private static final String DB_PATH = "res/sunnah10/sunnah10_books.db";

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
	 * Test method for {@link com.canadainc.sunnah10.SunnahBooksTable#process(java.util.Map)}.
	 */
	@Test
	public void testProcess()
	{
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
			c.setAutoCommit(false);

			SunnahBooksTable sct = new SunnahBooksTable( new StubSunnahCollectionsTable() );
			sct.setLanguage("english");
			sct.setConnection(c);

			HashSet<Book> bukhariBooks = new HashSet<Book>(2);
			bukhariBooks.add( new Book(1, "Intro") );
			bukhariBooks.add( new Book(2, "Knowledge") );
			bukhariBooks.add( new Book(2, "Knowledge") );

			HashSet<Book> adabBooks = new HashSet<Book>(2);
			adabBooks.add( new Book(1, "First", "Cool") );
			adabBooks.add( new Book(2, "Second") );

			Map<String, Set<Book>> collectionToBooks = new HashMap<String, Set<Book>>();
			collectionToBooks.put("adab", adabBooks);
			collectionToBooks.put("bukhari", bukhariBooks);

			sct.process(collectionToBooks);

			PreparedStatement ps = c.prepareStatement("SELECT * FROM "+sct.getTableName()+" ORDER BY collection_id,book_id");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( "Intro", rs.getString("title") );
			assertEquals( 1, rs.getInt("collection_id") );
			assertEquals( 1, rs.getInt("book_id") );
			assertNull( rs.getString("transliteration") );

			assertTrue( rs.next() );
			assertEquals( "Knowledge", rs.getString("title") );
			assertEquals( 1, rs.getInt("collection_id") );
			assertEquals( 2, rs.getInt("book_id") );
			assertNull( rs.getString("transliteration") );

			assertTrue( rs.next() );
			assertEquals( "First", rs.getString("title") );
			assertEquals( 2, rs.getInt("collection_id") );
			assertEquals( 1, rs.getInt("book_id") );
			assertEquals( "Cool", rs.getString("transliteration") );

			assertTrue( rs.next() );
			assertEquals( "Second", rs.getString("title") );
			assertEquals( 2, rs.getInt("collection_id") );
			assertEquals( 2, rs.getInt("book_id") );
			assertNull( rs.getString("transliteration") );

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
	 * Test method for {@link com.canadainc.sunnah10.SunnahBooksTable#getTableName()}.
	 */
	@Test
	public void testGetTableName() {
		assertEquals( "books", new SunnahBooksTable(null).getTableName() );
	}
}
