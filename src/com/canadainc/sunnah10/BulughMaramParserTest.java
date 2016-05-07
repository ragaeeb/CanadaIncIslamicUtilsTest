/**
 * 
 */
package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author rhaq
 *
 */
public class BulughMaramParserTest
{

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	/**
	 * Test method for {@link com.canadainc.sunnah10.BulughMaramParser#parseArabicChapter(java.io.File)}.
	 */
	@Test
	public void testParseArabicChapter()
	{
		try
		{
			BulughMaramParser bmap = new BulughMaramParser();
			List<Narration> narrations = bmap.parseArabicChapter( new File("res/sunnah10/static/12waktus.html") );

			assertEquals( 2, narrations.size() );

			Narration n = narrations.get(0);
			assertEquals( 1, n.id );
			assertEquals( "128", n.hadithNumber );
			assertTrue( !n.text.isEmpty() );

			n = narrations.get(1);
			assertEquals( 2, n.id );
			assertEquals( "129", n.hadithNumber );
			assertTrue( !n.text.isEmpty() );
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed!");
		}
	}


	/**
	 * Test method for {@link com.canadainc.sunnah10.SunnahPopulator#readStaticBulugh()}.
	 */
	@Test
	public void testReadStaticBulugh()
	{
		try
		{
			BulughMaramParser bmp = new BulughMaramParser();
			List<Narration> narrations = bmp.readStaticBulugh( new File("res/sunnah10/static") );

			assertEquals(1, narrations.size());

			Narration n = narrations.get(0);
			assertEquals( 1, n.id );
			assertEquals( "Zakah", n.book.name );
			assertEquals( 4, n.book.id );
			assertNull( n.book.transliteration );
			assertEquals( 4, n.chapter.number );
			assertEquals( "Division of Zakah (Zakah recipients)", n.chapter.title );
			assertNull(n.grading);
			assertEquals( "666", n.hadithNumber );
			assertEquals( 1, n.inBookNumber );
			assertEquals( 2021, n.translator );
			assertEquals( "Qabisah bin Mukhariq Al-Hilali (RAA) narrated that.", n.text );
		} catch (Exception e)
		{
			e.printStackTrace();
			fail("Failed");
		}
	}
}
