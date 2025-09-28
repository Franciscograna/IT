 # 🔑 Autenticazione con DAO + SQL

Questo progetto mostra un esempio semplice di autenticazione usando:
- Una classe **User** (per rappresentare i dati dell’utente)  
- Una classe **Dao** (per accedere al database e verificare le credenziali)  
- Una classe **Main** (punto di ingresso per eseguire il test)  
- Un database SQL con una tabella di utenti  

---

## 🔄 Funzionamento del `Dao`

### 1. Input (ingresso)
- L’applicazione legge un’**email** e una **password** fornite dal programma (o da un form, se integrato in una webapp).  
- Questi valori vengono passati al metodo `dao.login(email, password)`.  

### 2. Processo
- All’interno del metodo `login`:
  1. Si apre una connessione al database.  
  2. Si prepara una query tipo:  
     ```sql
     SELECT * FROM users WHERE email = ? AND password = ?
     ```
  3. I parametri `email` e `password` vengono impostati nella query.  
  4. Si esegue la query e si controlla se esiste almeno una riga corrispondente.  
  5. Se l’utente viene trovato, si restituisce `true` (autenticazione riuscita), altrimenti `false`.  

### 3. Output (uscita)
- Il metodo `login` ritorna un **booleano**:
  - `true` → l’utente è autenticato correttamente.  
  - `false` → le credenziali non corrispondono a nessun utente registrato.  

---

## 📂 Flusso riassunto

1. **Main.java** esegue il programma.  
2. Crea un’istanza di `Dao`.  
3. Chiama `dao.login("jose@hotmail.com", "1234")`.  
4. Il DAO interroga la tabella `users` nel database.  
5. Restituisce il risultato della verifica (true/false).  
6. `Main` stampa il valore su console.  

---

## 🗄️ Database

È necessaria una tabella `users` nel database, ad esempio:

```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

INSERT INTO users (email, password) VALUES ('jose@hotmail.com', '1234');
