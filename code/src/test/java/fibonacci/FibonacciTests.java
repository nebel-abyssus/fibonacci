package fibonacci;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

public class FibonacciTests {

// static fields

	private static final int[] FIB_SEQUENCE = {-377, 233, -144, 89, -55, 34, -21, 13, -8, 5, -3, 2, -1, 1, 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377};
	private static final int FIB_START = -14;

// static methods

	private static Stream<Arguments> fibTestSrc (
	) { // method body
		final Stream.Builder<Arguments> testCases = Stream.<Arguments>builder();
		for (int n = FIB_START, i = 0; i < FIB_SEQUENCE.length; n++, i++) {
			testCases.accept(Arguments.of(n, BigInteger.valueOf(FIB_SEQUENCE[i])));
		} // for
		return testCases.build();
	} // fibTestSrc()

// instance methods

	@Test
	public void mainThrowsTest (
	) { // method body
		Assertions.assertThrows(NullPointerException.class, () -> Fibonacci.main((String[]) null));
		Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, Fibonacci::main);
		Assertions.assertThrows(NumberFormatException.class, () -> Fibonacci.main(new String[] {null}));
		Assertions.assertThrows(NumberFormatException.class, () -> Fibonacci.main("abracadabra"));
		Assertions.assertThrows(IllegalArgumentException.class, () -> Fibonacci.main(Integer.toString(Integer.MIN_VALUE)));
		Assertions.assertDoesNotThrow(() -> Fibonacci.main("0"));
	} // mainThrowsTest()

	@Test
	public void fibThrowsTest (
	) { // method body
		Assertions.assertThrows(IllegalArgumentException.class, () -> Fibonacci.fib(Integer.MIN_VALUE));
		Assertions.assertDoesNotThrow(() -> Fibonacci.fib(0));
	} // fibThrowsTest()

	@ParameterizedTest
	@MethodSource("fibTestSrc")
	public void fibTest (
		final int n,
		final BigInteger expectedValue
	) { // method body
		Assertions.assertEquals(expectedValue, Fibonacci.fib(n));
	} // fibTest()

} // FibonacciTests
