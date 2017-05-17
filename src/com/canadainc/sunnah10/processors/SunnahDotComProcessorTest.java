package com.canadainc.sunnah10.processors;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Before;
import org.junit.Test;

import com.canadainc.common.io.IOUtils;
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
	}

	@Test
	public void sunanAbiDawud() throws IOException
	{
		loadAndAssertSize("abudawud/1.txt", s, 390);
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

	@Test
	public void testHasGrade()
	{
	}

	@Test
	public void testGetPageNumber()
	{
	}


	private void loadAndAssertSize(String file, Processor s, int size) throws IOException {
		SunnahTestUtils.loadAndAssertSize("/Users/rhaq/workspace/resources/sunnah.com/arabic/"+file, s, size);
	}
}