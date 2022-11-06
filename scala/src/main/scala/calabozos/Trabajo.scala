package calabozos

sealed trait Trabajo

case class Guerrero() extends Trabajo

object Guerrero {
  def unapply(heroe: Heroe): Option[Guerrero] = heroe.trabajo match {
    case guerrero: Guerrero => Some(guerrero)
    case _ => None
  }
}

case class Ladron(habilidad: Int) extends Trabajo {
  def tieneHabilidad(habilidad: Int): Boolean = this.habilidad >= habilidad
}

object Ladron {
  def unapply(heroe: Heroe): Option[Ladron] = heroe.trabajo match {
    case ladron: Ladron => Some(ladron)
    case _ => None
  }
}

case class Mago(aprendizajes: List[Aprendizaje]) extends Trabajo {
  def sabeHechizo(heroe: Heroe, hechizo: Hechizo): Boolean = aprendizajes.exists(_(heroe, hechizo))
}

object Mago {
  def unapply(heroe: Heroe): Option[Mago] = heroe.trabajo match {
    case mago: Mago => Some(mago)
    case _ => None
  }
}
