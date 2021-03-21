
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataStore class that provides access to book list 
 * Pretend this class accesses a database
 *
 */

public class DataStore {

	// List of sample books
	private ArrayList<Book> bookCollection = new ArrayList<>();

	// this class is a singleton and should not be instantiated directly
	private static DataStore instance = new DataStore();

	public static DataStore getInstance() {
		return instance;
	}

	// private constructor so that getInstace() function has to be used
	private DataStore() {
		// dummy data
		bookCollection.add(new Book("001", "Unsolved Murders",
				addAuthors(Arrays.asList("Emily G. Thompson", "Amber Hunt")), new Genre("Crime", 5), 10.99));
		bookCollection.add(new Book("002", "Alice in Wonderland", addAuthors(Arrays.asList("Lewis Carroll")),
				new Genre("Fantasy"), 5.99));

		bookCollection.add(new Book("003", "A Little Love Story", addAuthors(Arrays.asList("Roland Merullo")),
				new Genre("Romance"), 2.40));

		bookCollection
		.add(new Book("004", "Heresy", addAuthors(Arrays.asList("S J Parris")), new Genre("Fantasy"), 6.80));

		bookCollection.add(new Book("005", "The Neverending Story", addAuthors(Arrays.asList("Michael Ende")),
				new Genre("Fantasy"), 7.99));

		bookCollection.add(new Book("006", "Jack the Ripper", addAuthors(Arrays.asList("Philip Sugden")),
				new Genre("Crime", 5), 16.00));

		bookCollection.add(new Book("007", "The Tolkien Years", addAuthors(Arrays.asList("Greg Hildebrandt")),
				new Genre("Fantasy"), 22.90));
	}

	/**
	 * Get all sample books
	 * 
	 * @return bookCollection
	 */
	public ArrayList<Book> getAllBooks() {
		return this.bookCollection;
	}

	/**
	 * Create a list of authors from list of author names
	 * 
	 * @param authors
	 * @return authorsList
	 */
	private ArrayList<Author> addAuthors(List<String> authors) {
		ArrayList<Author> authorsList = new ArrayList<>();
		for (String name : authors) {
			authorsList.add(new Author(name));
		}
		return authorsList;
	}
}
