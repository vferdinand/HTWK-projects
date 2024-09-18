set datafile separator ";"
set terminal pngcairo
set output 'plot.png'
set title "Widerstandkennlinie"
set xlabel "U_A_A_0 in V"
set ylabel "U_A_E_0 in V"
set xr [0:5]
set yr [0:5]
plot 'werte100.txt' using 1:2 with lines title "Messwerte", '' using 1:1 with lines dashtype 2 title "Eingangsspannung"
