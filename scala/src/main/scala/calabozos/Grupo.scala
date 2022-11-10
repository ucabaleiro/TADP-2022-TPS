package calabozos

case class Grupo(heroes: List[Heroe], cofre: Cofre, puertasConocidas: List[Puerta] = List()) {
  def hayVivos: Boolean = heroes.exists(_.estaVivo)
  def heroesVivos: List[Heroe] = heroes.filter(_.estaVivo)
  def fuerza: Double = heroesVivos.map(_.stats.fuerza).sum
  def lider: Option[Heroe] = Option.when(hayVivos)(heroesVivos.head)

  private def puertasAbribles: List[Puerta] = puertasConocidas.filter(_(this))
  private def siguientePuerta: Option[Puerta] = lider.flatMap(_.elegirPuerta(puertasAbribles, this))

  def abrirSiguientePuerta(): Option[Grupo] = siguientePuerta.flatMap(puerta => puerta.ubicacion(quitarPuerta(puerta)))

  def agregarHeroe(heroe: Heroe): Grupo = copy(heroes = heroes ++ List(heroe))
  def agregarItem(item: Item): Grupo = copy(cofre = cofre ++ List(item))
  def agregarPuerta(puerta: Puerta): Grupo = copy(puertasConocidas = puertasConocidas ++ List(puerta))
  def agregarPuertas(puertasNuevas: List[Puerta]): Grupo = copy(puertasConocidas = puertasConocidas ++ puertasNuevas)
  private def quitarPuerta(puerta: Puerta): Grupo = copy(puertasConocidas = puertasConocidas.filterNot(_ == puerta))

  def afectarHeroe(criterio: List[Heroe] => Heroe, afectacion: Heroe => Heroe): Grupo = Option.when(hayVivos) {
    val heroe = criterio(heroesVivos)
    val nuevosHeroes = heroes.updated(heroes.indexOf(heroe), afectacion(heroe))
    copy(heroes = nuevosHeroes)
  }.getOrElse(this)

  def afectarHeroes(afectacion: Stats => Stats): Grupo =
    copy(heroes = heroes.map(heroe => if (heroe.estaVivo) heroe.afectarStats(afectacion) else heroe))

  def pelearCon(heroe: Heroe): Grupo =
    if (fuerza > heroe.stats.fuerza) afectarHeroes(_.subirNivel)
    else afectarHeroes(_.perderSalud(heroe.stats.fuerza / heroesVivos.size))

  def puntaje: Int = heroesVivos.size * 10
    - heroes.count(!_.estaVivo) * 5
    + cofre.size
    + heroesVivos.map(_.nivel).max

}
