package com.canadainc.sunnah10.processors.shamela.mubarak;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Before;
import org.junit.Test;

import com.canadainc.common.io.IOUtils;
import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;
import com.canadainc.sunnah10.processors.shamela.mubarak.ShamelaMubarakZuhdProcessor;

public class ShamelaMubarakZuhdProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaMubarakZuhdProcessor();
	}


	@Test
	public void process1() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("zuhd_mubarak/0063.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 61, null,
				"أَخْبَرَكُمْ أَبُو عُمَرَ بْنُ حَيَوَيْهِ", "ص: 21", "يُلْتَمَسَ الْعِلْمُ عِنْدَ الْأَصَاغِرِ");
	}


	@Test
	public void preprocess() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("zuhd_mubarak/1521.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1495, null);
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 1496, null);
	}
	
	
	@Test
	public void preprocessTypo() throws Exception
	{
		JSONObject json = (JSONObject)JSONValue.parse( SunnahTestUtils.loadFromDatabase("zuhd_mubarak/0782.txt") );
		s.preprocess(json);
		assertTrue( json.get("content").toString().contains("757 - ") );
	}
	
	
	@Test
	public void bonusNarrations() throws Exception
	{
		s.getNarrations().add( new Narration(1) );
		
		SunnahTestUtils.loadAndAssertSize("zuhd_mubarak/1662.txt", s, 1);
		SunnahTestUtils.loadAndAssertSize("zuhd_mubarak/1663.txt", s, 2);
		
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 2, null, "وَحَطَّ بِهَا عَنْكَ خَطِيئَةً");
	}
}