package main.java.Model.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class PlayerTest {

	@Test
	void validPlayerName() {
		Player playobj = new Player(); 
		playobj.setName("John");
		assertEquals("John",playobj.getName());
	}

}
