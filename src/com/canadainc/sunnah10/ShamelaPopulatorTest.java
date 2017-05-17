package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.canadainc.sunnah10.processors.shamela.ShamelaAwaanahProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaBazzaarProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaDarimiProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaDawudZuhdProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbaanahProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbnMajahNoVowelsProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaMustadrakProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaPopulator;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaSunanNasaiVowelledProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaTirmidhiNoVowelsProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaIrwaProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaJaamiProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaSilsilaDaifProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaTargheebProcessor;
import com.canadainc.sunnah10.processors.shamela.mubarak.ShamelaJihadProcessor;
import com.canadainc.sunnah10.processors.shamela.mubarak.ShamelaMubarakZuhdProcessor;

public class ShamelaPopulatorTest
{
	@Test
	public void processBazzaar() throws IOException {
		ShamelaPopulator sp = process("bazzaar", new ShamelaBazzaarProcessor(), 10338);
		sp.validateSequence();
	}


	@Test
	public void processIbnMajahNoVowels() throws IOException {
		ShamelaPopulator sp = process("ibnmajah", new ShamelaIbnMajahNoVowelsProcessor(), 4341);
		sp.validateSequence();
		//sp.validateGrades();
	}


	@Test
	public void processTirmidhiNoVowels() throws IOException {
		ShamelaPopulator sp = process("tirmidhi", new ShamelaTirmidhiNoVowelsProcessor(), 3954);
		sp.validateSequence();
		sp.validateGrades();
	}
	
	
	@Test
	public void processNasaiVowels() throws IOException {
		ShamelaPopulator sp = process("nasai", new ShamelaSunanNasaiVowelledProcessor(), 5769);
		sp.validateSequence();
		sp.validateGrades();
	}


	@Test
	public void processTargheeb() throws IOException {
		ShamelaPopulator sp = process("targheeb", new ShamelaTargheebProcessor(), 3773);
		sp.validateSequence();
		sp.validateGrades();
	}



	@Test
	public void testProcessJihad() throws IOException {
		process("jihad", new ShamelaJihadProcessor(), 262);
	}


	@Test
	public void testProcessIbaanah() throws IOException {
		process("ibaanah", new ShamelaIbaanahProcessor(), 3122);
	}


	@Test
	public void testProcessDarimi() throws IOException {
		process("sunan_darimi", new ShamelaDarimiProcessor(), 3541);
	}


	@Test
	public void testProcessZuhdMubarak() throws IOException {
		process("zuhd_mubarak", new ShamelaMubarakZuhdProcessor(), 2070);
	}


	@Test
	public void testProcessZuhdDawud() throws IOException {
		process("zuhd_dawud", new ShamelaDawudZuhdProcessor(), 502);
	}


	@Test
	public void testProcessMustadrak() throws IOException {
		process("mustadrak", new ShamelaMustadrakProcessor(), 8803);
	}


	@Test
	public void testProcessIrwa() throws IOException {
		process("irwa", new ShamelaIrwaProcessor(), 2447);
	}


	@Test
	public void testProcessDaif() throws IOException
	{
		ShamelaPopulator sp = process("silsila_daif", new ShamelaSilsilaDaifProcessor(), 7141);
		sp.validateGrades();
	}


	@Test
	public void testProcessAwaanah() throws IOException
	{
		ShamelaPopulator sp = process("awaanah", new ShamelaAwaanahProcessor(), 8682);
		sp.validateSequence();
		//sp.validateGrades();
	}


	@Test
	public void testProcessJaami() throws IOException
	{
		process("jaami", new ShamelaJaamiProcessor(), 8201);
	}


	private ShamelaPopulator process(String folderName, ShamelaProcessor processor, int expectedSize) throws IOException
	{
		ShamelaPopulator sp = new ShamelaPopulator("/Users/rhaq/workspace/resources/shamela/arabic/"+folderName, processor);
		sp.process();

		assertEquals( expectedSize, processor.getNarrations().size() );
		return sp;
	}
}