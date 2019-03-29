import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringBalance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Enter a string only of special characters");
			String unTrimmedInput = scanner.nextLine();
			List<Character> characters = new ArrayList<Character>();
			boolean flag = false;
			String input = unTrimmedInput.replaceAll(" ", "");
			if (input != null && !input.isEmpty()) {
				int length = input.length();
				if (length % 2 == 0) {
					for (int i = 0; i < length; i++) {
						if (input.charAt(i) == '(' || input.charAt(i) == ')'
								|| input.charAt(i) == '['
								|| input.charAt(i) == ']'
								|| input.charAt(i) == '{'
								|| input.charAt(i) == '}') {
							if (input.charAt(i) == '('
									|| input.charAt(i) == '{'
									|| input.charAt(i) == '[') {

								characters.add(input.charAt(i));

							} else if (input.charAt(i) == ']') {

								if (characters.get((characters.size() - 1)) == '[') {
									characters.remove((characters.size() - 1));
									flag = true;
								} else {
									System.out.println("Not Balanced.");
									break;
								}

							} else if (input.charAt(i) == '}') {

								if (characters.get((characters.size() - 1)) == '{') {
									characters.remove((characters.size() - 1));
									flag = true;
								} else {
									System.out.println("Not Balanced.");
									break;
								}

							} else if (input.charAt(i) == ')') {

								if (characters.get((characters.size() - 1)) == '(') {
									characters.remove((characters.size() - 1));
									flag = true;
								} else {
									System.out.println("Not Balanced.");
									break;
								}

							}
						} else {
							System.out.println("Invalid String.");
							break;
						}
					}
				} else {
					System.out.println("Not Balanced.");
				}
			} else {
				System.out
						.println("Run program again and enter a valid string.");
			}
			
			if(flag){
				System.out.println("Balanced.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
