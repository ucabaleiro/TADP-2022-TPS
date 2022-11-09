package calabozos

import scala.util.Try
import scala.util.Failure
import scala.util.Success
import scala.util.Some

class Habitacion(var puertas: List[Puerta], private val situacion: Situacion) extends (Grupo => Option[Grupo]) {

  def apply(grupo: Grupo): Option[Grupo] = situacion(grupo).siguientePuerta.flatMap(puerta =>
    if puerta.esSalida Some(grupo) else puerta.habitacion(grupo)
  )


    //  match {
    //   case Some(puerta) if puerta.esSalida => Success(grupo)
    //   case Some(puerta) => puerta.habitacion(grupo)
    //   case None => Failure(new Exception("bla"))
    // }
}
