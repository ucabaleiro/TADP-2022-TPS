package calabozos

object Grupo {
  type Cofre = List[Item]
}

case class Grupo(private val heroes: List[Heroe], cofre: Grupo.Cofre) {
  def heroesVivos = heroes.filter(_.estaVivo)
}

sealed class Item

object Llave extends Item

object Ganzua extends Item
