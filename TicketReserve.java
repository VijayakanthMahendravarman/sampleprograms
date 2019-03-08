import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Musketeers
 * 
 */
public class TicketReserve {

	/**
	 * @param args
	 */
	private static int counter = 100;
	Map<String, Object> map = new HashMap<String, Object>();
	List<String> nameList = new ArrayList<String>();

	public void reservation() {
		System.out.println("Enter the tickets needed:");
		Scanner scan = new Scanner(System.in);
		boolean flag = false;
		try {
			int tickets = scan.nextInt();
			int ct = 0;
			if (tickets <= counter) {
				while (ct < tickets) {
					System.out.println("Name and age please");
					System.out.println("age:");
					int Age = scan.nextInt();
					if (Age < 18) {
						System.out.println("You're under 18.Booking cancelled");
					} else {
						System.out.println("Name:");
						scan.nextLine();
						String name = scan.next();
						nameList.add(name);
						map.put(name, Age);
						counter--;
						flag = true;
					}
					ct++;
				}
			} else {
				System.out.println(tickets + " tickets  unavailable");
			}
			if (flag) {
				int count = 0;
				System.out.println("Total tickets booked: " + nameList.size());
				while (count < nameList.size()) {
					System.out.println("Name: " + nameList.get(count) + ","
							+ "Age: " + map.get(nameList.get(count)));
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}

	public static void main(String[] args) {
		TicketReserve t1 = new TicketReserve();
		t1.reservation();
	}

}
