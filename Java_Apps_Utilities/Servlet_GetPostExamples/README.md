# Java Servlets – Demo di combinazioni HTTP

Questo progetto raccoglie diversi esempi di interazione **frontend → backend** con **Java Servlets**.  
Ogni esempio mostra come inviare dati (HTML form, JSON, fetch, axios) e come vengono gestiti lato server (Servlet).  

---

## 🔹 Esempi inclusi

### Example 1 – HTML Form + POST (fetch)
- **Input**: HTML form (`servlet.html`)  
- **Metodo HTTP**: POST (JavaScript fetch)  
- **Servlet**: `Servlet.java`  
  - riceve i parametri dal form  
  - li re–invia come query string nell’URL  
- **Output**: parametri recuperati da JavaScript e mostrati in pagina (`servlet.html`)  

---

### Example 2 – Form HTML con 4 varianti
- **Input**: form HTML (`Demo.html`)  
- **Servlets**: `DemoG`, `DemoP`, `PostPrint`, `GetRedirect`  
- **Output**:  
  - `GetRedirect` e `DemoP` → reindirizzano a `out.html`  
  - `DemoG` e `PostPrint` → stampano direttamente dal Servlet  
- **Nota**: uso sperimentale del metodo POST che comunque mostra parametri nell’URL  

---

### Example 3 – JSON → Servlet (POST con form)
- **Input**: form HTML (`json.html`)  
- **Metodo HTTP**: POST JSON (via JavaScript)  
- **Servlet**: `Form3`  
  - legge l’oggetto JSON  
  - stampa il valore ricevuto  
- **Output**: risposta diretta con `PrintWriter` del Servlet  

---

### Example 4 – JSON → Servlet (POST automatico)
- **Input**: JSON generato automaticamente da JavaScript (`ajson.html`)  
  - nessun form, nessun bottone  
- **Metodo HTTP**: POST JSON  
- **Servlet**: `Form3` → parsea e stampa i valori  
- **Output**: risposta diretta (`PrintWriter`)  

---

### Example 5 – JSON → Servlet (GET automatico)
- **Input**: JSON inviato automaticamente da JavaScript (`DemoAG.html`)  
  - nessun form, nessun bottone  
- **Metodo HTTP**: GET JSON  
- **Servlet**: `DemoG` → riceve e stampa  
- **Output**: risposta diretta (`PrintWriter`)  

---

### Example 6 – JSON → Servlet (fetch con bottone)
- **Input**: bottone HTML che invia un JSON predefinito (`print.html`)  
- **Metodo HTTP**: POST JSON (fetch)  
- **Servlet**: `Servlet2` → legge JSON e lo stampa  
- **Output**: risposta diretta (`PrintWriter`)  

---

### Example 7 – JSON → Servlet con redirect (fetch)
- **Input**: bottone HTML che invia JSON (`index2.html`)  
- **Metodo HTTP**: POST JSON (fetch)  
- **Servlet**: `Servlet3`  
  - riceve i dati JSON  
  - aggiunge redirect a nuova pagina  
- **Output**: redirect a pagina di conferma  

---

### Example 8 – JSON → Servlet con redirect (axios)
- **Input**: bottone HTML che invia JSON (`axios.html`)  
- **Metodo HTTP**: POST JSON (axios)  
- **Servlet**: `Servlet3` (stessa logica dell’esempio 7)  
- **Output**: redirect a pagina di conferma  