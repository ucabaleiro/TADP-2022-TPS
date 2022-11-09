package calabozos

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
  def apply(puertas: List[Puerta], grupo: Grupo): Option[Puerta] = 
    Option.when(puertas.nonEmpty)(puertas)
      .flatMap(_.maxByOption(_.habitacion(grupo).map(_.puntaje)))
}
