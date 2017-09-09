package com.canadainc.sunnah10.processors.shamela.albaani;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;

public class ShamelaTaleeqaatHisaanProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaTaleeqaatHisaanProcessor();
	}

	@Test
	public void ignoreBookChapter() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("taleeqat_hisaan/00140.txt", s, 0);
	}

	@Test
	public void process1() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("taleeqat_hisaan/00142.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1, "ضعيف - ((الإرواء)) (1/ 30/2)",
				"أَخْبَرَنَا الْحُسَيْنُ بْنُ عَبْدِ اللَّهِ", "بِحَمْدِ اللَّهِ فَهُوَ أقطع");
	}

	@Test
	public void spacedGrade() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("taleeqat_hisaan/05483.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 2691, "حسن ـ وهو مكرر (2681).",
				"أَخْبَرَنَا سُلَيْمَانُ بْنُ الْحَسَنِ", "(2702) [104: 1]");
	}
}