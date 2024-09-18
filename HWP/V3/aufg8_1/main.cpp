#include <iostream>
#include <b15f/b15f.h>
#include <b15f/plottyfile.h>
#include <sstream>
#include <string>
#include <fstream>

void aufgabe8_1() {


std::string bauteil = "aufg8_1.txt";
std::string ausgabe = "Gatterlaufzeit_" + bauteil;

std::ifstream infile(bauteil);
std::ofstream outfile(ausgabe);

int volt = 0;

    std::string line;
    while (std::getline(infile, line)) {

        //Gl = Gatterlaufzeit
        double drei_Gl = std::stod(line);
        double einzelne_Gl = drei_Gl/3;

        outfile << volt << ";" << einzelne_Gl << std::endl;

        volt++;
    }
    infile.close();
    outfile.close();


}
int main() {
    
    aufgabe8_1();
}
