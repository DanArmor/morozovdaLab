package tech.reliab.course.morozovda.bank.utils;

// Генератор рандомных BigDecimal
// Взято из https://stackoverflow.com/a/71743540

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

public class BigRandom {

    private static Random defaultRandom = new Random();

    // Constants:
    private static double log2 = Math.log10(2.0);

    // Computes number of bits needed to represent an n digit positive integer.

    private static int bitCount(int n) {
        return (int) (n / log2);
    }

    // Static Methods for generating Random BigInteger values:

    public static BigInteger nextBigInteger(int precision) {
        return nextBigInteger(precision, defaultRandom);
    }

    public static BigInteger nextBigInteger(int precision, Random r) {
        return new BigInteger(bitCount(precision), r);
    }

    public static BigInteger nextBigInteger(BigInteger norm) {
        return nextBigInteger(norm, defaultRandom);
    }

    public static BigInteger nextBigInteger(BigInteger norm, Random r) {
        BigDecimal bdNorm = new BigDecimal(norm);
        int precision = bdNorm.precision() - bdNorm.scale();
        return bdNorm.multiply(nextBigDecimal(precision, r), new MathContext(precision + 1)).toBigInteger();
    }

    public static BigInteger between(BigInteger min, BigInteger MAX) {
        return between(min, MAX, defaultRandom);
    }

    public static BigInteger between(BigInteger min, BigInteger MAX, Random r) {
        return min.add(nextBigInteger(MAX.subtract(min), r));
    }

    // Static Methods for generating Random BigDecimal values:

    public static BigDecimal nextBigDecimal(int scale) {
        return nextBigDecimal(scale, defaultRandom);
    }

    public static BigDecimal nextBigDecimal(int scale, Random r) {
        BigInteger bi = nextBigInteger(scale, r); // generate random BigInteger with a number of digits equal to scale.
        BigDecimal bd = new BigDecimal(bi); // convert BigInteger to a BigDecimal
        return bd.movePointLeft(bd.precision()); // move the decimal point all the way to the left
    }

    public static BigDecimal nextBigDecimal(BigDecimal norm, int scale) {
        return nextBigDecimal(norm, scale, defaultRandom);
    }

    public static BigDecimal nextBigDecimal(BigDecimal norm, int scale, Random r) {
        return norm.multiply(nextBigDecimal(scale, r), new MathContext((norm.precision() - norm.scale()) + scale));
    }

    public static BigDecimal between(BigDecimal min, BigDecimal MAX) {
        return between(min, MAX, defaultRandom);
    }

    public static BigDecimal between(BigDecimal min, BigDecimal MAX, Random r) {
        return min.add(
                nextBigDecimal(
                        MAX.subtract(min),
                        Math.max(min.precision(), MAX.precision()),
                        r));
    }
}