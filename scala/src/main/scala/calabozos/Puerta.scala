package calabozos

import calabozos.Grupo.Cofre

class Puerta (obstaculos: List[Obstaculo]) {
  def puedeSerAbiertaPor(grupo: Grupo) = obstaculos.forall(_.puedeSerSuperadoPor(grupo))
}

trait Obstaculo {
  def puedeSerSuperadoPor(grupo: Grupo) = grupo.heroesVivos.exists(puedeSerSuperadoPorHeroe(_, grupo.cofre))

  def puedeSerSuperadoPorHeroe(heroe: Heroe, cofre: Cofre) = heroe match {
    case l: Ladron if l.tieneHabilidad(20) => true
    case _ => puedeSerSuperadoPorHeroeSegunObstaculo(heroe, cofre)
  }

  protected def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre): Boolean
}

object Cerrada extends Obstaculo {
  override def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre) = heroe match {
    case ladron: Ladron if ladron.tieneHabilidad(10) || cofre.contains(Ganzua) => true
    case _ => cofre.contains(Llave)
  }
}

object Escondida extends Obstaculo {
  override def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre) = heroe match {
    case mago: Mago => mago.sabeHechizo(Vislumbrar)
    case ladron: Ladron => ladron.tieneHabilidad(10)
    case _ => false
  }
}

case class Encantada(hechizo: Hechizo) extends Obstaculo {
  override def puedeSerSuperadoPorHeroeSegunObstaculo(heroe: Heroe, cofre: Cofre) = heroe match {
    case mago: Mago => mago.sabeHechizo(hechizo)
    case _ => false
  }
}
