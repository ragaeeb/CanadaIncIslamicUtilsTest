package com.canadainc.sunnah10;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

public class GradeCollectorTest
{
	@Test
	public void testProcess()
	{
		GradeCollector gc = new GradeCollector();

		ArrayList<Narration> narrations = new ArrayList<Narration>();
		Narration n = new Narration(1, "Chapter", 1, 1, "Intro", "1", 1, "Body");
		n.grading = "Sahih";
		narrations.add(n);

		gc.process(narrations, "english", "xyz");
		assertTrue( gc.getCollected().isEmpty() );
		gc.process(narrations, "english", "adab");

		n = new Narration(2, "Chapter", 1, 1, "Intro", "1", 1, "Body");
		n.grading = "Daif";
		narrations.clear();
		narrations.add(n);
		gc.process(narrations, "english", "adab");

		Map<Integer, Grade> result = gc.getCollected();
		assertEquals( new Grade(11,"Sahih"), result.get(1) );
		assertEquals( new Grade(11,"Daif"), result.get(2) );
	}
}