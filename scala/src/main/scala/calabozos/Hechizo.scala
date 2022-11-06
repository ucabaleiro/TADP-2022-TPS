package calabozos

case class Aprendizaje(hechizo: Hechizo, nivel: Int) extends ((Heroe, Hechizo) => Boolean) {
  def apply(heroe: Heroe, h: Hechizo): Boolean = heroe.nivel >= nivel && h == hechizo
}

sealed trait Hechizo

case object Vislumbrar extends Hechizo
case object Ibracadabra extends Hechizo
