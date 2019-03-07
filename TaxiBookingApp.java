import java.util.Scanner;

/**
 * 
 */

/**
 * @author Musketeers
 * 
 */
public class TaxiBookingApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			Taxi[] t = new Taxi[4];
			t[0] = new Taxi();
			t[1] = new Taxi();
			t[2] = new Taxi();
			t[3] = new Taxi();
			Booking[] b = new Booking[10];
			int i = 0, j = 0;
			while (true) {
				System.out
						.println("__________________CALL TAXI BOOKING__________________");
				System.out.println("1)Booking");
				System.out.println("2)Display");
				System.out.println("3)exit");
				System.out.println("Enter your choice");
				int ch = scan.nextInt();

				if (ch > 3 || ch < 1) {
					System.out.println("Invalid Input");
					return;
				}
				if (ch == 1) {
					System.out.println("Input " + (i + 1) + ":");
					System.out.println("Customer Id");
					int id = scan.nextInt();

					System.out.println("Pickup Point:");
					scan.nextLine();
					char pick = scan.nextLine().charAt(0);

					System.out.println("Drop Point:");
					char drop = scan.nextLine().charAt(0);

					System.out.println("Pickup Time:");
					int PickTime = scan.nextInt();

					// setting the entered input values
					b[i] = new Booking(id, pick, drop, PickTime);
					// check if a taxi is available
					int a = b[i].isAvailable(t);
					System.out.println("ouput " + (i + 1));

					if (a != -1) {
						System.out.println("Taxi-" + (a + 1) + " is alloted");

						t[a].calculateEarnings(b[i].pickupPoint, b[i].dropPoint);

						b[i].dropTime();
						b[i].calculateEarnings();
					} else
						System.out.println("Booking is rejected");
					i++;
				} else if (ch == 2) {
					// System.out.format("%-10s%-10s\n","Taxi No:","Total Earnings:");
					// System.out.format("%-10s%-13s%-10s%-10s%-13s%-10s%-10s\n","BookingID","CustomerId","From","To","PickupTime","DropTime","Amount");
					System.out.println("output");
					for (int k = 0; k < 4; k++) {
						if (t[k].earnings != 0) {
							System.out.println("Taxi-" + (k + 1) + "    "
									+ "Total Earnings:" + "Rs . "
									+ t[k].earnings);
							for (j = 0; j < i; j++) {
								if (b[j].taxino == k) {

									// System.out.format("%-10d%-10d%-10c%-10c%-10d%-10d%-10d\n",(j+1),b[j].customerId,
									// b[j].pickupPoint,b[j].dropPoint,b[j].pickupTime,b[j].dropTime,b[j].earnings);
								}

							}
						}
					}
				} else if (ch == 3)
					return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}
}

class Taxi {
	char initialPoint;
	int departureTime;
	int earnings;

	public Taxi() {
		initialPoint = 'A';
	}

	public void departureTime(int pickTime, char pick, char drop) {
		this.departureTime = (pickTime + (Math.abs(Character.toUpperCase(pick)
				- Character.toUpperCase(drop))));
	}

	public void calculateEarnings(char pick, char drop) {
		int dist = (Math.abs(Character.toUpperCase(pick)
				- Character.toUpperCase(drop)) * 15);
		int amount = ((dist - 5) * 10) + 100;
		this.earnings = earnings + amount;
	}

}

class Booking {
	int customerId;
	char pickupPoint;
	char dropPoint;
	int pickupTime;
	int dropTime;
	int earnings;
	int taxino;
	Taxi[] t;

	Booking(int id, char pick, char drop, int PickTime) {
		customerId = id;
		pickupPoint = pick;
		dropPoint = drop;
		pickupTime = PickTime;
	}

	public void dropTime() {
		this.dropTime = (pickupTime + (Math.abs(Character
				.toUpperCase(pickupPoint) - Character.toUpperCase(dropPoint))));
	}

	public void calculateEarnings() {
		int dist = (Math.abs(Character.toUpperCase(pickupPoint)
				- Character.toUpperCase(dropPoint)) * 15);
		this.earnings = ((dist - 5) * 10) + 100;

	}

	public int isAvailable(Taxi[] t) {
		int j, min = 6, temp = -1;
		for (j = 0; j < 4; j++) {
			if (Math.abs(Character.toUpperCase(pickupPoint)
					- Character.toUpperCase(t[j].initialPoint)) <= min
					&& t[j].departureTime <= pickupTime) {
				if (temp == -1
						|| Math.abs(Character.toUpperCase(pickupPoint)
								- Character.toUpperCase(t[j].initialPoint)) < min)
					temp = j;
				if (Math.abs(Character.toUpperCase(pickupPoint)
						- Character.toUpperCase(t[j].initialPoint)) == min
						&& t[j].earnings != 0) {
					if (t[temp].earnings > t[j].earnings)
						temp = j;
				}
				min = Math.abs(Character.toUpperCase(pickupPoint)
						- Character.toUpperCase(t[j].initialPoint));

			}
		}
		if (min != 6) {
			t[temp].departureTime(pickupTime, pickupPoint, dropPoint);
			t[temp].initialPoint = dropPoint;
			taxino = temp;
			return temp;
		}
		return -1;
	}
}


/** Design a Call taxi booking application

-There are n number of taxi’s. For simplicity, assume 4. But it should work for any number of taxi’s.

-The are 6 points(A,B,C,D,E,F)

-All the points are in a straight line, and each point is 15kms away from the adjacent points.

-It takes 60 mins to travel from one point to another

-Each taxi charges Rs.100 minimum for the first 5 kilometers and Rs.10 for the subsequent kilometers.

-For simplicity, time can be entered as absolute time. Eg: 9hrs, 15hrs etc.

-All taxi’s are initially stationed at A.

-When a customer books a Taxi, a free taxi at that point is allocated

-If no free taxi is available at that point, a free taxi at the nearest point is allocated.

-If two taxi’s are free at the same point, one with lower earning is allocated

-Note that the taxi only charges the customer from the pickup point to the drop point. Not the distance it travels from an adjacent point to pickup the customer.

-If no taxi is free at that time, booking is rejected
*/