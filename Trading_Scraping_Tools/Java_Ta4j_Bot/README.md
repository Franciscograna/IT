 # 📊 Java Trading Bot con TA4J + Binance

Questo progetto implementa un **trading bot in Java** basato su **TA4J** e dati di mercato da Binance.  
Analizza le candele (*klines*), calcola indicatori tecnici e genera segnali di trading.  
Il sistema è suddiviso in più componenti con ruoli specifici.

---

## 🔄 Componenti principali

### 1. **MainBot**
- Legge le *klines* (candele 1H) da Binance.  
- Costruisce serie temporali (`BarSeries`) e indicatori TA4J (es. **MACD**, medie mobili, ecc.).  
- Elabora la **tendenza lunga** (trend di fondo).  
- Scrive lo stato corrente in un file.  

### 2. **Trigger**
- Legge le *klines* (candele 15m) da Binance.
- - Costruisce serie temporali (`BarSeries`) e indicatori TA4J (es. **MACD**, medie mobili, ecc.).  
- Legge i dati/stato scritti dal **MainBot**.  
- Riconosce eventi di interesse (pattern, incroci MACD, segnali trend).  
- Invia un’**allerta** (es. SMS, Telegram, ecc.) quando viene rilevato un segnale.  

### 3. **Interact**
- Gestisce le **operazioni di allerta** (SMS, Telegram).  
- Coordina la comunicazione tra la logica di analisi e le azioni esterne.  
- Si occupa di leggere e scrivere file per condividere stati o segnali tra i moduli(Main-Trigger).
- Funziona come **punto centrale di esecuzione** per le notifiche e le operazioni collegate ai segnali generati dal bot.


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
- Segnali operativi chiari (BUY / SELL ).  
- Alert inviati via SMS, Telegram o altri canali configurati.  
- Possibile esecuzione diretta di ordini (se abilitato ).  

---


