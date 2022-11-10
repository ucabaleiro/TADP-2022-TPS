package calabozos

// TODO: Refactorizar la relación del Héroe con el Trabajo (https://www.youtube.com/watch?v=P-3IGjZiPm0)
case class Heroe(statsBase: Stats, trabajo: Trabajo, criterio: Criterio, personalidad: Personalidad) {
  def stats: Stats = statsBase(trabajo)
  def estaVivo: Boolean = statsBase(trabajo).salud > 0
  def elegirPuerta(puertas: List[Puerta], grupo: Grupo): Option[Puerta] = criterio(puertas, grupo)
  def nivel: Int = statsBase.nivel
  def leAgradaGrupo(grupo: Grupo): Boolean = personalidad(grupo)

  def afectarStats(fn: Stats => Stats): Heroe = copy(statsBase = fn(statsBase))
}


case class Stats(fuerza: Double, velocidad: Double, nivel: Int, salud: Double) extends (Trabajo => Stats) {
  def apply(trabajo: Trabajo): Stats = trabajo match {
    case _: Guerrero => copy(fuerza = fuerza + (fuerza * 0.2 * nivel))
    case _ => this
  }

  def morir(): Stats = copy(salud = 0)
  def perderSalud(cuanto: Double): Stats = copy(salud = salud - cuanto)
  def subirNivel: Stats = copy(nivel = nivel + 1)
}
