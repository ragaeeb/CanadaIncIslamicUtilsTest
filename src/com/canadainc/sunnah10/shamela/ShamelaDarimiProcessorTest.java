package com.canadainc.sunnah10.shamela;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Narration;

public class ShamelaDarimiProcessorTest
{
	private ShamelaProcessor s;
	
	@Before
	public void setUp() throws Exception {
		s = new ShamelaDarimiProcessor();
	}
	
	
	@Test
	public void process1() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("sunan_darimi/0003.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 1, "إسناده صحيح والحديث متفق عليه",
				"مُحَمَّدُ بْنُ يُوسُفَ،", "الْإِسْلَامِ، أُخِذَ بِالْأَوَّلِ ");
	}
	
	
	@Test
	public void process2() throws IOException
	{
		// this next one has 2 in 1
		ShamelaTestUtils.loadAndAssertSize("sunan_darimi/0048.txt", s, 2);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 39, "إسناده صحيح",
				"لَحَنَّ إِلَى يَوْمِ الْقِيَامَةِ");
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 40, "إسناده صحيح",
				"رَضِيَ اللَّهُ عَنْهُ بِمِثْلِهِ");
	}
	
	
	@Test
	public void testChapter() throws IOException
	{
		s.getNarrations().add( new Narration(5) );
		ShamelaTestUtils.loadAndAssertSize("sunan_darimi/0744.txt", s, 1);
		ShamelaTestUtils.loadAndAssertSize("sunan_darimi/1393.txt", s, 1);
	}
}