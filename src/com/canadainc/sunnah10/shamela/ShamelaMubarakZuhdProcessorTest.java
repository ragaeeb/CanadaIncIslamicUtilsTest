package com.canadainc.sunnah10.shamela;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Before;
import org.junit.Test;

import com.canadainc.common.io.IOUtils;
import com.canadainc.sunnah10.Narration;

public class ShamelaMubarakZuhdProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaMubarakZuhdProcessor();
	}


	@Test
	public void process1() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("zuhd_mubarak/0063.txt", s, 1);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 61, null,
				"أَخْبَرَكُمْ أَبُو عُمَرَ بْنُ حَيَوَيْهِ", "ص: 21", "يُلْتَمَسَ الْعِلْمُ عِنْدَ الْأَصَاغِرِ");
	}


	@Test
	public void preprocess() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("zuhd_mubarak/1521.txt", s, 2);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(0), 1495, null);
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 1496, null);
	}
	
	
	@Test
	public void preprocessTypo() throws IOException
	{
		JSONObject json = (JSONObject)JSONValue.parse( IOUtils.readFileUtf8( new File("/Users/rhaq/workspace/resources/shamela/arabic/zuhd_mubarak/0782.txt") ) );
		s.preprocess(json);
		assertTrue( json.get("content").toString().contains("757 - ") );
	}
	
	
	@Test
	public void bonusNarrations() throws IOException
	{
		s.getNarrations().add( new Narration(1) );
		
		ShamelaTestUtils.loadAndAssertSize("zuhd_mubarak/1662.txt", s, 1);
		ShamelaTestUtils.loadAndAssertSize("zuhd_mubarak/1663.txt", s, 2);
		
		ShamelaTestUtils.assertNarration(s.getNarrations().get(1), 2, null, "وَحَطَّ بِهَا عَنْكَ خَطِيئَةً");
	}
}