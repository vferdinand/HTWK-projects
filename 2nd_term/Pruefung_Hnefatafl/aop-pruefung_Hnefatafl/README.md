# Hinweise
Folgendes Dokument dient als _grobe_ Orientierung. Es muss nicht alles 1:1 so umgesetzt werden, je näher wir jedoch dort herankommen, desto weniger Probleme werden wir haben.
## Code
### Allgemein
- **Java-Version: JDK11**
- Kommentare in deutsch
- Bezeichner in englisch
- **Klassendiagramm immer entsprechend dem main-Branch aktuell halten** ([Draw.io](https://draw.io))

### Style
- maximal 2x einrücken
- Autoformatierungen sind ein eigenständiger commit ([siehe Besondere Commits](#besondere-commits))
- Code Formatierung gemäß [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

### Dokumentation
- muss gemacht werden

## Git-Repo
### Commits
Commits haben stets eine aussagekräftige und prägnante Kurzbeschreibung (Commit-Bezeichnung). Falls viele Änderungen vorgenommen wurden, welche nicht in die Commit-Bezeichnung passen, ist die Commit-Beschreibung zu nutzen.
- Ein Commit sollte eine logische Einheit darstellen.
- Kleine und häufige Commits sind besser als seltene und große.
- Nutze imperative Sprache (z.B., "Füge Funktion hinzu" statt "Funktion hinzugefügt").

### Besondere Commits
**Autoformatierungen des Codes** sind eigenständige Commits. Grund dafür ist, dass beim automatischen Formatieren jede Zeile Code verändert wird. Im Commit sieht es dadurch so aus, als würden alle früheren Zeilen gelöscht und durch neue Zeilen ersetzt. Dies macht die tatsächlichen Änderungen am Sourcecode unnachvollziehbar und merge-Konflikte unlösbar. In einem Commit, in welchem die automatische Formatierung genutzt wird, hat diese Formatierung also die einzige vorgenommene Änderung zu sein.

### Branching (OPTIONAL)
Ein effektives Branching-Modell hilft, die Arbeit zu organisieren und Konflikte zu minimieren. In unserem Projekt verwenden wir folgende Branches:

#### Main Branch
- **main**: Dieser Branch enthält den stabilen und aktuellen Stand des Projekts. Der Code im main-Branch muss immer kompilierbar und ausführbar sein und darf keinen temporären oder unfertigen Code enthalten.

Der main branch ist jederzeit perfekt. Das bedeutet er:
- ist immer kompilierbar und ausführbar
- enthält keinen temporären Sourcecode
- enthält keinen auskommentierten Code "für später"
- enthält keine halbfertigen Methoden

#### Feature Branches
- **Feature Branches**: Diese Branches werden für die Entwicklung neuer Funktionen verwendet. Jede neue Funktion oder größere Änderung sollte in einem eigenen Feature-Branch entwickelt werden.
- Namenskonvention: `feature/<beschreibung>` (z.B., `feature/login-system`)
- Ziel: Nach Fertigstellung in den `main`-Branch mergen.
- Beispiel: `feature/add-user-authentication`

#### Bugfix Branches
- **Bugfix Branches**: Diese Branches werden zur Behebung von Fehlern verwendet, die im `main`-Branch gefunden wurden.
- Namenskonvention: `bugfix/<beschreibung>` (z.B., `bugfix/fix-login-error`)
- Ziel: Nach Fertigstellung in den `main`-Branch mergen.
- Beispiel: `bugfix/resolve-null-pointer-exception`

### Branching Workflow (z.B. Feature-Branch)
1. **Erstellen eines Feature-Branch**
- Ausgehend vom `main`-Branch einen neuen Feature-Branch erstellen:
```
git checkout main
git pull
git checkout -b feature/<beschreibung>
```

2. **Arbeiten im Feature-Branch**
- Änderungen vornehmen und regelmäßig committen:
```
git add .
git commit -m "Aussagekräftige Beschreibung der Änderung"
```

3. **Mergen eines Feature-Branch**
- Vor dem Mergen sicherstellen, dass der `main`-Branch aktuell ist:
```
git checkout main
git pull
```

- Den Feature-Branch in den `main`-Branch mergen:
```
git checkout main
git merge --no-ff feature/<beschreibung>
```

### Best Practices
- **Regelmäßig synchronisieren**: Regelmäßig `main` in die Feature-Branches mergen, um Konflikte frühzeitig zu erkennen.
- **Kleine, häufige Commits**: Erleichtert das Reviewen und Nachvollziehen von Änderungen.
- **Pull Requests (PRs)**: Verwende PRs für das Mergen von Feature- und Bugfix-Branches in `main`. Jede PR sollte von mindestens einem anderen Teammitglied überprüft werden.
- **Konflikte vermeiden**: Konflikte direkt nach dem Auftreten lösen und nicht aufschieben.
- **Branch löschen**: Nach dem erfolgreichen Mergen einen Feature- oder Bugfix-Branch löschen, um das Repository sauber zu halten.

### Merges
Das Standardverhalten von `git pull` ist auf `ff-only` zu setzen. Dazu muss der Befehl
```
git config pull.ff only
```
im Git Repository ausgeführt werden.

Konflikte beim Mergen sind durch manuelle Überprüfung zu lösen. **Unter keinem Umstand sollte ein Push geforced werden!**
