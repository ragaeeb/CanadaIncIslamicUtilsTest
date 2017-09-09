package com.canadainc.sunnah10.processors.shamela.sharh;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.Processor;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.shared.ShamelaAbuDawudNoVowelsProcessor;

public class ShamelaSharhAbuDawudProcessorTest
{
	private Processor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaSharhAbuDawudProcessor();
	}

	@Test
	public void checkIfNumbersIncrease() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("abudawud_sharh_abbad/00060.txt", s, 1);
		SunnahTestUtils.assertBody(s.getNarrations().get(0), 1, "قال المصنف رحمه الله تعالى",
				"للناس إلى رؤيته", "ذهب بعيداً عن الناس");
		SunnahTestUtils.loadAndAssertSize("abudawud_sharh_abbad/00061.txt", s, 1);
		SunnahTestUtils.assertBody(s.getNarrations().get(0), 1, "قوله: [حدثنا عبد الله بن",
				"بكلمة (يعني): ابن عمرو");
		SunnahTestUtils.loadAndAssertSize("abudawud_sharh_abbad/00062.txt", s, 2);
		SunnahTestUtils.assertBody(s.getNarrations().get(1), 2, "مسدد بن مسرهد حدثنا عيسى",
				"بمعنى الحديث المتقدم");
	}
}