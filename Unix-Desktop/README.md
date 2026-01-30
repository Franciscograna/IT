sudo apt update && sudo apt install \
openbox xdotool network-manager \
yad stalonetray pasystray xclock hsetroot \
papirus-icon-theme mint-y-icons \
libreoffice evince qimgv thunar rfkill \
galculator xarchiver firefox-esr weston xwayland

 .xinitrc → starts YAD → YAD shows icons → icons execute scripts/programs
    └────→ starts Openbox

﻿weston(wayland) → starts .config/weston.ini → Panel shows icons → icons execute scripts/programs
###
dpkg-reconfigure console-setup 
/proc/cmdline
rfkill unblock 0,1 o 2...etc
sudo nano /etc/default/cpufrequtils
###€
sudo mkdir -p /etc/systemd/system/getty@tty1.service.d
sudo nano /etc/systemd/system/getty@tty1.service.d/override.conf

[Service]
ExecStart=
ExecStart=-/sbin/agetty --autologin TU_USUARIO --noclear %I 38400 linux
