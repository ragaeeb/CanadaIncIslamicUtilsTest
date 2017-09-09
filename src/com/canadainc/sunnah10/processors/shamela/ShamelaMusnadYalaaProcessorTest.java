package com.canadainc.sunnah10.processors.shamela;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaMusnadYalaaProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaMusnadYalaaProcessor();
	}

	@Test
	public void process() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("musnad_yalaa/7643.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 7555, null,
				"حَدَّثَنَا الْمُقَدَّمِيُّ، حَدَّثَنَا عُمَرُ بْنُ عَلِيٍّ", "وَأَضْمَنُ لَهُ الْجَنَّةَ");
		assertFalse( s.getNarrations().get(0).text.contains("إسناده صحيح") );
	}
}