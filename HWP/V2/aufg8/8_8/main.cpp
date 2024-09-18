#include <iostream>
#include <fstream>
#include <b15f/b15f.h>
#include <b15f/plottyfile.h>


int main() {                            //erzeugen des Rechtecksignals

    B15F& drv = B15F::getInstance();

    while (true) {
       drv.analogWrite0(1023);
        drv.analogWrite1(1023);
        drv.delay_ms(20);
        drv.analogWrite1(0);
      /*  drv.analogWrite0(1023);
        drv.delay_ms(20);
        drv.analogWrite0(0);
        */
    }

}