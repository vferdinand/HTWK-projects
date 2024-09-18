set grid
set datafile separator ";"
set terminal pngcairo
set output 'CD409BE.png'
set ylabel "I in mA"
set xlabel "Frequenz in 100 000 Hz"
set autoscale
plot 'Leistung_CD409BE.txt' using 1:2 with lines title "CD409BE"
