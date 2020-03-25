
//package App_Risk_Game.src.test.java.model.Units;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.LinkedList;
//import static org.junit.Assert.assertEquals;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Properties;
//import java.util.logging.FileHandler;
//
//import App_Risk_Game.src.main.java.Model.Units.Unit;
//import App_Risk_Game.src.main.java.Model.Units.UnitType;
//import org.junit.Test;
//
// /**
// *  Testcase for Units module, contains the initializeCheck() method.
// *  @throws SecurityException, IOException
// */
//public class UnitTest {
//
//	public static LinkedList<Unit> player_list = new LinkedList<Unit>();
//
//	/**
//	 * asserts the initialize function of Unit module.
//	 * @throws SecurityException
//	 * @throws IOException
//	 */
//
//	@Test
//	public void initializeCheck() throws SecurityException, IOException {
//
//		Properties prop = new Properties();
//		FileInputStream ip = new FileInputStream(
//				"C:\\Users\\Tanvi\\git\\RiskGame\\App_Risk_Game\\src\\main\\java\\Model\\Game\\properties.properties");
//		prop.load(ip);
//		HashMap<String, String> list = new HashMap<String, String>();
//		list.put("Player1", "blue");
//		list.put("Player2", "red");
//		list.put("Player3", "white");
//
//		LinkedList<Unit> player_list = new LinkedList<Unit>();
//		LinkedList<Unit> units = Unit.initialize(list, prop);
//
//		Iterator it = list.keySet().iterator();
//		while (it.hasNext()) {
//
//			String player = (String) it.next();
//			List<UnitType> types_of_Units = new LinkedList();
//			UnitType ut = new UnitType("infantry", 10,3);
//			types_of_Units.add(ut);
//			Unit UnitObj = new Unit(list.get(player).toUpperCase(), 3,
//					types_of_Units);
//			player_list.add(UnitObj);
//
//		}
//		System.out.println(player_list.toString());
//		System.out.println(units.toString());
//
//		assertEquals(player_list, units);
//
//	}
//
//}
