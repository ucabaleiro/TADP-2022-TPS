package calabozos

sealed trait Ubicacion extends (Grupo => Option[Grupo])

class Habitacion(var puertas: List[Puerta], private val situacion: Situacion) extends Ubicacion {

  // TODO: Considerar que la habitación pudo haber sido visitada por el grupo
  def apply(grupo: Grupo): Option[Grupo] = situacion(grupo)
    .siguientePuerta
    .map(grupo.quitarPuerta)
    .map(_.agregarPuertas(puertas))
    .flatMap(_.siguientePuerta)
    .flatMap(_.ubicacion(grupo))
}

class Salida extends Ubicacion {
  def apply(grupo: Grupo): Option[Grupo] = Some(grupo)
}
