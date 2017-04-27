package com.canadainc.islamicutils.io;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ShamelaReaderTest
{
	@Test
	public void testGetOrderedFiles()
	{
		File[] files = ShamelaReader.getOrderedFiles("res/sunnah10/shamela/silsilah_daif");
		
		assertEquals( "50.txt", files[0].getName() );
		assertEquals( "100.txt", files[1].getName() );
		assertEquals( "11256.txt", files[2].getName() );
	}
}