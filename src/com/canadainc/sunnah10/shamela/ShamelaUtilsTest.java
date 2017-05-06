package com.canadainc.sunnah10.shamela;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;
import org.junit.Test;

public class ShamelaUtilsTest
{
	private static Node produceRawNode(String html) {
		return Jsoup.parse(html).body().childNode(0);
	}
	
	
	@Test
	public void isHadithRangeNode()
	{
		assertTrue( ShamelaUtils.isHadithRangeNode( produceRawNode("<span class=\"red\">1375 - 1955</span>") ) );
		assertFalse( ShamelaUtils.isHadithRangeNode( produceRawNode("<span class=\"red\">1375</span>") ) );
	}
	
	
	@Test
	public void isRoundHadithNumNode()
	{
		assertTrue( ShamelaUtils.isRoundHadithNumNode( produceRawNode("<span class=\"title\">(1) - some tetx") ) );
		assertFalse( ShamelaUtils.isRoundHadithNumNode( produceRawNode("<span class=\"title\">1 - some tetx") ) );
		assertTrue( ShamelaUtils.isRoundHadithNumNode( produceRawNode("<span class=\"title\">(1) some tetx") ) );
	}
	
	
	@Test
	public void isFootnote()
	{
		assertTrue( ShamelaUtils.isFootnote( produceRawNode("<span class=\"footnote\">(1) lskfjd</span>") ) );
		assertFalse( ShamelaUtils.isFootnote( produceRawNode("<span class=\"title\">1 - some tetx") ) );
	}
	
	
	@Test
	public void parseRoundHadithNumber()
	{
		assertEquals( 1, ShamelaUtils.parseRoundHadithNumber( produceRawNode("<span class=\"title\">(1) - some tetx") ) );
	}
	
	
	@Test
	public void extractRoundHadith()
	{
		assertEquals( "some tetx", ShamelaUtils.extractRoundHadith( produceRawNode("<span class=\"title\">(1) - some tetx") ) );
	}
	
	
	@Test
	public void testIsHadithNumberNode()
	{
	}

	@Test
	public void testIsTextSpanNode()
	{
	}

	@Test
	public void testIsTextNode()
	{
	}

	@Test
	public void testIsTitleSpan()
	{
	}

	@Test
	public void testParseChildText()
	{
	}

	@Test
	public void testParseHadithNumber()
	{
	}
}