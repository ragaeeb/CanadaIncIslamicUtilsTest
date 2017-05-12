package com.canadainc.sunnah10.shamela.albaani;

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
import com.canadainc.sunnah10.shamela.ShamelaProcessor;
import com.canadainc.sunnah10.shamela.ShamelaTestUtils;
import com.canadainc.sunnah10.shamela.albaani.ShamelaIrwaProcessor;

public class ShamelaIrwaProcessorTest
{
	private ShamelaProcessor s;

	@Test
	public void preprocess() throws IOException
	{
		ShamelaProcessor s = new ShamelaIrwaProcessor();
		JSONObject json = (JSONObject)JSONValue.parse( IOUtils.readFileUtf8( new File("/Users/rhaq/workspace/resources/shamela/arabic/irwa/2327.txt") ) );
		s.preprocess(json);
		assertTrue( json.get("content").toString().contains("<br /><br />(2023) - ") );

		json = (JSONObject)JSONValue.parse( IOUtils.readFileUtf8( new File("/Users/rhaq/workspace/resources/shamela/arabic/irwa/2549.txt") ) );
		s.preprocess(json);
		assertTrue( json.get("content").toString().contains("<span class=\"title\">(2255) - ") );
	}


	@Before
	public void setUp() throws Exception {
		s = new ShamelaIrwaProcessor();
	}


	@Test
	public void process1() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("irwa/0024.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 1, "ضعيف جدا",
				new String[]{"وقد رواه السبكي في", "الأوزاعي به، إلا أنه", "للشيخ مرعي بن يوسف الكرمي."},
				"الرحيم فهو أبتر", "الرهاوي ص 5 ");

		ShamelaTestUtils.loadAndAssertSize("irwa/0025.txt", s, 2);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 1, "ضعيف جدا",
				new String[]{"قال: بحمد الله بدل بسم", "في فهرسه، والله أعلم"});
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 2, "ضعيف",
				new String[]{"رواه ابن ماجه", "فهو أجذم \". وقال"},
				"كل أمر ذى بال", "الأربعين \" له (صـ 5)");
	}


	@Test
	public void process2() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("irwa/0034.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 8, "صحيح",
				new String[]{"وهو من حديث عبد الله بن أبى", "بالثلج والماء والبرد"},
				"قول النبى صلى", "ص 8");
		assertFalse( s.getNarrations().get(0).commentary.contains("كتاب الطهارة") );
	}


	@Test
	public void sequenceTest() throws IOException
	{
		s.getNarrations().add( new Narration(2248) );

		ShamelaTestUtils.loadAndAssertSize("irwa/2546.txt", s, 3);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 2249, "حسن",
				new String[]{"ومضى (2247)"},
				"الإبل قد غلت");
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(2), 2250, "ضعيف",
				new String[]{"منهم عمر بن الخطاب"},
				"النصف من دية الرجل");
	}


	@Test
	public void process3() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("irwa/2551.txt", s, 2);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 2260, "ضعيف",
				new String[]{"أخرجه ابن أبى شيبة", "(4/34) وسكت عليه"},
				"وللشهر الحرام أربعة");
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 2261, "صحيح",
				new String[]{"وقد مضى (2220"},
				"هذيل وأنا والله عاقله");
	}


	@Test
	public void readNullGrade() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("irwa/2549.txt", s, 2);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 2255, "صحيح عن سعيد",
				new String[]{"كما هو مقرر فى"},
				"وسعيد بن منصور فى سننه");
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 2256, null,
				new String[]{},
				"حتى يبلغ الثلث");
	}


	@Test
	public void typoDetection() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("irwa/0440.txt", s, 2);
	}


	@Test
	public void fixDoubleSpan() throws IOException {
		ShamelaTestUtils.loadAndAssertSize("irwa/0879.txt", s, 2);
	}


	@Test
	public void gradeInNextFile() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("irwa/0891.txt", s, 3);
		ShamelaTestUtils.loadAndAssertSize("irwa/0892.txt", s, 3);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(2), 772, "صحيح",
				new String[]{"من طريق أيوب بن جابر عن سماك"},
				"");
	}
}