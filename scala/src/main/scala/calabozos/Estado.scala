package calabozos

// TODO: Convertir en monada
sealed trait Estado {
  val grupo: Grupo
}

case class Continuan(grupo: Grupo) extends Estado

object Continuan {
  def unapply(grupo: Grupo): Some[Continuan] = Some(Continuan(grupo))
  def unapply(estado: Estado): Option[Grupo] = Option.when(estado.isInstanceOf[Continuan])(estado.grupo)
}

case class SalieronConExito(grupo: Grupo) extends Estado

object SalieronConExito {
  def unapply(grupo: Grupo): Option[SalieronConExito] = for {
    puerta <- grupo.siguientePuerta
    if puerta.esSalida
  } yield SalieronConExito(grupo)
}

case class NoHayPuertas(grupo: Grupo) extends Estado

object NoHayPuertas {
  def unapply(grupo: Grupo): Option[NoHayPuertas] = Option.when(!grupo.puedeAbrirPuerta)(NoHayPuertas(grupo))
}

case class TodosMurieron(grupo: Grupo) extends Estado

object TodosMurieron {
  def unapply(grupo: Grupo): Option[TodosMurieron] = Option.when(!grupo.hayVivos)(TodosMurieron(grupo))
}
