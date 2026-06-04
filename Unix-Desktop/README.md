sudo apt update && sudo apt install \
openbox xdotool thunderbird \
yad stalonetray xclock hsetroot \
 mint-y-icons \
libreoffice evince qimgv thunar rfkill \
galculator xarchiver firefox-esr weston xwayland wvkbd sway

 .xinitrc → starts YAD → YAD shows icons → icons execute scripts/programs
    └────→ starts Openbox

﻿weston(wayland) → starts .config/weston.ini → Panel shows icons → icons execute scripts/programs
###
/proc/cmdline

rfkill unblock 0,1 o 2...etc
sudo nano /etc/default/cpufrequtils
#edit etc/fstab
tmpfs /root/.cache/mozilla tmpfs defaults,noatime,mode=0700,size=1G 0 0
###€
sudo mkdir -p /etc/systemd/system/getty@tty1.service.d
sudo nano /etc/systemd/system/getty@tty1.service.d/override.conf

[Service]
ExecStart=
ExecStart=-/sbin/agetty --autologin TU_USUARIO --noclear %I 38400 linux

###
AUDIO
sudo apt install pulseaudio pulseaudio-utils pavucontrol
sudo apt install libasound2-plugins
sudo nano /etc/asound.conf

Contenido recomendado:

pcm.!default {
    type pulse
}
ctl.!default {
    type pulse
}
