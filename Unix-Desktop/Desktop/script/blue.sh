#!/bin/bash

SELECCION=$(bluetoothctl devices | yad --list \
    --title="Bluetooth"\
    --width=500 \
    --height=400 \
    --column="Dispositivo" \
    --print-column=1 \
    --button="Scan:9")

if [ $? -eq 9 ]; then
        x-terminal-emulator -e bash -c   "bluetoothctl scan on"
fi


[ -z "$SELECCION" ] && exit 0

MAC=$(echo "$SELECCION" | awk '{print $2}')
INFO=$(bluetoothctl info "$MAC")

yad --text="$INFO" \
    --title="$SELECCION" \
    --button="Pair" \
    --button="UnPair" \
    --button="Connect" \
    --button="Disconnect" \
    --button="Trust" \
    --button="UnTrust"\
    --button="Remove"\
    --button="SCAN:9"

RET=$?

case $RET in
    0)
        xterm -e bash -c "bluetoothctl pair '$MAC'; echo; read -p 'Presione Enter para cerrar...'"
        ;;
    1)
        xterm -e bash -c "bluetoothctl remove '$MAC'; echo; read -p 'Presione Enter para cerrar...'"
        ;;
    2)
        xterm -e bash -c "bluetoothctl connect '$MAC'; echo; read -p 'Presione Enter para cerrar...'"
        ;;
    3)
        xterm -e bash -c "bluetoothctl disconnect '$MAC'; echo; read -p 'Presione Enter para cerrar...'"
        ;;
    4)
        xterm -e bash -c "bluetoothctl trust '$MAC'; echo; read -p 'Presione Enter para cerrar...'"
        ;;
    5)
        xterm -e bash -c "bluetoothctl untrust '$MAC'; echo; read -p 'Presione Enter para cerrar...'"
        ;;
    6)
        xterm -e bash -c "bluetoothctl remove '$MAC'; echo; read -p 'Presione Enter para cerrar...'"
        ;;
    *)
        exit 0
        ;;
esac

if [ $? -eq 9 ]; then
        x-terminal-emulator -e bash -c   "bluetoothctl scan on"
fi

