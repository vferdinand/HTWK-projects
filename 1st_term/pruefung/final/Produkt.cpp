#include"Produkt.hpp"
/**
 * @brief Konstruktor für ein neues Produkt mit angegebenen Eigenschaften.
 * @param newName Der Name des neuen Produkts.
 * @param newAnzahl Die Anzahl des neuen Produkts.
 * @param newAblaufdatum  Das Ablaufdatum des neuen Produkts.
 */
Produkt::Produkt(std::string& newName, int& newAnzahl, std::string& newAblaufdatum)
{
	name = newName;
	anzahl = newAnzahl;
	ablaufdatum = newAblaufdatum;
}
/**
 * @brief Setzt die Anzahl des Produkts auf den angegebenen Wert.
 * @param newAnzahl Die neue Anzahl, die für das Produkt festgelegt werden soll.
 */
void Produkt::setAnzahl(int& newAnzahl)
{
	anzahl = newAnzahl;
}
/**
 * @brief Gibt den Namen des Produkts zurück.
 * @return Den Namen des Produkts als Zeichenkette.
 */
std::string Produkt::getName() const
{
	return name;
}
/**
 * @brief Gibt die Anzahl des Produkts zurück.
 * @return Die Anzahl des Produkts als Ganzzahl.
 */
int Produkt::getAnzahl() const
{
	return anzahl;
}
/**
 * @brief Gibt das Ablaufdatum des Produkts zurück.
 * @return Das Ablaufdatum des Produkts als Zeichenkette.
 */
std::string Produkt::getAblaufdatum() const
{
	return ablaufdatum;
}
