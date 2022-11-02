package calabozos

sealed trait Habitacion extends (Grupo => Grupo)

case object NoPasaNada extends Habitacion {
  def apply(grupo: Grupo): Grupo = grupo
}

case class TesoroPerdido(tesoro: Item) extends Habitacion {
  def apply(grupo: Grupo): Grupo = grupo.agregarItem(tesoro)
}

case object MuchosDardos extends Habitacion {
  def apply(grupo: Grupo): Grupo = grupo.copy(heroes = grupo.heroes.map(_.perderSalud(10)))  
}

case object TrampaDeLeones extends Habitacion {
  def apply(grupo: Grupo): Grupo = ???
}

case class Encuentro(heroe: Heroe) extends Habitacion {
  def apply(grupo: Grupo): Grupo = ???
}
