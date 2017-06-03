package com.canadainc.sunnah10.processors.shamela.shared;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.Processor;
import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaAbuDawudNoVowelsProcessorTest
{
	private Processor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaAbuDawudNoVowelsProcessor();
	}

	@Test
	public void process() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("abudawud_no_vowels/5274.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 5274, "صحيح، الصحيحة (531)",
				"حدثنا محمد بن الصباح بن سفيان",
				"مكان سعيد والله أعلم");
	}
	
	
	@Test
	public void process2() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("abudawud_no_vowels/1601.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1601, "حسن",
				"حدثنا أحمد بن عبدة الضبي حدثنا",
				"عليه وسلم وحمى لهم وادييهم");
	}
	
	
	@Test
	public void inFootnote() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("abudawud_no_vowels/238.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 238, "صحيح",
				"حدثنا عبد الله بن مسلمة القعنبي",
				"الصيحاني ثقيل قال الصيحاني أطيب قال لا أدري");
	}
}
