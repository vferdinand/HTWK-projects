#include <iostream>
#include <fstream>
#include <b15f/b15f.h>
#include <b15f/plottyfile.h>
#include <array>

double to_volt (int zahl_Int) {
    return (5.0 / 1023.0) * zahl_Int;
}
int to_int(double zahl_Volt) {
    return (1023.0 / 5.0) * zahl_Volt;
}

double to_stromstaerke(double ae3_Int, double ae2_Int) {
    return (to_volt(ae3_Int - ae2_Int) / 1000.0);
}


int main() {

    B15F& drv = B15F::getInstance();
    
  //  datei << "Grundspannung" << "," << "ae0" << "," << "ae1" << "," << "I_Drain" << std::endl;

    std::array<double, 7> aa0_Volt = {0.0,1.0,2.0,2.5, 3.0, 4.0 ,5.0};

    for (int i  = 0; i < aa0_Volt.size();i++)
    {  
        std::ofstream datei("8_4werte" + std::to_string(i) + ".txt", std::ios::out);

        std::cout << to_int(aa0_Volt.at(i)) << std::endl;
        std::cout << i << "\t" << aa0_Volt[i] << std::endl;
        
        drv.analogWrite1(to_int(aa0_Volt[i]));

        for (int j = 0;j <= 1023 ;j += 5 )
        {

            drv.analogWrite0(j);
            
            double ae3_Int = drv.analogRead(3);
            double ae2_Int = drv.analogRead(2);

           // double ae0_Int = drv.analogRead(0);    // Uae0    
            //double ae1_Int = drv.analogRead(1);    //Uae1
            

            double I_Base_Ampere = to_stromstaerke(ae3_Int, ae2_Int);

            
            datei << to_volt(ae2_Int) << ";" << I_Base_Ampere << std::endl;
        }

        datei.close();
    }
}