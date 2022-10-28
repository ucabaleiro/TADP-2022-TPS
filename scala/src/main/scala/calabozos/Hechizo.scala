package calabozos

case class Aprendizaje(hechizo: Hechizo, nivel: Int) {
  def hechizoAprendidoPor(mago: Mago): Option[Hechizo] = Some(hechizo).filter(_ => mago.nivel >= nivel)
}

sealed trait Hechizo

case object Vislumbrar extends Hechizo
case object Ibracadabra extends Hechizo
case object Abracaibra extends Hechizo
