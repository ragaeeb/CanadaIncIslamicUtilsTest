package com.canadainc.quran10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SimilarParserTest
{
	private SimilarParser m_instance;

	@Test
	public void testParse()
	{
		m_instance = new SimilarParser("res/quran10/similar.txt");

		try {
			m_instance.parse();
			
			Map< Supplication, List<Supplication> > result = m_instance.getSimilar();
			assertEquals( 4, result.size() );
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}