#pragma once
#include <string>
#include <iostream>
class Item
{
private:
	std::string name;
	int anzahl;
	std::string ablaufdatum;
public:
	Item();
	Item(std::string name, int anzahl, std::string ablaufdatum);
	void setName(const std::string& newName);
	void setAnzahl(int newAnzahl);
	void setAblaufdatum(const std::string& newAblaufdatum);
	std::string getName() const;
	int getAnzahl() const;
	std::string getAblaufdatum() const;
};

