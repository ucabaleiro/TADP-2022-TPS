package calabozos

sealed trait Trabajo(fuerzaBase: Double, velocidadBase: Double) {
  def fuerza(nivel: Int): Double = fuerzaBase
  def velocidad(nivel: Int): Double = velocidadBase
}

case class Guerrero(fuerzaBase: Double, velocidadBase: Double) extends Trabajo(fuerzaBase, velocidadBase) {
  override def fuerza(nivel: Int): Double = fuerzaBase + fuerzaBase * nivel * 0.2
}

object Guerrero {
  def unapply(heroe: Heroe): Option[Guerrero] = heroe.trabajo match {
    case guerrero: Guerrero => Some(guerrero)
    case _ => None
  }
}

case class Ladron(fuerzaBase: Double, velocidadBase: Double, habilidad: Int) extends Trabajo(fuerzaBase, velocidadBase) {
  def tieneHabilidad(nivel: Int, unaHabilidad: Int): Boolean = (habilidad + nivel * 3) >= unaHabilidad
}

object Ladron {
  def unapply(heroe: Heroe): Option[Ladron] = heroe.trabajo match {
    case ladron: Ladron => Some(ladron)
    case _ => None
  }
}

case class Mago(fuerzaBase: Double, velocidadBase: Double, aprendizajes: List[Aprendizaje]) extends Trabajo(fuerzaBase, velocidadBase) {
  def sabeHechizo(nivel: Int, hechizo: Hechizo): Boolean = aprendizajes.exists(_(nivel, hechizo))
}

object Mago {
  def unapply(heroe: Heroe): Option[Mago] = heroe.trabajo match {
    case mago: Mago => Some(mago)
    case _ => None
  }
}

case class Aprendizaje(hechizo: Hechizo, nivel: Int) extends ((Int, Hechizo) => Boolean) {
  def apply(n: Int, h: Hechizo): Boolean = n >= nivel && h == hechizo
}
