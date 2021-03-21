
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class BookStore {

	final static double GST = 10.0;
	final static double DELIVERY_FEE = 5.95;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Generate the sample book list
		ArrayList<Book> bookList = DataStore.getInstance().getAllBooks();

		// Display the book list to choose from
		displayBookList(bookList);

		// Create an Online Order
		Order ord = createOnlineOrder(sc, bookList);

		// Display the Order
		displayFinalOrder(ord);

		sc.close();

	}

	/**
	 * Display all the book's title, unit price from list of available books
	 * 
	 * @param bookList
	 */
	private static void displayBookList(ArrayList<Book> bookList) {
		System.out.printf("Books available %n");
		for (int i = 0; i < bookList.size(); i++) {
			System.out.printf("%s", bookList.get(i).displayBook());
		}
	}

	/**
	 * This function creates an Order after user's selection of books and quantity
	 * 
	 * @param sc
	 * @param bookList
	 * 
	 * @return Order
	 */
	private static Order createOnlineOrder(Scanner sc, ArrayList<Book> bookList) {

		Order order = createOrder();

		boolean shoppingFlag = true;

		while (shoppingFlag) {

			boolean findBook = false;

			do {
				// Read book code from user
				System.out.printf("Which book do you want to buy? ");
				String inputCode = sc.next();

				// Loop through all the books to look for the code
				for (Book b : bookList) {
					if (b.getBookCode().equalsIgnoreCase(inputCode)) {
						findBook = true;
						System.out.printf("You select %s. %nHow many do you like to buy? ", b.getTitle());
						int qty = getPositiveQuantity(sc);

						// Create a new cartItem with book and quantity
						CartItem cartItem = new CartItem(b, qty);
						order.addItemToOrder(cartItem);
					}
				}

				if (!findBook) {
					System.out.printf("%n\"%s\" is not available, please select the right code %n", inputCode);
				}

			} while (!findBook);

			boolean userChoiceFlag = true;

			while (userChoiceFlag) {

				System.out.printf("Would you like to buy another? Y or N ");
				Character choiceChar = sc.next().charAt(0);
				String userChoice = choiceChar.toString();

				if (userChoice.equalsIgnoreCase("Y")) {
					shoppingFlag = true;
					userChoiceFlag = false;
					displayBookList(bookList);
				} else if (userChoice.equalsIgnoreCase("N")) {
					shoppingFlag = false;
					userChoiceFlag = false;
				} else {
					System.out.println("Please select an option: (Y or N)");
					userChoiceFlag = true;
					choiceChar = sc.next().charAt(0);
					userChoice = choiceChar.toString();
				}
			}
		}

		return order;
	}

	/**
	 * Create a new Order by generating an OrderNo
	 * 
	 * @return The new order
	 */
	public static Order createOrder() {

		// inits
		int len = 6;
		String orderCode = "";
		Random rand = new Random();

		// generate 6 digit long number
		for (int c = 0; c < len; c++) {
			orderCode += ((Integer) rand.nextInt(10)).toString();
		}

		// Create a new Order with order code
		Order newOrder = new Order(orderCode);

		return newOrder;
	}

	/**
	 * This function read the Quantity from user input
	 * 
	 * @param sc
	 * @return quantity
	 */
	public static int getPositiveQuantity(Scanner sc) {

		while (true) {
			String input = sc.next();
			try {
				int n = Integer.parseInt(input);
				if (n > 0) {
					return n;
				}
			} catch (NumberFormatException e) {

			}
			System.out.print("Error: input must be a positive number.Try again \n");
		}
	}

	/**
	 * This function displays the books that were ordered. It calculates total price
	 * (with or without Tax). This also provides the discount information and
	 * delivery charge (if any)
	 * 
	 * @param ord
	 */
	private static void displayFinalOrder(Order ord) {

		double subTotal = 0.0;

		System.out.printf("Order No :%s%n", ord.getOrderNo());
		System.out.printf("%-35s %-10s %-20s %10s %15s %n", "Title", "Price", "Discounted Price", "Quantity",
				"Line Total");

		// Displaying individual books from shoppingCart
		for (CartItem item : ord.getShoppingCart()) {

			Book b = item.getBook();
			int quantity = item.getQuantity();
			double discount = b.getDiscount();
			double unitPrice = b.getUnitPrice();
			double finalPrice = unitPrice;

			// Calculate the discounted price for any genre (if any) + Line total
			if (discount > 0.0) {
				finalPrice = finalPrice - ((finalPrice * discount) / 100);
				subTotal += finalPrice * quantity;
				System.out.printf("%-35s %-10.02f %-20.02f %10d %15.02f %n", b.getTitle(), unitPrice, finalPrice,
						quantity, finalPrice * quantity);
			} else {
				subTotal += unitPrice * quantity;
				System.out.printf("%-35s %-10.02f %-20s %10d %15.02f %n", b.getTitle(), unitPrice, " ", quantity,
						unitPrice * quantity);
			}
		}

		// Calculate the Net Price (With Tax) and Total Price
		double netPrice = subTotal + (GST / 100) * subTotal;
		double total = netPrice < 20.0 ? (netPrice + DELIVERY_FEE) : netPrice;

		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.printf("%88s %5.02f %n", "Sub Total (Without GST):", subTotal);
		System.out.printf("%88s %5.02f %n", "Total (including GST):", netPrice);

		if (netPrice < 20) {
			System.out.printf("%88s %5.02f %n", "Delivery Fee:", DELIVERY_FEE);
		}

		System.out.println(
				"----------------------------------------------------------------------------------------------");
		System.out.printf("%88s %5.02f %n", "Total:", total);
	}

}
