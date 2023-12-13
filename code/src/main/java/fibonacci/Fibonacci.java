package fibonacci;

import java.math.BigInteger;

/**
 * Нахождение чисел Фибоначчи.
 * <p>Класс предоставляет статический метод, для нахождения чисел Фибоначчи.</p>
 */
public class Fibonacci {

// static methods

	/**
	 * Точка входа в приложение.
	 * <p>Метод, предполагая, что начальным аргументом запуска указан номер желаемого числа Фибоначчи, вычисляет и выводит его в стандартный вывод.</p>
	 * @param args Набор аргументов запуска.
	 * @throws NullPointerException Если набор аргументов запуска не существует.
	 * @throws ArrayIndexOutOfBoundsException Если набор аргументов запуска пуст.
	 * @throws NumberFormatException Если начальный аргумент запуска не является строкой, содержащей запись целого числа.
	 * @throws IllegalArgumentException Если в качестве начального аргумента запуска указанно значение {@link Integer#MIN_VALUE}.
	 */
	public static void main (
		final String... args
	) { // method body
		final int n = Integer.parseInt(args[0]);
		System.out.println(fib(n));
	} // main()

	/**
	 * Число Фибоначчи под указанным номером.
	 * <p>Метод вычисляет и возвращает число Фибоначчи под указанным номером. Номер может быть как положительным, так и отрицательным.</p>
	 * @param n Номер числа в последовательности. Не должен являться {@link Integer#MIN_VALUE}.
	 * @return Выбранное число Фибоначчи.
	 * @throws IllegalArgumentException Если указанный номер равен {@link Integer#MIN_VALUE}.
	 */
	public static BigInteger fib (
		final int n
	) { // method body
		if (n == Integer.MIN_VALUE) throw new IllegalArgumentException();
		BigInteger answer = qfib(Math.abs(n)); // Здесь вычисляем абсолютное значение ответа, обратившись к одному из методов.
		if ((n < 0) && (n % 2 == 0)) {
			answer = answer.negate();
		} // if
		return answer;
	} // fib()

	/**
	 * Рекурсивное нахождение чисел Фибоначчи.
	 * <p>Метод реализует рекурсивный алгоритм нахождения чисел Фибоначчи.</p>
	 * @param n Номер числа в последовательности. Если номер отрицателен, то поведение метода не определено.
	 * @return Значение выбранного числа Фибоначчи.
	 */
	private static BigInteger fibNaive (
		final int n
	) { // method body
		return switch (n) {
			case 0 -> BigInteger.ZERO;
			case 1 -> BigInteger.ONE;
			default -> fibNaive(n-1).add(fibNaive(n-2));
		}; // switch
	} // fibNaive()

	/**
	 * Последовательное нахождение чисел Фибоначчи.
	 * <p>Метод реализует итеративный алгоритм нахождения чисел Фибоначчи.</p>
	 * @param n Номер числа в последовательности. Если номер отрицателен, то поведение метода не определено.
	 * @return Значение выбранного числа Фибоначчи.
	 */
	private static BigInteger fibIter (
		final int n
	) { // method body
		BigInteger prev = BigInteger.ONE;
		BigInteger cur = BigInteger.ZERO;
		for (int i = n; i > 0; i--) {
			final BigInteger next = cur.add(prev);
			prev = cur;
			cur = next;
		} // for
		return cur;
	} // fibIter()

	/**
	 * Быстрое нахождение значения произвольного элемента.
	 * <p>Метод, обращаясь к вспомогательному классу, находит за логарифмическое время и возвращает значение произвольного элемента последовательности Фибоначчи.</p>
	 * @param n Номер элемента в последовательности.
	 * @return Значение указанного элемента последовательности.
	 */
	private static BigInteger qfib (
		final int n
	) { // method body
		return QFib.of(BigInteger.valueOf(n)).value();
	} // qfib()

// constructors

	/**
	 * Конструктор, предотвращающий создание экземпляров класса.
	 * <p>Конструктор объявлен с единственной целью - предотвратить создание экземпляров класса.</p>
	 * @throws NoSuchMethodError При любом обращении к данному конструктору.
	 */
	private Fibonacci (
	) { // method body
		throw new NoSuchMethodError();
	} // Fibonacci()

} // Fibonacci
