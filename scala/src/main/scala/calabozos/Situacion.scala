package calabozos

sealed trait Situacion extends (Grupo => Grupo)

case object NoPasaNada extends Situacion {
  def apply(grupo: Grupo): Grupo = grupo
}

case class TesoroPerdido(tesoro: Item) extends Situacion {
  def apply(grupo: Grupo): Grupo = grupo.agregarItem(tesoro)
}

case object MuchosDardos extends Situacion {
  def apply(grupo: Grupo): Grupo = grupo.copy(heroes = grupo.heroes.map(_.perderSalud(10)))  
}

case object TrampaDeLeones extends Situacion {
  def apply(grupo: Grupo): Grupo = {
    val elMasLento = grupo.elMasLento
    val indiceLento = grupo.heroes.indexOf(elMasLento)
    grupo.copy(heroes = grupo.heroes.updated(indiceLento, elMasLento.morir()))
  }
}

case class Encuentro(heroe: Heroe) extends Situacion {
  def apply(grupo: Grupo): Grupo = ???
}
