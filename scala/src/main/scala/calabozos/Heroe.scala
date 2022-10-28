package calabozos

sealed trait Heroe(fuerza: Int, velocidad: Int, nivel: Int, salud: Int) {
  def estaVivo = salud > 0
}

case class Guerrero(fuerza: Int, velocidad: Int, nivel: Int, salud: Int)
  extends Heroe(fuerza, velocidad, nivel, salud)

case class Ladron(fuerza: Int, velocidad: Int, nivel: Int, salud: Int, habilidad: Int)
  extends Heroe(fuerza, velocidad, nivel, salud) {
  def tieneHabilidad(habilidad: Int) = this.habilidad >= habilidad
}

case class Mago(fuerza: Int, velocidad: Int, nivel: Int, salud: Int, private val aprendizajes: List[Aprendizaje])
  extends Heroe(fuerza, velocidad, nivel, salud) {
  def hechizos: List[Hechizo] = aprendizajes.flatMap(_.hechizoAprendidoPor(this))
  def sabeHechizo(hechizo: Hechizo): Boolean = hechizos.contains(hechizo)
}
