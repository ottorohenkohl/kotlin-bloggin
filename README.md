# Bloggin

[![pipeline status](http://sources.rohenkohl.dev/entwicklung/kotlin/bloggin/badges/main/pipeline.svg)](http://sources.rohenkohl.dev/entwicklung/kotlin/bloggin/-/commits/main) [![coverage report](http://sources.rohenkohl.dev/entwicklung/kotlin/bloggin/badges/main/coverage.svg)](http://sources.rohenkohl.dev/entwicklung/kotlin/bloggin/-/commits/main) [![Latest Release](http://sources.rohenkohl.dev/entwicklung/kotlin/bloggin/-/badges/release.svg)](http://sources.rohenkohl.dev/entwicklung/kotlin/bloggin/-/releases)

Das **Bloggin** backend ist die serverseitige Komponente für das Bloggin CMS. Als **modulares Multi-Module Gradle-Projekt** aufgebaut, folgt es einer **strikten Architektur** für eine flexible, erweiterbare und gut wartbare Codebasis.

## 1. Projektbeschreibung

Das Backend für Bloggin ist hochgradig modular und basiert auf **Quarkus**. Ziel ist es, eine flexible, skalierbare und wartbare Backend-Struktur für das CMS bereitzustellen, die verschiedene CMS-Funktionalitäten durch REST-APIs ermöglicht. Die Architektur trennt Verantwortlichkeiten strikt und stellt sicher, dass Services und Geschäftslogik unabhängig von REST-Implementationen bleiben.

### Architekturüberblick

Die Architektur ist stark modularisiert und durch eine klare Abhängigkeitshierarchie strukturiert. Für die Gleiderung werden Gradle-Module nach Features verwendet. Dafür wird vor Allem auf Kotlins internen Sichtbarkeitsmodifizierer gesetzt.

## 2. Installation/Einrichtung

### Grundlegende Voraussetzungen

Um das Projekt lokal auszuführen, wird ein PostgreSQL-Datenbankserver benötigt, der standardmäßig auf `localhost:5432` unter folgenden Einstellungen erreichbar sein sollte:

- **Datenbankname**: `quarkus`
- **Benutzername**: `quarkus`
- **Passwort**: `quarkus`

Diese Standardeinstellungen können durch Anpassen der `application.properties` im Application-Modul geändert werden, falls eine andere Datenbank oder andere Zugangsdaten genutzt werden sollen.

### Starten im Entwicklungsmodus

Das Bloggin-Backend kann im Entwicklungsmodus gestartet werden, um Hot-Reloading und andere Entwicklertools zu nutzen:

```bash
./gradlew application:quarkusDev
```

Im Entwicklungsmodus läuft der Server standardmäßig unter `localhost:8080`. Das **Swagger-UI** ist über `http://localhost:8080/q/swagger-ui` zugänglich und zeigt eine Übersicht aller verfügbaren REST-API-Endpunkte an. Hier lassen sich alle verfügbaren APIs testen und Dokumentationen einsehen.

## 3. Nutzung/Beispiele

Das Backend stellt REST-APIs bereit, die die Inhalte und Strukturen des Bloggin CMS steuern. Verschiedene bewährte Java-Technologien kommen zum Einsatz, darunter:

- **Quarkus RESTEasy** für die Implementierung und Verwaltung der REST-APIs.
- **Jackson** für die JSON-Serialisierung und -Deserialisierung.
- **Hibernate ORM und Validator** zur Verwaltung und Validierung der Entitäten.

### Beispiel: Abfrage und Verwaltung von Ressourcen

Um Ressourcen über die API zu testen oder Datenstrukturen und -antworten einzusehen, können Sie das Swagger-UI nutzen, das alle implementierten REST-Ressourcen vollständig dokumentiert.

1. **Swagger öffnen**: Besuchen Sie [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui).
2. **Endpunkt aufrufen**: Wählen Sie den gewünschten Endpunkt aus und führen Sie die Anfrage direkt über die Benutzeroberfläche aus.
3. **Antwort überprüfen**: Die JSON-Antworten zeigen die entsprechenden DTO-Formate an, und Fehlermeldungen werden durch die Exception-Handler aufbereitet.

### Datenbankwechsel

Standardmäßig wird PostgreSQL verwendet, aber jede JDBC-kompatible Datenbank kann eingebunden werden, indem der entsprechende JDBC-Treiber in den `build.gradle`-Dateien und die Einstellungen in `application.properties` angepasst werden.

### Container Images

Container-Images werden in der **Docker-Registry unter `archive.rohenkohl.dev`** abgelegt. Diese sind unter den GitLab-Releases einsehbar und für verschiedene Umgebungen bereitgestellt.

## 4. Weiterführende Links

- [Quarkus Dokumentation](https://https://quarkus.io/guides/) – Detaillierte Anleitungen und Konfigurationsoptionen für Quarkus.
- [Hibernate](https://hibernate.org/) – Details zum ORM und Validierungsframework.