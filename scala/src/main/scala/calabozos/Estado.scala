package calabozos

sealed trait Estado {
  val grupo: Grupo
}

case class Continuan(val grupo: Grupo) extends Estado

object Continuan {
  def unapply(grupo: Grupo): Some[Continuan] = Some(Continuan(grupo))
  def unapply(estado: Estado): Option[Grupo] = Option.when(estado.isInstanceOf[Continuan])(estado.grupo)
}

case class SalieronConExito(val grupo: Grupo) extends Estado

object SalieronConExito {
  def unapply(grupo: Grupo): Option[SalieronConExito] = for {
    puerta <- grupo.siguientePuerta
    if puerta.esSalida
  } yield SalieronConExito(grupo)
}

case class NoHayPuertas(val grupo: Grupo) extends Estado

object NoHayPuertas {
  def unapply(grupo: Grupo) = Option.when(!grupo.puedeAbrirPuerta)(NoHayPuertas(grupo))
}

case class TodosMurieron(val grupo: Grupo) extends Estado

object TodosMurieron {
  def unapply(grupo: Grupo) = Option.when(!grupo.hayVivos)(TodosMurieron(grupo))
}
