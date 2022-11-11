package calabozos

object TestFactories {
  def grupoCon(heroe: Heroe, cofre: Cofre = List()): Grupo = Grupo(List(heroe), cofre)

  def grupoCon(heroes: List[Heroe]): Grupo = Grupo(heroes, List())

  def grupoCon(item: Item): Grupo = Grupo(List(unGuerrero()), List(item))

  def unLadron(salud: Double = 1,
               nivel: Int = 0,
               fuerzaBase: Double = 1,
               velocidadBase: Double = 1,
               habilidadBase: Int = 1,
               criterio: Criterio = Heroico,
               personalidad: Personalidad = Loquito): Heroe =
    Ladron(salud, nivel, fuerzaBase, velocidadBase, habilidadBase, criterio, personalidad)

  def unMago(salud: Double = 1,
             nivel: Int = 0,
             fuerzaBase: Double = 1,
             velocidadBase: Double = 1,
             aprendizaje: Aprendizaje = null,
             criterio: Criterio = Heroico,
             personalidad: Personalidad = Loquito): Heroe =
    Mago(salud, nivel, fuerzaBase, velocidadBase, aprendizajes = aprendizaje match { case null => List(); case _ => List(aprendizaje) }, criterio, personalidad)

  def unGuerrero(salud: Double = 1,
                 nivel: Int = 0,
                 fuerzaBase: Double = 1,
                 velocidadBase: Double = 1,
                 criterio: Criterio = Heroico,
                 personalidad: Personalidad = Loquito): Heroe =
    Guerrero(salud, nivel, fuerzaBase, velocidadBase, criterio, personalidad)

  def unCofreCon(item: Item): Cofre = List(item)

  def unaHabitacion(puertas: List[Puerta] = List(), situacion: Situacion = NoPasaNada): Habitacion =
    Habitacion(puertas, situacion)

  def unaPuerta(obstaculos: List[Obstaculo] = List(), ubicacion: Ubicacion = unaHabitacion()): Puerta =
    Puerta(obstaculos, ubicacion)

  def unaPuertaDeSalida(obstaculo: Obstaculo = null): Puerta =
    Puerta(obstaculo match { case null => List(); case _ => List(obstaculo) }, Salida)

  def unaHabitacionConSalida(obstaculo: Obstaculo = null, situacion: Situacion = NoPasaNada): Habitacion =
    Habitacion(List(unaPuertaDeSalida(obstaculo)), situacion)
}
