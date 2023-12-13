package fibonacci;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

public class QFibTests {

// static fields

	private static final long[] FIB_NUMBERS = {0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L, 10946L, 17711L, 28657L, 46368L, 75025L, 121393L, 196418L, 317811L, 514229L, 832040L, 1346269L, 2178309L, 3524578L, 5702887L, 9227465L, 14930352L, 24157817L, 39088169L, 63245986L, 102334155L, 165580141L, 267914296L, 433494437L, 701408733L, 1134903170L, 1836311903L, 2971215073L, 4807526976L, 7778742049L, 12586269025L, 20365011074L, 32951280099L, 53316291173L, 86267571272L, 139583862445L, 225851433717L, 365435296162L, 591286729879L, 956722026041L, 1548008755920L, 2504730781961L, 4052739537881L, 6557470319842L, 10610209857723L, 17167680177565L, 27777890035288L, 44945570212853L, 72723460248141L, 117669030460994L, 190392490709135L, 308061521170129L, 498454011879264L, 806515533049393L, 1304969544928657L, 2111485077978050L, 3416454622906707L, 5527939700884757L, 8944394323791464L, 14472334024676221L, 23416728348467685L, 37889062373143906L, 61305790721611591L, 99194853094755497L, 160500643816367088L, 259695496911122585L, 420196140727489673L, 679891637638612258L, 1100087778366101931L, 1779979416004714189L, 2880067194370816120L, 4660046610375530309L, 7540113804746346429L};

	private static final int MAX_N = FIB_NUMBERS.length - 1;
	private static final int MIN_N = -MAX_N;

// static methods

	public static long stdFibNumber (
		final int n
	) throws IllegalArgumentException
	{ // method body
		if ((n < MIN_N) || (n > MAX_N)) throw new IllegalArgumentException();
		long answer = FIB_NUMBERS[Math.abs(n)];
		if ((n < 0) && ((n & 1) == 0)) {
			answer = -answer;
		} // if
		return answer;
	} // stdFibNumber()

// instance methods

	@Test
	public void ofThrowsTest (
	) { // method body
		final RandomGenerator rng = ThreadLocalRandom.current();
		final int n = rng.nextInt(MIN_N, MAX_N + 1);
		Assertions.assertThrows(NullPointerException.class, () -> QFib.of(null));
		Assertions.assertDoesNotThrow(() -> QFib.of(BigInteger.valueOf(n)));
	} // ofThrowsTest()

	@Test
	public void ofTest (
	) { // method body
		final RandomGenerator rng = ThreadLocalRandom.current();
		final int testCount = rng.nextInt(2, FIB_NUMBERS.length);
		for (int i = testCount; i > 0; i--) {
			final int n = rng.nextInt(MIN_N, MAX_N + 1);
			final BigInteger wrappedN = BigInteger.valueOf(n);
			final BigInteger expectedValue = BigInteger.valueOf(stdFibNumber(n));
			final QFib actual = QFib.of(wrappedN);
			Assertions.assertEquals(wrappedN, actual.n());
			Assertions.assertEquals(expectedValue, actual.value());
		} // for
	} // ofTest()

	@Test
	public void valueTest (
	) { // method body
		final RandomGenerator rng = ThreadLocalRandom.current();
		final int n = rng.nextInt(MIN_N, MAX_N + 1);
		final BigInteger expectedValue = BigInteger.valueOf(stdFibNumber(n));
		final QFib actual = QFib.of(BigInteger.valueOf(n));
		Assertions.assertEquals(expectedValue, actual.value());
		Assertions.assertEquals(expectedValue, actual.value());
	} // valueTest()

	@Test
	public void nTest (
	) { // method body
		final RandomGenerator rng = ThreadLocalRandom.current();
		final int n = rng.nextInt(MIN_N, MAX_N + 1);
		final BigInteger wrappedN = BigInteger.valueOf(n);
		final QFib fibElement = QFib.of(wrappedN);
		Assertions.assertEquals(wrappedN, fibElement.n());
		Assertions.assertEquals(wrappedN, fibElement.n());
	} // nTest()

	@Test
	public void equalsTest (
	) { // method body
		final RandomGenerator rng = ThreadLocalRandom.current();
		final BigInteger n = BigInteger.valueOf(rng.nextInt(MIN_N, MAX_N + 1));
		final QFib fib = QFib.of(n);
		final QFib equalFib = QFib.of(n);
		final QFib anotherEqualFib = QFib.of(n);
		final QFib nonEqualFib = fib.next();
		final Object anyObject = new Object();
		// ---------------------------------------------------------
		Assertions.assertTrue(fib.equals(fib));
		// ---------------------------------------------------------
		Assertions.assertTrue(fib.equals(equalFib));
		Assertions.assertTrue(equalFib.equals(fib));
		Assertions.assertFalse(fib.equals(nonEqualFib));
		Assertions.assertFalse(nonEqualFib.equals(fib));
		Assertions.assertFalse(fib.equals(anyObject));
		Assertions.assertFalse(anyObject.equals(fib));
		// ---------------------------------------------------------
		Assertions.assertTrue(fib.equals(equalFib));
		Assertions.assertTrue(equalFib.equals(anotherEqualFib));
		Assertions.assertTrue(fib.equals(anotherEqualFib));
		// ---------------------------------------------------------
		Assertions.assertTrue(fib.equals(fib));
		Assertions.assertTrue(fib.equals(fib));
		Assertions.assertTrue(fib.equals(equalFib));
		Assertions.assertTrue(fib.equals(equalFib));
		Assertions.assertTrue(fib.equals(anotherEqualFib));
		Assertions.assertTrue(fib.equals(anotherEqualFib));
		Assertions.assertFalse(fib.equals(nonEqualFib));
		Assertions.assertFalse(fib.equals(nonEqualFib));
		Assertions.assertFalse(fib.equals(anyObject));
		Assertions.assertFalse(fib.equals(anyObject));
		Assertions.assertFalse(fib.equals(null));
		Assertions.assertFalse(fib.equals(null));
		// ---------------------------------------------------------
		Assertions.assertFalse(fib.equals(null));
	} // equalsTest()

	@Test
	public void hashCodeTest (
	) { // method body
		final RandomGenerator rng = ThreadLocalRandom.current();
		final BigInteger n = BigInteger.valueOf(rng.nextInt(MIN_N, MAX_N + 1));
		final QFib fib = QFib.of(n);
		final QFib equalFib = QFib.of(n);
		final int fibHash = fib.hashCode();
		// -----------------------------------------------------------------
		for (int i = rng.nextInt(32) + 1; i > 0; i--) {
			Assertions.assertEquals(fibHash, fib.hashCode());
		} // for
		// -----------------------------------------------------------------
		Assertions.assertEquals(fibHash, equalFib.hashCode());
		// -----------------------------------------------------------------
		final int maxCount = 32;
		int i = 0;
		QFib anotherFib = fib.next();
		while ((i < maxCount) && (fibHash == anotherFib.hashCode())) {
			anotherFib = anotherFib.next();
			i++;
		} // while
		Assertions.assertTrue(i < maxCount);
	} // hashCodeTest()

	@Test
	public void previousTest (
	) { // method body
		final RandomGenerator rng = ThreadLocalRandom.current();
		final int start = rng.nextInt(0, MAX_N + 1);
		final int end = rng.nextInt(MIN_N, 0);
		QFib fib = QFib.of(BigInteger.valueOf(start));
		for (int i = start; i >= end; i--) {
			final BigInteger expectedValue = BigInteger.valueOf(stdFibNumber(i));
			final BigInteger expectedN = BigInteger.valueOf(i);
			Assertions.assertEquals(expectedValue, fib.value());
			Assertions.assertEquals(expectedN, fib.n());
			fib = fib.previous();
		} // for
	} // previousTest()

	@Test
	public void nextTest (
	) { // method body
		final RandomGenerator rng = ThreadLocalRandom.current();
		final int start = rng.nextInt(MIN_N, 0);
		final int end = rng.nextInt(0, MAX_N + 1);
		QFib fib = QFib.of(BigInteger.valueOf(start));
		for (int i = start; i <= end; i++) {
			final BigInteger expectedValue = BigInteger.valueOf(stdFibNumber(i));
			final BigInteger expectedN = BigInteger.valueOf(i);
			Assertions.assertEquals(expectedValue, fib.value());
			Assertions.assertEquals(expectedN, fib.n());
			fib = fib.next();
		} // for
	} // nextTest()

	@Test
	public void addNThrowsTest (
	) { // method body
		final RandomGenerator rng = RandomGenerator.getDefault();
		final BigInteger n = BigInteger.valueOf(rng.nextInt(MIN_N, MAX_N + 1));
		final BigInteger m = BigInteger.valueOf(rng.nextInt(MIN_N, MAX_N + 1));
		final QFib fib = QFib.of(n);
		final QFib validTerm = QFib.of(m);
		Assertions.assertThrows(NullPointerException.class, () -> fib.addN(null));
		Assertions.assertDoesNotThrow(() -> fib.addN(validTerm));
	} // addNThrowsTest()

	@Test
	public void addNTest (
	) { // method body
		final RandomGenerator rng = RandomGenerator.getDefault();
		final int testCount = 128;
		for (int i = testCount; i > 0; i--) {
			final int target = rng.nextInt(MIN_N, MAX_N + 1);
			final int lowLimit = Math.max(target - MAX_N, MIN_N);
			final int highLimit = Math.min(target - MIN_N, MAX_N);
			final int a = rng.nextInt(lowLimit, highLimit + 1);
			final int b = target - a;
			final QFib fibA = QFib.of(BigInteger.valueOf(a));
			final QFib fibB = QFib.of(BigInteger.valueOf(b));
			final QFib sumFib = fibA.addN(fibB);
			Assertions.assertEquals(BigInteger.valueOf(target), sumFib.n());
			Assertions.assertEquals(BigInteger.valueOf(stdFibNumber(target)), sumFib.value());
		} // for
	} // addNTest()

	@Test
	public void doubleNTest (
	) { // method body
		final int testCount = 128;
		final RandomGenerator rng = ThreadLocalRandom.current();
		final int lowInclusiveLimit = MIN_N / 2;
		final int highExclusiveLimit = (MAX_N / 2) + 1;
		for (int i = testCount; i > 0; i--) {
			final int n = rng.nextInt(lowInclusiveLimit, highExclusiveLimit);
			final QFib target = QFib.of(BigInteger.valueOf(n)).doubleN();
			Assertions.assertEquals(BigInteger.valueOf(n * 2), target.n());
			Assertions.assertEquals(BigInteger.valueOf(stdFibNumber(n * 2)), target.value());
		} // for
	} // doubleNTest()

	@Test
	public void negateNTest (
	) { // method body
		final int testCount = 128;
		final RandomGenerator rng = ThreadLocalRandom.current();
		for (int i = testCount; i > 0; i--) {
			final int n = rng.nextInt(MIN_N, MAX_N + 1);
			final QFib neg = QFib.of(BigInteger.valueOf(n)).negateN();
			Assertions.assertEquals(BigInteger.valueOf(stdFibNumber(-n)), neg.value());
			Assertions.assertEquals(BigInteger.valueOf(-n), neg.n());
		} // for
	} // negateNTest()

} // QFibTests
