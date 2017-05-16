package com.canadainc.sunnah10.processors.shamela;

import static org.junit.Assert.*;

import org.junit.Test;

import com.canadainc.sunnah10.processors.shamela.ShamelaBookDownloader;

public class ShamelaBookDownloaderTest
{
	@Test
	public void testDownload()
	{
		ShamelaBookDownloader sbd = new ShamelaBookDownloader(3610);
		sbd.download();
	}
}