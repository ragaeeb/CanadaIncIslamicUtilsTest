package com.canadainc.quran10;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.junit.Test;

public class QuranTafsirBoundaryTest
{
	private static final String ARCHIVE_PASSWORD = "55XXo@Z_11QHh@";

	@Test
	public void testCreateTable()
	{
		try {
			String[] languages = {"french"/*, "hausa", "indo", "malay", "russian", "spanish", "thai", "turkish", "urdu"*/};
			
			for (String language: languages)
			{
				System.out.println("Creating "+language+"...");
				File f = new File("res/quran10/tafsir/quran_tafsir_"+language+".db");
				f.delete();
				f.createNewFile();
				
				QuranTafsirBoundary qtb = new QuranTafsirBoundary( f.getPath() );
				System.out.println("Creating table...");
				qtb.createTable();
				qtb.createIndices();
				System.out.println("Copy tables from master...");
				qtb.copyFromMaster("/Users/synonymous2/workspace/cpp/Quran10/res/quran_tafsir_english.db");
				System.out.println("Cleaning up...");
				qtb.execute("VACUUM");
				qtb.close();
				
				System.out.println("Compressing...");
				Process p = Runtime.getRuntime().exec("./res/quran10/tafsir/compress.sh "+f.getParent()+"/quran_tafsir_"+language);
				p.waitFor();
				
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

				BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

				// read the output from the command
				System.out.println("Here is the standard output of the command:\n");
				String s = null;
				while ((s = stdInput.readLine()) != null) {
					System.out.println(s);
				}

				// read any errors from the attempted command
				System.out.println("Here is the standard error of the command (if any):\n");
				while ((s = stdError.readLine()) != null) {
					System.out.println(s);
				}
				
				stdInput.close();
				stdError.close();
				
				System.out.println("Compressed...");
				
				System.out.println();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Could not create");
		}
	}
}