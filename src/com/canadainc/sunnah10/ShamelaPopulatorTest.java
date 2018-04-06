package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.sunnah10.processors.Processor;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.DatabasePopulator;
import com.canadainc.sunnah10.processors.shamela.ShamelaPopulator;
import com.canadainc.sunnah10.processors.shamela.SunnahComPopulator;
import com.canadainc.sunnah10.processors.sunnah.com.AbstractSunnahDotComProcessor;
import com.canadainc.sunnah10.utils.SunnahUtils;

public class ShamelaPopulatorTest
{
	private static Connection c;
	private static Connection output;
	private ParserFactory pf;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName( org.sqlite.JDBC.class.getCanonicalName() ); // load the sqlite-JDBC driver using the current class loader
		c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/collections_source.db");
		output = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/translations.db");
	}


	@Before
	public void setUp() throws Exception {
		pf = new ParserFactory();
	}


	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		c.close();
	}


	//@Test
	public void write() throws Exception
	{
		final String collection = "jaami";
		Processor p = pf.getProcessor(collection);
		DatabasePopulator sp = new ShamelaPopulator(collection, null, p);
		sp.process(c);

		List<Narration> narrations = SunnahUtils.sort(p.getNarrations(), true);
		SunnahTestUtils.validateSequence(true, narrations);

		Connection c2 = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/"+collection+".db");
		sp.write(c2);
		c2.close();
	}


	//@Test
	public void sittah() throws Exception
	{
		HashMap<String,Integer> map = new HashMap<>();
		//map.put("ibnmajah_no_vowels", 4341);
		//map.put("ibnmajah_vowels", 4341);
		//map.put("nasai_no_vowels", 5758);
		//map.put("nasai_vowels", 5758);
		//map.put("tirmidhi_no_vowels", 3954);
		//map.put("tirmidhi_vowels", 3956);
		map.put("abudawud_no_vowels", 5273);

		for (String key: map.keySet())
		{
			Processor p = pf.getProcessor(key);
			DatabasePopulator sp = new ShamelaPopulator(key, null, p);
			sp.process(c);
			sp.write(output);

			List<Narration> narrations = SunnahUtils.sort(p.getNarrations(), true);
			SunnahTestUtils.validateSequence(true, narrations);
		}
	}


	@Test
	public void sunnahCom() throws Exception
	{
		HashMap<String,Integer> map = new HashMap<>();
		//map.put("ibnmajah", 4341);
		//map.put("nasai", 5758);
		//map.put("tirmidhi", 3956);
		//map.put("abudawud", 5274);
		//map.put("nawawi40", 42);
		//map.put("qudsi40", 40);
		//map.put("malik", 1895);
		//map.put("bukhari", 7291);
		//map.put("muslim", 7470);
		//map.put("riyadussaliheen", 1896);
		//map.put("adab", 1895);

		for (String key: map.keySet())
		{
			Processor p = pf.getProcessor(key);
			DatabasePopulator sp = new SunnahComPopulator(key, (AbstractSunnahDotComProcessor)p);
			sp.process(c);
			sp.write(output);

			List<Narration> narrations = SunnahUtils.sort(p.getNarrations(), false);
			SunnahTestUtils.validateSequence(false, narrations);
			//SunnahTestUtils.validateGrades(p);
		}
	}


	@Test
	public void process() throws Exception
	{
		boolean write = true;

		HashMap<String,Integer> map = new HashMap<>();
		//map.put("baghdadi_mutafaqqih", 1881);
		//map.put("bayhaqi_kubra", 21949);
		//map.put("bidah_waddah", 292);
		//map.put("daynuree_mujaalasah", 3816);
		//map.put("fadaail_sahaaba", 1962);
		//map.put("fath_albari", 4960);
		//map.put("hilya_awliyah", 16741);
		//map.put("ibaanah_kubra", 3122);
		//map.put("jaami_sagheer_daif", 6359);
		//map.put("khalq_ibaad", 283);
		//map.put("mishkaat_masaabeeh", 6282);
		//map.put("mukhtasar_shamaail", 329);
		//map.put("muntadhim", 7768);
		//map.put("saheeh_irwa", 2447);
		//map.put("saheeh_jaami", 8200);
		//map.put("saheeh_targheeb", 3773);
		//map.put("silsila_daif", 7141);
		//map.put("sunan_darimi", 3541);
		//map.put("sunnah_aasim", 1519);
		//map.put("sunnah_khallal", 1823);
		//map.put("taarikh_baghdadi", 7782);
		//map.put("taleeqat_hisaan", 7447);
		//map.put("tanbeeh_ghafileen", 929);
		//map.put("zuhd_ahmad", 2379);
		//map.put("zuhd_dawud", 502);
		//map.put("zuhd_wakee", 539);
		
		// irwa ghaleel: 2597, lots missing!

		for (String key: map.keySet())
		{
			Processor p = pf.getProcessor(key);
			DatabasePopulator sp = new ShamelaPopulator(key, null, p);
			sp.process(c);

			if (write) {
				sp.write(output);
			}

			assertEquals( map.get(key).intValue(), p.getNarrations().size() );

			List<Narration> narrations = SunnahUtils.sort(p.getNarrations(), true);
			SunnahTestUtils.validateSequence(true, narrations);
			//SunnahTestUtils.validateGrades(p);
		}
	}
}