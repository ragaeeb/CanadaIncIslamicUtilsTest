package com.canadainc.sunnah10.utils;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class JsonToSqliteConverterTest
{
	@Before
	public void setUp() throws Exception
	{
	}


	@Test
	public void testConvert() throws Exception
	{
		JsonToSqliteConverter j = new JsonToSqliteConverter("/Users/rhaq/workspace/resources/albaani_app/assets/data");
		j.convert();
	}
}