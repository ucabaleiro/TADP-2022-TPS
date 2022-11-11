package calabozos

sealed trait Ubicacion extends (Grupo => Option[Grupo]) {
  def pasar(grupo: Grupo): Grupo
}

class Habitacion(var puertas: List[Puerta], private val situacion: Situacion) extends Ubicacion {
  def apply(grupo: Grupo): Option[Grupo] = pasar(grupo)
    .agregarPuertas(puertas)
    .abrirSiguientePuerta()

  override def pasar(grupo: Grupo): Grupo =
    if grupo.habitacionesVisitadas.contains(this) then grupo else situacion(grupo.agregarUbicacion(this))
}

object Salida extends Ubicacion {
  def apply(grupo: Grupo): Option[Grupo] = Some(pasar(grupo))

  override def pasar(grupo: Grupo): Grupo = grupo
}
