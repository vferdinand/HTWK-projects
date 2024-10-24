#include <iostream>
#include <b15f/b15f.h>

using namespace std;

B15F& drv = B15F::getInstance();

int main() {
    
    //
    drv.setRegister(&DDRA , 0x0F);

    uint8_t portAValue;


    while (true) {
        drv.setRegister(&PORTA, 0x0F);
        portAValue = drv.getRegister(&PINA);
        cout << " "<< bitset<8>((int)portAValue).to_string() << endl;
        usleep(500000);

        drv.setRegister(&PORTA, 0x0);
        portAValue = drv.getRegister(&PINA);
        cout << " " << bitset<8> ((int)portAValue).to_string() << endl;
        usleep(500000);


    }

    return 0;
}
