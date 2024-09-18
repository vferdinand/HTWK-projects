#include <iostream>
#include "Vorratskammer.hpp"
#include <limits>

int main()
{
	Vorratskammer v1;

	int auswahl;
	
	do
		{
		v1.menuAnzeigen();
		std::cin >> auswahl;
		
		switch (auswahl)
			{
			case 1: //Lagerbestand
				v1.zeigeLagerbestand();
				break;
			case 2: //neues Produkt eingeben
				v1.ladeDaten();
				v1.produktHinzufuegen();
				v1.datenSpeichern();
				break;
			case 3: //bestehendes Produkt entfernen
				v1.ladeDaten();
				v1.produktEntfernen();
				v1.datenSpeichern();
				break;
			case 4: //Bestandswarnung
				v1.ladeDaten();
				v1.nachkaufWarnung();
				break;
			case 5: //Ablaufwarnung
				v1.ladeDaten();
				v1.ablaufdatumWarnung();
				break;
			case 6:
				std::cout << "Programm wird beendet!" << std::endl;
				break;
			default:
				// Zurücksetzen des Fehlerstatus des Eingabestream und Löschen des Pufferinhalts
				std::cout << "Ungültige Auswahl. Bitte erneut versuchen." << std::endl;
				std::cin.clear();
				std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
				break;
			}
		} 
		while (auswahl != 6);

		return 0;
}

