package com.canadainc.sunnah10.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.utils.SunnahUtils;

public class SunnahUtilsTest
{
	@Test
	public void sortById()
	{
		List<Narration> narrations = new ArrayList<>();
		narrations.add( new Narration(3) );
		narrations.add( new Narration(1) );
		narrations.add( new Narration(6) );
		narrations.add( new Narration(2) );

		narrations = SunnahUtils.sort(narrations, true);
		
		assertEquals( 1, narrations.get(0).id );
		assertEquals( 2, narrations.get(1).id );
		assertEquals( 3, narrations.get(2).id );
		assertEquals( 6, narrations.get(3).id );
	}


	@Test
	public void sortByHadithNumber()
	{
		List<Narration> narrations = new ArrayList<>();
		narrations.add( new Narration().setHadithNumber("3") );
		narrations.add( new Narration().setHadithNumber("1") );
		narrations.add( new Narration().setHadithNumber("6") );
		narrations.add( new Narration().setHadithNumber("2") );

		narrations = SunnahUtils.sort(narrations, false);

		assertEquals( "1", narrations.get(0).hadithNumber );
		assertEquals( "2", narrations.get(1).hadithNumber );
		assertEquals( "3", narrations.get(2).hadithNumber );
		assertEquals( "6", narrations.get(3).hadithNumber );
	}
}