# Full Classroom Privato

Applicazione web dinamica di tipo **classroom privato** (senza streaming) sviluppata in **Java** con **SQL**, **Servlets**, gestione delle **sessioni** e moduli per l’ingresso e le consultazioni.  

Il sistema è progettato per gestire l’accesso degli utenti a contenuti audiovisivi e consentire la comunicazione con l’amministratore o il professore tramite un **bot di Telegram**.  

---

## 🚪 Flusso di Autenticazione e Accesso

1. All’arrivo su `index`, viene richiesta l’**autenticazione** tramite indirizzo email.
2. Se l’email è valida e l’invio della mail di verifica va a buon fine:
   - L’utente viene informato che la registrazione è stata accettata.  
   - I suoi dati (email, nome, cognome, password) vengono salvati nel **database**.  
3. In questa fase iniziale, l’utente **non può ancora accedere**.  
4. Solo quando l’**amministratore** cambia il campo `pago` da **0 → 1** nel database, l’utente potrà entrare nel sistema.

---

## 👨‍🏫 Ruolo dell’Amministratore

- L’amministratore accede **dallo stesso `index` degli utenti**, utilizzando le credenziali speciali:  
  - **Email:** `admin@admin.admin`  
  - **Password:** `admin`  
- Da qui può:  
  - **Approvare gli accessi** modificando il campo `pago` di ciascun utente nel database.  
  - Gestire le registrazioni e mantenere il controllo sugli accessi.  

---

## 🎓 Ruolo dell’Utente

- Una volta approvato, l’utente potrà:  
  - **Accedere ai contenuti audiovisivi** caricati sulla piattaforma.  
  - **Inviare messaggi all’amministratore o al professore** direttamente dal sito web tramite un **bot di Telegram**.  

---

## 🛠️ Tecnologie Utilizzate

- **Java**  
- **SQL**  
- **Servlets**  
- **Gestione Sessioni**  
- **Form HTML**  
- **Bot di Telegram**  

---

## 📌 Riassunto

Questo sistema implementa un **classroom privato con accesso controllato dall’amministratore**, dove:  
- La registrazione iniziale viene validata via email.  
- L’amministratore decide l’accesso definitivo approvando il pagamento.  
- Gli utenti approvati possono accedere ai contenuti audiovisivi e hanno un canale diretto di comunicazione con l’amministratore/professore tramite Telegram.  
- L’amministratore accede dallo stesso index, utilizzando credenziali predefinite. 