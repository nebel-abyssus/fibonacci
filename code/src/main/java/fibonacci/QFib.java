package fibonacci;

import java.math.BigInteger;

/**
 * Быстрое вычисление элементов последовательности Фибоначчи.
 * <p>Экземпляры класса являются immutable-объектами представляющими элементы последовательности Фибоначчи. Каждый из таких объектов предоставляет методы, необходимые для реализации быстрого вычисления других элементов последовательности.</p>
 * <p>Кроме того, класс предоставляет статический фабричный метод, реализующий алгоритм быстрого вычисления элементов последовательности.</p>
 */
public class QFib {

// static fields

	/**
	 * Нулевой элемент последовательности.
	 */
	public static final QFib ZERO = new QFib(BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO);

	/**
	 * Минус первый элемент последовательности.
	 */
	public static final QFib MINUS_FIRST = ZERO.previous();

	/**
	 * Первый элемент последовательности.
	 */
	public static final QFib FIRST = ZERO.next();

// instance fields

	/**
	 * Значение данного элемента последовательности.
	 */
	private final BigInteger cur;

	/**
	 * Значение предшествующего элемента последовательности.
	 */
	private final BigInteger prev;

	/**
	 * Номер данного элемента последовательности.
	 */
	private final BigInteger n;

// static methods

	/**
	 * Получение элемента последовательности с заданным номером.
	 * <p>Метод, реализуя алгоритм быстрого вычисления элементов последовательности Фибоначчи, находит и возвращает элемент с заданным номером.</p>
	 * @param n Номер элемента в последовательности.
	 * @return Элемент последовательности с заданным номером.
	 * @throws NullPointerException Если указанный номер элемента не существует.
	 */
	public static QFib of (
		final BigInteger n
	) throws NullPointerException
	{ // method body
		QFib accum = ZERO;
		final BigInteger absN = n.abs();
		for (int i = absN.bitLength() - 1; i >= 0; i--) {
			accum = accum.doubleN();
			if (absN.testBit(i)) {
				accum = accum.next();
			} // if
		} // for
		if (n.signum() < 0) {
			accum = accum.negateN();
		} // if
		return accum;
	} // of()

// constructors

	/**
	 * Конструктор элемента последовательности.
	 * @param cur Значение данного элемента последовательности.
	 * @param prev Значение предшествующего элемента последовательности.
	 * @param n Номер данного элемента последовательности.
	 * @throws AssertionError Если разрешены операторы контроля, и любой из аргументов не существует.
	 */
	private QFib (
		final BigInteger cur,
		final BigInteger prev,
		final BigInteger n
	) throws AssertionError
	{ // method body
		assert cur != null;
		assert prev != null;
		assert n != null;
		this.cur = cur;
		this.prev = prev;
		this.n = n;
	} // QFib()

// instance methods

	/**
	 * Значение элемента.
	 * <p>Метод возвращает значение данного элемента последовательности.</p>
	 * @return Значение данного элемента.
	 */
	public BigInteger value (
	) { // method body
		return cur;
	} // value()

	/**
	 * Номер элемента.
	 * <p>Метод возвращает номер данного элемента последовательности.</p>
	 * @return Номер данного элемента в последовательности.
	 */
	public BigInteger n (
	) { // method body
		return n;
	} // n()

	/**
	 * Проверка эквивалентности.
	 * <p>Метод проверяет эквивалентность данного элемента последовательности указанному объекту. Эквивалентными считаются совпадающие элементы последовательности.</p>
	 * @param obj Проверяемый объект.
	 * @return Флаг эквивалентности объектов.
	 */
	@Override
	public boolean equals (
		final Object obj
	) { // method body
		// Так как номер элемента последовательности Фибоначчи однозначно определяет положение элемента в последовательности и его значение, то, очевидно, два элемента эквивалентны, если их номера совпадают.
		return (obj == this) || ((obj instanceof QFib anotherFib) && (this.n.equals(anotherFib.n)));
	} // equals()

	/**
	 * Хеш-код элемента последовательности.
	 * @return Хеш-код элемента последовательности.
	 */
	@Override
	public int hashCode (
	) { // method body
		return n.hashCode();
	} // hashCode()

	/**
	 * Предшествующий элемент последовательности.
	 * @return Предшествующий элемент последовательности.
	 */
	public QFib previous (
	) { // method body
		final BigInteger previousCur = prev;
		final BigInteger previousPrev = cur.subtract(prev);
		final BigInteger previousN = n.subtract(BigInteger.ONE);
		return new QFib(previousCur, previousPrev, previousN);
	} // previous()

	/**
	 * Следующий элемент последовательности.
	 * @return Следующий элемент последовательности.
	 */
	public QFib next (
	) { // method body
		final BigInteger nextCur = cur.add(prev);
		final BigInteger nextPrev = cur;
		final BigInteger nextN = n.add(BigInteger.ONE);
		return new QFib(nextCur, nextPrev, nextN);
	} // next()

	/**
	 * Сложение номеров.
	 * <p>Метод возвращает элемент последовательности номер которого равен сумме номеров данного и указанного в качестве аргумента.</p>
	 * @param term Элемент с добавляемым номером.
	 * @return Элемент с номером равным сумме номеров.
	 * @throws NullPointerException Если элемент с добавляемым номером не существует.
	 */
	public QFib addN (
		final QFib term
	) throws NullPointerException
	{ // method body
		final BigInteger targetCur = this.cur.parallelMultiply(term.prev).add(this.cur.add(this.prev).parallelMultiply(term.cur));
		final BigInteger targetPrev = this.cur.parallelMultiply(term.cur).add(this.prev.parallelMultiply(term.prev));
		final BigInteger targetN = this.n.add(term.n);
		return new QFib(targetCur, targetPrev, targetN);
	} // addN()

	/**
	 * Удвоение номера элемента.
	 * <p>Метод возвращает элемент последовательности номер которого равен удвоенному номеру данного.</p>
	 * @return Элемент с номером равным удвоенному номеру данного.
	 */
	public QFib doubleN (
	) { // method body
		final BigInteger doubleCur = cur.parallelMultiply(prev.add(prev).add(cur));
		final BigInteger doublePrev = cur.parallelMultiply(cur).add(prev.parallelMultiply(prev));
		final BigInteger doubleN = n.add(n);
		return new QFib(doubleCur, doublePrev, doubleN);
	} // doubleN()

	/**
	 * Смена знака номера.
	 * <p>Метод возвращает элемент последовательности с номером равным по абсолютному значению, но противоположным по знаку номеру данного элемента.</p>
	 * @return Элемент последовательности с номером противоположным по знаку.
	 */
	public QFib negateN (
	) { // method body
		BigInteger negCur = cur;
		BigInteger negPrev = cur.add(prev);
		if (n.testBit(0)) {
			negPrev = negPrev.negate();
		} else {
			negCur = negCur.negate();
		} // if
		final BigInteger negN = n.negate();
		return new QFib(negCur, negPrev, negN);
	} // negateN()

} // QFib
