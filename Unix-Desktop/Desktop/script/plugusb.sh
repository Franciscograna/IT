#!/bin/bash
STATUS=$?
# Obtener todas las particiones (sda1, sdb2, etc.)
PARTICIONES=$(lsblk -lpno NAME,TYPE | grep part | awk '{print $1}')

# Obtener particiones montadas específicamente en /mnt*
MONTADAS=$(mount | awk '$3 ~ /^\/mnt/ {print $1}')

# Construir lista para YAD con TRUE/FALSE según si están montadas en /mnt
LISTA=""
for P in $PARTICIONES; do
    if echo "$MONTADAS" | grep -q "^$P$"; then
        ESTADO="TRUE"
    else
        ESTADO="FALSE"
    fi

    LISTA="$LISTA $ESTADO $P "
done

# Ventana YAD
SELECCION=$(yad --width=400 --height=300 --title="Montar particiones" \
 --text="Seleccione las particiones que desea montar en /mnt" \
 --list --checklist \
 --column="Montada en /mnt" --column="Partición" \
 $LISTA \
 --separator=" " \
 --print-column=2 \
 --button="Desmontar todo (/mnt)":2 \
 --button="Cancelar":1 \
 --button="OK":0 )


if [ $? -eq 2 ]; then
    for DEV in /dev/sda /dev/sda1 /dev/sda2 /dev/sda3 /dev/sda4; do
        if mount | grep -q "^$DEV "; then
            xterm -fa 'Monospace' -fs 20 -hold \
                -e bash -c "sudo -k; sudo umount $DEV; pkill xterm"
        fi
    done
    exit 0
fi

[ -z "$SELECCION" ] && exit 0


for PART in $SELECCION; do
    DIR="/mnt/$(basename "$PART")"

    xterm -fa 'Monospace' -fs 20 -hold \
        -e bash -c "sudo mount \"$PART\" \"$DIR\" ; sleep 1; pkill xterm"
done
