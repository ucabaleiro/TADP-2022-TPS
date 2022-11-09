package calabozos

import scala.util.Option

sealed trait Criterio extends ((List[Puerta], Grupo) => Option[Puerta])

case object Heroico extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Option[Puerta] =
    Option.when(puertas.nonEmpty)(puertas.last)
}

case object Ordenado extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Option[Puerta] =
    Option.when(puertas.nonEmpty)(puertas.head)
}

case object Vidente extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Option[Puerta] = {
    if (puertas.isEmpty) {
      return None
    }

    val puerta = puertas.reduce((puerta1, puerta2) => {
      val grupoTrasPuerta1 = puerta1.habitacion(grupo)
      val grupoTrasPuerta2 = puerta2.habitacion(grupo)

      return (grupoTrasPuerta1, grupoTrasPuerta2) match {
        case (Some(grupo1), Some(grupo2)) => if (grupo1.puntaje > grupo2.puntaje) Some(puerta1) else Some(puerta2)
        case (Some(_), None) => Option(puerta1)
        case (None, Some(_)) => Option(puerta2)
        case (None, None) => None
      }
    })

    return Some(puerta)
  }
}
