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
double leistungsaufnahmediode (double aa0, double ergebnis){
    return(- ergebnis + aa0);
}
double strom (double ergebnis) {
    return (ergebnis/1000.0);
}



int main() {

    B15F& drv = B15F::getInstance();
    std::ofstream datei("werte_D2.txt", std::ios::out);
    datei.is_open();

    double aa0 = 0.0;
    double ergebnis;

    while(true) {
        do {
            drv.analogWrite0(umrechnen(aa0));
            ergebnis = zurueckrechnen(drv.analogRead(0));
            datei << leistungsaufnahmediode(aa0,ergebnis) << ";" << strom(ergebnis) << std::endl;
            aa0 += 0.1;
        } while (aa0 <= 5);                               // anpassung je nach diode
    datei.close();
    break;
    }
}