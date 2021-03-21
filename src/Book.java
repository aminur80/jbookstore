
import java.util.ArrayList;

public class Book {

	/**
	 * Code to identify a book uniquely
	 */
	private String code;

	/**
	 * Title of the book
	 */
	private String title;

	/**
	 * Book has one or more authors
	 */
	private ArrayList<Author> authors;

	/**
	 * Unit Price of the book
	 */
	private double unitPrice;

	/**
	 * Book's Genre (Crime, Fantasy, Romance etc)
	 */
	private Genre genre;

	public Book(String code, String title, ArrayList<Author> authors, Genre genre, double unitPrice) {
		this.code = code;
		this.title = title;
		this.authors = authors;
		this.genre = genre;
		this.unitPrice = unitPrice;
	}

	/**
	 * Display individual book information
	 */
	public String displayBook() {
		String authors = "";
		for (Author a : this.authors) {
			authors += a.toString();
		}
		return String.format("%-5s %-25s %-30s %-10s %5.02f%n", this.code, this.title, authors, this.genre.toString(),
				this.unitPrice);
	}

	public String getBookCode() {
		return this.code;
	}

	public String getTitle() {
		return this.title;
	}

	public double getDiscount() {
		return this.genre.getDiscount();
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}
}
