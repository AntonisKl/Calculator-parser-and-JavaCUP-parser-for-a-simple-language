public class Main {
	public static String name()  {
		return "John";
	}

	public static String surname() {
		return "Doe";
	}

	public static String fullname(first_name, sep, last_name) {
		return first_name + sep + last_name;
	}

	public static void main(String[] args) {
		name();
		surname();
		fullname(name(), " ", surname());
	}
}