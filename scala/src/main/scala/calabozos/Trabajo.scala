package calabozos

sealed trait Trabajo

case class Guerrero() extends Trabajo

object Guerrero {
  def unapply(trabajo: Trabajo): Boolean = trabajo.isInstanceOf[Guerrero]
  def unapply(heroe: Heroe): Option[Guerrero] = heroe.trabajo match {
    case guerrero: Guerrero => Some(guerrero)
    case _ => None
  }
}

case class Ladron(habilidad: Int) extends Trabajo {
  def tieneHabilidad(nivel: Int, unaHabilidad: Int): Boolean = (habilidad + nivel * 3) >= unaHabilidad
}

object Ladron {
  def unapply(trabajo: Trabajo): Boolean = trabajo.isInstanceOf[Ladron]
  def unapply(heroe: Heroe): Option[Ladron] = heroe.trabajo match {
    case ladron: Ladron => Some(ladron)
    case _ => None
  }
}

case class Mago(aprendizajes: List[Aprendizaje]) extends Trabajo {
  def sabeHechizo(nivel: Int, hechizo: Hechizo): Boolean = aprendizajes.exists(_(nivel, hechizo))
}

object Mago {
  def unapply(trabajo: Trabajo): Boolean = trabajo.isInstanceOf[Mago]
  def unapply(heroe: Heroe): Option[Mago] = heroe.trabajo match {
    case mago: Mago => Some(mago)
    case _ => None
  }
}

case class Aprendizaje(hechizo: Hechizo, nivel: Int) extends ((Int, Hechizo) => Boolean) {
  def apply(n: Int, h: Hechizo): Boolean = n >= nivel && h == hechizo
}
