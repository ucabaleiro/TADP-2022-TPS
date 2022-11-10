package calabozos

object TestFactories {
  def grupoCon(heroe: Heroe, cofre: Cofre = List()): Grupo = Grupo(List(heroe), cofre)

  def grupoCon(heroes: List[Heroe]): Grupo = Grupo(heroes, List())

  def unLadron(habilidad: Int): Heroe = heroe(Ladron(habilidad))

  def unMago(aprendizaje: Aprendizaje): Heroe = heroe(Mago(List(aprendizaje)))

  def unCofreCon(item: Item): Cofre = List(item)

  def heroe(trabajo: Trabajo): Heroe = Heroe(stats, trabajo, Heroico, Loquito)

  def stats: Stats = Stats(1, 1, 0, 1)

  def heroeBuffeado(trabajo: Trabajo): Heroe = Heroe(statsBuff, trabajo, Heroico, Loquito)

  def statsBuff: Stats = Stats(20,20,20,20)

  def puertaConObstaculos(obstaculos: List[Obstaculo] = List()): Puerta = Puerta(obstaculos, Habitacion(List(), NoPasaNada))


}
