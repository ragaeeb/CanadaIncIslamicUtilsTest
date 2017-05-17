package com.canadainc.sunnah10.processors.shamela;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaAwaanahProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;

public class ShamelaAwaanahProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaAwaanahProcessor();
	}

	@Test
	public void test2in1() throws IOException
	{
		SunnahTestUtils.loadAndAssertShamelaSize("awaanah/0003.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1, null,
				"حَدَّثَنَا أَحْمَدُ بْنُ شَيْبَانَ الرَّمْلِيُّ", "ص: 16", "كُلُّهُمْ قَالُوا: قَدْ نُهِينَا فِي الْقُرْآنِ");
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 2, null,
				"حَدَّثَنَا جَعْفَرٌ الصَّائِغُ", "يُعْجِبُنَا. فَذَكَرَ مَعْنَاهُ");
	}


	@Test
	public void testDisconnected() throws IOException {
		SunnahTestUtils.loadAndAssertShamelaSize("awaanah/0018.txt", s, 0);
		SunnahTestUtils.loadAndAssertShamelaSize("awaanah/0031.txt", s, 0);
	}


	@Test
	public void testTypoFix() throws IOException {
		SunnahTestUtils.loadAndAssertShamelaSize("awaanah/0010.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 10, null,
				"حَدَّثَنَا مُحَمَّدُ بْنُ حَيَّوَيْهِ", "لَا إِلَهَ إِلَّا اللَّهُ دَخَلَ الْجَنَّةَ");
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 11, null,
				"حَدَّثَنَا الصَّاغَانِيُّ قَالَ", "عَلَيْهِ وَسَلَّمَ بِمِثْلِهِ");
	}


	@Test
	public void testEmptyBody() throws IOException {
		SunnahTestUtils.loadAndAssertShamelaSize("awaanah/2775.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 2964, null,
				"أَبِيهِ، عَنِ ابْنِ عَبَّاسٍ، بِنَحْوِهِ");
	}


	@Test
	public void testMultiPages() throws IOException {
		SunnahTestUtils.loadAndAssertShamelaSize("awaanah/6478.txt", s, 1);
		SunnahTestUtils.loadAndAssertShamelaSize("awaanah/6479.txt", s, 1);
		SunnahTestUtils.loadAndAssertShamelaSize("awaanah/6480.txt", s, 1);
		SunnahTestUtils.loadAndAssertShamelaSize("awaanah/6481.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 6965, null,
				"وَغَزْوَةُ زَيْدِ بْنِ حَارِثَةَ رَضِيَ اللَّهُ",
				"عَنْهُ الْجَمُومَ مِنْ أَرْضِ بَنِي سُلَيْمٍ، وَغَزْوَةُ زَيْدِ بْنِ",
				"بَنِي حَنِيفَةَ وَالْأَسْوَدُ بْنُ كَعْبٍ الْعَنْسِيُّ بِصَنْعَاءَ",
				"بَقِيَّةُ بَابِ عَدَدِ غَزَوَاتِ النَّبِيِّ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ",
				"صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ رَسُولَانِ لَهُ بِهَذَا الْكِتَابِ");
	}
}