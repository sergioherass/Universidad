package aed.tries;

public class PruebaLab5 {

	public static void main(String[] args) {
		Dictionary d = new DictImpl();
		d.add("a");
		d.add("alici");
		d.delete("alici");
		d.add("al");
		System.out.println(d.isIncluded("a"));

	}

}
