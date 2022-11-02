package calabozos

sealed trait Heroe(val fuerza: Int, val velocidad: Int, val nivel: Int, val salud: Int, val criterio: Criterio) {
  def estaVivo = salud > 0
  def perderSalud(cuanto: Int): Heroe
  def morir: Heroe
  def elegirPuerta(puertas: List[Puerta], grupo: Grupo): Puerta = criterio(puertas, grupo)
}

case class Guerrero(fuerza: Int, velocidad: Int, nivel: Int, salud: Int, criterio: Criterio)
  extends Heroe(fuerza, velocidad, nivel, salud, criterio) {
    override def perderSalud(cuanto: Int) = copy(salud = salud - cuanto)
    def morir: Heroe = copy(salud = 0)
}

object Ladron {
  def unapply(grupo: Grupo) = Option.when(grupo.heroesVivos.exists(_.isInstanceOf[Ladron]))(grupo)
}

case class Ladron(fuerza: Int, velocidad: Int, nivel: Int, salud: Int, criterio: Criterio, val habilidad: Int)
  extends Heroe(fuerza, velocidad, nivel, salud, criterio) {
  def tieneHabilidad(habilidad: Int) = this.habilidad >= habilidad
  override def perderSalud(cuanto: Int) = copy(salud = salud - cuanto)
  def morir: Heroe = copy(salud = 0)

}

case class Mago(fuerza: Int, velocidad: Int, nivel: Int, salud: Int, criterio: Criterio, private val aprendizajes: List[Aprendizaje])
  extends Heroe(fuerza, velocidad, nivel, salud, criterio) {
  def hechizos: List[Hechizo] = aprendizajes.flatMap(_.hechizoAprendidoPor(this))
  def sabeHechizo(hechizo: Hechizo): Boolean = hechizos.contains(hechizo)
  override def perderSalud(cuanto: Int) = copy(salud = salud - cuanto)
  def morir: Heroe = copy(salud = 0)
}

sealed trait Criterio extends ((List[Puerta], Grupo) => Puerta)

case object Heroico extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Puerta = puertas.last
}

case object Ordenado extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Puerta = puertas.head
}

case object Vidente extends Criterio {
  def apply(puertas: List[Puerta], grupo: Grupo): Puerta = puertas.maxBy(puerta => puerta.habitacion.apply(grupo).grupo.puntaje)
}
