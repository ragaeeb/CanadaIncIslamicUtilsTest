package com.canadainc.sunnah10.processors;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Book;
import com.canadainc.sunnah10.Chapter;
import com.canadainc.sunnah10.Narration;

public class SunnahDotComProcessorTest
{
	private SunnahDotComProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new SunnahDotComProcessor();
	}

	@Test
	public void testPreprocess()
	{
		JSONObject j = new JSONObject();
		j.put("englishURN", "1251710");
		j.put("hadithNumber", "155");

		s.preprocess(j);
		assertEquals( "172", j.get("hadithNumber") );
	}

	@Test
	public void sunanAbiDawud() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_com/arabic/abudawud/1.txt", s, 390);
		Narration n = s.getNarrations().get(0);
		SunnahTestUtils.assertNarration(n, 900010, "حسن صحيح", "حَدَّثَنَا عَبْدُ اللَّهِ بْنُ مَسْلَمَةَ بْنِ", "وسلم كَانَ إِذَا ذَهَبَ الْمَذْهَبَ أَبْعَدَ");
		assertEquals( new Book(1, "كتاب الطهارة"), n.book );
		assertEquals( new Chapter("باب التَّخَلِّي عِنْدَ قَضَاءِ الْحَاجَةِ", 1), n.chapter );
		assertNull(n.commentary);
		assertEquals("1", n.hadithNumber);
		assertEquals(1, n.inBookNumber);

		n = s.getNarrations().get( s.getNarrations().size()-1 );
		SunnahTestUtils.assertNarration(n, 903900, "صحيح", "صلى الله عليه وسلم بِمِثْلِهِ", "حَدَّثَنَا مُوسَى بْنُ إِسْمَاعِيلَ");
		assertEquals( new Book(1, "كتاب الطهارة"), n.book );
		assertEquals( new Chapter("باب الْبُصَاقِ يُصِيبُ الثَّوْبَ", 144), n.chapter );
		assertNull(n.commentary);
		assertEquals("390", n.hadithNumber);
		assertEquals(390, n.inBookNumber);
	}


	public void testIgnored() throws Exception
	{
		JSONObject j = new JSONObject();
		j.put("englishURN", "1262090");

		assertFalse( s.preprocess(j) );
	}


	@Test
	public void breakUp() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_com/english/ibnmajah/12.txt", s, 171);

		Narration first = null;
		Narration second = null;

		for (Narration n: s.getNarrations())
		{
			if (n.hadithNumber.equals("2238")) {
				first = n;
			} else if ( n.hadithNumber.equals("2239") ) {
				second = n;
			}
		}

		assertTrue( first.text.equals("It was narrated from Ibn 'Umar that the Prophet (ﷺ) said:\n\"O Allah, bless my nation in their early mornings.\".") );
		assertTrue( second.text.equals("It was narrated from Abu Hurairah that the Prophet (ﷺ) said: \"Whoever buys a Musarrah, he has the choice (of annulling the deal) for three days. If he returns it, then he must also give a Sa' of dates, not Samra'.\" Meaning wheat.</b>") );
	}


	@Test
	public void appendToPrev() throws Exception
	{
		JSONObject j = new JSONObject();
		j.put("englishURN", "1290300");
		j.put("hadithNumber", "2");
		j.put("hadithText", "Some text");

		s.process(j);
		assertEquals( 1, s.getNarrations().size() );

		s.preprocess(j);
		assertEquals( 1, s.getNarrations().size() );
		assertEquals( "Some text Some text", s.getNarrations().get(0).text );
	}


	@Test
	public void testGetPageNumber()
	{
	}
}