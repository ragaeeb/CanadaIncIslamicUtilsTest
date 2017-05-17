package com.canadainc.sunnah10.processors.shamela;

import java.io.IOException;

import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbaanahProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;

public class ShamelaIbaanahProcessorTest
{
	@Test
	public void testProcess() throws IOException
	{
		ShamelaProcessor s = new ShamelaIbaanahProcessor();
		
		SunnahTestUtils.loadAndAssertShamelaSize("ibaanah/0005.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1, null,
				"الْمُقْرِئُ فِي جَامِعِ الْمَنْصُورِ", "نَحْنُ عَلَيْهِ الْيَوْمَ أَنَا وَأَصْحَابِي", "اللَّهِ بْنِ [ص: 167] يَزِيدَ");

		// this next one has 2 in 1
		SunnahTestUtils.loadAndAssertShamelaSize("ibaanah/0117.txt", s, 3);
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 2, null,
				"حَدِيثِهِ أَبَا عَبْدِ الرَّحْمَنِ");
		SunnahTestUtils.assertNarration(s.getNarrations().get(2), 3, null,
				"وَوَفَّقْنَا وَإِيَّاكُمْ لِصَالِحِ الْأَعْمَالِ");
		
		SunnahTestUtils.assertInBookNumbers(s, 1, 103, 104);
	}
}