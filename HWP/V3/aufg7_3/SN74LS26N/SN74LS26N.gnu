set grid
set datafile separator ";"
set terminal pngcairo
set output 'SN74LS26N.png'
set ylabel "I in mA"
set xlabel "Frequenz in 100 000 Hz"
set autoscale
plot 'Leistung_SN74LS26N.txt' using 1:2 with lines title "SN74LS26N"
