################### END OF FILE ####################

if [[ $(tty) == /dev/tty1 ]]; then
  PS3="Elige sesión: "
  select sesion in "X11 (startx)" "Wayland (weston)"; do
    case $REPLY in
      1) exec startx ;;
      2) exec weston ;;
      *) echo "Opción inválida" ;;
    esac
  done
fi

