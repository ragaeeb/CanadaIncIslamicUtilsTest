package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class BookCollectorTest
{
	@Test
	public void testProcess()
	{
		ArrayList<Narration> narrations = new ArrayList<Narration>();
		narrations.add( new Narration(1, "Chapter", 1, 1, "Intro", "1", 1, 1, "Body") );
		narrations.add( new Narration(2, "Chapter2", 2, 1, "Intro", "1", 2, 2, "Body2") );
		
		BookCollector bc = new BookCollector();
		bc.process(narrations, "english", "abudawud");
		assertEquals( 1, bc.getCollected().size() );
		
		Map<String, Set<Book>> map = bc.getCollected();
		assertEquals( 1, map.get("abudawud").size() );
	}
}