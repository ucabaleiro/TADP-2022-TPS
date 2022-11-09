package calabozos

case class Calabozo(val entrada: Habitacion) {

  def entrarACalabozo(grupo: Grupo): Option[Grupo] = entrada(grupo)

  def mejorGrupo(grupos: List[Grupo]): Option[Grupo] = grupos
    .flatMap(entrarACalabozo)
    .maxByOption(_.puntaje)

  def nivelesParaGrupo(grupo: Grupo): Option[Int] = nivelesParaGrupo(grupo, 0)

  private def nivelesParaGrupo(grupo: Grupo, niveles: Int): Option[Int] = entrarACalabozo(grupo) match {
    case Some(grupo) => Some(niveles)
    case None if niveles <= 20 => nivelesParaGrupo(grupo.afectarHeroes(_.subirNivel), niveles + 1)
    case _ => None
  }
}
