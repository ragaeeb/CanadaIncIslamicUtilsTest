package com.canadainc.sunnah10.controller;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.sunnah10.processors.Processor;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbnMajahNoVowelsProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbnMajahVowelledProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaPopulator;
import com.canadainc.sunnah10.processors.shamela.ShamelaSunanNasaiNoVowelsProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaSunanNasaiVowelledProcessor;

public class CollectionMergerTest
{
	private static Connection c;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName( org.sqlite.JDBC.class.getCanonicalName() ); // load the sqlite-JDBC driver using the current class loader
		c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/collections_source.db");
	}

	//@Test
	public void mergeIbnMajah() throws Exception
	{
		Processor commentary = new ShamelaIbnMajahNoVowelsProcessor();
		ShamelaPopulator sp = new ShamelaPopulator("ibnmajah_no_vowels", commentary);
		sp.process(c);

		Processor vowels = new ShamelaIbnMajahVowelledProcessor();
		sp = new ShamelaPopulator("ibnmajah_vowels", vowels);
		sp.process(c);

		CollectionMerger c = new CollectionMerger();
		c.merge(vowels.getNarrations(), commentary.getNarrations());
	}


	@Test
	public void mergeNasai() throws Exception
	{
		Processor commentary = new ShamelaSunanNasaiNoVowelsProcessor();
		ShamelaPopulator sp = new ShamelaPopulator("nasai_no_vowels", commentary);
		sp.process(c);

		Processor vowels = new ShamelaSunanNasaiVowelledProcessor();
		sp = new ShamelaPopulator("nasai_vowels", vowels);
		sp.process(c);

		CollectionMerger c = new CollectionMerger();
		c.merge(vowels.getNarrations(), commentary.getNarrations());
	}
}