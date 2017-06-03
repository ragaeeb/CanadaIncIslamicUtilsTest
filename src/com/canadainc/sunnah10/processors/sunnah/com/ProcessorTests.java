package com.canadainc.sunnah10.processors.sunnah.com;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.canadainc.sunnah10.processors.shamela.ShamelaAwaanahProcessorTest;
import com.canadainc.sunnah10.processors.shamela.ShamelaBazzaarProcessorTest;
import com.canadainc.sunnah10.processors.shamela.ShamelaBookDownloaderTest;
import com.canadainc.sunnah10.processors.shamela.ShamelaDarimiProcessorTest;
import com.canadainc.sunnah10.processors.shamela.ShamelaStandardProcessorTest;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbaanahProcessorTest;
import com.canadainc.sunnah10.processors.shamela.ShamelaMustadrakProcessorTest;
import com.canadainc.sunnah10.processors.shamela.ShamelaUtilsTest;
import com.canadainc.sunnah10.processors.shamela.ShamelaTypoProcessorTest;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaIrwaProcessorTest;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaJaamiProcessorTest;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaSilsilaDaifProcessorTest;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaTargheebProcessorTest;
import com.canadainc.sunnah10.processors.shamela.mubarak.ShamelaJihadProcessorTest;
import com.canadainc.sunnah10.processors.shamela.mubarak.ShamelaMubarakZuhdProcessorTest;
import com.canadainc.sunnah10.processors.shamela.shared.ShamelaIbnMajahNoVowelsProcessorTest;
import com.canadainc.sunnah10.processors.shamela.shared.ShamelaSunanNasaiNoVowelsProcessorTest;
import com.canadainc.sunnah10.processors.shamela.shared.ShamelaSunanNasaiVowelledProcessorTest;
import com.canadainc.sunnah10.processors.shamela.shared.ShamelaTirmidhiNoVowelsProcessorTest;

@RunWith(Suite.class)
@SuiteClasses({ SunnahDotComProcessorTest.class, ShamelaTypoProcessorTest.class,
	ShamelaAwaanahProcessorTest.class, ShamelaBazzaarProcessorTest.class, ShamelaBookDownloaderTest.class,
	ShamelaDarimiProcessorTest.class, ShamelaStandardProcessorTest.class, ShamelaIbaanahProcessorTest.class,
	ShamelaIbnMajahNoVowelsProcessorTest.class, ShamelaMustadrakProcessorTest.class,
	ShamelaSunanNasaiNoVowelsProcessorTest.class, ShamelaSunanNasaiVowelledProcessorTest.class,
	ShamelaTirmidhiNoVowelsProcessorTest.class, ShamelaUtilsTest.class, ShamelaIrwaProcessorTest.class, ShamelaJaamiProcessorTest.class, ShamelaSilsilaDaifProcessorTest.class,
	ShamelaTargheebProcessorTest.class, ShamelaJihadProcessorTest.class, ShamelaMubarakZuhdProcessorTest.class})
public class ProcessorTests
{

}