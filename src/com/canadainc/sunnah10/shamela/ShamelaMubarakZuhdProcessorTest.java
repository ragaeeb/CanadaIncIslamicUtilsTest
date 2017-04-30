package com.canadainc.sunnah10.shamela;

import java.io.IOException;

import org.junit.Test;

public class ShamelaMubarakZuhdProcessorTest
{
	@Test
	public void testProcess() throws IOException
	{
		ShamelaProcessor s = new ShamelaMubarakZuhdProcessor();
		
		ShamelaTestUtils.loadAndAssertSize("zuhd_mubarak/0063.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 61, null,
				"أَخْبَرَكُمْ أَبُو عُمَرَ بْنُ حَيَوَيْهِ", "ص: 21", "يُلْتَمَسَ الْعِلْمُ عِنْدَ الْأَصَاغِرِ");

		ShamelaTestUtils.loadAndAssertSize("zuhd_mubarak/1653.txt", s, 2);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 62, null,
				"هُوَ التَّشْدِيدُ أَوِ الْهَلَكَةُ");
	}
}