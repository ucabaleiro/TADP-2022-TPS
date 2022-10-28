package calabozos

sealed trait Heroe(fuerza: Int, velocidad: Int, nivel: Int, salud: Int) {
  def estaVivo() = true
}

case class Guerrero(fuerza: Int, velocidad: Int, nivel: Int, salud: Int)
  extends Heroe(fuerza, velocidad, nivel, salud)

case class Ladron(fuerza: Int, velocidad: Int, nivel: Int, salud: Int, habilidad: Int)
  extends Heroe(fuerza, velocidad, nivel, salud)

case class Mago(fuerza: Int, velocidad: Int, nivel: Int, salud: Int, hechizosYNiveles: List[HechizoYNivel])
  extends Heroe(fuerza, velocidad, nivel, salud) {
  def sabeHechizo(hechizo: Hechizo): Boolean = hechizosYNiveles
    .exists( hechizoYNivel => hechizoYNivel.hechizo == hechizo && hechizoYNivel.nivel <= nivel )
}
