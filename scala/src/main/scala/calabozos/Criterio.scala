package calabozos

sealed trait Criterio extends ((List[Puerta], Grupo) => Option[Puerta])

case object Heroico extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Option[Puerta] = puertas.lastOption
}

case object Ordenado extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Option[Puerta] = puertas.headOption
}

case object Vidente extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Option[Puerta] = puertas.maxByOption(_.hacerPasar(grupo).map(_.puntaje))
}
