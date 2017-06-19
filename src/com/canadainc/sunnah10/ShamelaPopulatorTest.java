package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.sunnah10.processors.Processor;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaPopulator;
import com.canadainc.sunnah10.utils.SunnahUtils;

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
	public void process() throws Exception
	{
		HashMap<String,Integer> map = new HashMap<>();
		/*map.put("awaanah", 8682);
		map.put("bazzaar", 10338);
		map.put("silsila_daif", 7141);
		map.put("sunan_darimi", 3541);
		map.put("ibaanah", 3122);

		map.put("irwa", 2447);
		map.put("jaami", 8201);
		map.put("jihad", 262);
		map.put("mustadrak", 8803);

		map.put("nasai_no_vowels", 5758);
		map.put("nasai_vowels", 5758);
		map.put("sunnah_com/english/nasai", 5758);

		map.put("targheeb", 3773);
		map.put("zuhd_ahmad", 2379);
		map.put("zuhd_dawud", 502);
		map.put("zuhd_mubarak", 2070); */

		//map.put("sunnah_com/english/tirmidhi", 3956);		
		//map.put("tirmidhi_no_vowels", 3954);
		//map.put("tirmidhi_vowels", 3956);

		//map.put("sunnah_com/english/abudawud", 5274);
		//map.put("abudawud_no_vowels", 5273);
		//map.put("sunnah_com/arabic/abudawud", 5276);
		
		//map.put("sunnah_com/arabic/ibnmajah", 4341);
		//map.put("sunnah_com/arabic/qudsi40", 40);
		//map.put("sunnah_com/english/qudsi40", 40);
		//map.put("sunnah_com/english/nawawi40", 42);
		//map.put("sunnah_com/arabic/nawawi40", 42);

		//map.put("sunnah_com/arabic/riyadussaliheen", 1895);
		//map.put("sunnah_com/english/riyadussaliheen", 1895);
		
		//map.put("sunnah_com/english/malik", 1895);
		//map.put("sunnah_com/arabic/malik", 1895);
		//map.put("sunnah_com/arabic/bukhari", 7291);
		//map.put("sunnah_com/english/bukhari", 7290);
		
		//map.put("sunnah_com/arabic/muslim", 7470);
		//map.put("sunnah_com/english/muslim", 7470);
		
		//map.put("ibnmajah_no_vowels", 4341);
		//map.put("ibnmajah_vowels", 4341);
		//map.put("sunnah_com/english/ibnmajah", 4341);
		map.put("sunnah_com/arabic/ibnmajah", 4341);

		ParserFactory pf = new ParserFactory();

		for (String key: map.keySet())
		{
			Processor p = pf.getProcessor(key);
			String path = null;
			String collection = key;
			int index = key.indexOf("/");
			boolean shamela = index == -1;

			if (!shamela)
			{
				path = key.substring(index+1);
				collection = key.substring(0, index);
			}

			ShamelaPopulator sp = new ShamelaPopulator(collection, path, p);
			sp.process(c);

			assertEquals( map.get(key).intValue(), p.getNarrations().size() );

			//List<Narration> narrations = SunnahUtils.sort(p.getNarrations(), shamela);
			//SunnahTestUtils.validateSequence(shamela, narrations);
			//SunnahTestUtils.validateGrades(p);
			/*
			for (int i = 0; i < narrations.size(); i++)
			{
				if ( !narrations.get(i).hadithNumber.equals( String.valueOf(i+1) ) ) {
					System.out.println(narrations.get(i));
					break;
				}
			} */
		}
	}
}