#!/bin/bash

# Obtener red actual
CURRENT=$(nmcli -t -f ACTIVE,SSID dev wifi | grep "^yes" | cut -d: -f2)
[ -z "$CURRENT" ] && CURRENT="Ninguna"

# Escanear redes
SCAN=$(nmcli -t -f SSID dev wifi | sed '/^$/d' | sort -u)

echo $SCAN

# Ejecutar YAD principal
ACTION=$(yad --title="WiFi" \
            --width=350 --height=400 \
            --text="Red actual: <b>$CURRENT</b>" --text-align=center \
            --button="Conectar!:0" \
            --button="Desconectar!:1" \
            --button="Salir:3" \
            --list --column="SSID"   --undecorated \
            <<<"$SCAN")

BTN=$?

### --- BOTÓN 0: Conectar ---
if [ $BTN -eq 0 ]; then
    SSID=${ACTION%%|*}
    [ -z $SSID ] && exit 0

    PASS=$(yad --title="Password" --form --field="Contraseña:":H)
	PASS=${PASS%%|*}

	if nmcli -t -f NAME con show | grep -qx "$SSID"; then
    # red ya guardada
    nmcli dev wifi connect "$SSID" >/tmp/wifi.log 2>&1 \
    || yad --error --text="No se pudo conectar a $SSID"
else
    # red nueva
    nmcli dev wifi connect "$SSID" password "$PASS" >/tmp/wifi.log 2>&1 \
    || yad --error --text="No se pudo conectar a $SSID"
fi

#    echo $PASS;
#    echo $SSID;
fi
### --- BOTÓN 1: Desconectar ---
if [ $BTN -eq 1 ]; then
    nmcli con down "$(nmcli -t -f NAME con show --active | head -n1)"
    yad --info --text="WiFi desconectado."
fi

exit 0
### ===============================
### MENÚ AVANZADO POR RED - Continua.
### ===============================

SSID=${ACTION%%|*}
[ -z "$SSID" ] && exit 0

INFO_RED=$(nmcli -f IN-USE,SSID,SECURITY,SIGNAL,CHAN,BARS,FREQ,MODE,DEVICE dev wifi list | grep -w "$SSID")

OP=$(yad --title="Red: $SSID" \
    --width=380 --height=300 \
    --text="<b>Información de la red</b>\n$INFO_RED" \
    --text-align=left \
    --button="Conectar:0" \
    --button="Desconectar:1" \
    --button="Olvidar red:2" \
    --button="Info detallada:3" \
    --button="Reset WiFi:4" \
    --button="Reset NetworkManager:5" \
    --button="Cancelar:6")

RET=$?

### ---- CONECTAR ----
if [ $RET -eq 0 ]; then
    if nmcli -t -f NAME con show | grep -qx "$SSID"; then
        nmcli dev wifi connect "$SSID" >/tmp/wifi.log 2>&1 \
        || yad --error --text="No se pudo conectar a $SSID"
    else
        PASS=$(yad --title="Password $SSID" --form --field="Contraseña:":H)
        PASS=${PASS%%|*}
        nmcli dev wifi connect "$SSID" password "$PASS" >/tmp/wifi.log 2>&1 \
        || yad --error --text="No se pudo conectar a $SSID"
    fi
fi

### ---- DESCONECTAR ----
if [ $RET -eq 1 ]; then
    nmcli dev disconnect "$(nmcli -t -f DEVICE dev status | grep wifi | cut -d: -f1)"
    yad --info --text="Desconectado de $SSID"
fi

### ---- OLVIDAR / ELIMINAR RED ----
if [ $RET -eq 2 ]; then
    if nmcli -t -f NAME con show | grep -qx "$SSID"; then
        nmcli con delete "$SSID"
        yad --info --text="Red $SSID eliminada"
    else
        yad --warning --text="La red $SSID no está guardada"
    fi
fi

### ---- INFO DETALLADA DE LA RED ----
if [ $RET -eq 3 ]; then
    nmcli -f all dev wifi list | grep -A15 -w "$SSID" > /tmp/wifi_info.txt
    yad --title="Info $SSID" \
        --width=500 --height=400 \
        --text-info --filename=/tmp/wifi_info.txt
fi

### ---- RESET AP / INTERFAZ WIFI ----
if [ $RET -eq 4 ]; then
    IFACE=$(nmcli -t -f DEVICE,TYPE dev status | grep wifi | cut -d: -f1)
    nmcli dev disconnect "$IFACE"
    sleep 2
    nmcli dev connect "$IFACE"
    yad --info --text="Interfaz WiFi reiniciada"
fi

### ---- RESET NETWORK MANAGER ----
if [ $RET -eq 5 ]; then
    systemctl restart NetworkManager
    yad --info --text="NetworkManager reiniciado"
fi

exit 0