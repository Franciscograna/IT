#!/bin/bash

# Obtener red actual
CURRENT=$(nmcli -t -f ACTIVE,SSID dev wifi | grep "^yes" | cut -d: -f2)
[ -z "$CURRENT" ] && CURRENT="Ninguna"

# Escanear redes
SCAN=$(nmcli -t -f SSID dev wifi | sed '/^$/d')

# Convertir lista para YAD

LIST=""
while IFS= read -r SSID; do
    LIST="$LIST "$SSID" "
done <<< "$SCAN"

echo $LIST

# Ejecutar YAD principal
ACTION=$(yad --title="WiFi" \
            --width=350 --height=400 \
            --text="Red actual: <b>$CURRENT</b>" --text-align=center \
            --button="Conectar!:0" \
            --button="Desconectar!:1" \
            --button="Salir:3" \
            --list --column="SSID"   --undecorated \
            $LIST)

BTN=$?

### --- BOTÓN 0: Conectar ---
if [ $BTN -eq 0 ]; then
    SSID=${ACTION%%|*}
    [ -z $SSID ] && exit 0

    PASS=$(yad --title="Password" --form --field="Contraseña:":H)
	PASS=${PASS%%|*}

	if ! nmcli dev wifi connect "$SSID" password "$PASS" >/tmp/wifi.log 2>&1; then
    nmcli dev wifi connect "$SSID" >/tmp/wifi.log 2>&1 \
    || yad --error --text="No se pudo conectar a $SSID"
fi

    echo $PASS;
    echo $SSID;
fi

### --- BOTÓN 1: Desconectar ---
if [ $BTN -eq 1 ]; then
    nmcli con down "$(nmcli -t -f NAME con show --active | head -n1)"
    yad --info --text="WiFi desconectado."
fi

exit 0
