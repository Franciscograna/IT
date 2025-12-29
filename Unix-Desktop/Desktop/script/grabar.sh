#!/bin/bash

yad --undecorated\
    --text-align=center\
    --text="\n\nGrabacion, \ndeseas continuar? \nen 10 sec se hara"\
    --button="Aceptar:0" \
    --center \
    --width=300 \
    --height=150





if [ $? -eq 0 ]; then
    echo "countdown"


	echo "Inicio del script"
	sleep 10 #setear luego
	ARCHIVO="grabacion_$(date +%Y%m%d_%H%M%S).mp4"
	ffmpeg -f x11grab -video_size 1024x600 -framerate 30 -i :0.0 \
       	-f pulse -i default \
       	-t 7200 \ #setear luego
       	-c:v libx264 -preset veryfast -crf 23 \
       	-c:a aac -b:a 128k \
       	"$ARCHIVO"
	echo "Fin del script"
       yad --undecorated\
    --text-align=center\
    --text="\n\nRecord ok,dirijase a  \n"$ARCHIVO" \npara verlo "\
    --button="Aceptar:0" \
    --center \
    --width=300 \
    --height=150

fi

