#include <iostream>
#include <b15f/b15f.h>
#include <b15f/plottyfile.h>
#include <sstream>
#include <string>
#include <fstream>

double to_volt (int zahl_Int) {
    return (5.0 / 1023.0) * zahl_Int;
}
int to_int(double zahl_Volt) {
    return (1023.0 / 5.0) * zahl_Volt;
}

void aufgabe7_Umrechnen() {


std::string bauteil = "U74HC00L.txt";
std::string ausgabe = "Leistung_" + bauteil;

std::ifstream infile(bauteil);
std::ofstream outfile(ausgabe);

int i = 1;

    std::string line;
    while (std::getline(infile, line)) {

        double messwert_mA = std::stod(line);
        double messwert_A = messwert_mA/1000;
        double leistung_W = messwert_A*5;

        outfile << i << ";" << leistung_W << std::endl;

        i++;
    }
    infile.close();
    outfile.close();


}
int main() {
    
    aufgabe7_Umrechnen();
}
