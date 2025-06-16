package com.diego;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MeanStdDevAppTest {

  @Test
  void testAddAndSize() {
    MeanStdDevApp.FunctionalLinkedList<Integer> list = new MeanStdDevApp.FunctionalLinkedList<>();
    list.add(10);
    list.add(20);
    list.add(30);
    assertEquals(3, list.size());
  }

  @Test
  void testMap() {
    MeanStdDevApp.FunctionalLinkedList<Integer> list = new MeanStdDevApp.FunctionalLinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);

    MeanStdDevApp.FunctionalLinkedList<Integer> mapped = list.map(x -> x * 2);
    assertEquals(3, mapped.size());

    StringBuilder result = new StringBuilder();
    mapped.forEach(x -> result.append(x).append(" "));
    assertEquals("2 4 6 ", result.toString());
  }

  @Test
  void testFilter() {
    MeanStdDevApp.FunctionalLinkedList<Integer> list = new MeanStdDevApp.FunctionalLinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);

    MeanStdDevApp.FunctionalLinkedList<Integer> even = list.filter(x ->
      x % 2 == 0
    );
    assertEquals(2, even.size());
  }

  @Test
  void testReduceSum() {
    MeanStdDevApp.FunctionalLinkedList<Double> list = new MeanStdDevApp.FunctionalLinkedList<>();
    list.add(1.0);
    list.add(2.0);
    list.add(3.0);
    assertEquals(6.0, list.reduce(0.0, Double::sum));
  }

  @Test
  void testMeanAndStdDevExampleData() {
    MeanStdDevApp.FunctionalLinkedList<Double> data = new MeanStdDevApp.FunctionalLinkedList<>();
    double[] values = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
    for (double v : values) data.add(v);

    double mean = data.reduce(0.0, Double::sum) / data.size();
    double variance =
      data.map(x -> Math.pow(x - mean, 2)).reduce(0.0, Double::sum) /
      (data.size() - 1);
    double stdDev = Math.sqrt(variance);

    assertEquals(55.0, mean, 0.001);
    assertEquals(30.2765, stdDev, 0.001);
  }

  @Test
  void testEmptyListReduce() {
    MeanStdDevApp.FunctionalLinkedList<Integer> list = new MeanStdDevApp.FunctionalLinkedList<>();
    int result = list.reduce(0, Integer::sum);
    assertEquals(0, result);
  }

  @Test
  void testSingleElementStdDev() {
    MeanStdDevApp.FunctionalLinkedList<Double> list = new MeanStdDevApp.FunctionalLinkedList<>();
    list.add(42.0);
    double mean = list.reduce(0.0, Double::sum) / list.size();

    // varianza no definida (división por 0), se puede permitir que sea 0.0 o Infinity
    double variance =
      list.map(x -> Math.pow(x - mean, 2)).reduce(0.0, Double::sum) /
      Math.max(1, list.size() - 1);
    double stdDev = Math.sqrt(variance);

    assertEquals(42.0, mean, 0.001);
    assertTrue(Double.isFinite(stdDev)); // debería dar 0.0 si se maneja correctamente
  }

  @Test
  void testForEachAccumulate() {
    MeanStdDevApp.FunctionalLinkedList<Integer> list = new MeanStdDevApp.FunctionalLinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    final int[] sum = { 0 };
    list.forEach(x -> sum[0] += x);
    assertEquals(6, sum[0]);
  }

  @Test
  void testFilterNoMatch() {
    MeanStdDevApp.FunctionalLinkedList<Integer> list = new MeanStdDevApp.FunctionalLinkedList<>();
    list.add(1);
    list.add(3);
    MeanStdDevApp.FunctionalLinkedList<Integer> even = list.filter(x ->
      x % 2 == 0
    );
    assertEquals(0, even.size());
  }

  @Test
  void testMapToString() {
    MeanStdDevApp.FunctionalLinkedList<Integer> list = new MeanStdDevApp.FunctionalLinkedList<>();
    list.add(5);
    list.add(10);
    MeanStdDevApp.FunctionalLinkedList<String> mapped = list.map(
      Object::toString
    );
    StringBuilder sb = new StringBuilder();
    mapped.forEach(sb::append);
    assertEquals("510", sb.toString());
  }

  @Test
  void testReduceMultiplication() {
    MeanStdDevApp.FunctionalLinkedList<Integer> list = new MeanStdDevApp.FunctionalLinkedList<>();
    list.add(2);
    list.add(3);
    list.add(4);
    int product = list.reduce(1, (a, b) -> a * b);
    assertEquals(24, product);
  }

  @Test
  void testSizeEmptyList() {
    MeanStdDevApp.FunctionalLinkedList<Double> list = new MeanStdDevApp.FunctionalLinkedList<>();
    assertEquals(0, list.size());
  }
}
