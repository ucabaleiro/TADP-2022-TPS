package calabozos

sealed trait Trabajo(fuerzaBase: Double, velocidadBase: Double) {
  def fuerza(nivel: Int): Double = fuerzaBase
  def velocidad(nivel: Int): Double = velocidadBase
}

case class Guerrero(fuerzaBase: Double, velocidadBase: Double) extends Trabajo(fuerzaBase, velocidadBase) {
  override def fuerza(nivel: Int): Double = fuerzaBase + fuerzaBase * nivel * 0.2
}

case class Ladron(fuerzaBase: Double, velocidadBase: Double, habilidadBase: Int) extends Trabajo(fuerzaBase, velocidadBase) {
  def tieneHabilidad(nivel: Int, habilidad: Int): Boolean = (habilidadBase + nivel * 3) >= habilidad
}

case class Mago(fuerzaBase: Double, velocidadBase: Double, aprendizajes: List[Aprendizaje]) extends Trabajo(fuerzaBase, velocidadBase) {
  def sabeHechizo(nivel: Int, hechizo: Hechizo): Boolean = aprendizajes.exists(_(nivel, hechizo))
}

case class Aprendizaje(hechizo: Hechizo, nivel: Int) extends ((Int, Hechizo) => Boolean) {
  def apply(n: Int, h: Hechizo): Boolean = n >= nivel && h == hechizo
}
