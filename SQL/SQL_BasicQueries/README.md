# 🗄️ Test Base di Dati SQLite con Java

Permette di provare facilmente la connessione al file `.sqlite3` e verificare il comportamento di query **SELECT** e **INSERT**.

---

## 🔄 Funzionamento del programma

### 1. Input (ingresso)
- L’utente fornisce:
  - `usernameInput` → nome dell’utente da inserire o cercare.  
  - `passwordInput` → password corrispondente.  
- Il programma può eseguire:
  - Una **SELECT** per leggere tutti gli utenti.  
  - Un **INSERT** per aggiungere un nuovo utente.  

---

### 2. Processo
- Il metodo `SqlExecuter` gestisce tutte le operazioni:
  1. Apre una connessione al database SQLite (`usersdb.sqlite3`).  
  2. Controlla se la query contiene `INSERT`:  
     - Se **no** → esegue una query `SELECT`:
       - Scorre il `ResultSet`.  
       - Stampa ogni utente con nome e password.  
       - Se non trova righe corrispondenti, stampa `"Usuario o contraseña incorrectos."`.  
     - Se **sì** → prepara una `PreparedStatement` per inserire i valori `name` e `pass`.  
       - Esegue l’`INSERT`.  
       - Chiude la statement al termine.  

---

### 3. Output (uscita)
  - per `SELECT`: uscita in consola.
  - per `INSERT`: si scrive nella DB.
