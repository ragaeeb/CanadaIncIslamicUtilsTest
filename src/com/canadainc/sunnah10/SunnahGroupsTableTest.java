package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.common.io.IOUtils;
import com.canadainc.islamicutils.io.DBUtils;

public class SunnahGroupsTableTest
{
	private static final String DB_PATH = "res/sunnah10/sunnah10_similar.db";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		Class.forName("org.sqlite.JDBC"); // load the sqlite-JDBC driver using the current class loader
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		DBUtils.cleanUp(DB_PATH);
	}

	@Test
	public void testGetTableName() {
		assertEquals( "grouped_narrations", new SunnahGroupsTable().getTableName() );
	}


	@Test
	public void testPopulate()
	{
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/ilm_english.db");
			c.setAutoCommit(false);

			String[] data = IOUtils.readFileUtf8( new File("res/sunnah10/related_narrations.csv") ).split("\n");
			ArrayList<RelatedNarration> narrations = new ArrayList<RelatedNarration>();

			for (int i = data.length-1; i > 0; i--) // skip header
			{
				String[] tokens = data[i].split(",");				
				narrations.add( new RelatedNarration( Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]) ) );
			}

			SunnahGroupsTable sgt = new SunnahGroupsTable();
			sgt.setConnection(c);

			sgt.process(narrations);
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Failed!");
		}
	}


	@Test
	public void testProcess()
	{
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
			c.setAutoCommit(false);

			ArrayList<RelatedNarration> all = new ArrayList<RelatedNarration>();
			all.add( new RelatedNarration(1,2) );
			all.add( new RelatedNarration(1,3) );
			all.add( new RelatedNarration(2,4) );
			all.add( new RelatedNarration(3,4) );
			all.add( new RelatedNarration(5,6) );
			all.add( new RelatedNarration(7,8) );

			SunnahGroupsTable sgt = new SunnahGroupsTable();
			sgt.setConnection(c);

			sgt.process(all);

			assertEquals( 1, sgt.getIdFor( new RelatedNarration(1,2) ) );
			assertEquals( 1, sgt.getIdFor( new RelatedNarration(1,3) ) );
			assertEquals( 1, sgt.getIdFor( new RelatedNarration(3,4) ) );
			assertEquals( 2, sgt.getIdFor( new RelatedNarration(5,6) ) );
			assertEquals( 3, sgt.getIdFor( new RelatedNarration(8,7) ) );

			PreparedStatement ps = c.prepareStatement("SELECT * FROM "+sgt.getTableName()+" ORDER BY group_number,narration_id");
			ResultSet rs = ps.executeQuery();

			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("group_number") );
			assertEquals( 1, rs.getInt("narration_id") );
			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("group_number") );
			assertEquals( 2, rs.getInt("narration_id") );
			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("group_number") );
			assertEquals( 3, rs.getInt("narration_id") );
			assertTrue( rs.next() );
			assertEquals( 1, rs.getInt("group_number") );
			assertEquals( 4, rs.getInt("narration_id") );

			assertTrue( rs.next() );
			assertEquals( 2, rs.getInt("group_number") );
			assertEquals( 5, rs.getInt("narration_id") );
			assertTrue( rs.next() );
			assertEquals( 2, rs.getInt("group_number") );
			assertEquals( 6, rs.getInt("narration_id") );

			assertTrue( rs.next() );
			assertEquals( 3, rs.getInt("group_number") );
			assertEquals( 7, rs.getInt("narration_id") );
			assertTrue( rs.next() );
			assertEquals( 3, rs.getInt("group_number") );
			assertEquals( 8, rs.getInt("narration_id") );

			assertFalse( rs.next() );
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Failed!");
		}
	}
}