package com.canadainc.sunnah10.processors.shamela;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class KhalqIbaadProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new KhalqIbaadProcessor();
	}

	@Test
	public void normal() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("khalq_ibaad/165.txt", s, 1);
		SunnahTestUtils.assertBody( s.getNarrations().get(0), 1, "حَدَّثَنَا إِبْرَاهِيمُ بْنُ",
				"فِي سَبْعٍ وَلَا تَنْثُرْهُ");
		assertNull( s.getNarrations().get(0).chapter );
	}


	@Test
	public void loadBlackChapter() throws Exception
	{
		// load chapter
		SunnahTestUtils.loadAndAssertSize("khalq_ibaad/166.txt", s, 0);

		SunnahTestUtils.loadAndAssertSize("khalq_ibaad/167.txt", s, 1);
		assertEquals( "بَابُ [ص: 85] الرَّدِّ عَلَى الْجَهْمِيَّةِ وَأَصْحَابِ التَّعْطِيلِ", s.getNarrations().get(0).chapter.title );
		assertEquals( 1, s.getNarrations().get(0).chapter.number );
		SunnahTestUtils.assertBody( s.getNarrations().get(0), 1, "حَدَّثَنَا عُبَيْدُ بْنُ يَعِيشَ،",
				"يأَذَنْ بِهِ اللَّهُ");
	}


	@Test
	public void loadRedChapter() throws Exception
	{
		// load chapter
		SunnahTestUtils.loadAndAssertSize("khalq_ibaad/279.txt", s, 0);

		SunnahTestUtils.loadAndAssertSize("khalq_ibaad/280.txt", s, 1);
		assertEquals( "بَابُ قَوْلِ اللَّهِ جَلَّ ذِكْرُهُ عَنْ أَهْلِ النَّارِ مِنَ الْكُفَّارِ وَالْمُشْركِينَ وَعَبَدَةِ الْأَوْثَانِ {وَنَادَوْا يَا مَالِكُ لِيَقْضِ عَلَيْنَا رَبُّكَ} [الزخرف: 77] وَقَوْلُهُ: {رَبَّنَا أَخْرِجْنَا مِنْهَا فَإِنْ عُدْنَا فإِنَّا ظَالِمُونَ} [المؤمنون: 107] {وَقَالَ الشَّيْطَانُ لَمَّا قُضِيَ الْأَمْرُ إِنَّ اللَّهَ وَعَدَكُمْ وَعْدَ الْحَقِّ وَوَعَدْتُكُمْ فَأَخْلَفْتُكُمْ} [إبراهيم: 22] وَقَالَ الْمُنَافِقُونَ: {انْظُرُونَا نَقْتَبِسْ مِنْ نُورِكُمْ} [الحديد: 13]", s.getNarrations().get(0).chapter.title );
		assertEquals( 1, s.getNarrations().get(0).chapter.number );
		SunnahTestUtils.assertBody( s.getNarrations().get(0), 1, "حَدَّثَنَا قُتَيْبَةُ، عَنْ سُفْيَانَ",
				"الزخرف: 77");
	}
}
