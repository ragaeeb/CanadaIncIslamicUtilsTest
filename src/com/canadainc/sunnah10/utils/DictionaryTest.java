package com.canadainc.sunnah10.utils;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.canadainc.common.io.DBUtils;

public class DictionaryTest
{
	private Dictionary d;

	@Before
	public void setUp() throws Exception
	{
		d = new Dictionary();
	}


	//@Test
	public void apply() throws Exception
	{
		String fileName = UUID.randomUUID().toString()+".db";
		Connection c = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			c.setAutoCommit(false);
			DBUtils.createTable(c, "sunnah_com", DBUtils.createNotNullColumns("id INTEGER", "json TEXT", "path TEXT"));

			PreparedStatement ps = c.prepareStatement("INSERT INTO sunnah_com(json,path) VALUES (?,?)");
			ps.setString(1,"Apostle");
			ps.setString(2,"english/abudawud");
			ps.execute();

			ps.setString(1,"Apostl e");
			ps.setString(2,"english/abudawud");
			ps.execute();

			ps.setString(1,"(saw)");
			ps.setString(2,"english/abudawud");
			ps.execute();

			ps.setString(1,"(SAW)");
			ps.setString(2,"english/abudawud");
			ps.execute();

			c.commit();
			ps.close();

			d.add("Messenger", "Apostle");
			d.addCased("X", "(sAw)");
			d.apply(c, "sunnah_com", "english/abudawud");

			ps = c.prepareStatement("SELECT * FROM sunnah_com ORDER BY id");
			ResultSet rs = ps.executeQuery();

			rs.next();
			assertEquals( "Messenger", rs.getString("json") );

			rs.next();
			assertEquals( "Apostl e", rs.getString("json") );

			rs.next();
			assertEquals( "X", rs.getString("json") );

			rs.next();
			assertEquals( "X", rs.getString("json") );

			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (c != null) {
				c.close();
			}

			DBUtils.cleanUp(fileName);
		}
	}

	@Test
	public void applyOnSource() throws SQLException
	{
		d.add("", "(Hanas)");
		d.add("?", " ?");
		d.add("<p>", "\"p>");
		d.add("about you", "a tout you");
		d.add("'Abdullah bin Shaqeeq", "Shaqiq bin 'Abdullah");
		d.add("A man", "I p man"); // b1018
		d.add("A'isha", "A'Asha", "A'isba");
		d.add("Abu Hurairah", "Abu Hurarirah", "Abu Huraire", "Ab Huraira");
		d.add("Abu Salama bin", "Abu Salamab.");
		d.add("Abu Sufyan", "Abu Safyan", "Abu Sfyan");
		d.add("Abu Talha", "Abu Talba", "Abu Tilha");
		d.add("al-Aziz", "al- Aziz");
		d.add("Allah", "Alalh", "Allh");
		d.add("Anas bin Malik", "Ans bin Malik");
		d.add("be tainted with", "be tained with");
		d.add("Believers", "Beleivers");
		d.add("bless us", "bleesus");
		d.add("burying", "b.rryirg"); // i2089
		d.add("came", "coame"); // n2906, n3044
		d.add("collected hair", "collectedhair");
		d.add("committed", "comitted");
		d.add("Condition", "Coindition");
		d.add("did not go", "did rot go");
		d.add("do not eat", "do not6 eat");
		d.add(" ears ", " eare ");
		d.add("evil eye is genuine", "evil is genuine"); // d3879
		d.add("female dog", "bitch");
		d.add("female donkey", " she-ass");
		d.add("garments", "garmeqts");
		d.add("[He said]:", "[He said:]");
		d.add("he had finished", "he hid finished");
		d.add("Hereafter", "eireafter");
		d.add("house", "YHouse"); // n2917
		d.add("Khutbah", "Kbutba", "Kutba");
		d.add("me, and", "me,and");
		d.add("Messenger", "Apostel", "Apostle", "Mersenger", "Mes- senger");
		d.add("Messenger", "Mesenger");
		d.add("narrated", "narated", "narraterd");
		d.add("narrated from", "narrated-from");
		d.add("No talk to people is lawful", "No talk to people in lawful");
		d.add("O Allah grant pardon even to his hands", "O Allah I grant pardon even to his hands");
		d.add("Of Judgment", "Ofjudgement");
		d.add(" you ", " oyu ");
		d.add("prescribed", "prescribd");
		d.add("pronounced", "pro- nounced");
		d.add("pronounces", "pro- nounces");
		d.add("Prophet", "Propet", "Holy Prophet");
		d.add("raising", "raice");
		d.add("sacrificed", "sacr ficed", "sacripced");
		d.add("Sallam! I", "Sallam!I");
		d.add("said:", "sai:");
		d.add("She said:", "Shesaid:"); // m1479a
		d.add("So I picked", "So P picked");
		d.add("that Umar", "thatUmar");
		d.add("three times", "tliree tinies");
		d.add("throwing pebbles", "throwingpebbles");
		d.add("transmitters", "transrmitters");
		d.add("Umm Salama", "Um Salama");
		d.add("Umm Sulaim", "Um Sulaim", "Um-Sulaim");
		d.add("unborn", "unboam");
		d.add("until", "untol"); // i3673
		d.add("version", "verso"); // d1494
		d.add("whereupon he", "whereupon be");
		d.add("Who is this (man)?", "Wh is this (man) ?");
		d.add("you absolute", "youabsolute"); // t2382
		d.add("you have", "youhave"); // m1479a
		d.add("ﷺ", "(mail peace he upon him)", "(may. peace be upon him)", "(may peace be upon him)", "(masy peace be upon him)");

		d.addCased("ﷺ", "(s.a.w)", "(saw)", "(saws)", "pbuh");
		d.addCased("(may Allah be pleased with him)", "(ra)");
		d.addCased("(may Allah be pleased with her)", "(raa)");

		Connection c = DriverManager.getConnection("jdbc:sqlite:res/sunnah10/collections_source.db");;
		d.apply(c, "sunnah_com", "english");

		d.reset();

		d.add("عَبْدِ اللهِ بْنِ شَقِيقٍ", "شقيق بن عبد الله");
		d.apply(c, "sunnah_com", "arabic/riyadussaliheen");

		c.close();
	}


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName( org.sqlite.JDBC.class.getCanonicalName() ); // load the sqlite-JDBC driver using the current class loader
	}
}