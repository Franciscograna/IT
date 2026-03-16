#!/bin/bash

# Scan
wpa_cli -i wlan0 scan
sleep 3

SSID_LIST=$(wpa_cli -i wlan0 scan_results | awk 'NR>2 {print $5}' | sort -u)

# Mostrar lista en YAD y seleccionar
SSID=$(echo "$SSID_LIST" | yad --list \
    --title="Select WiFi" \
    --column="SSID" \
    --height=300 --width=300)

[ -z "$SSID" ] && exit

# Ask password
PASS=$(yad --entry --title="Password" --text="Password for $SSID" --hide-text)

[ -z "$PASS" ] && exit

# Add network
ID=$(wpa_cli -i wlan0 add_network | tail -n1)

# Set parameters
wpa_cli -i wlan0 set_network $ID ssid "\"$SSID\""
wpa_cli -i wlan0 set_network $ID psk "\"$PASS\""

# Connect
wpa_cli -i wlan0 enable_network $ID

# Status
xterm -e "wpa_cli -i wlan0 status; read"