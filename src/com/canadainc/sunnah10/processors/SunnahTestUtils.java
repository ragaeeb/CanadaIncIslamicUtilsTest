package com.canadainc.sunnah10.processors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.canadainc.common.io.IOUtils;
import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.processors.Processor;

public class SunnahTestUtils
{
	public static void assertNarration(Narration n, int id, String grade, String... bodies)
	{
		assertEquals(id, n.id);
		assertEquals(grade, n.grading);
		
		for (String body: bodies) {
			assertTrue( n.text.contains(body) );
		}
	}
	
	
	public static void assertCommentary(Narration n, int id, String grade, String[] commentaries, String... bodies)
	{
		assertEquals(id, n.id);
		assertEquals(grade, n.grading);
		
		for (String commentary: commentaries) {
			assertTrue( n.commentary.contains(commentary) );
		}
		
		for (String body: bodies) {
			assertTrue( n.text.contains(body) );
		}
	}
	
	
	public static void assertInBookNumbers(Processor s, int ...values)
	{
		for (int i = 0; i < s.getNarrations().size(); i++) {
			assertEquals( values[i], s.getNarrations().get(i).inBookNumber );
		}
	}
	
	
	public static void loadAndAssertShamelaSize(String file, Processor s, int size) throws IOException {
		loadAndAssertSize("/Users/rhaq/workspace/resources/shamela/arabic/"+file, s, size);
	}
	
	
	public static void loadAndAssertSize(String file, Processor s, int size) throws IOException
	{
		Object obj = JSONValue.parse( IOUtils.readFileUtf8( new File(file) ) );
		JSONArray arr = new JSONArray();

		if (obj instanceof JSONArray) {
			arr = (JSONArray)obj;
		} else {
			arr.add(obj);
		}
		
		for (Object o: arr)
		{
			JSONObject json = (JSONObject)o;
			s.preprocess(json);
			s.process(json);
		}
		
		assertEquals( size, s.getNarrations().size() );
	}
}