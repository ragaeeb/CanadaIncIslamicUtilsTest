package com.canadainc.sunnah10.processors.shamela.shared;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.shared.ShamelaSunanNasaiNoVowelsProcessor;

public class ShamelaSunanNasaiNoVowelsProcessorTest
{
	private ShamelaSunanNasaiNoVowelsProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaSunanNasaiNoVowelsProcessor();
	}


	@Test
	public void process() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("nasai_no_vowels/0103.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 5188, "صحيح، التعليق الرغيب (3 / 104)",
				"أخبرنا أحمد بن عمرو بن السرح قال",
				"وسلم وقال إنك جئتني وفي يدك جمرة من نار");
	}


	@Test
	public void processDotted() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("nasai_no_vowels/0006.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 2571, "شاذ، بزيادة: \" اقرؤوا.....\"، صحيح أبي داود (1442)",
				"أخبرنا علي بن حجر قال أنبأنا إسمعيل",
				"شئتم (لا يسألون الناس إلحافا");
	}
}