package calabozos

case class Grupo(heroes: List[Heroe], cofre: List[Item]) {

  def vivos() = heroes.filter( heroe => heroe.estaVivo() )
  def puedeAbrir(puerta: Puerta) = vivos().exists( heroe => puerta.puedeSerAbiertaPor(heroe, cofre) )
}

sealed class Item

object Llave extends Item

object Ganzua extends Item
