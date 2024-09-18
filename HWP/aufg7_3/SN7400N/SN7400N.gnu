set grid
set datafile separator ";"
set terminal pngcairo
set output 'SN7400N.png'
set ylabel "P in Watt"
set xlabel "Frequenz in 100 000 Hz"
set autoscale
plot 'Leistung_SN7400N.txt' using 1:2 with lines title "SN7400N"
