# ⚡ Binance Java Client – Leggero e Potente

**Binance Java Client** è un’implementazione in Java per interagire con l’API di Binance.  
È progettato per essere **più veloce, leggero e controllabile** rispetto al client ufficiale.

---

## 🚀 Perché Binance Java Client?

- 🪶 **Leggero**: pesa solo un quarto della libreria ufficiale.  
- ⚡ **Veloce**: meno overhead, esecuzione più efficiente.  
- 🧾 **Codice semplice**: molte meno linee, più facile da leggere e mantenere.  
- 🎛️ **Maggiore controllo**: visibilità chiara di ciò che accade in ogni richiesta.  
- 🛠️ **Flessibile**: funziona in **margin trading**, si adatta ad altri prodotti e persino ad altri exchange.  
- 📦 **Dipendenze tradizionali**: niente di esotico, facile da integrare in qualsiasi progetto Java.  
- 🔍 **Trasparente**: l’endpoint utilizzato è sempre visibile chiaramente.  

---
---
## 🔄 Formato di comunicazione

- **Input** → tutte le richieste vengono inviate come **`application/x-www-form-urlencoded`**  
  (parametri nel body, ad esempio: `key1=val1&key2=val2`).  

- **Output** → tutte le risposte vengono restituite in **JSON**.  

## 🔄 Funzionalità principali

- 📥 **Creare un ordine**  
- 🗑️ **Cancellare un ordine specifico**  
- 🗑️ **Cancellare tutti gli ordini**  
- 🔍 **Consultare un ordine**  

## 🔑 1. Configurazione delle API Keys

Genera le chiavi dal tuo account Binance e **autorizza l’IP**.  
Inseriscile nel codice:

```java
private static final String API_KEY = "YOUR_API_KEY";
private static final String SECRET_KEY = "YOUR_SECRET_KEY";
```
## 💰 2. Impostare il simbolo di trading

Il simbolo della coppia da tradare deve essere impostato in ogni funzione.
Ad esempio, per BTC/USDC:

```java
String symbol = "BTCUSDC";
```
## 🛠️ 3. Inizializzare l’oggetto API
```java
Api api = new Api();
//🔻 Vendita (SELL) – Ordine a Mercato
api.Post("SELL", "MARKET", "90.123.0");
//❌ Cancellare tutti gli ordini
api.DeleteAll();
```

