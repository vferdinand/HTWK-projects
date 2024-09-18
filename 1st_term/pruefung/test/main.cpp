#include <iostream>
#include <vector>
#include "Item.hpp"
#include "Vorratskammer.hpp"
//Funktion um das Menü anzuzeigen
void visualmenu()
{
	std::cout << "1. Lagerbestand" << std::endl; //les alle daten aus einer datei heraus
	std::cout << "2. Neue Ware eingeben" << std::endl; //speicher daten in einer datei ab
	std::cout << "3. Ware entfernen" << std::endl; //entferne daten aus einer datei
	std::cout << "4. Geringer Bestand" << std::endl; // daten mit einer menge <100
	std::cout << "5. Ware die in weniger als einer Woche abläuft" << std::endl; //ware dessen haltbarkeitsdatum näher als eine woche ist
	std::cout << "6. Beenden des Programms" << std::endl;
}

int main()
{
	Vorratskammer v1;
	//Item i("Bockwurst",7,"keines");
	v1.ladeDaten();
	v1.ItemHinzufuegen();
	v1.datenSpeichern();
//	v1.ItemEntfernen();
	/*int auswahl;
	
	do
		{
		//Menü anzeigen
		visualmenu();
		std::cin >> auswahl;
		
		switch (auswahl)
			{
			case 1:
				std::cout << "Gib den Lagerbestand an" << std::endl;
				// Code
				break;
			case 2:
				std::cout << "Gib neue Ware in das System ein" << std::endl;
				//Code
				break;
			case 3:
				std::cout << "Entferne Ware aus dem System" << std::endl;
				//Code
				break;
			case 4:
				std::cout << "Geringer Bestand" << std::endl;
				//Code
				break;
			case 5:
				std::cout << "Ware die in weniger als einer Woche abläuft" << std::endl; 
				//Code
				break;
			case 6: 
				std::cout << "Programm wird beendet!" << std::endl;
				break;
			default:
				std::cout << "Ungültige Auswahl. Bitte erneut versuchen." << std::endl;
				break;
			}
		} 
		while (auswahl != 6);
				// Schleife wiederholen, bis der Nutzer das Programm beendet		
		return 0; */
}

