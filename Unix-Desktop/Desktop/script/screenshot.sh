#!/bin/bash

yad --undecorated\
    --text-align=center\
    --text="\n\nScreeenshot, \ndeseas continuar? \nen 10 sec se hara"\
    --button="Aceptar:0" \
    --center \
    --width=300 \
    --height=150

# Verificamos si se presionó Aceptar
if [ $? -eq 0 ]; then
    echo "countdown"
    # aquí va el resto del script
	sleep 10;
	DIR="$HOME/Pictures/screenshots"
	mkdir -p "$DIR"
	FILE="$DIR/screenshot_$(date +%Y%m%d_%H%M%S).png"
	scrot "$FILE"

       yad --undecorated\
    --text-align=center\
    --text="\n\nScreeenshot ok,dirijase a  \n"$FILE" \npara verlo "\
    --button="Aceptar:0" \
    --center \
    --width=300 \
    --height=150

fi
