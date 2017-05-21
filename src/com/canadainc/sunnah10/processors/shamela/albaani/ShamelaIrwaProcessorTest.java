package com.canadainc.sunnah10.processors.shamela.albaani;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Before;
import org.junit.Test;

import com.canadainc.common.io.IOUtils;
import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaIrwaProcessor;

public class ShamelaIrwaProcessorTest
{
	private ShamelaProcessor s;

	@Test
	public void preprocess() throws Exception
	{
		ShamelaProcessor s = new ShamelaIrwaProcessor();
		JSONObject json = (JSONObject)JSONValue.parse( SunnahTestUtils.loadFromDatabase("irwa/2327.txt") );
		s.preprocess(json);
		assertTrue( json.get("content").toString().contains("<br /><br />(2023) - ") );

		json = (JSONObject)JSONValue.parse( SunnahTestUtils.loadFromDatabase("irwa/2549.txt") );
		s.preprocess(json);
		assertTrue( json.get("content").toString().contains("<span class=\"title\">(2255) - ") );
	}


	@Before
	public void setUp() throws Exception {
		s = new ShamelaIrwaProcessor();
	}


	@Test
	public void process1() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("irwa/0024.txt", s, 1);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 1, "ضعيف جدا",
				new String[]{"وقد رواه السبكي في", "الأوزاعي به، إلا أنه", "للشيخ مرعي بن يوسف الكرمي."},
				"الرحيم فهو أبتر", "الرهاوي ص 5 ");

		SunnahTestUtils.loadAndAssertSize("irwa/0025.txt", s, 2);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 1, "ضعيف جدا",
				new String[]{"قال: بحمد الله بدل بسم", "في فهرسه، والله أعلم"});
		SunnahTestUtils.assertCommentary(s.getNarrations().get(1), 2, "ضعيف",
				new String[]{"رواه ابن ماجه", "فهو أجذم \". وقال"},
				"كل أمر ذى بال", "الأربعين \" له (صـ 5)");
	}


	@Test
	public void process2() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("irwa/0034.txt", s, 1);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 8, "صحيح",
				new String[]{"وهو من حديث عبد الله بن أبى", "بالثلج والماء والبرد"},
				"قول النبى صلى", "ص 8");
		assertFalse( s.getNarrations().get(0).commentary.contains("كتاب الطهارة") );
	}


	@Test
	public void sequenceTest() throws Exception
	{
		s.getNarrations().add( new Narration(2248) );

		SunnahTestUtils.loadAndAssertSize("irwa/2546.txt", s, 3);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(1), 2249, "حسن",
				new String[]{"ومضى (2247)"},
				"الإبل قد غلت");
		SunnahTestUtils.assertCommentary(s.getNarrations().get(2), 2250, "ضعيف",
				new String[]{"منهم عمر بن الخطاب"},
				"النصف من دية الرجل");
	}


	@Test
	public void process3() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("irwa/2551.txt", s, 2);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 2260, "ضعيف",
				new String[]{"أخرجه ابن أبى شيبة", "(4/34) وسكت عليه"},
				"وللشهر الحرام أربعة");
		SunnahTestUtils.assertCommentary(s.getNarrations().get(1), 2261, "صحيح",
				new String[]{"وقد مضى (2220"},
				"هذيل وأنا والله عاقله");
	}


	@Test
	public void readNullGrade() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("irwa/2549.txt", s, 2);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 2255, "صحيح عن سعيد",
				new String[]{"كما هو مقرر فى"},
				"وسعيد بن منصور فى سننه");
		SunnahTestUtils.assertCommentary(s.getNarrations().get(1), 2256, null,
				new String[]{},
				"حتى يبلغ الثلث");
	}


	@Test
	public void typoDetection() throws Exception {
		SunnahTestUtils.loadAndAssertSize("irwa/0440.txt", s, 2);
	}


	@Test
	public void fixDoubleSpan() throws Exception {
		SunnahTestUtils.loadAndAssertSize("irwa/0879.txt", s, 2);
	}


	@Test
	public void gradeInNextFile() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("irwa/0891.txt", s, 3);
		SunnahTestUtils.loadAndAssertSize("irwa/0892.txt", s, 3);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(2), 772, "صحيح",
				new String[]{"من طريق أيوب بن جابر عن سماك"},
				"");
	}
}