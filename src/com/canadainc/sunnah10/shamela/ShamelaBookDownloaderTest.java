package com.canadainc.sunnah10.shamela;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShamelaBookDownloaderTest
{
	@Test
	public void testDownload()
	{
		ShamelaBookDownloader sbd = new ShamelaBookDownloader(3610);
		sbd.download();
	}
}