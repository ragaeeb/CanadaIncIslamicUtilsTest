package com.canadainc.sunnah10.shamela;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;

import com.canadainc.common.io.IOUtils;
import com.canadainc.sunnah10.Narration;

public class ShamelaTestUtils
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
	
	
	public static void assertInBookNumbers(ShamelaProcessor s, int ...values)
	{
		for (int i = 0; i < s.getNarrations().size(); i++) {
			assertEquals( values[i], s.getNarrations().get(i).inBookNumber );
		}
	}
	
	
	public static void loadAndAssertSize(String file, ShamelaProcessor s, int size) throws IOException
	{
		JSONObject json = (JSONObject)JSONValue.parse( IOUtils.readFileUtf8( new File("/Users/rhaq/workspace/resources/shamela/arabic/"+file) ) );
		s.preprocess(json);
		s.process( Jsoup.parse( (String)json.get("content") ).body().childNodes(), json );
		assertEquals( size, s.getNarrations().size() );
	}
}
