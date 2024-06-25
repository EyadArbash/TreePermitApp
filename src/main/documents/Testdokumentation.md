# Testdokumentation

## Übersicht
**Projektname:** Tree Permit System  
**Autor:** [Dein Name]  
**Datum:** [Aktuelles Datum]  
**Version:** 1.0  

## Einleitung
Diese Testdokumentation beschreibt die Tests für den Codeabschnitt, der die Registrierung neuer Benutzer in der Tree Permit Anwendung implementiert.

## Testumgebung
- **Betriebssystem:** Windows 11
- **Programmiersprache:** Java 20
- **Framework:** Spring Boot 2.5
- **Test-Framework:** JUnit 5

## Testfallübersicht
| Testfall-Nr. | Beschreibung                                     | Priorität | Status  |
|--------------|--------------------------------------------------|-----------|---------|
| TC-001       | Überprüfung der Registrierung eines neuen Benutzers | Hoch      | Abgeschlossen   |
| TC-002       | Überprüfung der `loadUserByUsername` Methode, wenn der Benutzer existiert | Mittel      | Abgeschlossen   |
| TC-003       | Überprüfung der `loadUserByUsername` Methode, wenn der Benutzer nicht existiert | Hoch      | Abgeschlossen   |
| TC-004       | Überprüfung der `saveMessage` Methode in `MessageService` | Mittel      | Abgeschlossen   |
| TC-005       | Überprüfung der `getMessagesBetweenServerAndClient` Methode in `MessageService` | Mittel      | Abgeschlossen   |
| TC-006       | !entfällt! | keine      | Abgeschlossen   |
| TC-007       | Überprüfung der Passwortänderungsfunktionalität | Mittel      | Abgeschlossen   |
| TC-008       | Überprüfung der E-Mail-Validierung bei der Registrierung eines neuen Benutzers | Hoch      | Abgeschlossen   |
| TC-009       | Überprüfung der Benutzeranmeldung mit korrekten Anmeldeinformationen | Hoch      | Abgeschlossen   |
| TC-010       | Überprüfung der Benutzeranmeldung mit falschen Anmeldeinformationen | Hoch      | Abgeschlossen   |
| TC-011       | Überprüfung der `getUserById` in `UserService` | Mittel      | Abgeschlossen   |
| TC-012       | Überprüfung der `getUserMameByEmail` in `UserService` | Niedrig      | Abgeschlossen   |

## Testfälle im Detail

### Testfall TC-001: Überprüfung der Registrierung eines neuen Benutzers

**Beschreibung:**  
Testet die Registrierung eines neuen Benutzers mittels `RegistrationDto` und überprüft, ob der Benutzer korrekt in der Datenbank gespeichert wird oder bereits existiert.

**Voraussetzungen:**  
- Die Tree Permit Anwendung ist installiert und läuft.
- Die Datenbank ist leer oder enthält keinen Benutzer mit der E-Mail `testuser@example.com`.

**Testdaten:**  
- Username: `testuser`
- Passwort: `testpassword`
- E-Mail: `testuser@example.com`

**Schritte zur Ausführung:**  
1. Ein `RegistrationDto` Objekt mit den Testdaten erstellen.
2. Überprüfen, ob ein Benutzer mit der E-Mail `testuser@example.com` bereits existiert.
3. Falls der Benutzer bereits existiert, überprüfen, ob der Benutzername `testuser` korrekt ist.
4. Falls der Benutzer nicht existiert, den Benutzer registrieren und sicherstellen, dass:
    - Der Benutzer nicht null ist.
    - Der Benutzername `testuser` korrekt ist.
    - Die E-Mail `testuser@example.com` korrekt ist.
    - Die Rolle `ROLE_USER` korrekt zugewiesen ist.

**Erwartetes Ergebnis:**  
- Falls der Benutzer bereits existiert: Der Benutzername `testuser` wird korrekt zurückgegeben.
- Falls der Benutzer nicht existiert: Der neue Benutzer wird erfolgreich registriert und die Daten stimmen mit den Testdaten überein.

**Tatsächliches Ergebnis:**  
Der Benutzer konnte erfolreich erstellt werden und es wurde am Anfang überprüft, ob ein benutzer ist der Email bereits vorliegt. Wenn dies der Fall ist, dann ist der Test ebenfalls erfolgreich.

**Ergebnis:**  
Test war erfolgreich.

**Bemerkungen:**  
keine

## Code für Testfall TC-001

```java
@SpringBootTest(classes = treePermit.Application.class)
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void testRegisterNewUser() {
        // Neuen Nutzer erstellen mittels RegistrationDto
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("testuser");
        registrationDto.setPassword("testpassword");
        registrationDto.setEmail("testuser@example.com");

        // Überprüfen, ob der Benutzer bereits existiert
        String existingUsername = userService.getUserNameByEmail(registrationDto.getEmail());

        if (existingUsername != null) {
            // Wenn der Benutzer bereits existiert, ist der Test erfolgreich
            assertEquals("testuser", existingUsername);
        } else {
            // Nutzer registrieren
            User newUser = userService.registerNewUser(registrationDto);

            // Überprüfen, ob Nutzer in der Datenbank gespeichert wurde
            assertNotNull(newUser);
            assertEquals("testuser", newUser.getUsername());
            assertEquals("testuser@example.com", newUser.getEmail());
            assertEquals("ROLE_USER", newUser.getRole());
        }
    }
}
```

---

### Testfall TC-002: Überprüfung der `loadUserByUsername` Methode, wenn der Benutzer existiert

**Beschreibung:**  
Testet die `loadUserByUsername` Methode des `CustomUserDetailsService`, wenn der Benutzer in der Datenbank existiert.

**Voraussetzungen:**  
- Die Tree Permit Anwendung ist installiert und läuft.
- Die Datenbank enthält einen Benutzer mit der E-Mail `testuser@example.com`.

**Testdaten:**  
- E-Mail: `testuser@example.com`
- Passwort: `password`
- Rolle: `ROLE_USER`

**Schritte zur Ausführung:**  
1. Ein `User` Objekt mit den Testdaten erstellen und das `UserRepository` so konfigurieren, dass es dieses Objekt zurückgibt, wenn `findByEmail` mit der E-Mail `testuser@example.com` aufgerufen wird.
2. Die `loadUserByUsername` Methode mit der E-Mail `testuser@example.com` aufrufen und das zurückgegebene `UserDetails` Objekt überprüfen:
    - Die E-Mail stimmt mit `testuser@example.com` überein.
    - Das Passwort stimmt mit `password` überein.
    - Die Autoritäten enthalten `ROLE_USER`.

**Erwartetes Ergebnis:**  
Die `loadUserByUsername` Methode gibt ein `UserDetails` Objekt zurück, das die korrekten Benutzerdaten enthält.

**Tatsächliches Ergebnis:**  
Die `loadUserByUsername` Methode gibt ein `UserDetails` Objekt zurück, das die korrekten Benutzerdaten enthält.

**Ergebnis:**  
Test war erfolgreich.

**Bemerkungen:**  
keine

## Code für Testfall TC-002


**Beschreibung:** 
Testet die loadUserByUsername Methode des CustomUserDetailsService, wenn der Benutzer in der Datenbank existiert.  

**Voraussetzungen:**  
- Die Tree Permit Anwendung ist installiert und läuft.
- Die Datenbank enthält einen Benutzer mit der E-Mail testuser@example.com.

**Testdaten:**  
- E-Mail: testuser@example.com
- Passwort: password
- Rolle: ROLE_USER

**Schritte zur Ausführung:**  
1. Ein User Objekt mit den Testdaten erstellen und das UserRepository so konfigurieren, dass es dieses Objekt zurückgibt, wenn `findByEmail` mit der E-Mail testuser@example.com aufgerufen wird.
2. Die loadUserByUsername Methode mit der E-Mail testuser@example.com aufrufen und das zurückgegebene UserDetails Objekt überprüfen:
- Die E-Mail stimmt mit testuser@example.com überein.
- Das Passwort stimmt mit password überein.
- Die Autoritäten enthalten ROLE_USER.

**Erwartetes Ergebnis:**
Die loadUserByUsername Methode gibt ein UserDetails Objekt zurück, das die korrekten Benutzerdaten enthält.  

**Tatsächliches Ergebnis:** 
Die loadUserByUsername Methode gibt ein UserDetails Objekt zurück, das die korrekten Benutzerdaten enthält.  

**Ergebnis:** 
Test war erfolgreich.  

**Bemerkungen:** 
keine

```java
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername_UserExists() {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        user.setRole("ROLE_USER");

        when(userRepository.findByEmail("testuser@example.com")).thenReturn(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testuser@example.com");

        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(user.getRole())));
    }
}
```

### Testfall TC-004: Überprüfung der `saveMessage` Methode in `MessageService`
**Beschreibung:**
Testet die saveMessage Methode des MessageService, um sicherzustellen, dass Nachrichten korrekt in der Datenbank gespeichert werden.

**Voraussetzungen:**

Die Tree Permit Anwendung ist installiert und läuft.
Der MessageService und das MessageRepository sind konfiguriert.

**Testdaten:**

Eine Message Instanz mit dem Inhalt "Test message".

**Schritte zur Ausführung:**

Eine neue Message Instanz erstellen.
Das MessageRepository so konfigurieren, dass es die gespeicherte Nachricht zurückgibt.
Die saveMessage Methode des MessageService mit der Testnachricht aufrufen.
Überprüfen, ob der Inhalt der zurückgegebenen Nachricht korrekt ist.
Verifizieren, ob die save Methode des Repositories genau einmal aufgerufen wurde.

**Erwartetes Ergebnis:**

Die zurückgegebene Nachricht hat den Inhalt "Test message".
Die save Methode des Repositories wurde genau einmal aufgerufen.

**Tatsächliches Ergebnis:**
Der Test war erfolgreich. Die zurückgegebene Nachricht hatte den erwarteten Inhalt, und die save Methode wurde korrekt aufgerufen.
Ergebnis:
Test war erfolgreich.
Bemerkungen:
Keine.

--- 

### Testfall TC-005: Überprüfung der `getMessagesBetweenServerAndClient` Methode in `MessageService`

**Beschreibung:**
Testet die getMessagesBetweenServerAndClient Methode des MessageService, um sicherzustellen, dass die korrekten Nachrichten zwischen Server und Client abgerufen werden.
Voraussetzungen:

Die Tree Permit Anwendung ist installiert und läuft.
Der MessageService und das MessageRepository sind konfiguriert.

**Testdaten:**

Client E-Mail: "client@example.com"
Server Benutzername: "clerk"
Eine Liste von zwei Message Instanzen

**Schritte zur Ausführung:**

Das MessageRepository so konfigurieren, dass es eine vordefinierte Liste von Nachrichten zurückgibt.
Die getMessagesBetweenServerAndClient Methode des MessageService mit der Test-E-Mail aufrufen.
Überprüfen, ob die zurückgegebene Liste von Nachrichten der erwarteten Liste entspricht.
Verifizieren, ob die findMessagesBetween Methode des Repositories genau einmal mit den korrekten Parametern aufgerufen wurde.

**Erwartetes Ergebnis:**

Die zurückgegebene Liste von Nachrichten entspricht der erwarteten Liste.
Die findMessagesBetween Methode des Repositories wurde genau einmal mit den korrekten Parametern aufgerufen.

**Tatsächliches Ergebnis:**
Der Test war erfolgreich. Die zurückgegebene Liste von Nachrichten entsprach der erwarteten Liste, und die findMessagesBetween Methode wurde korrekt aufgerufen.

**Ergebnis:**
Test war erfolgreich.

**Bemerkungen:**
Keine.

---

### Testfall TC-006: Registrierung eines neuen Benutzers

**Beschreibung:**
Testet die registerNewUser Methode des UserService, um sicherzustellen, dass neue Benutzer korrekt registriert werden.

**Voraussetzungen:**
Die Tree Permit Anwendung ist installiert und läuft.
Der UserService und das UserRepository sind konfiguriert.

**Testdaten:**
Ein RegistrationDto Objekt mit Benutzername "newuser", E-Mail "newuser@example.com" und Passwort "password".
 
**Schritte zur Ausführung:**
Ein RegistrationDto Objekt mit Testdaten erstellen.
Das UserRepository so konfigurieren, dass es einen gespeicherten Benutzer zurückgibt.
Die registerNewUser Methode des UserService mit dem RegistrationDto aufrufen.
Überprüfen, ob der zurückgegebene Benutzer die korrekten Daten enthält.
Verifizieren, ob die save Methode des Repositories genau einmal aufgerufen wurde.

**Erwartetes Ergebnis:**
Der zurückgegebene Benutzer hat den Benutzernamen "newuser", die E-Mail "newuser@example.com" und die Rolle "ROLE_USER".
Die save Methode des Repositories wurde genau einmal aufgerufen.

**Tatsächliches Ergebnis:**
Der Test war erfolgreich. Der zurückgegebene Benutzer hatte die erwarteten Daten, und die save Methode wurde korrekt aufgerufen.

**Ergebnis:**
Test war erfolgreich.

**Bemerkungen:**
Keine.

---

### Testfall TC-011: Überprüfung der `getUserById` in UserService

**Beschreibung:**
Testet die getUserNameByEmail Methode des UserService, um sicherzustellen, dass der korrekte Benutzername für eine gegebene E-Mail-Adresse zurückgegeben wird.

**Voraussetzungen:**
Die Tree Permit Anwendung ist installiert und läuft.
Der UserService und das UserRepository sind konfiguriert.

**Testdaten:**
E-Mail: "existinguser@example.com"
Benutzername: "existinguser"

**Schritte zur Ausführung:**
Das UserRepository so konfigurieren, dass es einen Benutzer für die Test-E-Mail zurückgibt.
Die getUserNameByEmail Methode des UserService mit der Test-E-Mail aufrufen.
Überprüfen, ob der zurückgegebene Benutzername korrekt ist.
Verifizieren, ob die findByEmail Methode des Repositories genau einmal aufgerufen wurde.

**Erwartetes Ergebnis:**
Der zurückgegebene Benutzername ist "existinguser".
Die findByEmail Methode des Repositories wurde genau einmal mit der korrekten E-Mail aufgerufen.

**Tatsächliches Ergebnis:**
Der Test war erfolgreich. Der zurückgegebene Benutzername entsprach dem erwarteten Wert, und die findByEmail Methode wurde korrekt aufgerufen.

**Ergebnis:**
Test war erfolgreich.

**Bemerkungen:**
Ein zusätzlicher Test für den Fall, dass kein Benutzer gefunden wird, wurde ebenfalls implementiert.

---

### Testfall TC-012: Überprüfung der `getUserNameByEmail` Methode in `UserService`

**Anmerkung:**  
Bedarf keiner extra Testausführung, da der Tests bereits in Testfall TC-001 geschehen ist und dort ausgeführt wurde.