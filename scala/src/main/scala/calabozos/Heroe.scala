package calabozos

case class Heroe(salud: Double, nivel: Int, fuerzaBase: Double, velocidadBase: Double, trabajo: Trabajo, criterio: Criterio, personalidad: Personalidad) {
  def estaVivo: Boolean = salud > 0
  def fuerza: Double = trabajo match {
    case Guerrero() => fuerzaBase + fuerzaBase * nivel * 0.2
    case _ => fuerzaBase
  }
  def velocidad: Double = velocidadBase
  def elegirPuerta(puertas: List[Puerta], grupo: Grupo): Option[Puerta] = criterio(puertas, grupo)
  def leAgradaGrupo(grupo: Grupo): Boolean = personalidad(grupo)

  def perderSalud(cuanto: Double): Heroe = copy(salud = salud - cuanto)
  def morir(): Heroe = this.copy(salud = 0)
  def subirNivel(): Heroe = this.copy(nivel = nivel + 1)
}
