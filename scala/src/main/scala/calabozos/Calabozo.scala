package calabozos

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

class Calabozo(entrada: Puerta) {

  def hacerEntrar(grupo: Grupo): Try[Grupo] = grupo
    .agregarPuerta(entrada)
    .abrirSiguientePuerta()

  def mejorGrupo(grupos: List[Grupo]): Option[Grupo] = grupos
    .map(hacerEntrar)
    .flatMap(_.toOption)
    .maxByOption(_.puntaje)

  def nivelesParaGrupo(grupo: Grupo): Try[Int] = nivelesParaGrupo(grupo, 0)

  @tailrec
  private def nivelesParaGrupo(grupo: Grupo, niveles: Int): Try[Int] = hacerEntrar(grupo) match {
    case Success(_) => Success(niveles)
    case Failure(_) if niveles < 20 => nivelesParaGrupo(grupo.afectarHeroes(_.subirNivel()), niveles + 1)
    case Failure(exception) => Failure(exception)
  }
}
