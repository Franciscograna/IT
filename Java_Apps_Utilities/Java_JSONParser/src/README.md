
---

Java JSON Parser

Un progetto didattico in Java per imparare a lavorare con JSON ↔ tipi Java.


---

Descrizione

L’applicazione mostra come:

Convertire un JSON in tipi Java (String, int, ecc.).

Serializzare tipi Java di base in formato JSON.

Validare e gestire errori se i dati non sono coerenti.


Il file principale è Main.java, che contiene esempi pratici di conversione.


---

Funzionalità

Parsing di stringhe JSON in oggetti Java.

Conversione di tipi primitivi/strutture Java in stringhe JSON.

Gestione di oggetti e array JSON.

Esempi semplici per esercitarsi.



---

Esempio

Da JSON a Java

Input (JSON):

{
  "nome": "Anna",
  "eta": 28
}

Output (Java):

String nome = "Anna";
int eta = 28;


---

Da Java a JSON

Input (Java):

String nome = "Marco";
int eta = 35;

Output (JSON):

{
  "nome": "Marco",
  "eta": 35
}


---

Struttura del codice

Java_JSONParser/
 ├── src/
 │    └── Main.java      ← esempi di parsing e dependencies
 └── pom.xml
 

---

