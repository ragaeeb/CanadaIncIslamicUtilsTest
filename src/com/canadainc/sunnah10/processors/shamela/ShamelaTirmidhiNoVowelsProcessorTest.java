package com.canadainc.sunnah10.processors.shamela;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.SunnahTestUtils;

public class ShamelaTirmidhiNoVowelsProcessorTest
{
	private ShamelaTirmidhiNoVowelsProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaTirmidhiNoVowelsProcessor();
	}
	
	
	@Test
	public void process() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("tirmidhi/0008.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 8, "صحيح، ابن ماجة (318)",
				"حدثنا سعيد بن عبد الرحمن المخزومي حدثنا",
				"الصح راء ولا في الكنف أن يستقبل القبلة");
	}
	
	
	@Test
	public void noGrade() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("tirmidhi/0026.txt", s, 1);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 26, null,
				"حدثنا الحسن بن علي الحلواني حدثنا يزيد بن",
				"أبيها عن النبي صلى الله عليه وسلم مثله");
	}
}