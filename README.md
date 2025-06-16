# MeanStdDevApp 📈

Aplicación de línea de comandos en Java para calcular la **media** y la **desviación estándar** de un conjunto de números reales. Utiliza una **lista enlazada genérica propia**, compatible con programación funcional (`map`, `filter`, `reduce`, `forEach`). El proyecto se construye con **Maven**, cuenta con pruebas automatizadas en **JUnit 5** y análisis de calidad de código mediante **SonarQube** y **GitHub Actions**.

---

## 🚀 Comenzando

Sigue estos pasos para obtener una copia del proyecto, ejecutarlo localmente y entender su funcionamiento.

### ✅ Requisitos previos

```bash
Java 8 o superior  
Apache Maven 3.x  
Git
````

---

## 💻 Instalación

Clona el repositorio y navega a la carpeta del proyecto:

```bash
git clone https://github.com/marzo245/Mean-and-standard.git
cd Mean-and-standard
```

Compila el proyecto:

```bash
mvn clean install
```

---

## ▶️ Uso de la aplicación

Crea un archivo `data.txt` en la raíz del proyecto con una lista de números reales, uno por línea:

```txt
160
591
114
229
230
...
```

Ejecuta la aplicación con Maven:

```bash
mvn exec:java -Dexec.mainClass="com.diego.MeanStdDevApp"
```

Salida esperada:

```
Mean: 550.60
Standard Deviation: 572.03
```

---

## 🧠 Estructura del Proyecto

* `MeanStdDevApp`: Clase principal que orquesta lectura de archivo, cálculo y salida.
* `FunctionalLinkedList<T>`: Lista enlazada genérica propia con métodos funcionales (`map`, `filter`, `reduce`, `forEach`).
* `Node<T>`: Nodo individual de la lista enlazada.
* `data.txt`: Archivo externo con datos de entrada numéricos.

---

## 🧪 Pruebas

El proyecto incluye pruebas automatizadas con **JUnit 5**.

Para ejecutarlas:

```bash
mvn test
```

Casos cubiertos:

* Cálculo de media y desviación estándar con los datos del enunciado.
* Casos límite: lista vacía, un solo elemento.
* Validación de excepciones y consistencia en operaciones funcionales.

Consulta el archivo [`TESTREPORT.md`](TESTREPORT.md) para ver:

* Casos de prueba
* Resultados esperados y reales
* Cobertura y excepciones simuladas

---

## 📐 Diseño

Consulta el archivo [`DISENO.md`](DISENO.md) para entender:

* Diagrama de clases
* Uso de programación funcional (`Consumer`, `Function`, `Predicate`, `BinaryOperator`)
* Aplicación de genéricos y comodines (`<T>`, `? super T`, `? extends T`)
* Decisiones de diseño y principios aplicados

---

## 📊 Métrica LOC/h

* Tiempo estimado de desarrollo: 6 horas
* LOC producidas (código funcional): 90 líneas
* Productividad estimada: **15 LOC/h**

---

## 🛠️ Construido con

* [Java 8+](https://docs.oracle.com/javase/8/)
* [JUnit 5](https://junit.org/junit5/)
* [Apache Maven](https://maven.apache.org/)
* [SonarQube](https://www.sonarqube.org/) para análisis de calidad
* [GitHub Actions](https://docs.github.com/en/actions) para integración continua

---

## ✅ Integración continua con SonarQube

Este repositorio utiliza **GitHub Actions** para ejecutar automáticamente:

* Compilación del proyecto con Maven
* Pruebas automatizadas con JUnit
* **Análisis estático de calidad de código con SonarQube**

Cada vez que haces `push` o una `pull request` hacia la rama `main`, se activa el workflow ubicado en [`.github/workflows/maven.yml`](.github/workflows/maven.yml), que realiza lo siguiente:

1. Checkout del repositorio
2. Configuración del JDK 17
3. Construcción del proyecto (`mvn package`)
4. Pruebas (`mvn test`)
5. Análisis en SonarQube (`mvn sonar:sonar`)

```yaml
name: Java CI with Maven + SonarQube

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: maven

      - name: Build and analyze with SonarQube
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        run: |
          mvn clean verify sonar:sonar \
            -Dsonar.projectKey=MeanStdDevApp \
            -Dsonar.host.url=https://sonarcloud.io \
            -Dsonar.login=$SONAR_TOKEN
```

> 🔐 Asegúrate de configurar el `SONAR_TOKEN` en los secretos del repositorio (`Settings > Secrets > Actions`).

---

## 👤 Autor

**Diego Chicuazuque**
[marzo245](https://github.com/marzo245)

---

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia MIT. Ver archivo [LICENSE](LICENSE) para más información.

---

## 🙌 Agradecimientos

* A los profesores y mentores que guiaron el desarrollo.
* A las herramientas libres como JUnit, Maven y SonarQube.
* A la comunidad Java por sus buenas prácticas y documentación.



