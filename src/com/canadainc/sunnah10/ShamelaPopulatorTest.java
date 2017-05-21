package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.sunnah10.processors.shamela.ShamelaAwaanahProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaBazzaarProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaDarimiProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaDawudZuhdProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbaanahProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbnMajahNoVowelsProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbnMajahVowelledProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaMustadrakProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaPopulator;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaSunanNasaiNoVowelsProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaSunanNasaiVowelledProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaTirmidhiNoVowelsProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaIrwaProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaJaamiProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaSilsilaDaifProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaTargheebProcessor;
import com.canadainc.sunnah10.processors.shamela.mubarak.ShamelaJihadProcessor;
import com.canadainc.sunnah10.processors.shamela.mubarak.ShamelaMubarakZuhdProcessor;

public class ShamelaPopulatorTest
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


	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		c.close();
	}


	@Test
	public void processBazzaar() throws Exception {
		ShamelaPopulator sp = process("bazzaar", new ShamelaBazzaarProcessor(), 10338);
		sp.validateSequence();
	}


	@Test
	public void processIbnMajahNoVowels() throws Exception {
		ShamelaPopulator sp = process("ibnmajah_no_vowels", new ShamelaIbnMajahNoVowelsProcessor(), 4341);
		sp.validateSequence();
		//sp.validateGrades();
	}


	@Test
	public void processIbnMajahVowels() throws Exception {
		ShamelaPopulator sp = process("ibnmajah_vowels", new ShamelaIbnMajahVowelledProcessor(), 4341);
		sp.validateSequence();
		sp.validateGrades();
	}


	@Test
	public void processTirmidhiNoVowels() throws Exception {
		ShamelaPopulator sp = process("tirmidhi", new ShamelaTirmidhiNoVowelsProcessor(), 3954);
		sp.validateSequence();
		sp.validateGrades();
	}


	@Test
	public void processNasaiVowels() throws Exception {
		ShamelaPopulator sp = process("nasai", new ShamelaSunanNasaiVowelledProcessor(), 5769);
		sp.validateSequence();
		sp.validateGrades();
	}


	@Test
	public void processNasaiNoVowels() throws Exception {
		ShamelaPopulator sp = process("nasai_no_vowels", new ShamelaSunanNasaiNoVowelsProcessor(), 5758);
		//sp.validateSequence();
		sp.validateGrades();
	}


	@Test
	public void processTargheeb() throws Exception {
		ShamelaPopulator sp = process("targheeb", new ShamelaTargheebProcessor(), 3773);
		sp.validateSequence();
		sp.validateGrades();
	}



	@Test
	public void testProcessJihad() throws Exception {
		process("jihad", new ShamelaJihadProcessor(), 262);
	}


	@Test
	public void testProcessIbaanah() throws Exception {
		process("ibaanah", new ShamelaIbaanahProcessor(), 3122);
	}


	@Test
	public void testProcessDarimi() throws Exception {
		process("sunan_darimi", new ShamelaDarimiProcessor(), 3541);
	}


	@Test
	public void testProcessZuhdMubarak() throws Exception {
		process("zuhd_mubarak", new ShamelaMubarakZuhdProcessor(), 2070);
	}


	@Test
	public void testProcessZuhdDawud() throws Exception {
		process("zuhd_dawud", new ShamelaDawudZuhdProcessor(), 502);
	}


	@Test
	public void testProcessMustadrak() throws Exception {
		process("mustadrak", new ShamelaMustadrakProcessor(), 8803);
	}


	@Test
	public void testProcessIrwa() throws Exception {
		process("irwa", new ShamelaIrwaProcessor(), 2447);
	}


	@Test
	public void testProcessDaif() throws Exception
	{
		ShamelaPopulator sp = process("silsila_daif", new ShamelaSilsilaDaifProcessor(), 7141);
		sp.validateGrades();
	}


	@Test
	public void testProcessAwaanah() throws Exception
	{
		ShamelaPopulator sp = process("awaanah", new ShamelaAwaanahProcessor(), 8682);
		sp.validateSequence();
		//sp.validateGrades();
	}


	@Test
	public void testProcessJaami() throws Exception {
		process("jaami", new ShamelaJaamiProcessor(), 8201);
	}


	private ShamelaPopulator process(String collection, ShamelaProcessor processor, int expectedSize) throws Exception
	{
		ShamelaPopulator sp = new ShamelaPopulator(collection, processor);
		sp.process(c);

		assertEquals( expectedSize, processor.getNarrations().size() );
		return sp;
	}
}