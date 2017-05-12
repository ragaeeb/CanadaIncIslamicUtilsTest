package com.canadainc.sunnah10.shamela;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.canadainc.sunnah10.shamela.albaani.ShamelaIrwaProcessorTest;
import com.canadainc.sunnah10.shamela.albaani.ShamelaSilsilaDaifProcessorTest;
import com.canadainc.sunnah10.shamela.mubarak.ShamelaJihadProcessorTest;
import com.canadainc.sunnah10.shamela.mubarak.ShamelaMubarakZuhdProcessorTest;

@RunWith(Suite.class)
@SuiteClasses({ ShamelaDarimiProcessorTest.class, ShamelaDawudZuhdProcessorTest.class,
		ShamelaIbaanahProcessorTest.class, ShamelaIrwaProcessorTest.class, ShamelaJihadProcessorTest.class,
		ShamelaMubarakZuhdProcessorTest.class, ShamelaMustadrakProcessorTest.class,
		ShamelaSilsilaDaifProcessorTest.class, ShamelaUtilsTest.class })
public class ShamelaProcessorTests
{

}
