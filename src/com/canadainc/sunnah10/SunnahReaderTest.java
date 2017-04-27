package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
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
		SunnahReader sr = new SunnahReader();

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
		Narration n1 = new Narration(234,"babName", 111, 11, "bookName", "number", 777, "Hello");
		assertEquals(n, n1);
	}


	@Test
	public void testReadNarrations()
	{
		SunnahReader sr = new SunnahReader();

		try {
			List<Narration> narrations = sr.readNarrations( new File("res/sunnah10/english/abudawud/1.txt") );
			assertEquals( 2, narrations.size() );
		} catch (IOException e) {
			e.printStackTrace();
			fail("IOError");
		}
	}
}
