package calabozos

// TODO: Convertir en monada
object SalieronConExito {
  def unapply(grupo: Grupo): Option[Grupo] = for {
    puerta <- grupo.siguientePuerta
    if puerta.esSalida
  } yield SalieronConExito(grupo)
}

object NoHayPuertas {
  def unapply(grupo: Grupo): Option[Grupo] = Option.when(!grupo.puedeAbrirPuerta)(NoHayPuertas(grupo))
}

object TodosMurieron {
  def unapply(grupo: Grupo): Option[Grupo] = Option.when(!grupo.hayVivos)(TodosMurieron(grupo))
}
