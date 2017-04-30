package com.canadainc.sunnah10.shamela;

import java.io.IOException;

import org.junit.Test;

public class ShamelaDarimiProcessorTest
{
	@Test
	public void testProcess() throws IOException
	{
		ShamelaProcessor s = new ShamelaDarimiProcessor();
		
		ShamelaTestUtils.loadAndAssertSize("sunan_darimi/0003.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 1, "إسناده صحيح والحديث متفق عليه",
				"مُحَمَّدُ بْنُ يُوسُفَ،", "الْإِسْلَامِ، أُخِذَ بِالْأَوَّلِ ");

		// this next one has 2 in 1
		ShamelaTestUtils.loadAndAssertSize("sunan_darimi/0048.txt", s, 3);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 39, "إسناده صحيح",
				"لَحَنَّ إِلَى يَوْمِ الْقِيَامَةِ");
		ShamelaTestUtils.assertNarration(s.getNarrations().get(2), 40, "إسناده صحيح",
				"رَضِيَ اللَّهُ عَنْهُ بِمِثْلِهِ");
	}
}