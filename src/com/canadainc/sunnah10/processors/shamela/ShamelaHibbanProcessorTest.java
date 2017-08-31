package com.canadainc.sunnah10.processors.shamela;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaHibbanProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaHibbanProcessor();
	}

	@Test
	public void readNormal() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("saheeh_hibbaan/00650.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 329, "صحيح - «الصحيحة» (248): ق.",
				"أَخْبَرَنَا مُحَمَّدُ بْنُ عُبَيْدِ", "سَلَفَ لَكَ مِنْ أَجْرٍ");
		//assertEquals( 330, s.getNarrations().get(0).inBookNumber );
	}
}