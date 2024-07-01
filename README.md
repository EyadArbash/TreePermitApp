# TreePermitApp Benutzerhandbuch und Installationsanleitung


## Inhaltsverzeichnis
1. Einleitung
2. Systemanforderungen
3. Installation und Start
   - Git-Repository klonen
   - ActiveMQ starten
   - Anwendung ausführen
4. Benutzeranleitung
5. Datenbankzugriff mit HeidiSQL
6. Häufig gestellte Fragen (FAQ)
7. Fehlerbehebung

## 1. Einleitung

Willkommen bei TreePermitApp, einer Webanwendung zur Vereinfachung des Prozesses der Beantragung von Baumfällgenehmigungen. Dieses Handbuch führt Sie durch die Installation, Konfiguration und Nutzung der Anwendung.

## 2. Systemanforderungen

- Git
- Java 8 oder höher
- ActiveMQ
- HeidiSQL (optional)

## 3. Installation und Start

### ActiveMQ starten
Sie müssen alle erstens ActiveMQ starten, bevor Sie die Anwendung ausführen können. Dafür müssen Sie die Datei `apache-activemq-5.15.0-bin.zip` entpacken und Sie die Datei `activemq.bat` unter Ordner `apache-activemq-5.15.0\bin\win32` ausführen.

### Git-Repository klonen

Öffnen Sie ein Terminal oder eine Kommandozeile und führen Sie den folgenden Befehl aus:

- Falls Sie `maven` installiert haben:
    ````
    $ git clone https://github.com/EyadArbash/TreePermitApp.git
    $ cd TreePermitApp
    $ mvn clean package
    $ mvn spring-boot:run
    ````
- Ansonten:
    ````
    $ git clone https://github.com/EyadArbash/TreePermitApp.git
    $ cd TreePermitApp/target/TreePermit-0.0.1-SNAPSHOT.jar
    $ java -jar TreePermit-0.0.1-SNAPSHOT.jar
    ````
Rufen Sie dann mit Ihrem Browser die Seite `http://localhost:8081` auf.

####  Dummy-Konten

- Es gibt zwei Dummy-Konten, die für Testzwecke verwendet werden können:
  1. Als User:
     - E-mail: `user@example.com`, Passwort: `user`
  2. Als Sachbearbeiter
     - Email: `clerk@example.com`, Passwort: `clerk`

## 4. Benutzeranleitung

### Registrierung

1. Navigieren Sie zur Anmeldeseite.
2. Klicken Sie auf den "Registrieren" Button.
3. Füllen Sie Ihre Daten aus:
   - Benutzername
   - E-Mail-Adresse
   - Passwort
4. Klicken Sie auf "Registrieren", um Ihr Konto zu erstellen.

### Anmeldung

1. Geben Sie Ihre E-Mail-Adresse und Ihr Passwort auf der Anmeldeseite ein.
2. Klicken Sie auf "Anmelden".


### Einen neuen Antrag stellen

1. Klicken Sie auf Ihrem Dashboard auf "NEUEN ANTRAG STELLEN".
2. Füllen Sie das Antragsformular aus.
3. Überprüfen Sie Ihre Angaben auf Richtigkeit.
4. Klicken Sie auf "SENDEN", um Ihren Antrag einzureichen.

### Ihre Anträge einsehen

1. Klicken Sie auf Ihrem Dashboard auf "MEINE ANTRÄGE".
2. Sie sehen eine Liste aller Ihrer eingereichten Anträge.
3. Klicken Sie auf einen Antrag, um weitere Details anzuzeigen.

### Kommunikation mit Sachbearbeitern

1. Finden Sie auf der Seite "Meine Anträge" den betreffenden Antrag.
2. Klicken Sie auf den "KONTAKTIEREN" Button neben dem Antrag.
3. Nutzen Sie das Chatfenster zur Kommunikation.

### Abmeldung

1. Klicken Sie auf den "Logout" Button in der oberen rechten Ecke einer beliebigen Seite.

## 5. Datenbankzugriff mit HeidiSQL

Für Administratoren oder Entwickler:

1. Öffnen Sie HeidiSQL.
2. Erstellen Sie eine neue Sitzung mit folgenden Details:
   - Netzwerktyp: MariaDB or MySQL (TCP/IP)
   - Hostname / IP: mysql-342fd6f5-treepermit1-be85.e.aivencloud.com
   - Port: 17827
   - Benutzername: avnadmin
   - Passwort: AVNS_Cs04FO1g2KMRBJkzPwh
3. Klicken Sie auf "Öffnen", um die Verbindung herzustellen.

**Hinweis:** Behandeln Sie diese Zugangsdaten vertraulich.

## 6. Häufig gestellte Fragen (FAQ)

F: Wie lange dauert die Bearbeitung meines Antrags?
A: Die meisten Anträge werden innerhalb von 5-10 Werktagen geprüft.

F: Kann ich meinen Antrag nach dem Absenden noch bearbeiten?
A: Nein, kontaktieren Sie in diesem Fall bitte einen Sachbearbeiter.

F: Was passiert, wenn mein Antrag abgelehnt wird?
A: Sie erhalten eine Benachrichtigung mit einer Erklärung und können einen neuen Antrag einreichen.

## 7. Fehlerbehebung

Bei Problemen:

1. Überprüfen Sie, ob Git korrekt installiert ist.
2. Stellen Sie sicher, dass Java 8 oder höher korrekt installiert ist und JAVA_HOME gesetzt ist.
3. Überprüfen Sie, ob ActiveMQ läuft.
4. Überprüfen Sie die Anwendungsprotokolle auf Fehlermeldungen.

Für weitere Unterstützung kontaktieren Sie bitte support@treepermitapp.com.
