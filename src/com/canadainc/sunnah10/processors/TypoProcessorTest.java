package com.canadainc.sunnah10.processors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.processors.TypoProcessor;

public class TypoProcessorTest
{
	private TypoProcessor m_typos;

	@Before
	public void setUp() throws Exception {
		m_typos = new TypoProcessor();
	}

	@Test
	public void add()
	{
		m_typos.add(1, "abc", "bcd");
		assertEquals( "bcd bcd", m_typos.process(1, "abc bcd") );
	}

	@Test
	public void addRegexed()
	{
		m_typos.addRegexed(1, "<span class=\"red\">[1-9]{1,2} - </span>", "");
		assertEquals( "", m_typos.process(1, "<span class=\"red\">15 - </span>") );
	}

	@Test
	public void addNumericListStripperForward()
	{
		m_typos.addNumericListStripper(1, "abc ", "xyz");
		assertEquals( "beginning abc  this is something xyz ending", m_typos.process(1, "beginning abc "+TypoProcessor.decorateContent("15")+" this is something xyz ending") );
	}

	@Test
	public void addNumericListStripperBack()
	{
		m_typos.addNumericListStripper(1, "abc ", "xyz", false);
		assertEquals( "beginning abc <span class=\"red\">15 - </span> abc  this is something xyz ending", m_typos.process(1, "beginning abc "+TypoProcessor.decorateContent("15")+" abc "+TypoProcessor.decorateContent("16")+" this is something xyz ending") );
	}

	@Test
	public void addContentStripper()
	{
		m_typos.addContentStripper(1, "abc", "xyz", "cool", "replaced", true);
		assertEquals("beginning abc This is a replaced replaced string xyz ending", m_typos.process(1, "beginning abc This is a "+TypoProcessor.decorate("cool")+" "+TypoProcessor.decorate("cool")+" string xyz ending"));
	}

	@Test
	public void prependHadithNumber()
	{
		m_typos.prependHadithNumber(1,1);
		assertEquals(TypoProcessor.decorateContent("1")+"abc", m_typos.process(1, "abc"));
	}

	@Test
	public void decorateContent() {
		assertEquals( "<span class=\"red\">1 - </span>", TypoProcessor.decorateContent("1") );
	}


	@Test
	public void decorate() {
		assertEquals( "<span class=\"red\">1</span>", TypoProcessor.decorate("1") );
	}


	@Test
	public void process() {
		assertEquals( "hello", m_typos.process(1, "hello") );
	}
}