package com.canadainc.sunnah10.processors.shamela.shared;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Book;
import com.canadainc.sunnah10.Chapter;
import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.processors.Processor;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.shared.ShamelaTirmidhiVowelledProcessor;

public class ShamelaTirmidhiVowelledProcessorTest
{
	private Processor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaTirmidhiVowelledProcessor();
	}

	@Test
	public void process() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("tirmidhi_vowels/0001.txt", s, 0);
		SunnahTestUtils.loadAndAssertSize("tirmidhi_vowels/0002.txt", s, 0);
		SunnahTestUtils.loadAndAssertSize("tirmidhi_vowels/0003.txt", s, 1);
		Narration narration = s.getNarrations().get(0);
		SunnahTestUtils.assertNarration(narration, 1, "صحيح",
				"حَدَّثَنَا قُتَيْبَةُ بْنُ سَعِيدٍ قَالَ: أَخْبَرَنَا أَبُو عَوَانَةَ",
				"أُسَامَةَ اسْمُهُ عَامِرٌ، وَيُقَالُ: زَيْدُ بْنُ أُسَامَةَ بْنِ عُمَيْرٍ الْهُذَلِيُّ");
		assertEquals(new Book(1, "أَبْوَابُ الطَّهَارَةِ"), narration.book);
		assertEquals(new Chapter("بَابُ مَا جَاءَ لَا تُقْبَلُ صَلَاةٌ بِغَيْرِ طُهُورٍ", 1), narration.chapter);
	}
}