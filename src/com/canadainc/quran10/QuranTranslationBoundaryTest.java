package com.canadainc.quran10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuranTranslationBoundaryTest
{
	private QuranTranslationBoundary m_instance;
	private static final String PATH = "res/quran10/quran_english.db";

	@BeforeClass
	public static void begin() throws Exception
	{
		Class.forName("org.sqlite.JDBC"); // load the sqlite-JDBC driver using the current class loader

		File f = new File(PATH);
		f.delete();
		f.createNewFile();
	}


	@Before
	public void setUp() throws Exception
	{
		m_instance = new QuranTranslationBoundary(PATH);
	}


	@After
	public void tearDown() throws Exception
	{
		m_instance.getConnection().close();
	}


	@Test
	public void testCreateTable()
	{
		try {
			m_instance.createTable();

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT name FROM sqlite_master WHERE type='table'");
			ResultSet rs = ps.executeQuery();
			advance(rs, 2);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void testPopulateChapters()
	{
		HashMap<Integer,ChapterTranslation> supplications = new HashMap<Integer,ChapterTranslation>();
		supplications.put( 1, new ChapterTranslation("translation1","transliteration1") );
		supplications.put( 2, new ChapterTranslation("translation2","transliteration2") );

		try {
			m_instance.populateChapters(supplications);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT * FROM chapters");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("id") );
			assertEquals( "transliteration1", rs.getString("transliteration") );
			assertEquals( "translation1", rs.getString("translation") );

			assertTrue( rs.next() );
			assertEquals( 2, rs.getInt("id") );
			assertEquals( "transliteration2", rs.getString("transliteration") );
			assertEquals( "translation2", rs.getString("translation") );

			assertFalse( rs.next() );
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	

	@Test
	public void testPopulateVerses()
	{
		try {
			m_instance.populateVerses("translation_source", "english");
			
			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT * FROM verses");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 1, rs.getInt("chapter_id") );
			assertEquals( 1, rs.getInt("verse_id") );
			assertEquals( "In the Name of Allah, the Most Beneficent, the Most Merciful.", rs.getString("translation") );

			advance(rs, 6);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	private void advance(ResultSet rs, int n) throws SQLException
	{
		for (int i = 0; i < n; i++) {
			assertTrue( rs.next() );
		}
	}
}