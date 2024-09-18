#pragma once
#include "Item.hpp"
#include <iostream>
#include <vector> 
class Vorratskammer 
{
	private:
		std::vector<Item> vorraete;
	public:
		void ItemHinzufuegen();
		void ItemEntfernen();
		void ablaufDatumPruefen();
		void nachkaufWarnung();
		void datenSpeichern();
		void ladeDaten();
		void menuAnzeigen();
};
