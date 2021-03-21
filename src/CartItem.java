
public class CartItem {

	private Book bookPurchased;

	private int quantity;

	public CartItem(Book b, int qty) {
		this.bookPurchased = b;
		this.quantity = qty;
	}

	public Book getBook() {
		return this.bookPurchased;
	}

	public int getQuantity() {
		return this.quantity;
	}

}
