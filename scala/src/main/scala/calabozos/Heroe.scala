package calabozos

case class Heroe(salud: Double, nivel: Int, trabajo: Trabajo, criterio: Criterio, personalidad: Personalidad) {
  def estaVivo: Boolean = salud > 0
  def fuerza: Double = trabajo.fuerza(nivel)
  def velocidad: Double = trabajo.velocidad(nivel)
  def elegirPuerta(puertas: List[Puerta], grupo: Grupo): Option[Puerta] = criterio(puertas, grupo)
  def leAgradaGrupo(grupo: Grupo): Boolean = personalidad(grupo)

  def perderSalud(cuanto: Double): Heroe = copy(salud = salud - cuanto)
  def morir(): Heroe = this.copy(salud = 0)
  def subirNivel(): Heroe = this.copy(nivel = nivel + 1)
}
