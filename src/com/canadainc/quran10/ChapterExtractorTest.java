package com.canadainc.quran10;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class ChapterExtractorTest
{
	@Test
	public void testLoad()
	{
		try {
			ChapterExtractor c = new ChapterExtractor("res/quran10/SoarNamesFr.txt");
			c.load();
			
			List<String> result = c.getResult();
			assertEquals( 8, result.size() );
			assertEquals( "Al-Fâtiha", result.get(0) );
			assertEquals( "Al-'Anfâl", result.get(7) );
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}