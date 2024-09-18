#pragma once
#include "Produkt.hpp"
#include <iostream>
#include <vector>
/**
 * @brief Die Klasse 'Vorratskammer' reprästentiert eine Lagerhaltung von Produkten und bietet Funktionen zum Hinzufügen, Entfernen und Verwalten von Produkten sowie zum Anzeigen von Warnungen bezüglich des Ablaufdatums oder niedriger Lagerbestände.
 * @details Die Klasse 'Vorratskammer' hat folgende Attribute und Methoden:
 * #1 'vorraete': Ein Vektor von Produktobjekten, der die Lagerbestände in der Vorratskammer speichert.
 * #2 'instance': Ein statisches Singleton-Objekt, das eine Instanz der Vorratskammer repräsentiert.
 * #3 'getInstance': Eine statische Methode, die die Instanz der Vorratskammer abruft. Sie verwendet das Singleton-Muster, um sicherzustellen, dass nur eine Instanz der Vorratskammer existiert.
 */
class Vorratskammer
{
private:
	std::vector<Produkt> vorraete;

	static Vorratskammer* instance;

public:
	static Vorratskammer* getInstance()
	{
		if (!instance)
		{
			instance = new Vorratskammer();
		}
		return instance;
	}

    /**
     * @brief Die Funktion 'menuAnzeigen()' dient dazu, das Hauptmenü des Programms anzuzeigen. Es listet die verschiedenen Optionen auf, die dme Benutzer zur Verfügung stehen
     * @details Die Funktion gibt die verschiedenen Optionen auf der Standardausgabe aus, die dem Benutzer angezeigt werden sollen. Jede Option ist durchnummeriert und ist mit einer kurzen Beschreibung versehen.
     */
    void menuAnzeigen();
    /**
     * @brief Die Funktion 'zeigeLagerbestand()' dient dazu, den akutellen Lagerbestand aus der Textdatei "Vorratskammer.txt" auf der Konsole anzuzeigen
     * @details Öffnen der Datei "Vorratskammer.txt" im Lesemodus und überprüft, ob die Datei erfolgreich geöffnet wurde. Liest bei erfolgreichen Öffnen jede Zeile aus der Datei und gibt diese in der Konsole aus. Vor und nach der Ausgabe wird ein Trennstrich ausgegeben, um den Lagerbestand visuell vom restlichen Ausgabefenster zu trennen. Wenn das Öffnen der Datei fehlschlägt, erhält man eine Fehlermeldung.
     */
    void zeigeLagerbestand();
    /**
     * @brief Die Funtion 'datenSpeichern()' dient dazu, den aktuellen Lagerbestand in die Textdatei "Vorratskammer.txt" zu speichern.
     * @details Eine Instanz von 'std::ofstream' mit dem Dateinamen "Vorratskammer.txt" wird erstellt, um die Datei um Schreib-Modus zu öffnen. Das Flag 'std::io::trunc' wird verwendet, um den Inhalt der Datei zu löschen falls sie bereits existiert. Die 'is_open()' Methode wird verwendet, um zu überprüfen, ob die Datei erfolgreich geöffnet wurde. Wenn die Datei erfolgreich geöffnet wurde, werden die Lagerbestandsdaten in die Datei geschrieben. Für jedes Produkt im Lagerbestand werden der Name, das Ablaufdatum und die Anzahl in dei Datei geschrieben, durch Semikolons getrennt. Nach dem Schreiben aller Daten werden wird die Datei geschlossen. Wenn das Öffnen der Datei fehlschlägt, wird eine Fehlermeldung auf der Standardausgabe ausgegeben.
     */
    void datenSpeichern();
    /**
     * @brief Die Funktion 'ladeDaten()' ist dafür verantworlich, Daten aus der Textdatei 'Vorratskammer.txt' zu laden und sie dem Lagerbestand der Vorratskamer hinzuzufügen.
     * @details Löscht den aktuellen Lagerbestand 'vorraete' mit 'clear()' um Platz für die geladenen Daten zu schaffen. Öffnet die Datei "Vorratskammer.txt" im Lese-Modus. Überprüft, ob die Datei erfolgreich geöffnet wurde- Wenn die Datei erfolgreich geöffnet wurde, werden die Daten aus der Datei eingelesen und dem Lagerbestand zugefügt. Jede Zeile aus der Datei wird einzeln eingelesen und die einzelne Teile werden mit Hilfe von 'std::istringstream' in seperate Variablen gespeichert. Mithilfe der eingelesenen Daten werden neue Produktobjekte erstellt und diese dem Lagerbestand hinzugefügt. Wenn das Öffnen der Datei fehlschlägt, wird eien Fehlermeldung ausgegeben.
     */
    void ladeDaten();
    /**
     * @brief Die Funktion 'produktHinzufuegen()' ermöglicht dem Benutzer, ein neues Produkt mit Name, Ablaufdatum und Anzahl hinzuzufügen.
     * @details Der Benutzer wird aufgefordert, den Namen, das Ablaufdatum und die Menge des neuen Produkts einzugeben. Diese Eingaben werden mit 'std::cin' eingelesen und in den entsprechenden Variablen gespeichert. Mit den Benutzereingaben wird ein neues Produktobjekt 'neuesProdukt' erstellt, indem der Konstruktor der Klasse 'Produkt' aufgerufen wird. Das erstelle Produktobjekt 'neuesProdukt' wird dem Lagerbestand 'vorraete' hinzugefügt, indem es mit der Methode 'push_back()' dem Vektor hinzugefügt wird.
     */
    void produktHinzufuegen();
    /**
     * @brief ie Funktion 'produktEntfernen()' ermöglicht dem Benutzer, ein bestimmtes Produkt aus dem Lagerbestand zu entfernen, wobei die Anzahl der zu entfernenden Einheiten berücksichtigt wird.
     * @details Der Benutzer wird aufgefordert, den Namen des zu entfernenden Produkts und die Menge, die entfernt werden soll, einzugeben. Der Lagerbestand wird durchlaufen, um das gesuchte Produkt zu finden. Wenn das Produkt gefunden wird, wird die angegebene Menge vom Bestand subtrahiert und die entsprechende Rückmeldung ausgegeben. Wenn die Anzahl des Produkts nach der Entfernung 0 ist, wird das Produkt vollständig aus dem Lagerbestand entfernt. Wenn das gesuchte Produkt nicht gefunden oder nicht genügend Bestand zum Entfernen vorhanden ist, wird die entsprechende Fehlermeldung mit 'throw' generiert und mit 'catch' abgefangen, um eine ungewollte Endlossschleife zu verhindern.
     */
    void produktEntfernen();
    /**
     * @brief Die Funktion 'nachkaufWarnung()' gibt eine Liste aus, welche alle Produkte ausgibt, dessen Menge unter den Schwellenwert von 100 liegt.
     * @details Die Funktion durchläuft den ganzen Lagerbestand, indem sie jedes Produkt überprüft. Für jedes Produkt im Lagerbestand wird überprüft, ob die Anzahl unter dem Schwellenwert von 100 liegt. Wenn die Anzahl eines Produkts unter dem Schwellenwert leigt, wird der Name des Produkts zusammen mit seiner aktuellen Anzahl ausgegeben. Vor und nach der Ausgabe wird ein Trennstrich ausgegeben, um die Ausgabe visuell vom restlichen Ausgabefenster zu trennen.
     */
    void nachkaufWarnung();
    /**
     * @brief Die Funktion 'ablaufdatumWarnung' gibt eine List aus, welche alle Produkte ausgibt, dessen Ablaufdatum in 7 Tagen oder früher ist.
     * @details Die Funktion ruft die aktuelle Zeit mit 'time(0)' ab. Die Funktion durchläuft den gesamten Lagerbestand, indem sie jedes Produkt überprüft, für jedes Produkt im Lagerbestand wird überprüft, ob es ein gültiges Ablaufdatum hat( nicht 'keines'). Wenn das Produkt ein gültiges Ablaufdatum hat, wird die Differenz zwischen dem Ablaufdatum und der aktuellen Zeit berechnet. Wenn die Differenz weniger als 7 Tage beträgt, wird der Name und das Ablaufdatum des Produkts ausgegeben. Vor und nach der Ausgabe wird ein Trennstrich ausgegeben, um die Ausgabe visuell vom restlichen Ausgabefenster zu trennen.
     */
    void ablaufdatumWarnung();
};
