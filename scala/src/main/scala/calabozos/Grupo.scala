package calabozos

case class Grupo(heroes: List[Heroe], cofre: List[Item]) {

  def vivos() = heroes.filter(_.estaVivo())
  def puedeAbrir(puerta: Puerta) = vivos().exists(puerta.puedeSerAbiertaPor(_, cofre))
}

sealed class Item

object Llave extends Item

object Ganzua extends Item
