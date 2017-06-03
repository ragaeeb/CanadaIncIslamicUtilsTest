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
		map.put("ibnmajah_no_vowels", 4341);
		map.put("ibnmajah_vowels", 4341);
		map.put("irwa", 2447);
		map.put("jaami", 8201);
		map.put("jihad", 262);
		map.put("mustadrak", 8803);
		map.put("nasai_no_vowels", 5758);
		map.put("nasai_vowels", 5758);
		map.put("targheeb", 3773);
		map.put("tirmidhi_no_vowels", 3954);
		map.put("tirmidhi_vowels", 3939); // 17 total: missing 611, 612, 980, 1823, 1842, 1973, 3078, 3172, 3308, 3374, 3413, 3488, 3515, 3582, 3716, 3767, 3793
		map.put("zuhd_ahmad", 2379);
		map.put("zuhd_dawud", 502);
		map.put("zuhd_mubarak", 2070);
		map.put("sunnah_com/english/ibnmajah", 4341);
		map.put("sunnah_com/english/nasai", 5758);
		map.put("sunnah_com/english/tirmidhi", 3956); */
		
		//map.put("sunnah_com/english/abudawud", 5274);
		map.put("abudawud_no_vowels", 5273);
		
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
			
			List<Narration> narrations = SunnahUtils.sort(p.getNarrations(), shamela);
			SunnahTestUtils.validateSequence(shamela, narrations);
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