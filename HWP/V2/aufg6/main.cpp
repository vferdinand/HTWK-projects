#include <iostream>
#include <fstream>
#include <b15f/b15f.h>
#include <b15f/plottyfile.h>

double umrechnen (double zahl) {
    return(1023 / 5.0) * zahl;
}
double zurueckrechnen (double zahl) {
    return(5.0 / 1023) * zahl;
}

int main() {

    B15F& drv = B15F::getInstance();
    std::ofstream datei("werteLED.txt", std::ios::out); //werteLED.txt; werte1k.txt; werte100.txt
    datei.is_open();

    double aa0 = 0.0;
    double ergebnis;

    while(true) {
        do {
            drv.analogWrite0(umrechnen(aa0));
            ergebnis = zurueckrechnen(drv.analogRead(0));
            datei << aa0 << ";" << ergebnis << std::endl;
            aa0 += 0.1;
        } while (aa0 <= 5.0);
    datei.close();
    break;
    }
}
