sudo apt update && sudo apt install \
openbox \
yad stalonetray pasystray xclock hsetroot \
papirus-icon-theme \
libreoffice \
galculator xarchiver

 .xinitrc → starts YAD → YAD shows icons → icons execute scripts/programs
    └────→ starts Openbox

﻿weston(wayland) → starts .config/weston.ini → Panel shows icons → icons execute scripts/programs


sudo nano /etc/default/cpufrequtils

sudo mkdir -p /etc/systemd/system/getty@tty1.service.d
sudo nano /etc/systemd/system/getty@tty1.service.d/override.conf

[Service]
ExecStart=
ExecStart=-/sbin/agetty --autologin TU_USUARIO --noclear %I 38400 linux
