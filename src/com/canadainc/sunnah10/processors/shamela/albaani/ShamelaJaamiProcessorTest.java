package com.canadainc.sunnah10.processors.shamela.albaani;

import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.processors.SunnahTestUtils;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;

public class ShamelaJaamiProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception {
		s = new ShamelaJaamiProcessor();
	}

	@Test
	public void testProcess() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0004.txt", s, 7);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 12, "صحيح",
				new String[]{"[حل] عن أبى هريرة. الصحيحة 685."},
				"آمركم بثلاث وأنهاكم عن ثلاث آمركم", "وقال وكثرة السؤال وإضاعة المال");
		SunnahTestUtils.assertCommentary(s.getNarrations().get(2), 14, "صحيح",
				new String[]{"موسى1. الصحيحة 656"},
				"في نفسها وإذنها صماتها");
		SunnahTestUtils.assertCommentary(s.getNarrations().get(6), 18, "حسن",
				new String[]{"عمر. الصحيحة 379"},
				"يخرج من شجرة مباركة");
	}


	@Test
	public void testSplit() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0606.txt", s, 9);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(8), 3578, null,
				new String[]{},
				"زوروا القبور ولا تقولوا هجرا");

		SunnahTestUtils.loadAndAssertSize("jaami/0607.txt", s, 15);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(8), 3578, "صحيح",
				new String[]{"عن زيد بن ثابت. أحكام الجنائز 178 - 179 - بريدة"},
				"زوروا القبور ولا تقولوا هجرا");
		SunnahTestUtils.assertCommentary(s.getNarrations().get(9), 3579, "حسن",
				new String[]{"عن أنس. الكلم الطيب 170"},
				"زودك الله التقوى وغفر ذنبك ويسر لك الخير حيثما كنت");
	}


	@Test
	public void testTypos() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0037.txt", s, 8);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 186, "صحيح",
				new String[]{"ابن عمر. الصحيحة 912: حم, م, خد"},
				"احثوا التراب في وجوه المداحين");
	}


	@Test
	public void testTypos2() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0618.txt", s, 8);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(6), 3645, "حسن",
				new String[]{"حم، م، د، ن، ابن أبي شيبة، هق"},
				"سووا القبور على وجه الأرض إذا دفنتم الموتى");
	}


	@Test
	public void testTypos3() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0632.txt", s, 8);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 3731, "صحيح",
				new String[]{"الحكيم عن أبي بكر. الضعيفة 3755"},
				"الشرك فيكم أخفى من دبيب النمل وسأدلك على شيء");
	}


	@Test
	public void testMixed() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0883.txt", s, 7);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 5379, "صحيح",
				new String[]{"النضير 1196، الصحيحة 545"},
				"الناس فينمي خيرا ويقول خيرا");
	}


	@Test
	public void testMixed2() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0922.txt", s, 6);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(5), 5662, "حسن",
				new String[]{"عائشة. الصحيحة 1048"},
				"لصبيكم هذا يبكي؟ هلا استرقيتم له من العين");
	}


	@Test
	public void testUnderscored() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0098.txt", s, 6);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(0), 569, "صحيح",
				new String[]{"عن أنس. مختصر مسلم"},
				"رأيتم المداحين فاحثوا");
	}


	@Test
	public void testNull() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0608.txt", s, 3);
	}


	@Test
	public void testContinuation() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0206.txt", s, 7);
		SunnahTestUtils.loadAndAssertSize("jaami/0207.txt", s, 14);
		Narration n = SunnahTestUtils.getNarration(s, 1247);

		SunnahTestUtils.assertCommentary(n, 1247, "صحيح",
				new String[]{"الصحيحة 1535: عد، ابن عساكر"},
				"الزم بيتك");
	}


	@Test
	public void testPlainGrade() throws Exception
	{
		SunnahTestUtils.loadAndAssertSize("jaami/0007.txt", s, 8);
		SunnahTestUtils.assertCommentary(s.getNarrations().get(2), 36, "صحيح",
				new String[]{"حم هـ] عن ابن عمرو. الصحيحة"},
				"أبشروا هذا ربكم قد فتح بابا من أبواب");
	}
}