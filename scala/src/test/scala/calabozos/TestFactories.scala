package calabozos

object TestFactories {
  def grupoCon(heroe: Heroe, cofre: Cofre = List()): Grupo = Grupo(List(heroe), cofre)

  def grupoCon(heroes: List[Heroe]): Grupo = Grupo(heroes, List())

  def unLadron(habilidad: Int): Heroe = heroe(Ladron(habilidad))

  def unMago(aprendizaje: Aprendizaje, nivel: Int = 0): Heroe = heroe(Mago(List(aprendizaje)), nivel)

  def unGuerrero(fuerza: Double, salud: Int): Heroe = Heroe(Stats(fuerza, 1, 0, salud), Guerrero(), Heroico, Loquito)

  def unCofreCon(item: Item): Cofre = List(item)

  def heroe(trabajo: Trabajo, nivel: Int = 0): Heroe = Heroe(stats(nivel), trabajo, Heroico, Loquito)

  def stats(nivel: Int = 0): Stats = Stats(1, 1, nivel, 1)

  def heroeBuffeado(trabajo: Trabajo): Heroe = Heroe(statsBuff, trabajo, Heroico, Loquito)

  def statsBuff: Stats = Stats(20,20,20,20)

  def puertaConObstaculos(obstaculos: List[Obstaculo] = List()): Puerta = Puerta(obstaculos, Habitacion(List(), NoPasaNada))

  def puertaDeSalida(obstaculos: List[Obstaculo] = List()): Puerta = Puerta(obstaculos, Salida)

  def puertaCon(habitacion: Habitacion): Puerta = Puerta(List(), habitacion)

  def habitacionConSalida(): Habitacion = Habitacion(List(puertaDeSalida()), NoPasaNada)
}
