package calabozos

case class Grupo(heroes: List[Heroe], cofre: Cofre, puertasConocidas: List[Puerta] = List()) {
  def hayVivos: Boolean = heroes.exists(heroe => heroe.estaVivo)
  def heroesVivos: List[Heroe] = heroes.filter(_.estaVivo)
  def lider: Option[Heroe] = Option.when(hayVivos)(heroesVivos.head)
  def elMasLento: Heroe = heroesVivos.minBy(_.stats.velocidad)

  def puertasAbribles: List[Puerta] = puertasConocidas.filter(_(this))
  def puedeAbrirPuerta: Boolean = puertasConocidas.exists(_(this))
  def siguientePuerta: Option[Puerta] = lider.map(_.elegirPuerta(puertasAbribles, this))

  def agregarHeroe(heroe: Heroe): Grupo = copy(heroes = heroes ++ List(heroe))
  def agregarItem(item: Item): Grupo = copy(cofre = cofre ++ List(item))
  def agregarPuertas(puertasNuevas: List[Puerta]): Grupo = copy(puertasConocidas = puertasConocidas ++ puertasNuevas)

  def puntaje: Int = heroesVivos.size * 10 
    - heroes.count(!_.estaVivo) * 5
    + cofre.size
    + heroesVivos.map(_.nivel).max
}
