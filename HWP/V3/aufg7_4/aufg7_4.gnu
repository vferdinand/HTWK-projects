set grid
set datafile separator ";"
set terminal pngcairo
set output 'aufg7_4.png'
set ylabel "P in Watt"
set xlabel "U in V"
set autoscale
plot 'Leistung_aufg7_4.txt' using 1:2 with lines title "MM74C04N"
