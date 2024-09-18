set grid
set datafile separator ";"
set terminal pngcairo
set output 'U74HC00L.png'
set ylabel "I in mA"
set xlabel "Frequenz in 100 000 Hz"
set autoscale
plot 'Leistung_U74HC00L.txt' using 1:2 with lines title "U74HC00L"
