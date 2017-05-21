package com.canadainc.sunnah10.processors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.canadainc.sunnah10.Narration;

public class SunnahTestUtils
{
	private static Connection c;

	public static void assertNarration(Narration n, int id, String grade, String... bodies)
	{
		assertEquals(id, n.id);
		assertEquals(grade, n.grading);

		for (String body: bodies) {
			assertTrue( n.text.contains(body) );
		}
	}


	public static void assertCommentary(Narration n, int id, String grade, String[] commentaries, String... bodies)
	{
		assertEquals(id, n.id);
		assertEquals(grade, n.grading);

		for (String commentary: commentaries) {
			assertTrue( n.commentary.contains(commentary) );
		}

		for (String body: bodies) {
			assertTrue( n.text.contains(body) );
		}
	}


	public static void assertInBookNumbers(Processor s, int ...values)
	{
		for (int i = 0; i < s.getNarrations().size(); i++) {
			assertEquals( values[i], s.getNarrations().get(i).inBookNumber );
		}
	}

	/*
	private String loadFromFileSystem(String file) throws IOException {
		return IOUtils.readFileUtf8( new File(file) );
	} */


	public static String loadFromDatabase(String file) throws Exception
	{
		Class.forName( org.sqlite.JDBC.class.getCanonicalName() ); // load the sqlite-JDBC driver using the current class loader

		String[] tokens = file.split("/");

		// sunnah_com/arabic/abudawud/1.txt
		// abudawud/1.txt
		String table = tokens[0];
		String fileName = tokens[tokens.length-1];
		String path = tokens.length > 2 ? file.substring( file.indexOf("/")+1, file.lastIndexOf("/") ) : "";

		if (c == null) {
			c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/collections_source.db");
		}

		String statement = "SELECT * FROM "+table+" WHERE file_name=?";
		
		if (tokens.length > 2) {
			statement += " AND path=?";
		}
		
		PreparedStatement ps = c.prepareStatement(statement);
		ps.setString(1, fileName);

		if (tokens.length > 2) {
			ps.setString(2, path);
		}

		ResultSet rs = ps.executeQuery();
		rs.next();
		String result = rs.getString("json");
		rs.close();
		ps.close();

		return result;
	}


	public static void loadAndAssertSize(String file, Processor s, int size) throws Exception
	{
		//Object obj = JSONValue.parse( IOUtils.readFileUtf8( new File(file) ) );
		Object obj = JSONValue.parse( loadFromDatabase(file) );
		JSONArray arr = new JSONArray();

		if (obj instanceof JSONArray) {
			arr = (JSONArray)obj;
		} else {
			arr.add(obj);
		}

		for (Object o: arr)
		{
			JSONObject json = (JSONObject)o;
			s.preprocess(json);
			s.process(json);
		}

		assertEquals( size, s.getNarrations().size() );
	}
}