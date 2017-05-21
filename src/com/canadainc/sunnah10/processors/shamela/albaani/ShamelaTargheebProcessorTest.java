package com.canadainc.sunnah10.processors.shamela.albaani;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Book;
import com.canadainc.sunnah10.Chapter;
import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaTargheebProcessor;

public class ShamelaTargheebProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaTargheebProcessor();
	}

	@Test
	public void testContinuedNarration() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("targheeb/0100.txt", s, 1);
		SunnahTestUtils.loadAndAssertSize("targheeb/0101.txt", s, 1);
		SunnahTestUtils.loadAndAssertSize("targheeb/0102.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 1, "صحيح",
				"عن ابن عمر رضي الله عنهما قال: سمعت رسول الله - صَلَّى اللهُ عَلَيْهِ ",
				"فانْفَرَجَتْ شيئاً لا يستطيعون الخروجَ، -قال النبي - صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ",
				"رواه البخاري ومسلم والنسائي", "بقراً، وأنَّه أتاني يطلبُ أجرَه، فقلتُ له: اعمِدْ إلى تلك");

		assertEquals( new Book(1,"كتاب الإخلاص"), s.getNarrations().get(0).book);
		assertEquals( new Chapter("الترغيب في الإخلاص والصدق والنية الصالحة", 1), s.getNarrations().get(0).chapter);
		assertEquals( 1, s.getNarrations().get(0).inBookNumber);
		assertFalse( s.getNarrations().get(0).text.contains("كما يأتي عند المصنف في آخر الحديث") );

		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 2, "صحيح",
				"بالسين والحاء المهملتين، اْي: تَنَحَّتِ الصخرة وزالت عن فم الغار");
		assertEquals( new Book(1,"كتاب الإخلاص"), s.getNarrations().get(1).book);
		assertEquals( new Chapter("الترغيب في الإخلاص والصدق والنية الصالحة", 1), s.getNarrations().get(1).chapter);
		assertEquals( 2, s.getNarrations().get(1).inBookNumber);
	}


	@Test
	public void testPartialChapter() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("targheeb/0106.txt", s, 2);
		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 9, "حسن لغيره",
				"رواه الطبراني بإسناد لا بأس");
		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 10, "صحيح",
				"قاله الحافظ علي بن المديني وغيره من");
		assertNull( s.getNarrations().get(0).book );
		assertNull( s.getNarrations().get(0).chapter );
		assertNull( s.getNarrations().get(1).book );
		assertNull( s.getNarrations().get(1).chapter );
	}


	@Test
	public void testMultiChapter() throws Exception
	{
		Chapter c = new Chapter("الترغيب في إِكرام العلماء إجلالهم وتوقيرهم، والترهيب من إضاعتهم وعدم المبالاة بهم", 5);

		SunnahTestUtils.loadAndAssertSize("targheeb/0150.txt", s, 3);

		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 97, "صحيح",
				"أحدِهما، قدّمه في اللحدِ. رواه البخاري");
		assertEquals(c, s.getNarrations().get(0).chapter);

		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 98, "حسن",
				"عنه، وإكرامَ ذي السلطان المُقْسِطِ");
		assertEquals(c, s.getNarrations().get(1).chapter);

		SunnahTestUtils.assertNarration(s.getNarrations().get(2), 99, "صحيح",
				"صحيح على شرط مسلم");
		assertEquals(c, s.getNarrations().get(2).chapter);
	}


	@Test
	public void testBookNoBrackets() throws Exception
	{
		Book b = new Book(3, "كتاب العِلم");
		Chapter c = new Chapter("الترغيب في العلم وطلبه وتعلمه وتعليمه، وما جاء في فضل العلماء والمتعلمين", 1);

		SunnahTestUtils.loadAndAssertSize("targheeb/0135.txt", s, 1);

		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 67, "صحيح",
				"وفي إسناده راوٍ لم يسم");
		assertEquals(b, s.getNarrations().get(0).book);
		assertEquals(c, s.getNarrations().get(0).chapter);
		assertEquals(1, s.getNarrations().get(0).inBookNumber);
	}


	@Test
	public void numberedList() throws Exception
	{
		Chapter c = new Chapter("الترهيب من الالتفات في الصلاة وغيره مما يذكَر", 36);

		s.getNarrations().add( new Narration(551) );

		SunnahTestUtils.loadAndAssertSize("targheeb/0357.txt", s, 2);

		SunnahTestUtils.assertNarration(s.getNarrations().get(1), 552, "صحيح",
				"الله عنه؛ أنّ النبي - صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ",
				"بالصدقة، فإن مَثَلَ ذلك كمثل رجلٍ أسَرَهُ العَدُوّ، فأوثقوا يَدَه");
		assertEquals(c, s.getNarrations().get(1).chapter);
		assertEquals(1, s.getNarrations().get(1).inBookNumber);
	}
	
	
	@Test
	public void deletedHadeeth() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("targheeb/1709.txt", s, 1);

		SunnahTestUtils.assertNarration(s.getNarrations().get(0), 3492, "صحيح",
				"أسْكَنُه إلى يوم القِيامَةِ");
	}


	@Test
	public void infiniteLoop() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("targheeb/1057.txt", s, 3);

		Narration n = s.getNarrations().get(0);
		SunnahTestUtils.assertNarration(n, 1964, "صحيح",
				"وقد تقدم هذا الحديث وغيره في باب الإنفاق والإمساك");
		assertNull( n.chapter);
		assertNull( n.book);
		assertEquals(14, n.inBookNumber);

		n = s.getNarrations().get(1);
		SunnahTestUtils.assertNarration(n, 1965, "حسن لغيره",
				"صحيح الإسناد");
		assertNull( n.chapter);
		assertNull( n.book);
		assertEquals(15, n.inBookNumber);

		n = s.getNarrations().get(2);
		SunnahTestUtils.assertNarration(n, 1966, "حسن صحيح",
				"رواه ابن حبان في");
		assertNull( n.chapter);
		assertNull( n.book);
		assertEquals(16, n.inBookNumber);
	}
}