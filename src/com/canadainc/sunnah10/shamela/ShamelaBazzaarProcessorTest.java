package com.canadainc.sunnah10.shamela;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ShamelaBazzaarProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaBazzaarProcessor();
	}

	@Test
	public void testMultiPages1() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("bazzaar/9281.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 9801, null,
				"فأخاف أن يطرحني في النار",
				"حَدَّثَنا يوسف بن موسى , حَدَّثَنا جرير بن عبد");

		ShamelaTestUtils.loadAndAssertSize("bazzaar/9282.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 9801, null,
				"وقوله: إني سقيم انطلقوا إلى غيري , نفسي نفسي");

		ShamelaTestUtils.loadAndAssertSize("bazzaar/9283.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 9801, null,
				"الْوَجْهِ وَلا نَعْلَمُ رَوَاهُ عن عمارة إلا جرير");
	}


	@Test
	public void testMultiPages2() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("bazzaar/9123.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 9518, null,
				"قلت: ما هؤلاء يا جبريل؟ قال: هؤلاء الذين لا يؤدون صدقات");

		ShamelaTestUtils.loadAndAssertSize("bazzaar/9124.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 9518, null,
				"بي وبرسلي، وعمل صالحا، ولم يشرك بي");

		ShamelaTestUtils.loadAndAssertSize("bazzaar/9131.txt", s, 2);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 9518, null);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 9519, null,
				"تكبه قال فما زال معي حتى قتل عثمان رَضِيَ اللهُ عَنْهُ");
	}


	@Test
	public void subHadithNumber() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("bazzaar/6789.txt", s, 3);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 6837, null,
				"عَمْرو بْنُ الرَّبِيعِ، عَن يَحْيَى بْنِ أَيُّوبَ , عَنْ حُمَيد");
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 6837, null,
				"يَحْيَى بْنِ أَيُّوبَ , عَنْ حُمَيد، عَنْ ثابتٍ، عَن أَنَس");
		ShamelaTestUtils.assertNarration(s.getNarrations().get(2), 6838, null,
				"عَنْ ثابتٍ عَنْ أَنَسٍ: أَنَّ رَسُولَ اللهِ صَلَّى اللَّهُ عَلَيه");
	}

	
	@Test
	public void subHadithNumber2() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("bazzaar/6727.txt", s, 2);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 6769, null,
				"وحَدَّثنا مُحَمَّدُ بْنُ الْمُثَنَّى، حَدَّثنا عَبد الوَهَّاب , عن أيوب");
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 6769, null,
				"أَنَسٍ، قَالَ: أُمِرَ بِلالٌ أن يشفع الأذان ويوتر الإقامة");
	}
	
	
	@Test
	public void coupledNarrations() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("bazzaar/5820.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 5717, null,
				"وَهَذَا الْحَدِيثُ لا نَعْلَمُ رَوَاهُ إلاَّ الثَّوْرِيّ، وَأبُو أُسَامة");
		s.getNarrations().get(0).hadithNumber.equals("5717,5718");
	}
	
	
	@Test
	public void coupledNarrations2() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("bazzaar/5813.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 5705, null,
				"يَتَنَخَّمَنَّ قِبَلَ وَجْهِهِ فَإِنَّ اللَّهَ قِبَلَ وَجْهِ أَحَدِكُمْ إِذَا كان في الصلاة");
		s.getNarrations().get(0).hadithNumber.equals("5705,5706");
	}
	

	@Test
	public void testTypo() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("bazzaar/7819.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 7939, null,
				"الله عَلَيه وَسَلَّم على رأس ثمانين سنة برأس القدوم");
	}
}