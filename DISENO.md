## 🧠 Diseño del Proyecto: `MeanStdDevApp`

Este diseño se enfoca en cumplir con los objetivos del laboratorio usando **programación funcional**, **genéricos**, **comodines (`?`)**, y estructuras de datos implementadas manualmente (`LinkedList`). El diseño prioriza **modularidad**, **expresividad funcional** y **pruebas automatizadas**.

---

### 📦 1. **Estructura general del paquete**

```plaintext
com.diego
├── MeanStdDevApp.java         ← clase principal + lógica de cálculo
└── MeanStdDevAppTest.java     ← pruebas unitarias con JUnit 5
```

> El proyecto está intencionadamente contenido en un solo archivo para enfocarse en el uso de clases internas, como exige el enunciado.

---

### 🧱 2. **Clases y componentes**

#### 🟦 `MeanStdDevApp`

* **Tipo**: Clase principal (`public`)
* **Responsabilidad**:

  * Carga datos numéricos desde un archivo externo (`data.txt`)
  * Calcula media y desviación estándar
  * Imprime los resultados formateados
* **Método principal**: `public static void main(String[] args)`

#### 🟩 `FunctionalLinkedList<T>`

* **Tipo**: Clase interna estática genérica (`static class`)
* **Responsabilidad**: Implementar una lista enlazada con soporte para programación funcional

##### 📌 Métodos:

| Método                                  | Descripción                                                        |
| --------------------------------------- | ------------------------------------------------------------------ |
| `add(T)`                                | Agrega un nodo al final de la lista                                |
| `size()`                                | Devuelve el número total de elementos                              |
| `forEach(Consumer<? super T>)`          | Itera sobre los elementos aplicando una acción                     |
| `map(Function<? super T, ? extends R>)` | Devuelve una nueva lista con la transformación aplicada            |
| `filter(Predicate<? super T>)`          | Devuelve una lista con solo los elementos que cumplen el predicado |
| `reduce(T identity, BinaryOperator<T>)` | Aplica acumulación de izquierda a derecha con valor inicial        |

#### 🟨 `Node<T>`

* **Tipo**: Clase interna estática de `FunctionalLinkedList<T>`
* **Responsabilidad**: Almacenar el dato y el enlace al siguiente nodo

##### 📌 Atributos:

| Atributo | Tipo      | Descripción                  |
| -------- | --------- | ---------------------------- |
| `data`   | `T`       | Elemento almacenado          |
| `next`   | `Node<T>` | Referencia al siguiente nodo |

---

### 🔁 3. **Flujo funcional del programa (`main`)**

```java
BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
reader.lines()
      .map(Double::parseDouble)
      .forEach(numbers::add); // add a lista funcional
```

1. **Lectura** de cada línea del archivo como `String`
2. **Conversión** de texto a `Double` con `map`
3. **Almacenamiento** de los números con `add()`
4. **Cálculo de media** con `reduce` y división
5. **Cálculo de desviación estándar** con `map` y `reduce`
6. **Impresión** con `System.out.printf(...)`

---

### ☑️ 4. **Principios de diseño aplicados**

| Principio / Técnica                 | Aplicación en el proyecto                                                         |
| ----------------------------------- | --------------------------------------------------------------------------------- |
| **Programación funcional**          | Uso de `map`, `filter`, `reduce`, `forEach`                                       |
| **Genéricos (`<T>`)**               | `FunctionalLinkedList<T>` y `Node<T>`                                             |
| **Comodines (`?`)**                 | `Consumer<? super T>`, `Function<? super T, ? extends R>`                         |
| **Clases anidadas**                 | `Node<T>` dentro de `FunctionalLinkedList<T>`                                     |
| **Clases estáticas**                | `FunctionalLinkedList` y `Node` son `static` para evitar dependencia de instancia |
| **Inmutabilidad controlada**        | No hay setters públicos; los datos solo se agregan con `add()`                    |
| **Separación de responsabilidades** | Lógica de cálculo en `main`, almacenamiento en lista propia                       |

---

### 📐 5. **Diagrama de clases simplificado**

```
+----------------------------+
|      MeanStdDevApp        |
|----------------------------|
| - main(String[] args)     |
+----------------------------+
           |
           v
+----------------------------+
|  FunctionalLinkedList<T>  |
|----------------------------|
| - Node<T> head            |
| - int size                |
|----------------------------|
| + add(T)                  |
| + size()                  |
| + forEach(Consumer)       |
| + map(Function)           |
| + filter(Predicate)       |
| + reduce(BinaryOperator)  |
+----------------------------+
           |
           v
    +------------------+
    |    Node<T>       |
    |------------------|
    | - data : T       |
    | - next : Node<T> |
    +------------------+
```

---

### 🚀 6. Posibles mejoras o extensiones

* Separar `FunctionalLinkedList` en un archivo externo reutilizable
* Implementar interfaz `Iterable<T>` para integrarse con `Stream<T>`
* Agregar método `sort(Comparator<? super T>)` usando clases anónimas
* Adaptar a lectura de múltiples columnas para analizar más vectores de datos


