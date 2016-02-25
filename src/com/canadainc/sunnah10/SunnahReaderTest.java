package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SunnahReaderTest
{
	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}


	@Test
	public void testParseNarration()
	{
		SunnahReader sr = new SunnahReader("englishURN");

		JSONObject jo = new JSONObject();
		jo.put("matchingArabicURN", "234");
		jo.put("babName", "babName");
		jo.put("babNumber", "111");
		jo.put("bookID", "11");
		jo.put("bookName", "bookName");
		jo.put("hadithNumber", "number");
		jo.put("ourHadithNumber", "777");
		jo.put("englishURN", "66");
		jo.put("hadithText", "Hello");

		Narration n = sr.parseNarration(jo);
		Narration n1 = new Narration(234,"babName", 111, 11, "bookName", "number", 66, 777, "Hello");
		assertEquals(n, n1);
	}


	@Test
	public void testReadNarrations()
	{
		SunnahReader sr = new SunnahReader("englishURN");

		try {
			List<Narration> narrations = sr.readNarrations( new File("res/sunnah10/english/abudawud/1.txt") );
			assertEquals( 2, narrations.size() );
			assertEquals( narrations.get(0), new Narration(900010, "Seclusion While Relieving Oneself", 1, 1, "Purification (Kitab Al-Taharah)", "1", 800010, 1, "relieve himself, he went to a far-off place.") );
			assertEquals( narrations.get(1), new Narration(903900, "Saliva Falling On A Garment", 145, 1, "Purification (Kitab Al-Taharah)", "390", 803900, 390, "A similar tradition has also been narrated by Anas from the Prophet") );
		} catch (IOException e) {
			e.printStackTrace();
			fail("IOError");
		}
	}
}
