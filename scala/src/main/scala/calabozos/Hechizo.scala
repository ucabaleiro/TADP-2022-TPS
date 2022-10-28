package calabozos

case class HechizoYNivel(hechizo: Hechizo, nivel: Int)

sealed trait Hechizo

case object Vislumbrar extends Hechizo
case object Ibracadabra extends Hechizo
case object Abracaibra extends Hechizo
