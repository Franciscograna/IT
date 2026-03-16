#!/bin/bash

IFACE="wlan0"

# Escanear redes
SCAN=$(wpa_cli -i $IFACE scan >/dev/null 2>&1 && sleep 2 && \
wpa_cli -i $IFACE scan_results | awk 'NR>2 {print $5}' | sort -u)

# Seleccionar red
SSID=$(echo "$SCAN" | yad --list \
--title="WiFi Scanner" \
--column="SSID" \
--height=300 --width=300)

[ -z "$SSID" ] && exit

# Pedir password
PASS=$(yad --entry \
--title="Password" \
--text="Password for $SSID" \
--hide-text)

[ -z "$PASS" ] && exit

# Conectar
NETID=$(wpa_cli -i $IFACE add_network | tail -n1)

wpa_cli -i $IFACE set_network $NETID ssid "\"$SSID\"" >/dev/null
wpa_cli -i $IFACE set_network $NETID psk "\"$PASS\"" >/dev/null
wpa_cli -i $IFACE enable_network $NETID >/dev/null

# Mostrar estado
xterm -e "wpa_cli -i $IFACE status; echo; echo 'Press Enter to close'; read"