package com.canadainc.quran10;

import static org.junit.Assert.*;

import org.junit.Test;

import com.canadainc.quran10.Sajda.SajdaType;
import com.canadainc.quran10.SurahMetadata.RevealedLocation;

public class QuranArabicExtractorTest
{
	@Test
	public void testLoad()
	{
		QuranArabicExtractor q = new QuranArabicExtractor("res/quran10/quran-data.xml");

		try {
			q.load();

			assertEquals( 2, q.getMetadata().size() );
			assertEquals( new SurahMetadata("الفلق", 5, 6225, RevealedLocation.Meccan, 20, 1), q.getMetadata().get(113) );
			assertEquals( new SurahMetadata("الناس", 6, 6230, RevealedLocation.Medinan, 21, 1), q.getMetadata().get(114) );

			assertEquals( 2, q.getEnglish().size() );
			assertEquals( new ChapterTranslation("The Dawn", "Al-Falaq"), q.getEnglish().get(113) );
			assertEquals( new ChapterTranslation("Mankind", "An-Naas"), q.getEnglish().get(114) );

			assertEquals( 3, q.getJuzs().size() );
			assertEquals( new SurahAyat(1,1), q.getJuzs().get(1) );
			assertEquals( new SurahAyat(2,142), q.getJuzs().get(2) );
			assertEquals( new SurahAyat(2,253), q.getJuzs().get(3) );

			assertEquals( 2, q.getHizbs().size() );
			assertEquals( new SurahAyat(1,1), q.getHizbs().get(1) );
			assertEquals( new SurahAyat(2,26), q.getHizbs().get(2) );

			assertEquals( 3, q.getManzils().size() );
			assertEquals( new SurahAyat(1,1), q.getManzils().get(1) );
			assertEquals( new SurahAyat(5,1), q.getManzils().get(2) );
			assertEquals( new SurahAyat(10,1), q.getManzils().get(3) );

			assertEquals( 3, q.getRukus().size() );
			assertEquals( new SurahAyat(1,1), q.getRukus().get(1) );
			assertEquals( new SurahAyat(2,1), q.getRukus().get(2) );
			assertEquals( new SurahAyat(2,8), q.getRukus().get(3) );

			assertEquals( 2, q.getMushafPages().size() );
			assertEquals( new SurahAyat(1,1), q.getMushafPages().get(1) );
			assertEquals( new SurahAyat(2,1), q.getMushafPages().get(2) );

			assertEquals( 2, q.getSajdas().size() );
			assertEquals( new Sajda(7,206,SajdaType.Obligatory), q.getSajdas().get(0) );
			assertEquals( new Sajda(13,15,SajdaType.Recommended), q.getSajdas().get(1) );
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
