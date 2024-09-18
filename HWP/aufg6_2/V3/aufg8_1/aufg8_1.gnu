set grid
set datafile separator ";"
set terminal pngcairo
set output 'aufg8_1.png'
set ylabel "Gatterlaufzeit in ns"
set xlabel "U in V"
set autoscale
plot 'Gatterlaufzeit_aufg8_1.txt' using 1:2 with lines title "Gatterlaufzeit"
