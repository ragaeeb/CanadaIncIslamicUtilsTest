package com.canadainc.sunnah10.processors;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.processors.sunnah.com.SunnahTypoProcessor;

public class SunnahTypoProcessorTest
{
	private SunnahTypoProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new SunnahTypoProcessor();
	}

	@Test
	public void fixHadithNumber()
	{
		s.fixHadithNumber(1,233);

		JSONObject j = new JSONObject();
		j.put("englishURN", "1");
		j.put("hadithNumber", "22");
		s.process(j, null);

		assertEquals( "233", j.get("hadithNumber") );
	}
	
	
	@Test
	public void track()
	{
		s.track(100, 2);
		assertEquals( 2, s.getIndex(100) );
	}

	
	@Test
	public void ignore()
	{
		s.ignore(100, 200);
		
		JSONObject j = new JSONObject();
		j.put("englishURN", "100");
		assertFalse( s.process( j, null) );
		
		j.put("englishURN", "1000");
		assertTrue( s.process( j, null) );
	}
	

	@Test
	public void merge()
	{
		Narration n = new Narration();
		n.text = "Abc";
		final List<Narration> narrations = Arrays.asList(n);

		s.merge(1,123);
		s.track(123, 0);

		JSONObject j = new JSONObject();
		j.put("englishURN", "1");
		j.put("hadithText", "Hello");

		s.process(j, new Processor()
		{
			@Override
			public void process(JSONObject json)
			{
			}

			@Override
			public boolean preprocess(JSONObject json) {
				return false;
			}

			@Override
			public boolean hasGrade(int id) {
				return false;
			}

			@Override
			public int getPageNumber(JSONObject json) {
				return 0;
			}

			@Override
			public List<Narration> getNarrations() {
				return narrations;
			}

			@Override
			public void postProcess()
			{
			}
		});

		assertEquals( "Abc Hello", n.text);
	}

	@Test
	public void mergeSelf()
	{
		Narration n = new Narration();
		n.text = "Abc";
		final List<Narration> narrations = Arrays.asList(n);

		s.merge(1);
		s.track(123, 0);

		JSONObject j = new JSONObject();
		j.put("englishURN", "1");
		j.put("hadithNumber", "123");
		j.put("hadithText", "Hello");

		s.process(j, new Processor()
		{
			@Override
			public void process(JSONObject json)
			{
			}

			@Override
			public boolean preprocess(JSONObject json) {
				return false;
			}

			@Override
			public boolean hasGrade(int id) {
				return false;
			}

			@Override
			public int getPageNumber(JSONObject json) {
				return 0;
			}

			@Override
			public List<Narration> getNarrations() {
				return narrations;
			}

			@Override
			public void postProcess()
			{
			}
		});

		assertEquals( "Abc Hello", n.text);
	}

	@Test
	public void fixRange()
	{
		s.fixRange(1,10,-1);

		JSONObject j = new JSONObject();
		j.put("englishURN", "2");
		j.put("hadithNumber", "122");
		s.process(j, null);
		assertEquals( "121", j.get("hadithNumber") );

		j.put("englishURN", "10");
		j.put("hadithNumber", "122");
		s.process(j, null);
		assertEquals( "121", j.get("hadithNumber") );
	}

	@Test
	public void decompose()
	{
		s.decompose(1, 110, "Yes");

		final JSONObject result = new JSONObject();

		JSONObject j = new JSONObject();
		j.put("englishURN", "1");
		j.put("hadithNumber", "122,123");
		j.put("hadithText", "Hello Yes We Are Good");
		s.process(j, new Processor()
		{
			@Override
			public void process(JSONObject json) {
				result.put("hadithNumber", json.get("hadithNumber"));
				result.put("hadithText", json.get("hadithText"));
			}

			@Override
			public boolean preprocess(JSONObject json) {
				return false;
			}

			@Override
			public boolean hasGrade(int id) {
				return false;
			}

			@Override
			public int getPageNumber(JSONObject json) {
				return 0;
			}

			@Override
			public List<Narration> getNarrations() {
				return null;
			}

			@Override
			public void postProcess()
			{
			}
		});

		assertEquals( "110", result.get("hadithNumber") );
		assertEquals( "Hello", result.get("hadithText") );

		assertEquals( "111", j.get("hadithNumber") );
		assertEquals( "Yes We Are Good", j.get("hadithText") );
		assertEquals( "2", j.get("englishURN") ); // TODO: Must do this!
	}


	@Test
	public void decomposeInvalid()
	{
		s.decompose(1, 110, "Yes");

		final JSONObject result = new JSONObject();

		JSONObject j = new JSONObject();
		j.put("englishURN", "1");
		j.put("hadithNumber", "122,123");
		j.put("hadithText", "Hello We Are Good");
		s.process(j, new Processor()
		{
			@Override
			public void process(JSONObject json) {
				result.put("hadithNumber", json.get("hadithNumber"));
				result.put("hadithText", json.get("hadithText"));
			}

			@Override
			public boolean preprocess(JSONObject json) {
				return false;
			}

			@Override
			public boolean hasGrade(int id) {
				return false;
			}

			@Override
			public int getPageNumber(JSONObject json) {
				return 0;
			}

			@Override
			public List<Narration> getNarrations() {
				return null;
			}

			@Override
			public void postProcess()
			{
			}
		});

		assertTrue( result.isEmpty() );

		assertEquals( "122,123", j.get("hadithNumber") );
		assertEquals( "Hello We Are Good", j.get("hadithText") );
	}
	
	
	@Test
	public void combo()
	{
		s.fixRange(1,10,-1);
		
		Narration n = new Narration();
		n.text = "Abc";
		final List<Narration> narrations = Arrays.asList(n);

		s.merge(2);
		s.track(123, 0);

		JSONObject j = new JSONObject();
		j.put("englishURN", "2");
		j.put("hadithNumber", "123");
		j.put("hadithText", "Hello");

		s.process(j, new Processor()
		{
			@Override
			public void process(JSONObject json)
			{
			}

			@Override
			public boolean preprocess(JSONObject json) {
				return false;
			}

			@Override
			public boolean hasGrade(int id) {
				return false;
			}

			@Override
			public int getPageNumber(JSONObject json) {
				return 0;
			}

			@Override
			public List<Narration> getNarrations() {
				return narrations;
			}

			@Override
			public void postProcess()
			{
			}
		});

		assertEquals( "Abc Hello", n.text);
	}
}