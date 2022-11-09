package calabozos

sealed trait Personalidad extends (Grupo => Boolean)

case object Introvertido extends Personalidad {
  override def apply(grupo: Grupo): Boolean = grupo.heroesVivos.length <= 3
}

case object Bigote extends Personalidad {
  override def apply(grupo: Grupo): Boolean = grupo.heroes.exists {
    case Ladron(_) => true
    case _ => false
  }
}

case class Interesado(item: Item) extends Personalidad {
  override def apply(grupo: Grupo): Boolean = grupo.cofre.contains(item)
}

case object Loquito extends Personalidad {
  override def apply(grupo: Grupo): Boolean = false
}
