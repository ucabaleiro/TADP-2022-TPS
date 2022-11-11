package calabozos

sealed trait Ubicacion extends (Grupo => Option[Grupo]) {
  def pasar(grupo: Grupo): Grupo
}

class Habitacion(var puertas: List[Puerta], private val situacion: Situacion) extends Ubicacion {

  // TODO: Considerar que la habitación pudo haber sido visitada por el grupo
  def apply(grupo: Grupo): Option[Grupo] = pasar(grupo)
    .agregarPuertas(puertas)
    .abrirSiguientePuerta()

  override def pasar(grupo: Grupo): Grupo = situacion(grupo)
}

object Salida extends Ubicacion {
  def apply(grupo: Grupo): Option[Grupo] = Some(grupo)

  override def pasar(grupo: Grupo): Grupo = grupo
}
