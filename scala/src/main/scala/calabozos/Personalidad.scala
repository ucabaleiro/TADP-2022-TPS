package calabozos


sealed trait Personalidad extends (Grupo => Boolean)

case object Introvertido extends Personalidad {
  override def apply(grupo: Grupo): Boolean = grupo.heroesVivos.length <= 3
}

case object Bigote extends Personalidad {
  override def apply(grupo: Grupo): Boolean = ???

}

case class Interesado(val item: Item) extends Personalidad {
  override def apply(grupo: Grupo): Boolean = ???
}

case object Loquito extends Personalidad {
  override def apply(grupo: Grupo): Boolean = false
}