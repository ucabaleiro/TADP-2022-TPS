package calabozos

import scala.util.Try
import scala.util.{Success, Failure}

sealed trait Ubicacion {
  def serRecorridaPor(grupo: Grupo): Try[Grupo]
  def hacerPasar(grupo: Grupo): Try[Grupo]
}

class Habitacion(var puertas: List[Puerta], private val situacion: Situacion) extends Ubicacion {
  override def serRecorridaPor(grupo: Grupo): Try[Grupo] = hacerPasar(grupo)
    .map(_.agregarPuertas(puertas))
    .flatMap(_.abrirSiguientePuerta())

  override def hacerPasar(grupo: Grupo): Try[Grupo] =
    if grupo.visitoHabitacion(this) then Success(grupo)
    else Try(situacion(grupo.agregarHabitacion(this)))
}

object Salida extends Ubicacion {
  def serRecorridaPor(grupo: Grupo): Try[Grupo] = hacerPasar(grupo)

  override def hacerPasar(grupo: Grupo): Try[Grupo] = Success(grupo)
}
