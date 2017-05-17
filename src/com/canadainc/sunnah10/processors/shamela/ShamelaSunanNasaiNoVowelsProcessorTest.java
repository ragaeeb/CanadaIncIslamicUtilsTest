package com.canadainc.sunnah10.processors.shamela;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaSunanNasaiNoVowelsProcessorTest
{
	private ShamelaSunanNasaiNoVowelsProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaSunanNasaiNoVowelsProcessor();
	}
	
	
	@Test
	public void process() throws IOException
	{
		SunnahTestUtils.loadAndAssertShamelaSize("nasai_no_vowels/0103.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 5188, "صحيح، التعليق الرغيب (3 / 104)",
				"أخبرنا أحمد بن عمرو بن السرح قال",
				"الكذب علي يولج النار");
	}
	

	@Test
	public void processDotted() throws IOException
	{
		SunnahTestUtils.loadAndAssertShamelaSize("nasai_no_vowels/0006.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 6, "شاذ، بزيادة: \" اقرؤوا \"، صحيح أبي داود (1442)",
				"أخبرنا علي بن حجر قال أنبأنا",
				"إنك جئتني وفي يدك جمرة من نار");
	}
}