#include "Vorratskammer.hpp"
#include <fstream>
#include <sstream>
#include <ctime>
#include <iomanip>

void Vorratskammer::menuAnzeigen()
{
	std::cout << "1. Lagerbestand" << std::endl;
	std::cout << "2. Neues Produkt eingeben" << std::endl;
	std::cout << "3. Produktbestand verringern" << std::endl;
	std::cout << "4. Geringer Bestand " << std::endl;
	std::cout << "5. Produkte, die innerhalb der nächsten 7 Tage ablaufen" << std::endl;
	std::cout << "6. Beenden des Programms" << std::endl;
}

void Vorratskammer::zeigeLagerbestand()
{
	// Öffnen der Datei "Vorratskammer.txt" im Lese-Modus
	std::ifstream file("Vorratskammer.txt");

	if (file.is_open())
	{
		std::cout << "===================================" << std::endl;

		// Einlesen jeder Zeile aus der Datei und Ausgabe auf der Konsole
		std::string line;
		while (std::getline(file, line))
		{
			std::cout << line << std::endl;
		}
		std::cout << "===================================" << std::endl;
	}
	else
	{
		std::cerr << "Fehler beim Öffnen der Datei Vorratskammer.txt" << std::endl;
	}
}

void Vorratskammer::datenSpeichern()
{
	// Öffnen der Datei "Vorratskammer.txt" im Schreib-Modus und überschreibt den Inhalt
	std::ofstream file("Vorratskammer.txt",std::ios::trunc);

		if (file.is_open())
		{
			// Schreiben der Lagerbestandsdaten	 in die Datei
			for (size_t i= 0; i < vorraete.size(); ++i)
			{
				file << vorraete[i].getName() << ";" << vorraete[i].getAblaufdatum() << ";" << vorraete[i].getAnzahl() << std::endl;
			}
			file.close();
		}
		else
		{
			std::cerr << "Fehler beim Öffnen der Datei zum Speichern der Daten." << std::endl;
		}
}

void Vorratskammer::ladeDaten()
{
	vorraete.clear();

	std::ifstream file("Vorratskammer.txt");
	if (file.is_open())
	{
		std::string line;
		while (std::getline(file, line))
		{
			// Trennen der Zeile in seperate Teile
			std::istringstream iss(line);

			// Deklaration von Variablen zur Speicherung der Produktdaten
			std::string name;
			int anzahl;
			std::string ablaufdatum;

			// Trennen der Zeile und Speichern der Daten in Variablen
			std::getline(iss, name, ';');
			std::getline(iss, ablaufdatum, ';');
			iss >> anzahl;

			// Erstellen eines neuen Produktobjekts mit den geladenen Daten
			Produkt produkt(name, anzahl, ablaufdatum);

			//Hinzufügen des erstellten Produktobjekts zum Lagerbestand
			vorraete.push_back(produkt);
		}
		file.close();
	}
	else
	{
		std::cerr << "Fehler beim Öffnen der Datei zum Laden der Daten." << std::endl;
	}
}

void Vorratskammer::produktHinzufuegen()
{
	// Deklaration von Variablen zur Speicherung der Benutzereingaben
	std::string name;
	int anzahl;
	std::string ablaufdatum;

	// Eingabe des Namens des neuen Produkts fordern und diese speichern
	std::cout << "Name des Produkts: ";
	std::cin >> name;

	// Eingabe des Ablaufdatums des neuen Produkts fordern und diese speichern
	std::cout << "Ablaufdatum (Format: dd.mm.yyyy, oder 'keines'): ";
	std::cin >> ablaufdatum;

	// Eingabe der Menge des neuen Produkts fordern und diese speichern
	std::cout << "Menge: ";
	std::cin >> anzahl;

	// Erstellen eines neues Produktobjekts mit den Benutzereingaben
	Produkt neuesProdukt(name, anzahl, ablaufdatum);

	// Hinzufügen des erstellten Produktobjekts mit den Benutzereingaben
	vorraete.push_back(neuesProdukt);
}

void Vorratskammer::produktEntfernen()
{
	try
	{
		std::string produktName;
		std::cout << "Name des zu entfernenden Produkts: ";
		std::cin >> produktName;

		// Variablen zur Speicherung der Benutzereingaben und Zwischenspeicherung von Daten
		int mengeZuEntfernen = 0;
		int neueAnzahl = 0;

		// Durchlaufen des Lagerbestands, um das gesuchte Produkt zu finden
		for (size_t i = 0; i < vorraete.size(); ++i)
		{
			// Überprüfen, ob das gesuchte Produkt im System ist
			if (vorraete[i].getName() == produktName)
			{

				std::cout << "Menge zu Entfernen: ";
				std::cin >> mengeZuEntfernen;

				// Überprüfen, ob genug Bestand vorhanden ist, um die angegebene Menge zu entfernen
				if (mengeZuEntfernen <= vorraete[i].getAnzahl())
				{
					// Berechnung der neuen Anzahl des Produkts
					neueAnzahl = vorraete[i].getAnzahl() - mengeZuEntfernen;
                    
					// Aktualisierung der Anzahl des Produkts im Lagerbestand
					vorraete[i].setAnzahl(neueAnzahl);
					std::cout << mengeZuEntfernen << " Einheiten von " << produktName << " entfernt." << std::endl;
					std::cout << "Übrige Anzahl: " << vorraete[i].getAnzahl() << std::endl;
                    
					// Wenn die Anzahl des Produkts nach der Entfernung 0 ist, wird das Produkt vollständig entfernt
					if (neueAnzahl == 0)
					{
						vorraete.erase(vorraete.begin() + i);
						std::cout << "Produkt " << produktName << " wurde entfernt" << std::endl;
					}
					return;
				}
				else
 				{
					throw std::runtime_error("Nicht genügend Bestand zum Entfernen.");
				}
			}
		}
		throw std::runtime_error("Produkt nicht im System gefunden.");
	}
	// Abfangen von Ausnahmen und Ausgabe entsprechender Fehlermeldungen
	catch (const std::exception& e)
	{
		std::cerr << "Fehler: " << e.what() << std::endl;
	}
}

void Vorratskammer::nachkaufWarnung()
{
	std::cout << "===================================" << std::endl;
	
	// Durchlaufen des Lagerbestands, um Produkte mit niedriger Anzahl zu finden
	for (size_t i= 0; i < vorraete.size(); ++i)
	{
		if (vorraete[i].getAnzahl() < 100)
		{
			// Ausgabe des Namens un der Anzahl des Produkts mit niedriger Anzahl
			std::cout << vorraete[i].getName() << " (" << vorraete[i].getAnzahl() << ")" <<  std::endl;
		}
	}
	std::cout << "===================================" << std::endl;
}

void Vorratskammer::ablaufdatumWarnung()
{
	std::cout << "===================================" << std::endl;

	// Aktuelle Zeit abrufen
	time_t now = time(0);

	// Durchlaufen des Lagerbestands, um Produkte mit nahendem Ablaufdatum zu finden
	for (size_t i= 0; i < vorraete.size(); ++i)
	{
		if (vorraete[i].getAblaufdatum() != "keines")
		{
			// Ablaufdatum des Produkts analysieren
			tm ablaufdatum = {};
			std::istringstream(vorraete[i].getAblaufdatum()) >> std::get_time(&ablaufdatum, "%d.%m.%Y");

			// Zeitpunkt des Ablaufdatums berechnen
			time_t ablaufzeit = mktime(&ablaufdatum);

			// Berechne die Differenz zwischen dem Ablaufdatum und dem aktuellen Datum
			double  differenz = difftime(ablaufzeit, now);

			//Überprüfe, ob die Differenz weniger als 7 Tage beträgt
			if (differenz < 7 * 24 * 3600)
			{
				std::cout << vorraete[i].getName() << ", am " << vorraete[i].getAblaufdatum() << std::endl;
			}
		}
	}
	std::cout << "===================================" << std::endl;
}
