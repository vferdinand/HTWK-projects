#include <b15f/b15f.h>
#include <string>
#include <array>
#include <iostream>
#include <fstream>
#include <cstdint>
#include <cmath>
#include <vector>
#include <iomanip>
#include <vector>

using namespace std;
B15F &drv = B15F::getInstance();

string toBinary(int n)
{
    string r;
    while (n != 0)
    {
        r = (n % 2 == 0 ? "0" : "1") + r;
        n /= 2;
    }
    return r;
}



int main (){


    // Die Anzahl der zu testenden Ausgänge abfragen
    int max = 4;
    cout << "\n\n";

    for (int i = 0; i < max; i++)
    {
        // Aktuellen Testausgang konvertieren
        string bin = toBinary(i);

        // Führende Nullen ergänzen
        while (bin.length() < (log(max) / log(2)))
        {
            bin = bin.insert(0, "0");
        }

        // Seperator für die Darstellung einfügen
        int length = bin.length() * 4 - 3;
        for (int j = 0; j < length; j += 4)
        {
            bin.insert(j, " | ");
        }

        // Tabellenkopf erzeugen
        if (i == 0)
        {
            string letters = " | a | b | c | d | e | f | g | h |";
            string header = letters.substr(0, bin.length() + 2);
            cout << header << "| A |\n" << endl;
        }

        // Ausgang auf Eingang testen
        drv.digitalWrite0(i);
        int input = (int) drv.digitalRead1();       // achtung nur wenn eingang 1 verwendet wird --> sonst anpassen

        // Ergebnis ausgeben
        cout << bin << " || " << input << " |" << endl;
    }
}