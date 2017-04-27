package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class DiskNarrationReaderTest
{
	@Test
	public void testReadStaticBulugh() throws IOException
	{
		DiskNarrationReader dnr = new DiskNarrationReader();
		dnr.setTranslator(5);
		List<Narration> narrations = dnr.readStaticBulugh( new File("res/sunnah10/disk") );
		
		assertEquals(1, narrations.size());
		
		Narration n = narrations.get(0);
		assertEquals( new Book(4, "Zakah"), n.book );
		assertEquals( new Chapter("Division of Zakah (Zakah recipients)", 4), n.chapter );
		assertEquals("666", n.hadithNumber);
		assertEquals(1, n.id);
		assertEquals("Qabisah bin Mukhariq Al-Hilali (RAA) narrated that.", n.text);
	}
	
	
	@Test
	public void populate() throws IOException
	{
		DiskNarrationReader dnr = new DiskNarrationReader();
		dnr.setTranslator(5);
		List<Narration> narrations = dnr.readStaticBulugh( new File("/Users/rhaq/workspace/resources/disk/english/bulugh") );
		assertEquals( 784, narrations.size() );
	}
}