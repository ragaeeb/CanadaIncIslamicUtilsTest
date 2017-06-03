package com.canadainc.sunnah10.processors.shamela.shared;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Book;
import com.canadainc.sunnah10.Chapter;
import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.shared.ShamelaIbnMajahVowelledProcessor;

public class ShamelaIbnMajahVowelledProcessorTest
{
	private ShamelaIbnMajahVowelledProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaIbnMajahVowelledProcessor();
	}

	@Test
	public void process() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("ibnmajah_vowels/0001.txt", s, 0);
		SunnahTestUtils.loadAndAssertSize("ibnmajah_vowels/0002.txt", s, 0);
		SunnahTestUtils.loadAndAssertSize("ibnmajah_vowels/0003.txt", s, 0);
		SunnahTestUtils.loadAndAssertSize("ibnmajah_vowels/0004.txt", s, 1);
		Narration narration = s.getNarrations().get(0);
		SunnahTestUtils.assertNarration(narration, 1, "صحيح",
				"حَدَّثَنَا أَبُو بَكْرِ بْنُ أَبِي شَيْبَةَ، قَالَ: حَدَّثَنَا شَرِيكٌ",
				"فَخُذُوهُ، وَمَا نَهَيْتُكُمْ عَنْهُ فَانْتَهُوا");
		assertEquals(new Book(0, "افتتاح الكتاب في الإيمان وفضائل الصحابة والعلم"), narration.book);
		assertEquals(new Chapter("بَابُ اتِّبَاعِ سُنَّةِ رَسُولِ اللَّهِ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ", 1), narration.chapter);
	}


	@Test
	public void processWithSharh() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("ibnmajah_vowels/0008.txt", s, 1);
		Narration narration = s.getNarrations().get(0);
		SunnahTestUtils.assertNarration(narration, 5, "حسن",
				"وَالَّذِي نَفْسِي بِيَدِهِ، لَتُصَبَّنَّ عَلَيْكُمُ الدُّنْيَا",
				"تَرَكَنَا وَاللَّهِ عَلَى مِثْلِ الْبَيْضَاءِ، لَيْلُهَا وَنَهَارُهَا سَوَاءٌ");
	}


	@Test
	public void processUndecoratedChapter() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("ibnmajah_vowels/2755.txt", s, 0);
		SunnahTestUtils.loadAndAssertSize("ibnmajah_vowels/2756.txt", s, 1);
		Narration narration = s.getNarrations().get(0);
		assertEquals(new Chapter("بَابُ الْمُظَاهِرِ يُجَامِعُ قَبْلَ أَنْ يُكَفِّرَ", 1), narration.chapter);
	}


	@Test
	public void pluralChapter() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("ibnmajah_vowels/5145.txt", s, 0);
		SunnahTestUtils.loadAndAssertSize("ibnmajah_vowels/5146.txt", s, 1);
		Narration narration = s.getNarrations().get(0);
		assertEquals(new Chapter("بَابُ الْمُزَاحِ", 1), narration.chapter);
	}
}