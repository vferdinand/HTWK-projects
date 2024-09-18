#pragma once
#include <string>
/**
 * @brief Die Klasse 'Produkt' repräsentiert ein Produkt mit einem Namen, einer Anzahl und einem Ablaufdatum. Sie bietet Methoden zum Setzen und Abrufen dieser Attribute.
 * @details Die Klasse 'Produkt' hat drei private Attribute:
 * #1 'name': Eine Zeichenfolge, welche den Namen des Produkts speichert.
 * #2 'anzahl': Eine Ganzzahl, welche die Anzahl des Produkts speichert.
 * #3 'ablaufdatum': Eine Zeichenfolge, welche das Ablaufdatum des Produkts speichert.
 * Die Klasse enthält einen Konstruktor, der die Attribute des Produkts initialisiert. Die Parameter des Konstruktors sind Referenzen auf Zeichenfolgen und Referenz auf eine Ganzzahl.
 * Die Klasse 'Produkt' bietet folgende Methoden:
 * #1 'getName() const': Eine Konstantenmethode, die den Namen des Produkts zurückgibt. Sie gibt eine Konstantenreferenz auf eine Zeichenfolge zurück und ermöglicht es, den Namen des Produkts zu lesen ohne sie zu verändern.
 * #2 'setAnzahl(int& newAnzahl)': Eine Methode zum Setzen der Anzahl des Produkts. Sie erhält eine Referenz auf eine Ganzzahl 'newAnzahl' und aktualisiert das Attribut 'anzahl' des Produkts entsprechend.
 * #3 'getAnzahl() const': Eine Konstantenmethode, die die Anzahl des Produkts zurückgibt. Sie gibt eine Ganzzahl zurück und ermöglicht es, die Anzahl des Produkts zu lesen ohne sie zu verändern.
 * #4 'getAblaufsdatum() const': Eine Konstantenmethode, die das Ablaufdatum des Produkts zurückgibt. Sie gibt eine Konstantenreferenz auf eine Zeichenfolge zurück und ermöglicht es, das Ablaufdatum des Produkts zu lesen, ohne es zu verändern.
 * Die Klasse 'Produkt' bietet somit eine einfache Möglichkeit, Produkte zu repräsentieren und auf ihre Attribute zuzugreifen.
 */
class Produkt
{
	private:
		std::string name;
		int anzahl;
		std::string ablaufdatum;
	public:
		Produkt(std::string& name, int& anzahl, std::string& ablaufdatum);
		std::string getName() const;
		void setAnzahl(int& newAnzahl);
		int getAnzahl() const;
		std::string getAblaufdatum() const;
};

