package com.canadainc.sunnah10.processors.shamela;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Test;

import com.canadainc.common.io.DBUtils;
import com.canadainc.sunnah10.utils.FileSystemCollector;

public class ShamelaBookDownloaderTest
{
	//@Test
	public void bookChapters()
	{
		ShamelaBookDownloader sbd = new ShamelaBookDownloader(1435);
		sbd.download();

		assertEquals( 2360, sbd.getPageToEntry().size() );
		assertEquals( "أبواب المناقب/باب", sbd.getPageToEntry().get(6459) );
		assertEquals( "أبواب الطهارة", sbd.getPageToEntry().get(1) );
		assertEquals( "أبواب الطهارة/باب ما جاء في فضل الطهور", sbd.getPageToEntry().get(4) );
	}


	//@Test
	public void chaptersOnly()
	{
		ShamelaBookDownloader sbd = new ShamelaBookDownloader(3610);
		sbd.download();

		assertEquals( 252, sbd.getPageToEntry().size() );
		assertEquals( "أي الأعمال أحب إلى الله عز وجل؟ قال: فهبنا أن يقول منا أحد. قال: فأرسل إلينا رسول الله", sbd.getPageToEntry().get(2) );
		assertEquals( "أنا فئة كل مسلم", sbd.getPageToEntry().get(268) );
		assertNull( sbd.getPageToEntry().get(178) );
	}

	//@Test
	public void writeToDB() throws SQLException
	{
		String fileName = UUID.randomUUID().toString()+".db";
		Connection c = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);

			List<String> columns = DBUtils.createNotNullColumns("id INTEGER", "file_name TEXT", "json TEXT");
			DBUtils.createTable(c, "test", columns);

			ShamelaBookDownloader fsc = new ShamelaBookDownloader(1);
			TreeMap<Integer,String> map = new TreeMap<>();
			map.put(6, "XYZ");
			map.put(2, "ABC");
			map.put(9, "ZZZ");
			fsc.setPageToEntries(map);

			fsc.writeToDB(c, "test");

			PreparedStatement ps = c.prepareStatement("SELECT * FROM test ORDER BY id");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( "index.txt", rs.getString("file_name") );
			assertEquals( "{\"data\":[{\"path\":\"ABC\",\"pid\":2},{\"path\":\"XYZ\",\"pid\":6},{\"path\":\"ZZZ\",\"pid\":9}]}", rs.getString("json") );

			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (c != null) {
				c.close();
			}

			DBUtils.cleanUp(fileName);
		}
	}

	@Test
	public void fillIndexes() throws SQLException
	{
		Map<Integer,String> idToTable = new HashMap<>();
		//idToTable.put(1755, "abudawud_no_vowels");
		//idToTable.put(1726, "abudawud_vowels");
		//idToTable.put(21618, "awaanah");
		//idToTable.put(12981, "bazzaar");
		//idToTable.put(9111, "bulugh");
		//idToTable.put(21795, "sunan_darimi");
		//idToTable.put(8241, "gheeba");
		//idToTable.put(1734, "hibban");
		//idToTable.put(8026, "ibaanah");
		//idToTable.put(810, "ibnmajah_no_vowels");
		//idToTable.put(1198, "ibnmajah_vowels");
		//idToTable.put(22592, "irwa");
		//idToTable.put(22367, "jaami_fadl");
		//idToTable.put(10757, "jaami");
		//idToTable.put(3610, "jihad");
		//idToTable.put(7861, "kubra");
		//idToTable.put(21495, "musnad_shafiee");
		//idToTable.put(2266, "mustadrak");
		//idToTable.put(783, "nasai_no_vowels");
		//idToTable.put(6899, "qasr_amr");
		//idToTable.put(12672, "silsila_daif");
		//idToTable.put(1077, "sunnah_khallal");
		//idToTable.put(179, "targheeb");
		//idToTable.put(782, "tirmidhi_no_vowels");
		//idToTable.put(1435, "tirmidhi_vowels");
		//idToTable.put(8494, "zuhd_ahmad");
		//idToTable.put(8215, "zuhd_dawud");
		//idToTable.put(13028, "zuhd_mubarak");
		//idToTable.put(1446, "khuzayma");
		//idToTable.put(12985, "ilm_aml");

		Connection c = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/collections_source.db");

			for (int bookId: idToTable.keySet())
			{
				System.out.println(bookId+"; "+idToTable.get(bookId));
				ShamelaBookDownloader s = new ShamelaBookDownloader(bookId);
				s.download();
				s.writeToDB(c, idToTable.get(bookId));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (c != null) {
				c.close();
			}
		}
	}
}