package calabozos

import scala.annotation.tailrec

case class Calabozo(entrada: Puerta) {

  def hacerEntrar(grupo: Grupo): Option[Grupo] = grupo
    .agregarPuerta(entrada)
    .abrirSiguientePuerta()

  def mejorGrupo(grupos: List[Grupo]): Option[Grupo] = grupos
    .flatMap(hacerEntrar)
    .maxByOption(_.puntaje)

  def nivelesParaGrupo(grupo: Grupo): Option[Int] = nivelesParaGrupo(grupo, 0)

  @tailrec
  private def nivelesParaGrupo(grupo: Grupo, niveles: Int): Option[Int] = hacerEntrar(grupo) match {
    case Some(_) => Some(niveles)
    case None if niveles < 20 => nivelesParaGrupo(grupo.afectarHeroes(_.subirNivel()), niveles + 1)
    case _ => None
  }
}
