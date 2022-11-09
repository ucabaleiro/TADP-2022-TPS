package calabozos

case class Heroe(statsBase: Stats, trabajo: Trabajo, criterio: Criterio) {
  def stats: Stats = statsBase(trabajo)
  def estaVivo: Boolean = stats.salud > 0
  def perderSalud(cuanto: Int): Heroe = copy(statsBase = stats.perderSalud(cuanto))
  def morir(): Heroe = copy(statsBase = stats.morir())
  def elegirPuerta(puertas: List[Puerta], grupo: Grupo): Puerta = criterio(puertas, grupo)
  def nivel: Int = stats.nivel
}

case class Stats(fuerza: Double, velocidad: Double, nivel: Int, salud: Double) extends (Trabajo => Stats) {
  def apply(trabajo: Trabajo): Stats = trabajo match {
    case _: Guerrero => copy(fuerza = fuerza + (fuerza * 0.2 * nivel))
    case _: Ladron => copy(velocidad = velocidad + (velocidad * 0.2 * nivel))
    case _: Mago => this
  }
 
  def morir(): Stats = copy(salud = 0)
  def perderSalud(cuanto: Int): Stats = copy(salud = salud - cuanto)
}

sealed trait Criterio extends ((List[Puerta], Grupo) => Puerta)

case object Heroico extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Puerta = puertas.last
}

case object Ordenado extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Puerta = puertas.head
}

case object Vidente extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Puerta = puertas.maxBy(puerta => puerta.habitacion(grupo).grupo.puntaje)
}
