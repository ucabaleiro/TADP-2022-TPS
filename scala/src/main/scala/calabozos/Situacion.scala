package calabozos

// TODO: Ver si debería devolver Try[Grupo]
sealed trait Situacion extends (Grupo => Grupo)

case object NoPasaNada extends Situacion {
  def apply(grupo: Grupo): Grupo = grupo
}

case class TesoroPerdido(tesoro: Item) extends Situacion {
  def apply(grupo: Grupo): Grupo = grupo.agregarItem(tesoro)
}

case object MuchosDardos extends Situacion {
  def apply(grupo: Grupo): Grupo = grupo.afectarHeroes(_.perderSalud(10))
}

case object TrampaDeLeones extends Situacion {
  def apply(grupo: Grupo): Grupo = {
    grupo.afectarHeroe(_.minBy(_.velocidad), _.morir())
  }
}

case class Encuentro(heroe: Heroe) extends Situacion {
  def apply(grupo: Grupo): Grupo = {
    val grupoConHeroe = grupo.agregarHeroe(heroe)
    if (grupo.lider.exists(_.leAgradaGrupo(grupoConHeroe)) && heroe.leAgradaGrupo(grupo))
      grupoConHeroe
    else
      grupo.pelearCon(heroe)
  }
}
