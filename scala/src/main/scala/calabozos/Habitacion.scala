package calabozos

class Habitacion(var puertas: List[Puerta], private val situacion: Situacion) extends (Grupo => Option[Grupo]) {

  // TODO: Considerar que la habitación pudo haber sido visitada por el grupo
  def apply(grupo: Grupo): Option[Grupo] = situacion(grupo).siguientePuerta match {
    case Some(puerta) if puerta.esSalida => Some(grupo)
    case Some(puerta) => grupo
      .quitarPuerta(puerta)
      .agregarPuertas(puertas)
      .siguientePuerta
      .flatMap(_.habitacion(grupo))
    case None => None
  }

}
