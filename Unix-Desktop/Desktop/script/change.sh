#!/bin/bash
touch .next
echo "$1" > ~/.next
pkill yad
pkill sway; pkill weston; pkill openbox; pkill Xorg
exit
