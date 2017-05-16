package com.canadainc.sunnah10;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.canadainc.sunnah10.processors.shamela.ShamelaAwaanahProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaBazzaarProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaDarimiProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaDawudZuhdProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaIbaanahProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaMustadrakProcessor;
import com.canadainc.sunnah10.processors.shamela.ShamelaPopulator;
import com.canadainc.sunnah10.processors.shamela.ShamelaProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaIrwaProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaJaamiProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaSilsilaDaifProcessor;
import com.canadainc.sunnah10.processors.shamela.albaani.ShamelaTargheebProcessor;
import com.canadainc.sunnah10.processors.shamela.mubarak.ShamelaJihadProcessor;
import com.canadainc.sunnah10.processors.shamela.mubarak.ShamelaMubarakZuhdProcessor;

public class ShamelaPopulatorTest
{
	@Test
	public void processBazzaar() throws SQLException, IOException {
		ShamelaPopulator sp = process("bazzaar", new ShamelaBazzaarProcessor(), 10338);
		sp.validateSequence();
	}

	
	@Test
	public void processTargheeb() throws SQLException, IOException {
		ShamelaPopulator sp = process("targheeb", new ShamelaTargheebProcessor(), 3773);
		sp.validateSequence();
		sp.validateGrades();
	}
	
	
	
	@Test
	public void testProcessJihad() throws SQLException, IOException {
		process("jihad", new ShamelaJihadProcessor(), 262);
	}


	@Test
	public void testProcessIbaanah() throws SQLException, IOException {
		process("ibaanah", new ShamelaIbaanahProcessor(), 3122);
	}


	@Test
	public void testProcessDarimi() throws SQLException, IOException {
		process("sunan_darimi", new ShamelaDarimiProcessor(), 3541);
	}


	@Test
	public void testProcessZuhdMubarak() throws SQLException, IOException {
		process("zuhd_mubarak", new ShamelaMubarakZuhdProcessor(), 2070);
	}


	@Test
	public void testProcessZuhdDawud() throws SQLException, IOException {
		process("zuhd_dawud", new ShamelaDawudZuhdProcessor(), 502);
	}


	@Test
	public void testProcessMustadrak() throws SQLException, IOException {
		process("mustadrak", new ShamelaMustadrakProcessor(), 8803);
	}


	@Test
	public void testProcessIrwa() throws SQLException, IOException {
		process("irwa", new ShamelaIrwaProcessor(), 2447);
	}
	

	@Test
	public void testProcessDaif() throws SQLException, IOException
	{
		ShamelaPopulator sp = process("silsila_daif", new ShamelaSilsilaDaifProcessor(), 7141);
		sp.validateGrades();
	}
	
	
	@Test
	public void testProcessAwaanah() throws SQLException, IOException
	{
		ShamelaPopulator sp = process("awaanah", new ShamelaAwaanahProcessor(), 8682);
		sp.validateSequence();
		//sp.validateGrades();
	}

	
	@Test
	public void testProcessJaami() throws SQLException, IOException
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