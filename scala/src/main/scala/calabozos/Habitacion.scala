package calabozos

class Habitacion(var puertas: List[Puerta], private val situacion: Situacion) extends (Grupo => Option[Grupo]) {

  def apply(grupo: Grupo): Option[Grupo] = situacion(grupo).siguientePuerta match {
    case Some(puerta) if puerta.esSalida => Some(grupo)
    case Some(puerta) => puerta.habitacion(grupo.quitarPuerta(puerta))
    case None => None
  }

}
