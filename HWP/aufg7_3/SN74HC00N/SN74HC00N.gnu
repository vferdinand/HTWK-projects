set grid
set datafile separator ";"
set terminal pngcairo
set output 'SN74HC00N.png'
set ylabel "I in mA"
set xlabel "Frequenz in 100 000 Hz"
set autoscale
plot 'Leistung_SN74HC00N.txt' using 1:2 with lines title "SN74HC00N"
