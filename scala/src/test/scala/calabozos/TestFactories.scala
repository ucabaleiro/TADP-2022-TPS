package calabozos

import calabozos.Grupo.Cofre

object TestFactories {
  def grupoCon(heroe: Heroe, cofre: Cofre = List()): Grupo = Grupo(List(heroe), cofre)

  def grupoCon(heroes: List[Heroe]): Grupo = Grupo(heroes, List())

  def unLadron(habilidad: Int): Heroe = heroe(Ladron(habilidad))

  def unMago(aprendizaje: Aprendizaje): Heroe = heroe(Mago(List(aprendizaje)))

  def unCofreCon(item: Item): Cofre = List(item)

  def heroe(trabajo: Trabajo): Heroe = Heroe(stats, trabajo, Heroico)

  def stats: Stats = Stats(1, 1, 1, 1)

  def puertaConObstaculos(obstaculos: List[Obstaculo] = List()): Puerta = Puerta(obstaculos, Habitacion(List(), NoPasaNada), false)
}
