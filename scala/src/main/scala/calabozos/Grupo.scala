package calabozos

object Grupo {
  type Cofre = List[Item]
}

case class Grupo(val heroes: List[Heroe], val cofre: Grupo.Cofre, val puertasConocidas: List[Puerta] = List()) {
  def hayVivos = heroes.exists(heroe => heroe.estaVivo)
  def heroesVivos = heroes.filter(_.estaVivo)
  def lider = Option.when(hayVivos)(heroesVivos.head)
  def elMasLento = heroesVivos.minBy(_.velocidad)

  def puertasAbribles = puertasConocidas.filter(_(this))
  def puedeAbrirPuerta = puertasConocidas.exists(_(this))
  def siguientePuerta = lider.map(_.elegirPuerta(puertasAbribles, this))

  def agregarHeroe(heroe: Heroe) = copy(heroes = heroes ++ List(heroe))
  def agregarItem(item: Item) = copy(cofre = cofre ++ List(item))
  def agregarPuertas(puertasNuevas: List[Puerta]) = copy(puertasConocidas = puertasConocidas ++ puertasNuevas)

  def puntaje: Int = heroesVivos.size * 10 
    - heroes.filter(!_.estaVivo).size * 5
    + cofre.size
    + heroesVivos.map(_.nivel).max
}

sealed class Item

object Llave extends Item

object Ganzua extends Item
