package calabozos

// TODO: Ver si se podría validar que haya héroes vivos acá
case class Grupo(private val heroes: List[Heroe],
                 private val cofre: Cofre,
                 private val puertasConocidas: List[Puerta] = List(),
                 private val habitacionesVisitadas: List[Habitacion] = List()) {
  def heroesVivos: List[Heroe] = heroes.filter(_.estaVivo)
  def fuerza: Double = heroesVivos.map(_.fuerza).sum
  def lider: Option[Heroe] = heroesVivos.headOption
  def tieneItem(item: Item): Boolean = cofre.contains(item)
  def visitoHabitacion(habitacion: Habitacion): Boolean = habitacionesVisitadas.contains(habitacion)

  private def puertasAbribles: List[Puerta] = puertasConocidas.filter(_.puedeSerAbiertaPor(this))
  private def siguientePuerta: Option[Puerta] = lider.flatMap(_.elegirPuerta(puertasAbribles, this))

  def abrirSiguientePuerta(): Option[Grupo] = siguientePuerta.flatMap(_.serRecorridaPor(this))

  def agregarHeroe(heroe: Heroe): Grupo = copy(heroes = heroes ++ List(heroe))
  def agregarItem(item: Item): Grupo = copy(cofre = cofre ++ List(item))
  def agregarHabitacion(habitacion: Habitacion): Grupo = agregarHabitaciones(List(habitacion))
  def agregarHabitaciones(habitaciones: List[Habitacion]): Grupo = copy(habitacionesVisitadas = habitacionesVisitadas ++ habitaciones)
  def agregarPuerta(puerta: Puerta): Grupo = agregarPuertas(List(puerta))
  def agregarPuertas(puertasNuevas: List[Puerta]): Grupo = copy(puertasConocidas = puertasConocidas ++ puertasNuevas)
  def quitarPuerta(puerta: Puerta): Grupo = copy(puertasConocidas = puertasConocidas.filterNot(_ == puerta))

  def afectarHeroe(criterio: List[Heroe] => Heroe, afectacion: Heroe => Heroe): Grupo = Option(heroesVivos).map(criterio)
    .fold(this)(heroe => copy(heroes.updated(heroes.indexOf(heroe), afectacion(heroe))))

  def afectarHeroes(afectacion: Heroe => Heroe): Grupo =
    copy(heroes = heroes.map(heroe => if (heroe.estaVivo) afectacion(heroe) else heroe))

  def pelearCon(heroe: Heroe): Grupo =
    if (fuerza > heroe.fuerza) afectarHeroes(_.subirNivel())
    else afectarHeroes(_.perderSalud(heroe.fuerza / heroesVivos.size))

  def puntaje: Int = heroesVivos.size * 10
    - heroes.count(!_.estaVivo) * 5
    + cofre.size
    + heroesVivos.map(_.nivel).max

}
