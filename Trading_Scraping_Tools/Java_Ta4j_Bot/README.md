 # 📊 Java Trading Bot con TA4J + Binance

Questo progetto implementa un **trading bot in Java** basato su **TA4J** e dati di mercato da Binance.  
Analizza le candele (*klines*), calcola indicatori tecnici e genera segnali di trading.  
Il sistema è suddiviso in più componenti con ruoli specifici.

---

## 🔄 Componenti principali

### 1. **MainBot**
- Legge le *klines* (candele) da Binance.  
- Costruisce serie temporali (`BarSeries`) e indicatori TA4J (es. **MACD**, medie mobili, ecc.).  
- Elabora la **tendenza lunga** (trend di fondo).  
- Scrive lo stato corrente in una risorsa condivisa.  

### 2. **Trigger**
- Legge i dati/stato scritti dal **MainBot**.  
- Riconosce eventi di interesse (pattern, incroci MACD, segnali trend).  
- Invia un’**allerta** (es. SMS, Telegram, ecc.) quando viene rilevato un segnale.  

### 3. **Interact**
- Contiene le operazioni di alarma ().  
- È il punto di contatto tra la logica di analisi e l’esecuzione delle alarm e flags.  

### 4. **Enum States / Modes / Targets**
- **States** → definiscono lo stato attuale della strategia (es. *WAITING, BUY, SELL*).  
- **Modes** → regolano il comportamento del bot (es. *LIVE, PAPER, TEST*).  
- **Targets** → obiettivi o configurazioni operative (es. coppia di trading, timeframe).  

---

## 🔄 Flusso logico

### Input
- Serie di candele (*klines*) recuperate da Binance.  
- Parametri di configurazione (modi, target, timeframe).  

### Processo
1. **MainBot** crea indicatori (es. MACD) a partire dalle candele.  
2. Analizza i pattern e scrive lo **stato corrente** (tendenza lunga, segnali tecnici).  
3. **Trigger** legge lo stato, verifica condizioni specifiche e genera segnali.  
4. **Interact** esegue le operazioni reali o simulate in base agli **enum States e Modes**.  

### Output
- Segnali operativi chiari (BUY / SELL / HOLD).  
- Alert inviati via SMS, Telegram o altri canali configurati.  
- Possibile esecuzione diretta di ordini (se abilitato in modalità *LIVE*).  

---

## 📂 Esempio di utilizzo

```java
MainBot mainBot = new MainBot();
Trigger trigger = new Trigger();
Interact interact = new Interact();

// Analisi del trend e segnali
mainBot.runAnalysis();
String stato = mainBot.getTrendState();

// Generazione allerta
if (trigger.checkSignal(stato)) {
    trigger.sendAlert("Segnale MACD BUY rilevato su BTCUSDT!");
    interact.executeTrade(State.BUY, Target.BTCUSDT);
}
