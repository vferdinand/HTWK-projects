#include"Item.hpp"
#include <iostream>
Item::Item(std::string newName, int newAnzahl, std::string newAblaufdatum)
{
	name = newName;
	anzahl = newAnzahl;
	ablaufdatum = newAblaufdatum;
}

void Item::setName(const std::string& newName) {
        name = newName; //newname = this.name
    }

void Item::setAnzahl(int newAnzahl) {
        anzahl = newAnzahl;
    }

void Item::setAblaufdatum(const std::string& newAblaufdatum) {
        ablaufdatum = newAblaufdatum;
    }

std::string Item::getName() const {
        return name;
    }
    
    int Item::getAnzahl() const {
        return anzahl;
    }

    std::string Item::getAblaufdatum() const {
        return ablaufdatum;
    }

