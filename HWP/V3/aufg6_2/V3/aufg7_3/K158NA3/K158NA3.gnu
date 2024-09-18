set grid
set datafile separator ";"
set terminal pngcairo
set output 'K158NA3.png'
set ylabel "P in Watt"
set xlabel "Frequenz in 100 000 Hz"
set autoscale
plot 'Leistung_K158NA3.txt' using 1:2 with lines title "K158NA3"
