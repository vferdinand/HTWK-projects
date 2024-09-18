#include <iostream>
#include <b15f/b15f.h>
#include <b15f/plottyfile.h>
#include <sstream>
#include <string>
#include <fstream>

void aufgabe7_4() {


std::string bauteil = "aufg7_4.txt";
std::string ausgabe = "Leistung_" + bauteil;

std::ifstream infile(bauteil);
std::ofstream outfile(ausgabe);

int volt = 0;

    std::string line;
    while (std::getline(infile, line)) {

        double messwert_mA = std::stod(line);
        double messwert_A = messwert_mA/1000;
        double leistung_W = volt * messwert_A;

        outfile << volt << ";" << leistung_W << std::endl;

        volt++;
    }
    infile.close();
    outfile.close();


}
int main() {
    
    aufgabe7_4();
}
