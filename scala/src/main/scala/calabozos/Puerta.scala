package calabozos

case class Puerta (obstaculos: List[Obstaculo], ubicacion: Ubicacion)
  extends (Grupo => Boolean) {
  def apply(grupo: Grupo): Boolean = obstaculos.forall(_(grupo))
}

trait Obstaculo extends (Grupo => Boolean) {
  def apply(grupo: Grupo): Boolean = grupo.heroesVivos.exists(puedeSerSuperadoPorHeroe(_, grupo.cofre))

  def puedeSerSuperadoPorHeroe(heroe: Heroe, cofre: Cofre): Boolean = heroe match {
    case Ladron(ladron) if ladron.tieneHabilidad(heroe, 20) => true
    case _ => puedeSerSuperadoPorHeroeSegunObstaculo(heroe, cofre)
  }

  protected def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre): Boolean
}

object Cerrada extends Obstaculo {
  override def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre): Boolean = heroe match {
    case Ladron(ladron) if ladron.tieneHabilidad(heroe, 10) || cofre.contains(Ganzua) => true
    case _ => cofre.contains(Llave)
  }
}

object Escondida extends Obstaculo {
  override def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre): Boolean = heroe match {
    case Mago(mago) => mago.sabeHechizo(heroe, Vislumbrar)
    case Ladron(ladron) => ladron.tieneHabilidad(heroe, 6)
    case _ => false
  }
}

case class Encantada(hechizo: Hechizo) extends Obstaculo {
  override def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre): Boolean = heroe match {
    case Mago(mago) => mago.sabeHechizo(heroe, hechizo)
    case _ => false
  }
}
