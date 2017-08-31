package com.canadainc.sunnah10.processors.shamela.albaani;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;

public class ShamelaJaamiDaifProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaJaamiDaifProcessor();
	}

	@Test
	public void process() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami_sagheer_daif/0221.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 220, "ضعيف",
				"أخاف على أمتي ثلاثا: زلة", "عن أبي الدرداء");
	}
}