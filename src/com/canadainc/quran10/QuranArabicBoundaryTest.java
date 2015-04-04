package com.canadainc.quran10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.quran10.Sajda.SajdaType;
import com.canadainc.quran10.SurahMetadata.RevealedLocation;

public class QuranArabicBoundaryTest
{
	private QuranArabicBoundary m_instance;
	private static final String PATH = "res/quran10/quran_arabic.db";

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
		m_instance = new QuranArabicBoundary(PATH);
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
			m_instance.createTable(false);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT name FROM sqlite_master WHERE type='table'");
			ResultSet rs = ps.executeQuery();
			advance(rs, 11);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void testPopulateRecitations()
	{
		try {
			m_instance.populateRecitations("res/quran10/qarees.csv", "res/quran10/recitations.csv");
			
			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT * FROM qarees");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( "Abdullah 'Awwad Al-Juhany", rs.getString("name") );
			assertEquals( 1, rs.getInt("level") );
			
			assertTrue( rs.next() );
			assertEquals( "Ali Abdur-rahman al-Hudhaify", rs.getString("name") );
			assertEquals( 2, rs.getInt("level") );
			
			ps = m_instance.getConnection().prepareStatement("SELECT * FROM recitations");
			rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("qaree_id") );
			assertEquals( "Medium Quality", rs.getString("description") );
			assertEquals( "Abdullaah_3awwaad_Al-Juhaynee_128kbps", rs.getString("value") );
			
			assertTrue( rs.next() );
			assertEquals( 6, rs.getInt("qaree_id") );
			assertEquals( "Low Quality", rs.getString("description") );
			assertEquals( "Hudhaify_64kbps", rs.getString("value") );
			
			assertTrue( rs.next() );
			assertEquals( 6, rs.getInt("qaree_id") );
			assertEquals( "Medium Quality", rs.getString("description") );
			assertEquals( "Hudhaify_128kbps", rs.getString("value") );
			
			assertTrue( !rs.next() );
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	
	@Test
	public void testPopulateMetadata()
	{
		HashMap<Integer,SurahMetadata> supplications = new HashMap<Integer,SurahMetadata>();
		supplications.put( 1, new SurahMetadata("Fatiha", 4,2,RevealedLocation.Medinan,5,3) );
		supplications.put( 2, new SurahMetadata("Baqara", 1,3,RevealedLocation.Meccan,6,2) );

		try {
			m_instance.populateMetadata(supplications);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT * FROM surahs");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( "Fatiha", rs.getString("name") );
			assertEquals( 1, rs.getInt("id") );
			assertEquals( 4, rs.getInt("verse_count") );
			assertEquals( 2, rs.getInt("start") );
			assertEquals( 2, rs.getInt("type") );
			assertEquals( 5, rs.getInt("revelation_order") );
			assertEquals( 3, rs.getInt("rukus") );

			assertTrue( rs.next() );
			assertEquals( "Baqara", rs.getString("name") );
			assertEquals( 2, rs.getInt("id") );
			assertEquals( 1, rs.getInt("verse_count") );
			assertEquals( 3, rs.getInt("start") );
			assertEquals( 1, rs.getInt("type") );
			assertEquals( 6, rs.getInt("revelation_order") );
			assertEquals( 2, rs.getInt("rukus") );

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
			m_instance.populateVerses("source_uthmani", "source_clean");
			
			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT * FROM ayahs");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 1, rs.getInt("id") );
			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 1, rs.getInt("verse_number") );
			assertEquals( "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ", rs.getString("content") );
			assertEquals( "بسم الله الرحمن الرحيم", rs.getString("searchable") );

			advance(rs, 6);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testPopulateJuzs()
	{
		HashMap<Integer,SurahAyat> supplications = new HashMap<Integer,SurahAyat>();
		supplications.put( 2, new SurahAyat(1,3) );
		supplications.put( 3, new SurahAyat(5,4) );

		try {
			m_instance.populateJuzs(supplications);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT id,surah_id,verse_number FROM juzs");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 2, rs.getInt("id") );
			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 3, rs.getInt("verse_number") );

			assertTrue( rs.next() );
			assertEquals( 3, rs.getInt("id") );
			assertEquals( 5, rs.getInt("surah_id") );
			assertEquals( 4, rs.getInt("verse_number") );
			assertFalse( rs.next() );
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}


	@Test
	public void testPopulateHizbs()
	{
		HashMap<Integer,SurahAyat> supplications = new HashMap<Integer,SurahAyat>();
		supplications.put( 2, new SurahAyat(1,3) );
		supplications.put( 3, new SurahAyat(5,4) );

		try {
			m_instance.populateHizbs(supplications);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT id,surah_id,verse_number FROM hizbs");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 2, rs.getInt("id") );
			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 3, rs.getInt("verse_number") );

			assertTrue( rs.next() );
			assertEquals( 3, rs.getInt("id") );
			assertEquals( 5, rs.getInt("surah_id") );
			assertEquals( 4, rs.getInt("verse_number") );
			assertFalse( rs.next() );
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}


	@Test
	public void testPopulateManzils()
	{
		HashMap<Integer,SurahAyat> supplications = new HashMap<Integer,SurahAyat>();
		supplications.put( 2, new SurahAyat(1,3) );
		supplications.put( 3, new SurahAyat(5,4) );

		try {
			m_instance.populateManzils(supplications);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT id,surah_id,verse_number FROM manzils");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 2, rs.getInt("id") );
			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 3, rs.getInt("verse_number") );

			assertTrue( rs.next() );
			assertEquals( 3, rs.getInt("id") );
			assertEquals( 5, rs.getInt("surah_id") );
			assertEquals( 4, rs.getInt("verse_number") );
			assertFalse( rs.next() );
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}


	@Test
	public void testPopulateRukus()
	{
		HashMap<Integer,SurahAyat> supplications = new HashMap<Integer,SurahAyat>();
		supplications.put( 2, new SurahAyat(1,3) );
		supplications.put( 3, new SurahAyat(5,4) );

		try {
			m_instance.populateRukus(supplications);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT id,surah_id,verse_number FROM rukus");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 2, rs.getInt("id") );
			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 3, rs.getInt("verse_number") );

			assertTrue( rs.next() );
			assertEquals( 3, rs.getInt("id") );
			assertEquals( 5, rs.getInt("surah_id") );
			assertEquals( 4, rs.getInt("verse_number") );
			assertFalse( rs.next() );
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}


	@Test
	public void testPopulateSupplications()
	{
		ArrayList<Supplication> supplications = new ArrayList<Supplication>();
		supplications.add( new Supplication(2,1,2) );
		supplications.add( new Supplication(3,5,5) );

		try {
			m_instance.populateSupplications(supplications);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT surah_id,verse_number_start,verse_number_end FROM supplications");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 2, rs.getInt("surah_id") );
			assertEquals( 1, rs.getInt("verse_number_start") );
			assertEquals( 2, rs.getInt("verse_number_end") );

			assertTrue( rs.next() );
			assertEquals( 3, rs.getInt("surah_id") );
			assertEquals( 5, rs.getInt("verse_number_start") );
			assertEquals( 5, rs.getInt("verse_number_end") );
			assertFalse( rs.next() );
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}


	@Test
	public void testPopulateMushafPages()
	{
		HashMap<Integer,SurahAyat> supplications = new HashMap<Integer,SurahAyat>();
		supplications.put( 1, new SurahAyat(1,1) );
		supplications.put( 2, new SurahAyat(1,4) );

		try {
			m_instance.populateMushafPages(supplications);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT page_number,surah_id,verse_number FROM mushaf_pages");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 1, rs.getInt("page_number") );
			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 1, rs.getInt("verse_number") );

			assertTrue( rs.next() );
			assertEquals( 2, rs.getInt("page_number") );
			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 4, rs.getInt("verse_number") );
			assertFalse( rs.next() );
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}


	@Test
	public void testPopulateSajdas()
	{
		ArrayList<Sajda> supplications = new ArrayList<Sajda>();
		supplications.add( new Sajda(1,1,SajdaType.Recommended) );
		supplications.add( new Sajda(2,1,SajdaType.Obligatory) );

		try {
			m_instance.populateSajdas(supplications);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT surah_id,verse_number,type FROM sajdas");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 1, rs.getInt("verse_number") );
			assertEquals( 1, rs.getInt("type") );

			assertTrue( rs.next() );
			assertEquals( 2, rs.getInt("surah_id") );
			assertEquals( 1, rs.getInt("verse_number") );
			assertEquals( 2, rs.getInt("type") );
			assertFalse( rs.next() );
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void testPopulateSimilar()
	{
		Map< Supplication, List<Supplication> > similar = new HashMap< Supplication, List<Supplication> >();
		
		Supplication s = new Supplication(1,1,1);
		ArrayList<Supplication> values = new ArrayList<Supplication>();
		values.add( new Supplication(2,1,1) );
		values.add( new Supplication(4,2,6) );
		similar.put(s, values);
		
		s = new Supplication(112,1,1);
		values = new ArrayList<Supplication>();
		values.add( new Supplication(22,1,1) );
		similar.put(s, values);
		
		try {
			m_instance.populateSimilar(similar);

			PreparedStatement ps = m_instance.getConnection().prepareStatement("SELECT * FROM related");
			ResultSet rs = ps.executeQuery();
			assertTrue( rs.next() );

			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 1, rs.getInt("from_verse_id") );
			assertEquals( 1, rs.getInt("to_verse_id") );
			assertEquals( 2, rs.getInt("other_surah_id") );
			assertEquals( 1, rs.getInt("other_from_verse_id") );
			assertEquals( 1, rs.getInt("other_to_verse_id") );
			
			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("surah_id") );
			assertEquals( 1, rs.getInt("from_verse_id") );
			assertEquals( 1, rs.getInt("to_verse_id") );
			assertEquals( 4, rs.getInt("other_surah_id") );
			assertEquals( 2, rs.getInt("other_from_verse_id") );
			assertEquals( 6, rs.getInt("other_to_verse_id") );

			assertTrue( rs.next() );
			assertEquals( 112, rs.getInt("surah_id") );
			assertEquals( 1, rs.getInt("from_verse_id") );
			assertEquals( 1, rs.getInt("to_verse_id") );
			assertEquals( 22, rs.getInt("other_surah_id") );
			assertEquals( 1, rs.getInt("other_from_verse_id") );
			assertEquals( 1, rs.getInt("other_to_verse_id") );
			assertFalse( rs.next() );
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	//@Test
	public void testPopulateImages()
	{
		try {
			m_instance.populateImages("res/quran10/ayats");
		} catch (Exception e) {
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