package calabozos

object TestFactories {
  def grupoCon(heroe: Heroe, cofre: Cofre = List()): Grupo = Grupo(List(heroe), cofre)

  def grupoCon(heroes: List[Heroe]): Grupo = Grupo(heroes, List())

  def unLadron(habilidad: Int): Heroe = heroe(Ladron(fuerzaBase = 1, velocidadBase = 1, habilidad))

  def unMago(aprendizaje: Aprendizaje, nivel: Int = 0): Heroe = heroe(Mago(fuerzaBase = 1, velocidadBase = 1, List(aprendizaje)), nivel)

  def unGuerrero(fuerza: Double = 1, salud: Int = 1): Heroe = Heroe(salud, nivel = 0, Guerrero(fuerza, 1), criterio = Heroico, personalidad = Loquito)

  def unCofreCon(item: Item): Cofre = List(item)

  def heroe(trabajo: Trabajo = Guerrero(1, 1), nivel: Int = 0, personalidad: Personalidad = Loquito): Heroe = Heroe(salud = 1, nivel, trabajo, criterio = Heroico, personalidad)

  def heroeBuffeado(trabajo: Trabajo): Heroe = Heroe(salud = 20, nivel = 20, trabajo, Heroico, Loquito)

  def puertaConObstaculos(obstaculos: List[Obstaculo] = List()): Puerta = Puerta(obstaculos, Habitacion(List(), NoPasaNada))

  def puertaDeSalida(obstaculos: List[Obstaculo] = List()): Puerta = Puerta(obstaculos, Salida)

  def puertaCon(habitacion: Habitacion): Puerta = Puerta(List(), habitacion)

  def habitacionConSalida(situacion: Situacion = NoPasaNada): Habitacion = Habitacion(List(puertaDeSalida()), situacion)
}
