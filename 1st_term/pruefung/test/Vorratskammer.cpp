#include "Vorratskammer.hpp"
#include <iostream>
#include <fstream>
#include <sstream>

void Vorratskammer::ItemHinzufuegen()	//fügt neues Item mit Anzahl und Ablaufdatum
{
	std::string name;
	int anzahl;
	std::string ablaufdatum;
	
	std::cout << "Name des Produkts: ";
	std::cin >> name;
	
	std::cout << "Ablaufdatum (Format: dd.mm.yyyy, oder 'keines'): ";
	std::cin >> ablaufdatum;
	
	std::cout << "Menge: ";
	std::cin >> anzahl;
	
	Item neuesItem(name, anzahl, ablaufdatum);
	
	vorraete.push_back(neuesItem);
}	
void Vorratskammer::datenSpeichern()
{
 std::ofstream file("Vorratskammer.txt",std::ios::trunc); //löscht die komplette Datei und öffnet siess
    if (file.is_open()) {
        for (const Item& item : vorraete) {
            file << item.getName() << ";" << item.getAblaufdatum() << ";" << item.getAnzahl() << std::endl;
        }
        file.close();
        std::cout << "Daten erfolgreich gespeichert." << std::endl;
    } else {
        std::cout << "Fehler beim Öffnen der Datei zum Speichern der Daten." << std::endl;
	}
}

void Vorratskammer::ladeDaten() {
    vorraete.clear();  // Leert den aktuellen Lagerbestand, um die Daten aus der Datei zu laden.

    std::ifstream file("Vorratskammer.txt");
    if (file.is_open()) {
        std::string line;
        while (std::getline(file, line)) {
            std::istringstream iss(line);
            
          	std::string name;
		int anzahl;
		std::string ablaufdatum;
			
            std::getline(iss, name, ';');
            std::getline(iss, ablaufdatum, ';');
            iss >> anzahl;
            Item item(name, anzahl, ablaufdatum);

            vorraete.push_back(item);
        }
        file.close();
        std::cout << "Daten erfolgreich aus Datei geladen." << std::endl;
    } else {
        std::cout << "Fehler beim Öffnen der Datei zum Laden der Daten." << std::endl;
    }
}


/*void ItemEntfernen(std::vector<Item>& vorraete) {
    std::string ItemName;
    std::cout << "Name der zu verbrauchenden Ware: ";
    std::cin >> ItemName;

	
    for (auto& item : vorraete) {
    
    std::string name;
	int anzahl;
	std::string ablaufdatum;
    
        if (Item.name == Itemname) {
            std::cout << "Menge zu Entfernen: ";
            int MengezuEntfernen;
            std::cin >> MengezuEntfernen;

            if (MengezuEntfernen <= item.anzahl) {
                item.anzahl -= MengezuEntfernen;
                std::cout << MengezuEntfernen << " Einheiten von " << itemName << " entfernt." << std::endl;
            } else {
                std::cout << "Nicht genügend Bestand zum Entfernen." << std::endl;
            }

            return;
        }
    }

    std::cout << "Produkt nicht gefunden." << std::endl;
  
}*/
/*
		void ablaufDatumPruefen();
		void nachkaufWarnung();
		void menuAnzeigen();*/
