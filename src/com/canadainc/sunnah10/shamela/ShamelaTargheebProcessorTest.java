package com.canadainc.sunnah10.shamela;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Book;
import com.canadainc.sunnah10.Chapter;

public class ShamelaTargheebProcessorTest
{
	private ShamelaProcessor s;
	
	@Before
	public void setUp() throws Exception {
		s = new ShamelaTargheebProcessor();
	}

	@Test
	public void test4CommentsIn1() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("targheeb/0100.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 1, "صحيح",
				new String[]{"أسقى، كما يأتي عند المصنف في آخر الحديث", "مراحها الذي تبيت فيه"},
				"قال النبي - صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ", "عن ابن عمر رضي الله عنهما",
				"أغبُقَ (4) قبلَهما أهلاً ولا مالاً");
		assertEquals( new Book(1,"كتاب الإخلاص"), s.getNarrations().get(0).book);
		assertEquals( new Chapter("الترغيب في الإخلاص والصدق والنية الصالحة", 1), s.getNarrations().get(0).chapter);
		assertEquals( 1, s.getNarrations().get(0).inBookNumber);
	}

	@Test
	public void testPreprocess()
	{
	}
}