package com.canadainc.sunnah10.processors.shamela;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Book;
import com.canadainc.sunnah10.Chapter;
import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaSunanNasaiVowelledProcessorTest
{
	private ShamelaSunanNasaiVowelledProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaSunanNasaiVowelledProcessor();
	}


	@Test
	public void process() throws IOException
	{
		SunnahTestUtils.loadAndAssertShamelaSize("nasai/0001.txt", s, 0); // load book
		SunnahTestUtils.loadAndAssertShamelaSize("nasai/0002.txt", s, 0); // load chapter
		SunnahTestUtils.loadAndAssertShamelaSize("nasai/0104.txt", s, 1); // load narration
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 57, "صحيح",
				"أَخْبَرَنَا إِسْحَاقُ بْنُ إِبْرَاهِيمَ قَالَ: أَنْبَأَنَا",
				"عَنِ النَّبِيِّ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ مِثْلهُ");
		assertEquals( new Book(1, "كِتَابُ الطَّهَارَةِ"), s.getNarrations().get(0).book);
		assertEquals( new Chapter("تَأْوِيلُ قَوْلِهِ عَزَّ وَجَلَّ: {إِذَا قُمْتُمْ إِلَى الصَّلَاةِ فَاغْسِلُوا وُجُوهَكُمْ وَأَيْدِيَكُمْ إِلَى الْمَرَافِقِ} ", 1), s.getNarrations().get(0).chapter);
	}
	
	
	@Test
	public void noGrade() throws IOException
	{
		SunnahTestUtils.loadAndAssertShamelaSize("nasai/0623.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 384, null,
				"قَالَ إِسْحَاقُ: أَنْبَأَنَا أَبُو مُعَاوِيَةَ، عَنِ الْأَعْمَشِ بِهَذَا الْإِسْنَادِ مِثْلَهُ");
	}
	
	
	@Test
	public void hadithInFooter() throws IOException
	{
		SunnahTestUtils.loadAndAssertShamelaSize("nasai/0518.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 317, "صحيح",
				"فِي يَدَيْهِ، وَمَسَحَ بِهِمَا وَجْهَهُ وَكَفَّيْهِ مَرَّةً وَاحِدَةً");
	}


	@Test
	public void testChapter() throws IOException
	{
		SunnahTestUtils.loadAndAssertShamelaSize("nasai/0002.txt", s, 0); // load 1st chapter
		SunnahTestUtils.loadAndAssertShamelaSize("nasai/0103.txt", s, 0); // load 2nd chapter
		SunnahTestUtils.loadAndAssertShamelaSize("nasai/0104.txt", s, 1); // load narration
		assertEquals( new Chapter("بَابُ الْمَاءِ الدَّائِمِ", 2), s.getNarrations().get(0).chapter);
	}
}