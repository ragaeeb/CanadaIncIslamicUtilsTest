package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaKhallalProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaKhallalProcessor();
	}

	@Test
	public void emptyKhallal() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_khallal/0280.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 251, null,
				"وَأَخْبَرَنِي مُحَمَّدُ بْنُ عَبْدُوسٍ", "الْعَرْشِ لِيَرَى الْخَلَائِقُ مَنْزِلَتَهُ");		
	}
	
	
	@Test
	public void emptyKhallal2() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("sunnah_khallal/0300.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 270, null,
				"وَقَالَ هَارُونُ بْنُ الْعَبَّاسِ", "نَعَمْ، قَدْ حَكَوْا هَذَا عَنْهُ");		
	}
}