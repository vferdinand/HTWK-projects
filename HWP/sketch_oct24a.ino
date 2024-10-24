

void setup() {
 //serielle Kom
 Serial.begin(9600);

    for(int i = 2; i <= 5; i++) {
      pinMode(i,INPUT);
    }
    for(int i = 6; i <= 9; i++) {
      pinMode(i,OUTPUT);
    }
}

void loop() {
  for(int i = 2; i <= 5; i++){
    Serial.print(digitalRead(i));
    
  }
  for(int i = 6; i <= 9 ;i ++) {
    digitalWrite(i,HIGH); 
  }

  //Kurze Pause für  die Ausgabe PINs
  Serial.println();
  delay(500);

  for(int i = 6; i <= 9; i++) {
    digitalWrite(i, LOW);
  }
  delay(500);
}
