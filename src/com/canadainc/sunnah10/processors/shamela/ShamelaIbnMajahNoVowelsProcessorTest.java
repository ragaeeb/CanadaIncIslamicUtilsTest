package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaIbnMajahNoVowelsProcessorTest
{
	private ShamelaIbnMajahNoVowelsProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaIbnMajahNoVowelsProcessor();
	}

	@Test
	public void process() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("ibnmajah_no_vowels/0103.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 31, "صحيح",
				"حدثنا عبد الله بن عامر بن",
				"الكذب علي يولج النار");
	}
}