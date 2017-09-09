package com.canadainc.sunnah10.controller.merge;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.processors.Processor;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.DatabasePopulator;
import com.canadainc.sunnah10.processors.shamela.ShamelaPopulator;
import com.canadainc.sunnah10.processors.sunnah.com.IbnMajahProcessor;
import com.canadainc.sunnah10.utils.SunnahUtils;

public class CommentaryBasedMergerTest
{
	private CollectionMerger s;

	@Before
	public void setUp() throws Exception {
		s = new CommentaryBasedMerger();
	}
	
	@Test
	public void test() throws Exception
	{
		Class.forName( org.sqlite.JDBC.class.getCanonicalName() ); // load the sqlite-JDBC driver using the current class loader
		Connection c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/collections_source.db");
		
		IbnMajahProcessor imp = new IbnMajahProcessor();
		DatabasePopulator sp = new ShamelaPopulator("sunnah_com", "english/ibnmajah", imp);
		sp.process(c);
		
		Map<Integer,Integer> translations = imp.getTranslations();
		System.out.println(translations.size());
		
		imp = new IbnMajahProcessor();
		sp = new ShamelaPopulator("sunnah_com", "arabic/ibnmajah", imp);
		sp.process(c);
		
		translations = imp.getTranslations();
		System.out.println(translations.size());
	}
	

	@Test
	public void testMerge()
	{
		ArrayList<Narration> arabic = new ArrayList<>();
		arabic.add( new Narration(2).setText("2_ARABIC_TEXT").setGrade("2_Sahih") );
		arabic.add( new Narration(1).setText("1_ARABIC_TEXT").setGrade("1_Sahih") );
		s.setArabic(arabic);

		arabic = new ArrayList<>();
		arabic.add( new Narration(2).setText("2_ARABIC_BODY").setGrade("2_Saheeh") );
		arabic.add( new Narration(1).setText("1_ARABIC_BODY").setGrade("1_Saheeh") );
		s.setCommentary(arabic);

		arabic = new ArrayList<>();
		arabic.add( new Narration(200).setHadithNumber("2").setText("2_ENGLISH_BODY").setGrade("2_Authentic") );
		arabic.add( new Narration(100).setHadithNumber("1").setText("1_ENGLISH_BODY").setGrade("1_Authentic") );
		s.setTranslation(arabic, null);

		s.merge();

		assertEquals( 2, s.getArabic().size() );
		assertEquals( new Narration(1).setText("1_ARABIC_TEXT").setGrade("1_Sahih").setCommentary("1_Saheeh"), s.getArabic().get(0) );
		assertEquals( new Narration(2).setText("2_ARABIC_TEXT").setGrade("2_Sahih").setCommentary("2_Saheeh"), s.getArabic().get(1) );

		assertEquals( 2, s.getTranslations().size() );
		assertEquals( new Narration(1).setText("1_ENGLISH_BODY").setGrade("1_Authentic").setHadithNumber("1"), s.getTranslations().get(0) );
		assertEquals( new Narration(2).setText("2_ENGLISH_BODY").setGrade("2_Authentic").setHadithNumber("2"), s.getTranslations().get(1) );
	}
}