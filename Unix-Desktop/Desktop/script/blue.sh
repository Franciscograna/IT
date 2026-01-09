#!/bin/bash

# Asegurar BT activo
systemctl is-active --quiet bluetooth || systemctl start bluetooth

bluetoothctl power on >/dev/null
bluetoothctl agent on >/dev/null
bluetoothctl default-agent >/dev/null

# Obtener dispositivos
CONNECTED=$(bluetoothctl devices Connected | awk '{print $2 " | " substr($0,index($0,$3))}')
PAIRED=$(bluetoothctl paired-devices | awk '{print $2 " | " substr($0,index($0,$3))}')
AVAILABLE=$(bluetoothctl devices | awk '{print $2 " | " substr($0,index($0,$3))}')

# Marcar estados
LIST=""
while read -r line; do
    MAC=${line%% |*}
    NAME=${line#*| }

    STATE="Disponible"

    bluetoothctl info "$MAC" | grep -q "Connected: yes" && STATE="Conectado"
    bluetoothctl info "$MAC" | grep -q "Paired: yes" && [ "$STATE" != "Conectado" ] && STATE="Paired"

    LIST+="$STATE|$NAME|$MAC\n"
done <<< "$AVAILABLE"

# Mostrar YAD
ACTION=$(echo -e "$LIST" | yad --list \
    --title="Bluetooth" \
    --width=500 \
    --height=400 \
    --column="Estado" \
    --column="Nombre" \
    --column="MAC" \
    --button="Conectar:0" \
    --button="Desconectar:1" \
    --button="Pair:2" \
    --button="Unpair:3" \
    --button="Salir:9")

BTN=$?

STATE=$(echo "$ACTION" | cut -d'|' -f1)
NAME=$(echo "$ACTION" | cut -d'|' -f2)
MAC=$(echo "$ACTION" | cut -d'|' -f3)

[ -z "$MAC" ] && exit 0

case $BTN in
    0)  # Conectar
        bluetoothctl connect "$MAC" \
        || yad --error --text="No se pudo conectar a $NAME"
        ;;
    1)  # Desconectar
        bluetoothctl disconnect "$MAC"
        ;;
    2)  # Pair
        bluetoothctl pair "$MAC" && bluetoothctl trust "$MAC"
        ;;
    3)  # Unpair
        bluetoothctl remove "$MAC"
        ;;
esac

exit 0