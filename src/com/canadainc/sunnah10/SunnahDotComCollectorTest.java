package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class SunnahDotComCollectorTest
{

	@Test
	public void testParse()
	{
		SunnahDotComCollector sdcc = new SunnahDotComCollector( new File("/Users/rhaq/workspace/resources/sunnah.com/arabic/ibnmajah"), new File("/Users/rhaq/workspace/resources/sunnah.com/english/ibnmajah") );
		sdcc.parse();
	}

}
