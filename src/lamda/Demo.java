package lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] array = { "a", "b", "c" };
		List list = Arrays.asList(array);
		list.forEach(value -> System.out.println(value));
		Stream<String> ab = list.stream().filter(value -> value.equals("b"));

		List<String> myList = Arrays.asList("a", "aa", "bb", "b", "c");
		Stream<String> stream = Stream.of("d2", "a2", "a1", "b3", "c").filter(s -> s.startsWith("a"));
		System.out.println("first:" + stream.findFirst().orElse(null));

		myList.stream().filter(s -> s.startsWith("b") || s.startsWith("a")).map(String::toUpperCase).sorted()
				.forEach(System.out::println);

		Map<String, String> map = new HashMap<String, String>();
		map.put("10", "C");
		map.put("20", "D");

		Stream<Entry<String, String>> mapstream = map.entrySet().stream();

		mapstream.forEach(value -> System.out.println("value: " + value.getValue()));

		// String[] myArray = { "this", "is", "a", "sentence" };
		// String result = Stream.of(myArray).reduce("tri", (a, b) -> a + " " +
		// b);
		// System.out.println("RESULT:" + result + "," + result.length());

		Integer[] myArray = { 2, 3, 4 };
		Integer result = Stream.of(myArray).reduce(2, (a, b) -> a * b);
		System.out.println("RESULT:" + result);

	}

	public static void main2(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("php");
		list.add("python");
		// list.add("perl");
		// list.add("c");
		// list.add("lisp");
		// list.add("c#");
		Stream<String> wordStream = list.stream();

		Stream<Integer> lengthStream = wordStream.map(s -> s.length());
		// System.out.println(lengthStream);

		Optional<Integer> sum = lengthStream.reduce((x, y) -> x + y);

		sum.ifPresent(System.out::println);
	}

	public static void main3(String[] args) {

		String[][] data = new String[][] { { "1", "2" }, { "3", "4" }, { "5", "6" } };

		// Stream<String[]>
		Stream<String[]> temp = Arrays.stream(data);
		// Stream<String>, GOOD!
		Stream<String> stringStream = temp.flatMap((x) -> Arrays.stream(x));
		stringStream.forEach(System.out::println);

		// filter a stream of string[], and return a string[]?
		Stream<String> stream = stringStream.filter(x -> "a".equals(x.toString()));

		stream.forEach(System.out::println);

	}

	public static void main1(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("100");
		list.add("200");
		List<List<String>> list1 = new ArrayList();
		list1.add(list);
		List<List<List<String>>> list2 = new ArrayList();
		list2.add(list1);

		Stream<List<List<String>>> temp = list2.stream();

		Stream<String> stream = temp.flatMap(x -> x.stream().flatMap(x1 -> x1.stream()));
		stream.forEach(System.out::println);
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(3);
		list3.add(7);
		list3.add(6);
		System.out.println(findSquareOfMaxOdd(list3));

	}

	public static int findSquareOfMaxOdd(List<Integer> numbers) {
		return numbers.stream().filter(Demo::isOdd).filter(v -> Demo.isGreaterThan3(v)).filter(Demo::isLessThan11)
				.max(Comparator.naturalOrder()).map(i -> i * i).get();
	}

	public static boolean isOdd(int i) {
		return i % 2 != 0;
	}

	public static boolean isOdd(long i) {
		return i % 2 != 0;
	}

	public static boolean isGreaterThan3(int i) {
		return i > 3;
	}

	public static boolean isLessThan11(int i) {
		return i < 11;
	}

	@Test
	public void orElse_whenNamePresent_ThenName() {
		Optional<String> petName = Optional.of("Bobby");

		Assert.assertEquals("Bobby", petName.orElse(""));
	}

	@Test
	public void orElse_whenNameNotPresent_ThenEmptyString() {
		Optional<String> petName = Optional.empty();

		Assert.assertEquals("", petName.orElse(""));
	}

}
