package Model.Turns;
import org.junit.Assert.*;				
import org.junit.Test;

/**
 * The class <code>TestTurns</code> contains tests for the class
 * <code> {@link BoardGame}</code>
 * 
 * @author Akhila
 * @version 1.0
 */
 public class TestTurns{
	 //BoardGame game; need to get it from the game module
	
	 Turns t;
	 public TestTurns()
	 {
		  t= new Turns();
	 }
	 
		@Before
		public void testBefore() {
			System.out.println("This is BeforeClass");
			//game = new BoardGame();
		}
		
		@Test
		public void TestGameBeginTrue()
		{
			t.setGameBegin(true);
			assertTrue(t.isGameBegin());
		}
		
		@Test
		public void TestGameBeginFalse()
		{
			t.setGameBegin(false);
			assertFalse(t.isGameBegin());
		}
		
		@Test
		public void TestMapCreatedTrue()
		{
			t.setMapCreated(true);
			assertTrue(t.isMapCreated());
		}
		@Test
		public void TestMapCreatedFalse()
		{
			t.setMapCreated(false);
			assertTrue(t.isMapCreated());
		}
		
		@Test
		public void TestCurrentPIDTrue()
		{
			t.setCurrentPlayerID(int id);
			assertEquals(t.currentPlayerID());
		}
		
		@Test
		public void TestCurrentPlayerIDFalse()
		{
			t.setCurrentPID(int id);
			assertNotEquals(t.currentPlayerID());
		}
		
		@Test
		public void testGetCurrentPhase() {
				TurnPhase p = game.turns.GetCurrentPhase();
				assertEquals(p, TurnPhase.StartGame);
		}
			
		@Test
		public void testGetUpcomingPhase()
		{
			TurnPhase p= game.turns.GetUpcomingPhase();
			assertEquals(p, TurnPhase.Reinforcement);
		}
		@Test
		public void testGetUpcomingPhase()
		{
			TurnPhase p= game.turns.GetUpcomingPhase();
			assertEquals(p, TurnPhase.Fortification);
		}
 }
