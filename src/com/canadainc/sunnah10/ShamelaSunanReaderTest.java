package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.canadainc.islamicutils.io.ShamelaReader;

public class ShamelaSunanReaderTest
{
	@Test
	public void testReadNarrationIbnMajah() throws IOException
	{
		ShamelaSunanReader simr = new ShamelaSunanReader( new Tahqeeq("(سنن ابن ماجة)", "تحقيق الألباني:") );
		Narration n = simr.readNarration( new File("res/sunnah10/shamela/sunan_ibn_majah/8.txt") );

		assertEquals("866", n.hadithNumber);
		assertEquals("صحيح الصحيح أيضا", n.grading);
	}

	@Test
	public void testReadNarrationTirmidhi() throws IOException
	{
		ShamelaSunanReader simr = new ShamelaSunanReader( new Tahqeeq("(سنن الترمذي)", "تحقيق الألباني:") );
		Narration n = simr.readNarration( new File("res/sunnah10/shamela/jaami_tirmidhi/16.txt") );

		assertEquals("16", n.hadithNumber);
		assertEquals("صحيح، ابن ماجة (316)", n.grading);
	}

	@Test
	public void testReadNarrationNasai() throws IOException
	{
		ShamelaSunanReader simr = new ShamelaSunanReader( new Tahqeeq("سنن النسائي", "[حكم الألباني]") );
		Narration n = simr.readNarration( new File("res/sunnah10/shamela/sunan_nasai/17.txt") );

		assertEquals("8", n.hadithNumber);
		assertEquals("صحيح", n.grading);
	}

	@Test
	public void testReadNarrationBulugh() throws IOException
	{
		ShamelaSunanReader simr = new ShamelaSunanReader( new Tahqeeq("سنن النسائي", "[حكم الألباني]") );
		simr.readBulughMaram( new File("res/sunnah10/shamela/bulugh/153.txt") );
/*
		assertEquals("151", n.hadithNumber);
		assertTrue("الشَّمْسُ». رَوَاهُ مُسْلِمٌ", n.text.contains(""));
		assertTrue("قرني شيطان». وله ألفاظ أخر", n.grading.contains(""));
		assertEquals( new Chapter("بَابُ الْمَوَاقِيتِ", 0), n.chapter);
		assertEquals( new Book(0, "كِتَابُ الصَّلَاةِ"), n.book);

		n = simr.readBulughMaram( new File("res/sunnah10/shamela/bulugh/177.txt") );

		assertEquals("175", n.hadithNumber);
		assertTrue("عَمْرِوِ بْنِ الْعَاصِ", n.text.contains(""));
		assertTrue("صلاة الفجر إلا ركعتين", n.grading.contains(""));
		assertNull(n.chapter);
		assertEquals( new Book(0, null), n.book); */
	}

	@Test
	public void testReadNarrationAbuDawud() throws IOException
	{
		ShamelaSunanReader simr = new ShamelaSunanReader( new Tahqeeq("صحيح وضعيف سنن أبي داود", "تحقيق الألباني:") );
		Narration n = simr.readNarration( new File("res/sunnah10/shamela/sunan_abu_dawud/129.txt") );

		assertEquals("129", n.hadithNumber);
		assertEquals("حسن", n.grading);

		n = simr.readNarration( new File("res/sunnah10/shamela/sunan_abu_dawud/1601.txt") );
		assertEquals("1601", n.hadithNumber);
		assertEquals("حسن", n.grading);
	}

	@Test
	public void populate() throws IOException
	{
		ShamelaSunanReader simr = new ShamelaSunanReader( new Tahqeeq("(سنن ابن ماجة)", "تحقيق الألباني:") );
		File[] files = ShamelaReader.getOrderedFiles("/Users/rhaq/workspace/resources/shamela/arabic/ibnmajah");
		ArrayList<Narration> narrations = new ArrayList<>();

		for (File f: files) {
			narrations.add( simr.readNarration(f) );
		}
		
		assertEquals( 4341, narrations.size() );

		narrations.clear();
		simr = new ShamelaSunanReader( new Tahqeeq("سنن النسائي", "[حكم الألباني]") );
		files = ShamelaReader.getOrderedFiles("/Users/rhaq/workspace/resources/shamela/arabic/nasai");

		for (File f: files) {
			narrations.add( simr.readNarration(f) );
		}
		
		assertEquals( 8331, narrations.size() );

		narrations.clear();
		simr = new ShamelaSunanReader( new Tahqeeq("(سنن الترمذي)", "تحقيق الألباني:") );
		files = ShamelaReader.getOrderedFiles("/Users/rhaq/workspace/resources/shamela/arabic/tirmidhi");

		for (File f: files) {
			narrations.add( simr.readNarration(f) );
		}
		
		assertEquals( 3956, narrations.size() );

		narrations.clear();
		simr = new ShamelaSunanReader( new Tahqeeq("صحيح وضعيف سنن أبي داود", "تحقيق الألباني:") );
		files = ShamelaReader.getOrderedFiles("/Users/rhaq/workspace/resources/shamela/arabic/abudawud");

		for (File f: files) {
			narrations.add( simr.readNarration(f) );
		}
		
		assertEquals( 5274, narrations.size() );
		
		narrations.clear();

		for (int i = 153; i <= 524; i++) { // 380
			simr.readBulughMaram( new File("/Users/rhaq/workspace/resources/shamela/arabic/bulugh/"+i+".txt") );
		}
		
		for (int i = 767; i <= 1132; i++) { // 384
			simr.readBulughMaram( new File("/Users/rhaq/workspace/resources/shamela/arabic/bulugh/"+i+".txt") );
		}
		
		assertEquals( 784, narrations.size() );
	}
}