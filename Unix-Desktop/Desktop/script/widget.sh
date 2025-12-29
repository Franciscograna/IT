#!/bin/bash
RES=$(xrandr | awk '/\*/{print $1}' | head -n1)
W=${RES%x*}
H=${RES#*x}

W=$((W - 100))
H=$((H - 165))

echo "screen size: ${W}x${H}"


 yad  --icons  --read-dir=/home/$USER/Desktop    --width=${W} --height=${H}   --item-width=20 --icon-size=28  --center --undecorated --no-buttons --skip-taskbar  &

openbox &

wait

