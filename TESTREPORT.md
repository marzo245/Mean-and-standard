# 🧪 Reporte de Pruebas: MeanStdDevApp

Este documento describe las pruebas realizadas para validar el comportamiento del proyecto `MeanStdDevApp`, enfocado en una implementación de lista enlazada genérica funcional que calcula la media y desviación estándar de números leídos desde un archivo de texto.

Se utilizó **JUnit 5** como framework de pruebas, y las pruebas se ejecutan automáticamente con **Maven** y **GitHub Actions**.

---

## ✅ Pruebas unitarias detalladas

### 🔹 `testMainWithExampleData`
**Objetivo**: Validar la salida del programa usando una entrada conocida con valores del 10 al 100.

- **Entrada simulada**: archivo `data.txt` con 10 valores (`10.0` a `100.0`)
- **Comprobaciones**:
  - Que la salida contenga `"Mean: 55.00"`
  - Que contenga `"Standard Deviation: 30.28"`
- **Valor**: Asegura el correcto funcionamiento del flujo completo: lectura, cálculo y salida.

---

### 🔹 `testMainWithEmptyFile`
**Objetivo**: Validar el comportamiento cuando el archivo de entrada está vacío.

- **Entrada**: archivo `data.txt` vacío
- **Comprobaciones**:
  - Que el programa no se caiga
  - Que imprima algún mensaje relacionado con "Mean" o maneje la excepción
- **Valor**: Asegura robustez ante entradas vacías y evita errores por división por cero.

---

### 🔹 `testMeanStdDevAppConstructor`
**Objetivo**: Cubrir el constructor de la clase principal `MeanStdDevApp`.

- **Comprobación**: Que se puede instanciar y no sea `null`
- **Valor**: Aumenta cobertura aunque no sea funcionalmente necesario.

---

### 🔹 `testAddAndSize`
**Objetivo**: Verificar que los elementos se agregan correctamente a la lista y se cuenta el tamaño adecuadamente.

- **Entrada**: `[10, 20, 30]`
- **Comprobación**: `size()` debe retornar `3`
- **Valor**: Asegura el correcto funcionamiento de las operaciones básicas de la lista.

---

### 🔹 `testMap`
**Objetivo**: Verificar el comportamiento de la transformación funcional `map`.

- **Entrada**: `[1, 2, 3]`, función `x * 2`
- **Resultado esperado**: `[2, 4, 6]`
- **Comprobación**: contenido textual y tamaño
- **Valor**: Valida `map()` con lambdas.

---

### 🔹 `testFilter`
**Objetivo**: Validar que el filtro funcional funciona correctamente.

- **Entrada**: `[1, 2, 3, 4]`, filtro `x % 2 == 0`
- **Resultado esperado**: `[2, 4]`
- **Comprobación**: tamaño final debe ser `2`
- **Valor**: Confirma que `filter()` selecciona correctamente los elementos.

---

### 🔹 `testReduceSum`
**Objetivo**: Validar que `reduce` sume correctamente todos los elementos.

- **Entrada**: `[1.0, 2.0, 3.0]`
- **Acumulador**: `Double::sum`
- **Resultado esperado**: `6.0`
- **Valor**: Prueba esencial para asegurar cálculos como la media.

---

### 🔹 `testMeanAndStdDevExampleData`
**Objetivo**: Validar la lógica de cálculo de media y desviación estándar directamente.

- **Entrada**: `[10, 20, ..., 100]`
- **Resultado esperado**:
  - `mean = 55.0`
  - `stdDev ≈ 30.2765`
- **Tolerancia**: `±0.001`
- **Valor**: Verifica las fórmulas estadísticas aplicadas correctamente.

---

### 🔹 `testEmptyListReduce`
**Objetivo**: Asegurar que reducir una lista vacía no causa error.

- **Entrada**: `[]`
- **Resultado esperado**: `0` al reducir con suma
- **Valor**: Valida comportamiento seguro y correcto de `reduce()` con listas vacías.

---

### 🔹 `testSingleElementStdDev`
**Objetivo**: Verificar el comportamiento con una lista de un solo número.

- **Entrada**: `[42.0]`
- **Resultado esperado**:
  - `mean = 42.0`
  - `stdDev = 0.0` (manejada con `Math.max(1, n-1)`)
- **Valor**: Verifica que el cálculo no falla con 1 solo valor.

---

### 🔹 `testForEachAccumulate`
**Objetivo**: Usar `forEach()` para recorrer y acumular valores.

- **Entrada**: `[1, 2, 3]`
- **Resultado esperado**: suma = `6`
- **Valor**: Valida `forEach()` como mecanismo de iteración funcional.

---

### 🔹 `testFilterNoMatch`
**Objetivo**: Validar `filter()` cuando ningún elemento cumple la condición.

- **Entrada**: `[1, 3]`, filtro `x % 2 == 0`
- **Resultado esperado**: lista vacía
- **Valor**: Verifica que `filter()` retorna correctamente vacío si no hay coincidencias.

---

### 🔹 `testMapToString`
**Objetivo**: Transformar enteros a texto con `map()` y luego concatenar con `forEach()`.

- **Entrada**: `[5, 10]`
- **Resultado esperado**: `"510"`
- **Valor**: Valida la transformación de tipos y operaciones funcionales encadenadas.

---

### 🔹 `testReduceMultiplication`
**Objetivo**: Probar reducción con una operación distinta a suma: multiplicación.

- **Entrada**: `[2, 3, 4]`
- **Resultado esperado**: `24`
- **Valor**: Asegura flexibilidad de `reduce()` con diferentes lambdas.

---

### 🔹 `testSizeEmptyList`
**Objetivo**: Confirmar que una lista nueva tenga tamaño 0.

- **Comprobación**: `size()` retorna `0`
- **Valor**: Asegura estado inicial correcto de la estructura.

---

## 📊 Cobertura de código

| Clase / Método                  | Cobertura alcanzada                     |
|--------------------------------|------------------------------------------|
| `MeanStdDevApp.main()`         | ✅ Con múltiples escenarios (`data.txt`) |
| `FunctionalLinkedList.add()`   | ✅ Cubierto directamente en varios tests |
| `FunctionalLinkedList.size()`  | ✅ Probado en varios contextos           |
| `FunctionalLinkedList.map()`   | ✅ Usado en `map`, `mean`, `stdDev`      |
| `FunctionalLinkedList.filter()`| ✅ Probado con aciertos y sin aciertos   |
| `FunctionalLinkedList.reduce()`| ✅ Con suma, producto y listas vacías    |
| `FunctionalLinkedList.forEach()`| ✅ Usado para acumulación y salida      |

---

## 🧪 Resultado final de pruebas

```

\[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
\[INFO] BUILD SUCCESS

```

---

## 🔍 Observaciones

- La prueba `testMainWithExampleData` fue corregida para usar punto decimal (`55.00`, `30.28`) en vez de coma, compatible con el locale por defecto (`en-US`) de GitHub Actions.
- Se usaron `@BeforeEach` y `@AfterEach` para redirigir y restaurar `System.out`, asegurando testabilidad de la salida del programa.
- Las pruebas son lo suficientemente genéricas como para adaptarse si se cambia el backend de la lista (por ejemplo, si se reemplaza por una implementación `Stream` en el futuro).

---

## 📈 Conclusión

El conjunto de pruebas proporciona **alta cobertura funcional y estructural**, validando tanto lógica funcional (`map`, `reduce`) como escenarios reales (`main()` con archivos). La estrategia de pruebas asegura robustez, precisión estadística y modularidad del código.

