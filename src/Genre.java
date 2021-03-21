
public class Genre {

	private String name;

	private double discount;

	public Genre(String name) {
		this.name = name;
	}

	public Genre(String name, double discount) {
		this(name);
		this.discount = discount;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public double getDiscount() {
		return this.discount;
	}

}
