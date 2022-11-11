package calabozos

class Puerta(val obstaculos: List[Obstaculo], val ubicacion: Ubicacion)
  extends (Grupo => Boolean) {
  def apply(grupo: Grupo): Boolean = obstaculos.forall(_(grupo))
}

trait Obstaculo extends (Grupo => Boolean) {
  def apply(grupo: Grupo): Boolean = grupo.heroesVivos.exists(puedeSerSuperadoPorHeroe(_, grupo.cofre))

  def puedeSerSuperadoPorHeroe(heroe: Heroe, cofre: Cofre): Boolean = heroe match {
    case ladron: Ladron if ladron.tieneHabilidad(20) => true
    case _ => puedeSerSuperadoPorHeroeSegunObstaculo(heroe, cofre)
  }

  protected def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre): Boolean
}

object Cerrada extends Obstaculo {
  override def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre): Boolean = heroe match {
    case ladron: Ladron if ladron.tieneHabilidad(10) || cofre.contains(Ganzua) => true
    case _ => cofre.contains(Llave)
  }
}

object Escondida extends Obstaculo {
  override def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre): Boolean = heroe match {
    case mago: Mago => mago.sabeHechizo(Vislumbrar)
    case ladron: Ladron => ladron.tieneHabilidad(6)
    case _ => false
  }
}

case class Encantada(hechizo: Hechizo) extends Obstaculo {
  override def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre): Boolean = heroe match {
    case mago: Mago => mago.sabeHechizo(hechizo)
    case _ => false
  }
}
