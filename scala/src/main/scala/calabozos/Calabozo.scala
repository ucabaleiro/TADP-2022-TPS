package calabozos

import scala.annotation.tailrec

case class Calabozo(entrada: Puerta) {

  def entrarACalabozo(grupo: Grupo): Option[Grupo] = grupo
    .agregarPuerta(entrada)
    .siguientePuerta
    .flatMap(_.ubicacion(grupo))

  def mejorGrupo(grupos: List[Grupo]): Option[Grupo] = grupos
    .flatMap(entrarACalabozo)
    .maxByOption(_.puntaje)

  def nivelesParaGrupo(grupo: Grupo): Option[Int] = nivelesParaGrupo(grupo, 0)

  @tailrec
  private def nivelesParaGrupo(grupo: Grupo, niveles: Int): Option[Int] = entrarACalabozo(grupo) match {
    case Some(_) => Some(niveles)
    case None if niveles <= 20 => nivelesParaGrupo(grupo.afectarHeroes(_.subirNivel), niveles + 1)
    case _ => None
  }
}
