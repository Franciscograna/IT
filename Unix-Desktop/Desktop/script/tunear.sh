#!/bin/bash
yad --width=360 --height=400 --center --title="Configs" --text="Desktop Setup" --form --scroll --undecorated --text-align=center --borders=15\
 --field="     :LBL" ""\
 --field="      AUDIO, TOOLBAR, STATUSB:LBL" ""\
 --field="pulse audio system tray, on toolbar:BTN" "bash -c 'pasystray'" \
 --field="Add toolbar:BTN" "bash -c 'stalonetray &'" \
 --field="close toolbar:BTN" "bash -c 'killall stalonetray &'"\
 --field="      USB DEVICES:LBL" ""\
 --field="USB:BTN" "bash -c './$home/$user/Desktop/script/plugusb.sh &'" \
 --field="      NETWORKING:LBL" ""\
 --field="WIFI:BTN" "bash -c './$home/$user/Desktop/script/wifi.sh &'" \
 --field="      BACKGROUND:LBL" ""\
 --field="Fondo Blanco:BTN" "bash -c 'hsetroot -solid \"#ffffff\" &'" \
 --field="Fondo Negro:BTN" "bash -c 'hsetroot -solid \"#000000\" &'" \
 --field="Fondo Rojo:BTN" "bash -c 'hsetroot -solid \"#ff0000\" &'" \
 --field="Fondo Verde:BTN" "bash -c 'hsetroot -solid \"#00ff00\" &'" \
 --field="Fondo Azul:BTN" "bash -c 'hsetroot -solid \"#0000ff\" &'" \
 --field="Fondo Dinámico:BTN" "bash -c './loop-color.sh &'" \
 --field="Restablecer Fondo:BTN" "bash -c 'killall loop-color.sh &'" \
 --field="      DATE, TIME, CLOCKS:LBL" ""\
 --field="Calendario:BTN" "bash -c 'yad --calendar --undecorated --no-buttons &'" \
 --field="Reloj B/N TV:BTN" "bash -c 'xclock -digital -update 1 -bg \"#FFFFFF\" -fg \"#000000\" -face \"Monospace-60\" -padding 10 &'" \
 --field="Reloj B/Cian TV:BTN" "bash -c 'xclock -digital -update 1 -bg \"#FFFFFF\" -fg \"#00FFFF\" -face \"Monospace-60\" -padding 10 &'" \
 --field="Reloj B/Magenta TV:BTN" "bash -c 'xclock -digital -update 1 -bg \"#FFFFFF\" -fg \"#FF00FF\" -face \"Monospace-60\" -padding 10 &'" \
 --field="Reloj N/B TV:BTN" "bash -c 'xclock -digital -update 1 -bg \"#000000\" -fg \"#FFFFFF\" -face \"Monospace-60\" -padding 10 &'" \
 --field="Reloj N/Cian TV:BTN" "bash -c 'xclock -digital -update 1 -bg \"#000000\" -fg \"#00FFFF\" -face \"Monospace-60\" -padding 10 &'" \
 --field="Reloj N/Magenta TV:BTN" "bash -c 'xclock -digital -update 1 -bg \"#000000\" -fg \"#FF00FF\" -face \"Monospace-60\" -padding 10 &'" \
 --field="Reloj B/N Mini:BTN" "bash -c 'xclock -digital -update 1 -bg \"#FFFFFF\" -fg \"#000000\" -face \"Monospace-30\" -padding 5 &'" \
 --field="Reloj B/Cian Mini:BTN" "bash -c 'xclock -digital -update 1 -bg \"#FFFFFF\" -fg \"#00FFFF\" -face \"Monospace-30\" -padding 5 &'" \
 --field="Reloj B/Magenta Mini:BTN" "bash -c 'xclock -digital -update 1 -bg \"#FFFFFF\" -fg \"#FF00FF\" -face \"Monospace-30\" -padding 5 &'" \
 --field="Reloj N/B Mini:BTN" "bash -c 'xclock -digital -update 1 -bg \"#000000\" -fg \"#FFFFFF\" -face \"Monospace-30\" -padding 5 &'" \
 --field="Reloj N/Cian Mini:BTN" "bash -c 'xclock -digital -update 1 -bg \"#000000\" -fg \"#00FFFF\" -face \"Monospace-30\" -padding 5 &'" \
 --field="Reloj N/Magenta Mini:BTN" "bash -c 'xclock -digital -update 1 -bg \"#000000\" -fg \"#FF00FF\" -face \"Monospace-30\" -padding 5 &'" \
 --field="Close xClock:BTN" "bash -c 'killall xclock &'"\
 --field="      SREENSHOOT, RECORD TOOL:LBL" ""\
 --field="screenshot:BTN" "bash -c './$home/$user/Desktop/script/screenshot.sh &'" \
 --field="video:BTN" "bash -c './$home/$user/Desktop/script/grabar.sh &'" \


