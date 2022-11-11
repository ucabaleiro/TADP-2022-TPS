package calabozos

sealed trait Trabajo

case class Guerrero() extends Trabajo

case class Ladron(habilidadBase: Int) extends Trabajo {
  def tieneHabilidad(nivel: Int, habilidad: Int): Boolean = (habilidadBase + nivel * 3) >= habilidad
}

case class Mago(aprendizajes: List[Aprendizaje]) extends Trabajo {
  def sabeHechizo(nivel: Int, hechizo: Hechizo): Boolean = aprendizajes.exists(_(nivel, hechizo))
}

case class Aprendizaje(hechizo: Hechizo, nivel: Int) extends ((Int, Hechizo) => Boolean) {
  def apply(n: Int, h: Hechizo): Boolean = n >= nivel && h == hechizo
}
