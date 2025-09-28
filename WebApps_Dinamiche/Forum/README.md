# 📝 Mini Forum con Java Servlet + SQLite

Questo progetto è un forum semplice che utilizza:
- **Java Servlet** per la logica web  
- **SQLite** come database per salvare i messaggi  

Il sistema funziona con un `index.html` che contiene un **iframe**.  
Questo `iframe` carica il `ForumServlet`, che si occupa di **mostrare** e **salvare** i messaggi.  

---

## 🔄 Funzionamento del `ForumServlet`

### 1. Input (ingresso)
- L’utente apre `index.html`.  
- L’**iframe** punta al servlet `ForumServlet`.  
- Il servlet riceve:
  - Una richiesta **GET** → per elencare i messaggi.  
  - Una richiesta **POST** → quando il form invia un nuovo messaggio (`username` e `message`).  

### 2. Processo
- In **GET**:
  1. Si connette al database SQLite (`foro.sqlite3`).  
  2. Esegue `SELECT * FROM messages`.  
  3. Scorre ogni riga con `ResultSet`.  
  4. Genera HTML con `<p><b>utente</b> (data): messaggio</p>`.  

- In **POST**:
  1. Legge i parametri `username` e `message`.  
  2. Esegue `INSERT INTO messages (username, message) VALUES (?, ?)`.  
  3. Reindirizza in modo che il browser faccia di nuovo un **GET**, mostrando la lista aggiornata.  

### 3. Output (uscita)
- Il servlet genera HTML dinamico:  
  - Elenca tutti i messaggi salvati.  
  - Mostra un form per aggiungere un nuovo messaggio.  
- Questo HTML è il contenuto che l’utente vede dentro l’**iframe** in `index.html`.  

---

## 📂 Flusso riassunto

1. **L’utente apre** `index.html`.  
2. L’**iframe carica** `ForumServlet`.  
3. `ForumServlet` esegue un **GET** → stampa la lista dei messaggi.  
4. L’utente scrive un nuovo messaggio nel form → **POST** allo stesso servlet.  
5. Il servlet salva nel database e reindirizza a se stesso.  
6. L’iframe mostra di nuovo il forum con il nuovo messaggio incluso.  
 
