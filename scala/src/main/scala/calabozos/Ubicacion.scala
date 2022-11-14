package calabozos

sealed trait Ubicacion {
  def serRecorridaPor(grupo: Grupo): Option[Grupo]
  def hacerPasar(grupo: Grupo): Grupo
}

class Habitacion(var puertas: List[Puerta], private val situacion: Situacion) extends Ubicacion {
  override def serRecorridaPor(grupo: Grupo): Option[Grupo] = hacerPasar(grupo)
    .agregarPuertas(puertas)
    .abrirSiguientePuerta()

  override def hacerPasar(grupo: Grupo): Grupo =
    if grupo.visitoHabitacion(this) then grupo
    else situacion(grupo.agregarHabitacion(this))
}

object Salida extends Ubicacion {
  def serRecorridaPor(grupo: Grupo): Option[Grupo] = Some(hacerPasar(grupo))

  override def hacerPasar(grupo: Grupo): Grupo = grupo
}
