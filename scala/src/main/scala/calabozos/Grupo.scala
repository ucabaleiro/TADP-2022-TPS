package calabozos

object Grupo {
  type Cofre = List[Item]
}

case class Grupo(val heroes: List[Heroe], val cofre: Grupo.Cofre) {
  def heroesVivos = heroes.filter(_.estaVivo)

  def agregarHeroe(heroe: Heroe): Grupo = copy(heroes = heroes ++ List(heroe))
  def agregarItem(item: Item): Grupo = copy(cofre = cofre ++ List(item))

  def puntaje(): Int = heroesVivos.count() * 10 - 

}

sealed class Item

object Llave extends Item

object Ganzua extends Item
