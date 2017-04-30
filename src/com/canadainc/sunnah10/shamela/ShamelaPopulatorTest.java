package com.canadainc.sunnah10.shamela;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

public class ShamelaPopulatorTest
{
	//@Test
	public void testProcessJihad() throws SQLException, IOException {
		process("jihad", new ShamelaJihadProcessor(), 262);
	}


	//@Test
	public void testProcessIbaanah() throws SQLException, IOException {
		process("ibaanah", new ShamelaIbaanahProcessor(), 1627);
	}


	//@Test
	public void testProcessDarimi() throws SQLException, IOException {
		process("sunan_darimi", new ShamelaDarimiProcessor(), 1627);
	}


	//@Test
	public void testProcessZuhdMubarak() throws SQLException, IOException {
		process("zuhd_mubarak", new ShamelaMubarakZuhdProcessor(), 1627);
	}


	//@Test
	public void testProcessZuhdDawud() throws SQLException, IOException {
		process("zuhd_dawud", new ShamelaDawudZuhdProcessor(), 502);
	}


	//@Test
	public void testProcessMustadrak() throws SQLException, IOException {
		process("mustadrak", new ShamelaMustadrakProcessor(), 8803);
	}


	private void process(String folderName, ShamelaProcessor processor, int expectedSize) throws IOException
	{
		ShamelaPopulator sp = new ShamelaPopulator("/Users/rhaq/workspace/resources/shamela/arabic/"+folderName, processor);
		sp.process();

		assertEquals( expectedSize, processor.getNarrations().size() );
	}
}