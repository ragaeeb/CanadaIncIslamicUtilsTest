package com.canadainc.sunnah10.shamela;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;
import org.junit.Test;

import com.canadainc.sunnah10.Narration;

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
	public void isArabicText()
	{
		assertTrue( ShamelaUtils.isArabicText("فكتَم عليه غَفَر") );
		assertTrue( ShamelaUtils.isArabicText("Hello الضعيفhow are you") );
		assertFalse( ShamelaUtils.isArabicText("This is not an arabic text") );
	}
	
	
	@Test
	public void isLineBreak()
	{
		assertTrue( ShamelaUtils.isLineBreak( produceRawNode("<br>") ) );
		assertTrue( ShamelaUtils.isLineBreak( produceRawNode("<br />") ) );
		assertTrue( ShamelaUtils.isLineBreak( produceRawNode("<br/>") ) );
		assertFalse( ShamelaUtils.isLineBreak( produceRawNode("<span></span>") ) );
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
	public void isHadithNumberNode()
	{
		assertTrue( ShamelaUtils.isHadithNumberNode( produceRawNode("<span class=\"red\">1375 -</span>") ) );
		assertFalse( ShamelaUtils.isHadithNumberNode( produceRawNode("<span class=\"red\">1375x -</span>") ) );
	}

	@Test
	public void isTextSpanNode()
	{
		assertTrue( ShamelaUtils.isTextSpanNode( produceRawNode("<span class=\"title\">(1) - some tetx"), "title" ) );
		assertFalse( ShamelaUtils.isTextSpanNode( produceRawNode("<span class=\"title\"><span></span></span>"), "title" ) );
	}

	@Test
	public void isTextNode()
	{
		assertTrue( ShamelaUtils.isTextNode( produceRawNode("Hello") ) );
		assertFalse( ShamelaUtils.isTextNode( produceRawNode("<span class=\"red\">1375 -</span>") ) );
	}

	@Test
	public void isTitleSpan()
	{
		assertTrue( ShamelaUtils.isTitleSpan( produceRawNode("<span class=\"title\">(1) - some tetx") ) );
		assertFalse( ShamelaUtils.isTitleSpan( produceRawNode("Hello") ) );
	}

	@Test
	public void parseHadithNumber()
	{
		assertEquals( 123, ShamelaUtils.parseHadithNumber( produceRawNode("<span class=\"title\">123 - some tetx") ) );
		assertEquals( 555, ShamelaUtils.parseHadithNumber( produceRawNode("<span class=\"title\">555-") ) );
	}
	
	@Test
	public void createNewNarration()
	{
		Narration n = null;
		ArrayList<Narration> result = new ArrayList<>();
		n = ShamelaUtils.createNewNarration(n, produceRawNode("<span class=\"red\">1375 -</span>"), result);
		
		assertEquals(1375, n.id);
		assertEquals("", n.text);
		assertEquals( 0, result.size() );
	}
	
	
	@Test
	public void createNewNarrationDuplicate()
	{
		Narration n = new Narration();
		n.text = "Test";

		ArrayList<Narration> result = new ArrayList<>();
		n = ShamelaUtils.createNewNarration(n, produceRawNode("<span class=\"red\">1375 -</span>"), result);
		
		assertEquals(1375, n.id);
		assertEquals("", n.text);
		assertEquals( 1, result.size() );
	}
	
	
	@Test
	public void sortLongestToShortest()
	{
		String[] values = new String[]{"1", "123", "1234", "12"};
		ShamelaUtils.sortLongestToShortest(values);
		assertArrayEquals( new String[]{"1234","123","12","1"}, values );
	}
	
	
	@Test
	public void isAllText()
	{
		assertTrue( ShamelaUtils.isAllText( Jsoup.parse("Hey<br/>How are you doing<br/>That is quite cool<br>").body().childNodes() ) );
		assertFalse( ShamelaUtils.isAllText( Jsoup.parse("Hey<br/><span>How are you doing</span><br/>That is quite cool<br>").body().childNodes() ) );
	}
	
	
	@Test
	public void isHadithNumberValid()
	{
		ArrayList<Narration> result = new ArrayList<>();
		result.add( new Narration(3) );
		
		assertTrue( ShamelaUtils.isHadithNumberValid( produceRawNode("<span class=\"title\">123 - some tetx"), result ) );
		assertFalse( ShamelaUtils.isHadithNumberValid( produceRawNode("<span class=\"title\">2 - some tetx"), result ) );
	}
}