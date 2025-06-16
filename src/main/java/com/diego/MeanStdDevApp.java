package com.diego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *  Aplicación que calcula la media y desviación estándar de una lista de números
 *   leídos desde un archivo de texto.
 *  *  Utiliza una LinkedList funcional para almacenar los números y realizar
 *   operaciones funcionales como map, filter y reduce.
 *  *  La media se calcula como la suma de los números dividida por la cantidad de
 *    números.
 *
 *   La desviación estándar se calcula como la raíz cuadrada de la varianza,
 *   donde la varianza es la suma de las diferencias al cuadrado entre cada número
 *    y la media, dividida por la cantidad de números menos uno.
 *  *  *  El programa imprime la media y la desviación estándar formateadas a dos
 *    decimales.
 *  *   Requiere un archivo de texto llamado "data.txt" en el mismo directorio que
 *    el código fuente, que contenga una lista de números separados por líneas.
 *  *   Ejemplo de contenido del archivo "data.txt":
 *    10.0
 *    20.0
 *     30.0
 *    40.0
 *     50.0
 *    60.0
 *     70.0
 *     80.0
 *     90.0
 *    100.0
 *  *   El programa utiliza una clase interna `FunctionalLinkedList` que implementa
 *    una lista enlazada funcional con métodos para agregar elementos, iterar sobre
 *     ellos, aplicar funciones de mapeo, filtrado y reducción, y calcular el tamaño
 *    de la lista.
 *
 *
 * Author: Diego Chicuazuque
 * Version: 1.0
 */
public class MeanStdDevApp {

  /**
   * Método principal que ejecuta la aplicación.
   * Lee números desde un archivo de texto, calcula la media y desviación estándar,
   * e imprime los resultados.
   *
   * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
   */
  public static void main(String[] args) {
    FunctionalLinkedList<Double> numbers = new FunctionalLinkedList<>();

    try (
      BufferedReader reader = new BufferedReader(new FileReader("data.txt"))
    ) {
      reader.lines().map(Double::parseDouble).forEach(numbers::add);
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      return;
    }

    double mean = numbers.reduce(0.0, Double::sum) / numbers.size();
    double variance =
      numbers.map(x -> Math.pow(x - mean, 2)).reduce(0.0, Double::sum) /
      (numbers.size() - 1);
    double stdDev = Math.sqrt(variance);

    System.out.printf("Mean: %.2f\nStandard Deviation: %.2f\n", mean, stdDev);
  }

  /**
   * Clase interna que implementa una lista enlazada funcional.
   * Permite agregar elementos, iterar sobre ellos, aplicar funciones de mapeo,
   * filtrado y reducción, y calcular el tamaño de la lista.
   *
   * @param <T> Tipo de los elementos en la lista.
   */
  // Clase interna con LinkedList funcional
  static class FunctionalLinkedList<T> {

    private Node<T> head;
    private int size = 0;

    private static class Node<T> {

      T data;
      Node<T> next;

      Node(T data) {
        this.data = data;
      }
    }

    /**
     * Agrega un nuevo elemento al final de la lista.
     *
     * @param data El dato a agregar.
     */
    public void add(T data) {
      Node<T> newNode = new Node<>(data);
      if (head == null) {
        head = newNode;
      } else {
        Node<T> current = head;
        while (current.next != null) current = current.next;
        current.next = newNode;
      }
      size++;
    }

    /**
     * Devuelve el número de elementos en la lista.
     *
     * @return El tamaño de la lista.
     */
    public int size() {
      return size;
    }

    /**
     * Itera sobre cada elemento de la lista y aplica la acción dada.
     *
     * @param action La acción a aplicar a cada elemento.
     */
    public void forEach(Consumer<? super T> action) {
      Node<T> current = head;
      while (current != null) {
        action.accept(current.data);
        current = current.next;
      }
    }

    /**
     * Aplica una función de mapeo a cada elemento de la lista y devuelve una nueva
     * lista con los resultados.
     *
     * @param mapper La función de mapeo a aplicar.
     * @param <R> El tipo de los elementos en la nueva lista.
     * @return Una nueva lista con los resultados del mapeo.
     */
    public <R> FunctionalLinkedList<R> map(
      Function<? super T, ? extends R> mapper
    ) {
      FunctionalLinkedList<R> result = new FunctionalLinkedList<>();
      forEach(t -> result.add(mapper.apply(t)));
      return result;
    }

    /**
     * Filtra los elementos de la lista según un predicado y devuelve una nueva lista
     * con los elementos que cumplen el predicado.
     *
     * @param predicate El predicado para filtrar los elementos.
     * @return Una nueva lista con los elementos que cumplen el predicado.
     */
    public FunctionalLinkedList<T> filter(Predicate<? super T> predicate) {
      FunctionalLinkedList<T> result = new FunctionalLinkedList<>();
      forEach(t -> {
        if (predicate.test(t)) result.add(t);
      });
      return result;
    }

    /**
     * Reducción de los elementos de la lista utilizando un valor inicial y un
     * acumulador.
     *
     * @param identity El valor inicial para la reducción.
     * @param accumulator La función acumuladora que combina el valor actual con el
     *                   siguiente elemento.
     * @return El resultado de la reducción.
     */
    public T reduce(T identity, BinaryOperator<T> accumulator) {
      final Object[] result = { identity };
      forEach(t -> result[0] = accumulator.apply((T) result[0], t));
      return (T) result[0];
    }
  }
}
