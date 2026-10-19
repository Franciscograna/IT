#!/bin/bash

# Escanear redes
SCAN=$(cat /etc/netplan/armbian.yaml)


# Ejecutar YAD principal

ACTION=$(yad --title="WiFi" \
 --width=350 --height=400 \
 --text="Red actual" --text-align=center \
 --button="Cambiar!:0" \
 --button="Salir!:1" \
 --list --column="SSID"\
<<< "$SCAN")

BTN=$?

### --- BOTÓN 0: Conectar ---
if [ $BTN -eq 0 ]; then
xterm -e "sudo nano /etc/netplan/armbian.yaml"	
fi
### --- BOTÓN 1: Desconectar ---
if [ $BTN -eq 1 ]; then
    ;
exit 0
