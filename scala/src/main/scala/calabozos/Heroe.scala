package calabozos

case class Heroe(stats: Stats, trabajo: Trabajo, criterio: Criterio) {
  def estaVivo: Boolean = stats.salud > 0
  def perderSalud(cuanto: Int): Heroe = copy(stats = stats.perderSalud(cuanto))
  def morir: Heroe = copy(stats = stats.morir())
  def elegirPuerta(puertas: List[Puerta], grupo: Grupo): Puerta = criterio(puertas, grupo)
  def nivel: Int = stats.nivel
}

case class Stats(fuerza: Int, velocidad: Int, nivel: Int, salud: Int) {
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
