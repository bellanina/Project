# Cinema Management System - Οδηγός Χρήσης & Τεκμηρίωσης (2025/26)

Το παρόν σύστημα αποτελεί μια ολοκληρωμένη λύση για τη διαχείριση προγραμμάτων κινηματογράφου. Υλοποιεί μια αυστηρή μηχανή καταστάσεων (State Machine) 8 σταδίων, διαχείριση χρηστών με ρόλους και REST API.

---

## 1. Οδηγίες Εγκατάστασης (Step-by-Step)

Αν δεν έχετε τα απαραίτητα εργαλεία, ακολουθήστε τα παρακάτω βήματα:

### Α. Εγκατάσταση Java (JDK)
1. Κατεβάστε την **Java 21** (ή 17) από το [Oracle JDK](https://www.oracle.com/java/technologies/downloads/).
2. Εγκαταστήστε την και βεβαιωθείτε ότι η μεταβλητή συστήματος `JAVA_HOME` δείχνει στη σωστή διαδρομή.

### Β. Εγκατάσταση IntelliJ IDEA
1. Κατεβάστε το **IntelliJ IDEA Community Edition** (είναι δωρεάν) από την [JetBrains](https://www.jetbrains.com/idea/download/).
2. Κατά την εγκατάσταση, επιλέξτε όλες τις προεπιλεγμένες ρυθμίσεις.

### Γ. Άνοιγμα του Project
1. Ανοίξτε το IntelliJ IDEA.
2. Επιλέξτε **File -> Open**.
3. Εντοπίστε τον φάκελο της εργασίας και επιλέξτε το αρχείο **`pom.xml`**.
4. Στο παράθυρο που θα εμφανιστεί, επιλέξτε **"Open as Project"** και **"Trust Project"**.
5. Περιμένετε 1-2 λεπτά μέχρι το IntelliJ να κατεβάσει τις βιβλιοθήκες (Maven dependencies). Θα δείτε μια μπάρα προόδου κάτω δεξιά.

---

## 2. Εκτέλεση της Εφαρμογής
1. Στο αριστερό δέντρο φακέλων, πηγαίνετε στη διαδρομή: `src/main/java/com/example/CinemaApplication.java`.
2. Κάντε **Δεξί κλικ** πάνω στο αρχείο `CinemaApplication.java`.
3. Επιλέξτε **Run 'CinemaApplication'**.
4. Η εφαρμογή έχει ξεκινήσει επιτυχώς όταν δείτε στην κονσόλα το μήνυμα:  
   `Started CinemaApplication in X seconds`.

---

## 3. Έλεγχος με Postman (Workflow & Manual Testing)

Η εφαρμογή χρησιμοποιεί **Basic Authentication**. Για κάθε αίτημα (εκτός του Visitor), πρέπει να πάτε στο tab **Authorization** -> **Type: Basic Auth** και να βάλετε τα διαπιστευτήρια.

### Βήμα 1: Δημιουργία Προγράμματος (Programmer)
* **Auth:** `admin` / `admin123`
* **Method:** `POST`
* **URL:** `http://localhost:8080/api/programs`
* **Body (raw JSON):**
```json
{
  "name": "Winter Festival 2025",
  "description": "Premium Cinema Selection",
  "startDate": "2025-01-10",
  "endDate": "2025-02-20"
}
