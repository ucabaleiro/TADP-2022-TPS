package calabozos

class Puerta (obstaculos: List[Obstaculo]) {
  def puedeSerAbiertaPor(heroe: Heroe, cofre: List[Item]): Boolean = {
    heroe match {
      case ladron: Ladron if ladron.habilidad >= 20 => true
      case _ => obstaculos.forall(_.puedeSerSuperadoPor(heroe, cofre))
    }
  }
}

trait Obstaculo {
  def puedeSerSuperadoPor(heroe: Heroe, cofre: List[Item]): Boolean
}

object Cerrada extends Obstaculo {
  override def puedeSerSuperadoPor(heroe: Heroe, cofre: List[Item]): Boolean = {
    (heroe, cofre) match {
      case (ladri: Ladron, cofre) if ladri.habilidad >= 10 || cofre.contains(Ganzua) => true
      case ( _, cofre) => cofre.contains(Llave)
    }
  }
}

object Escondida extends Obstaculo {
  override def puedeSerSuperadoPor(heroe: Heroe, cofre: List[Item]): Boolean = {
    heroe match {
      case mago: Mago => mago.sabeHechizo(Vislumbrar)
      case ladron: Ladron => ladron.habilidad >= 10
      case _ => false
    }
  }
}

case class Encantada(hechizo: Hechizo) extends Obstaculo {
  override def puedeSerSuperadoPor(heroe: Heroe, cofre: List[Item]): Boolean = {
    heroe match {
      case mago: Mago => mago.sabeHechizo(hechizo)
      case _ => false
    }
  }
}
