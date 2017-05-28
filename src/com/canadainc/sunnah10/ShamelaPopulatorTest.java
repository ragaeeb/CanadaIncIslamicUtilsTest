package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.sunnah10.processors.Processor;
import com.canadainc.sunnah10.processors.SunnahDotComProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaBazzaarProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaContinuedProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaDarimiProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbaanahProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbnMajahNoVowelsProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbnMajahVowelledProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaMustadrakProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaPopulator;
import com.canadainc.sunnah10.processors.shamela.ShamelaStandardProcessor;
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
	public void awaanah() throws Exception
	{
		ShamelaPopulator sp = process("awaanah", new ShamelaContinuedProcessor(), 8682);
		sp.validateSequence();
		//sp.validateGrades();
	}


	@Test
	public void bazzaar() throws Exception {
		ShamelaPopulator sp = process("bazzaar", new ShamelaBazzaarProcessor(), 10338);
		sp.validateSequence();
	}


	@Test
	public void daif() throws Exception
	{
		ShamelaPopulator sp = process("silsila_daif", new ShamelaSilsilaDaifProcessor(), 7141);
		sp.validateGrades();
	}


	@Test
	public void darimi() throws Exception {
		process("sunan_darimi", new ShamelaDarimiProcessor(), 3541);
	}


	@Test
	public void ibaanah() throws Exception {
		process("ibaanah", new ShamelaIbaanahProcessor(), 3122);
	}


	@Test
	public void ibnMajahNoVowels() throws Exception {
		ShamelaPopulator sp = process("ibnmajah_no_vowels", new ShamelaIbnMajahNoVowelsProcessor(), 4341);
		sp.validateSequence();
		//sp.validateGrades();
	}


	@Test
	public void ibnMajahVowels() throws Exception {
		ShamelaPopulator sp = process("ibnmajah_vowels", new ShamelaIbnMajahVowelledProcessor(), 4341);
		sp.validateSequence();
		sp.validateGrades();
	}


	@Test
	public void irwa() throws Exception {
		process("irwa", new ShamelaIrwaProcessor(), 2447);
	}


	@Test
	public void jaami() throws Exception {
		process("jaami", new ShamelaJaamiProcessor(), 8201);
	}


	@Test
	public void jihad() throws Exception {
		process("jihad", new ShamelaJihadProcessor(), 262);
	}


	@Test
	public void mustadrak() throws Exception {
		process("mustadrak", new ShamelaMustadrakProcessor(), 8803);
	}


	@Test
	public void nasaiNoVowels() throws Exception {
		ShamelaPopulator sp = process("nasai_no_vowels", new ShamelaSunanNasaiNoVowelsProcessor(), 5758);
		sp.validateSequence();
		//sp.validateGrades();
	}


	@Test
	public void nasaiSunnah() throws Exception {
		Processor p = new SunnahDotComProcessor();
		ShamelaPopulator sp = new ShamelaPopulator("sunnah_com", "english/nasai", p);
		sp.process(c);

		assertEquals( 5758, p.getNarrations().size() );
		sp.validateSequence(false, true);
	}


	@Test
	public void nasaiVowels() throws Exception {
		ShamelaPopulator sp = process("nasai_vowels", new ShamelaSunanNasaiVowelledProcessor(), 5758);
		sp.validateSequence();
		sp.validateGrades();
	}


	private ShamelaPopulator process(String collection, Processor processor, int expectedSize) throws Exception
	{
		ShamelaPopulator sp = new ShamelaPopulator(collection, processor);
		sp.process(c);

		assertEquals( expectedSize, processor.getNarrations().size() );
		return sp;
	}


	@Test
	public void processSunnahDotComIbnMajah() throws Exception {
		Processor p = new SunnahDotComProcessor();
		ShamelaPopulator sp = new ShamelaPopulator("sunnah_com", "english/ibnmajah", p);
		sp.process(c);

		List<Narration> narrations = p.getNarrations();

		Collections.sort(narrations, new Comparator<Narration>()
		{
			@Override
			public int compare(Narration o1, Narration o2)
			{
				int a = Integer.parseInt(o1.hadithNumber);
				int b = Integer.parseInt(o2.hadithNumber);
				return a-b;
			}
		});

		int i = 0;

		for (Narration n: narrations)
		{
			++i;
			System.out.println(n.hadithNumber);

			if (i != Integer.parseInt(n.hadithNumber) ) {
				System.err.println("*** BAD: "+n.hadithNumber);
				break;
			}
		}
	}


	@Test
	public void targheeb() throws Exception {
		ShamelaPopulator sp = process("targheeb", new ShamelaTargheebProcessor(), 3773);
		sp.validateSequence();
		sp.validateGrades();
	}


	@Test
	public void tirmidhiNoVowels() throws Exception {
		ShamelaPopulator sp = process("tirmidhi", new ShamelaTirmidhiNoVowelsProcessor(), 3954);
		sp.validateSequence();
		sp.validateGrades();
	}


	@Test
	public void zuhdAhmad() throws Exception {
		ShamelaPopulator sp = process("zuhd_ahmad", new ShamelaStandardProcessor(), 2379);
		sp.validateSequence();
	}


	@Test
	public void zuhdDawud() throws Exception {
		process("zuhd_dawud", new ShamelaStandardProcessor(), 502);
	}


	@Test
	public void zuhdMubarak() throws Exception {
		process("zuhd_mubarak", new ShamelaMubarakZuhdProcessor(), 2070);
	}
}