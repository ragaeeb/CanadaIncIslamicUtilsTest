package com.canadainc.sunnah10.shamela.albaani;

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.canadainc.sunnah10.Narration;
import com.canadainc.sunnah10.shamela.ShamelaProcessor;
import com.canadainc.sunnah10.shamela.ShamelaTestUtils;
import com.canadainc.sunnah10.shamela.albaani.ShamelaSilsilaDaifProcessor;

public class ShamelaSilsilaDaifProcessorTest
{
	private ShamelaProcessor s;

	@Before
	public void setUp() throws Exception
	{
		s = new ShamelaSilsilaDaifProcessor();
	}

	@Test
	public void process() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/00050.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 1, "باطل",
				new String[]{"أخرجه النسائي في", "أورده منها أبو بكر بن أبي"},
				"الدين هو العقل، ومن لا دين له لا عقل له)");

		ShamelaTestUtils.loadAndAssertSize("silsila_daif/00051.txt", s, 1);
		s.getNarrations().get(0).commentary.contains("وانظر الحديث (370 و5644");

		ShamelaTestUtils.loadAndAssertSize("silsila_daif/00058.txt", s, 2);
		s.getNarrations().get(0).commentary.contains("وغيره فيها: لا أصل له منها");
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 4, "لا أصل له",
				new String[]{"المسجد يأكل الحسنات كما تأكل النار الحطب"},
				"الحديث فى المسجد يأكل الحسنات كما تأكل البهائم الحشيش");
	}


	@Test
	public void dropRanges() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/00958.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 630, "ضعيف",
				new String[]{"ذكره الغزالي في", "الهجيمي عن عبد", "74 طبع سنة", "الغالب. والله أعلم"},
				"قال الله تعالى: الإخلاص سر من سري، استودعته قلب من أحببت من عبادي");
	}


	@Test
	public void testFootnotes() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/00959.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 631, "موضوع",
				new String[]{"ولا ذكره إلا على جهة"},
				"والمتسحر، والمرابط ");
	}


	@Test
	public void testTypos() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/07130.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 5179, "منكر",
				new String[]{"ذكره وتخريجه هناك", "رحمه الله - لم يذكر حديثاً برقم"},
				"أول ما يوضع في ميزان العبد نفقته على أهله");
	}


	@Test
	public void testEmbeddedGrades() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/00984.txt", s, 1);
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/00985.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 656, "ضعيف",
				new String[]{"رواه الطبراني في", "بدليل قوله في الحديث الأول"},
				"أغيثوني، فإن لله عبادا لا نراهم");

		ShamelaTestUtils.loadAndAssertSize("silsila_daif/01491.txt", s, 2);
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/01492.txt", s, 2);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 1015, "ضعيف",
				new String[]{"وعبد الله بن الزبير صحابي معروف"},
				"من سنة الحج أن يصلي", "النساء والطيب حتى يزور");

		ShamelaTestUtils.loadAndAssertSize("silsila_daif/01976.txt", s, 3);
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/01977.txt", s, 4);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(2), 1338, "منكر",
				new String[]{"الفضل بن الأغر الكلابي عن أبيه"},
				"رحمته، وإن شئت عذبته");
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(3), 1339, "منكر",
				new String[]{"وفيه روح بن جناح وثقه دحيم وقال"},
				"يوم يكشف عن ساق");
	}


	@Test
	public void testNumberedList1() throws IOException
	{
		s.getNarrations().add( new Narration(2416) );
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/03299.txt", s, 2);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 2417, "موضوع بهذا السياق",
				new String[]{"لا يتابع عليه، ولا يعرف إلا به"},
				"وعبد مملوك أدى حق الله، وحق مواليه من نفسه");
	}


	@Test
	public void testNumberedList2() throws IOException
	{
		s.getNarrations().add( new Narration(6740) );
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/10495.txt", s, 2);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 6741, "ضعيف جداً",
				new String[]{"واه جداً؛ آفته نوح بن جعونة"},
				"كَظَمَهَا عَبْدٌ لِلَّهِ إِلَّا مَلَأَ اللَّهُ جَوْفَهُ");
	}


	@Test
	public void testNumberedList3() throws IOException
	{
		s.getNarrations().add( new Narration(6434) );
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/09777.txt", s, 2);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 6435, "منكر",
				new String[]{"إسناد ضعيف جداً"},
				"يَشَاءُ مِنْ عِبَادِهِ، وَمَا");
	}


	@Test
	public void testNumberedList4() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/10489.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 6739, "منكر بذكر الفقرة",
				new String[]{"سلمان وأبي الدرداء، وقوله له"},
				"إِلا فَتَحَ اللَّهُ عَلَيْهِ بَابَ فَقْرٍ");
	}


	@Test
	public void testNumberedList5() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/08195.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 5697, "ضعيف جدًا - أو موضوع بهذا السياق والتمام",
				new String[]{"عبد الله بن عمر مرفوعًا"},
				"يمشي امرؤٌ على الأرضِ يَبْغِي");
	}


	@Test
	public void testDetached1() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/09217.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 6194, "منكر",
				new String[]{"وحسّن له الترمذي، فانظر! الصحيحة"},
				"أجر غسله، وأجر غسل امرأته");
	}


	@Test
	public void testDetached2() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/09687.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 6393, "ضعيف",
				new String[]{"والسياق للترمذي وقال"},
				"ابْتَغَى الْهُدَى فِي غَيْرِهِ أَضَلَّهُ اللَّهُ ");
	}


	@Test
	public void testDetached3() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/04442.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 3294, "موضوع",
				new String[]{"كذب على رسول الله - صلى الله عليه وسلم"},
				"ومن أخذ منه القذاة بقدر ما تقذى");
	}


	@Test
	public void testDetached4() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/02916.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 2108, "ضعيف",
				new String[]{"عنه جرير، وكذلك سائر الرواة ثقات رجال"},
				"المنكر");
	}


	@Test
	public void testDetached5() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/07710.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 5490, "موضوع بهذا التمام",
				new String[]{"وقد عزاه للطبراني، وضعفه بكثير"},
				"والملاحم: بدر، وأحد، والخندق، وحنين");
	}


	@Test
	public void testDetached6() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/10793.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 6861, "منكر جداً بزيادة: (وواحدة)",
				new String[]{"طريق محمد بن عشان القزاز"},
				"يارسول الله! وواحدة؟ قال: وواحدة");
	}


	@Test
	public void testDetached7() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/02828.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 2034, "ضعيف",
				new String[]{"الحسين عن أبيه عن علي مرفوعا"},
				"السلطان، فإذا فعلوا ذلك، فاحذروهم");
	}


	@Test
	public void testDetached8() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/08802.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 6010, "منكر",
				new String[]{"عثمان بن إبراهيم الحاطبي -، قال الذهبي في"},
				"خَدِّهِ بَعْدَمَا مَاتَ، وَلا نعْلَمُ");
	}


	@Test
	public void testMixedGrade1() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/08662.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 5949, "موضوع",
				new String[]{"وقال البردعي؛ كما في"},
				"بشيءٍ أفضل من شربة عسل");
	}


	@Test
	public void testMixedGrade2() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/08426.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 5805, "ضعيف جداً",
				new String[]{"الأشعث عن أبي عثمان عن ثوبان مرفوعاً"},
				"لرحمه، ومن التاجر المكنز");
	}


	@Test
	public void testMixedGrade3() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/00829.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 513, "موضوع",
				new String[]{"رواه الخطيب (12 / 92) والسلفي في"},
				"غسل الإناء وطهارة الفناء، يورثان الغنى");
	}


	@Test
	public void testBrokenUp() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/08978.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 6087, "منكر بذكر الملكين",
				new String[]{"حفظهما، قال الحافظ في"},
				"الدَّجَّالُ، وَذَلِكَ فِتْنَةُ ");
	}


	@Test
	public void noGrade1() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/07536.txt", s, 1);
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/07537.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 5406, null,
				new String[]{"سكت عنها ساعة، وتحينت"},
				"فإذا الجفنة قد امتلأت");
	}


	@Test
	public void noGrade2() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/08695.txt", s, 1);
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/08696.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 5961, null,
				new String[]{"الكديمي يَقِينًا؛ فإنهم"},
				"سُليم عن عروة بن");
	}


	@Test
	public void testNumberedListMultiPages() throws IOException
	{
		s.getNarrations().add( new Narration(7128) );
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/11224.txt", s, 2);
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/11225.txt", s, 2);
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/11226.txt", s, 2);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(1), 7129, "منكر جداً",
				new String[]{"وقد أخرجه الطبراني في"},
				"دونه فجاءته شهادة أن لا إله إلا");
	}
	
	
	@Test
	public void testAbandoned1() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/03875.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 2859, null,
				new String[]{"وفهرس الكتاب (ص: 597"},
				"");
	}
	
	
	@Test
	public void testAbandoned2() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/04849.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 3615, null,
				new String[]{") كشاهد حسن لحديث الترجمة"},
				"");
	}
	
	
	@Test
	public void testAbandoned3() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/04858.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 3622, null,
				new String[]{"نقل إلى الصحيحة (3948"},
				"");
	}
	
	
	@Test
	public void testAbandoned4() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/05363.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 4022, null,
				new String[]{"برقم (3286"},
				"");
	}
	
	
	@Test
	public void testStrangeVowel() throws IOException
	{
		ShamelaTestUtils.loadAndAssertSize("silsila_daif/11254.txt", s, 1);
		ShamelaTestUtils.assertCommentary(s.getNarrations().get(0), 7143, "َضعيف جداً",
				new String[]{"متروك متهم"},
				"بخير عملٍ، واجعل ثوابه الجنة");
	}


	@Test
	public void preprocess()  throws IOException
	{
		JSONObject json = new JSONObject();
		String[] rejected = new String[]{"6829", "6830", "6831"};

		for (String r: rejected) {
			json.put("pid", r);
			assertFalse( s.preprocess(json) );
		}
	}
}