package calabozos

case class Heroe(val stats: Stats, val trabajo: Trabajo, val criterio: Criterio) {
  def estaVivo = stats.salud > 0
  def perderSalud(cuanto: Int) = copy(stats = stats.perderSalud(cuanto))
  def morir = copy(stats = stats.morir())
  def elegirPuerta(puertas: List[Puerta], grupo: Grupo): Puerta = criterio(puertas, grupo)
  def nivel = stats.nivel
}

case class Stats(val fuerza: Int, val velocidad: Int, val nivel: Int, val salud: Int) {
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
