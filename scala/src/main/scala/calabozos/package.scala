package object calabozos {
  sealed trait Item
  object Llave extends Item
  object Ganzua extends Item

  sealed trait Hechizo
  case object Vislumbrar extends Hechizo
  case object Ibracadabra extends Hechizo
  
  type Cofre = List[Item]
}
