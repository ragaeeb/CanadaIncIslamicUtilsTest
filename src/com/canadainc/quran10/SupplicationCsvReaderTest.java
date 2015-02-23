package com.canadainc.quran10;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class SupplicationCsvReaderTest
{
	@Test
	public void testLoad()
	{
		List<Supplication> supplications;
		try {
			supplications = SupplicationCsvReader.load("res/quran10/supplications.csv");

			assertEquals( 2, supplications.size() );
			assertEquals( new Supplication(2,127,128), supplications.get(0) );
			assertEquals( new Supplication(71,28,28), supplications.get(1) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}