package calabozos

sealed trait Heroe(val salud: Double,
                   val nivel: Int,
                   fuerzaBase: Double,
                   velocidadBase: Double,
                   val criterio: Criterio,
                   val personalidad: Personalidad) {
  def estaVivo: Boolean = salud > 0
  def fuerza: Double = fuerzaBase
  def velocidad: Double = velocidadBase

  def elegirPuerta(puertas: List[Puerta], grupo: Grupo): Option[Puerta] = criterio(puertas, grupo)
  def leAgradaGrupo(grupo: Grupo): Boolean = personalidad(grupo)

  def perderSalud(cuanto: Double): Heroe = copy(salud = salud - cuanto)
  def morir(): Heroe = this.copy(salud = 0)
  def subirNivel(): Heroe = this.copy(nivel = nivel + 1)

  def copy(salud: Double = salud, nivel: Int = nivel): Heroe
}

object Guerrero {
  def unapply(heroe: Heroe): Option[Guerrero] = Option.when(heroe.isInstanceOf[Guerrero])(heroe.asInstanceOf[Guerrero])
}

class Guerrero(override val salud: Double,
               override val nivel: Int,
               fuerzaBase: Double,
               velocidadBase: Double,
               override val criterio: Criterio,
               override val personalidad: Personalidad) extends Heroe(salud, nivel, fuerzaBase, velocidadBase, criterio, personalidad) {
  override def fuerza: Double = fuerzaBase + fuerzaBase * nivel * 0.2

  override def copy(salud: Double = salud, nivel: Int = nivel): Heroe = Guerrero(salud, nivel, fuerzaBase, velocidadBase, criterio, personalidad)
}

object Ladron {
  def unapply(heroe: Heroe): Option[Ladron] = Option.when(heroe.isInstanceOf[Ladron])(heroe.asInstanceOf[Ladron])
}

class Ladron(override val salud: Double,
             override val nivel: Int,
             fuerzaBase: Double,
             velocidadBase: Double,
             habilidadBase: Double,
             override val criterio: Criterio,
             override val personalidad: Personalidad) extends Heroe(salud, nivel, fuerzaBase, velocidadBase, criterio, personalidad) {
  def tieneHabilidad(habilidad: Int): Boolean = (habilidadBase + nivel * 3) >= habilidad

  override def copy(salud: Double = salud, nivel: Int = nivel): Heroe = Ladron(salud, nivel, fuerzaBase, velocidadBase, habilidadBase, criterio, personalidad)
}

object Mago {
  def unapply(heroe: Heroe): Option[Mago] = Option.when(heroe.isInstanceOf[Mago])(heroe.asInstanceOf[Mago])
}

class Mago(override val salud: Double,
           override val nivel: Int,
           fuerzaBase: Double,
           velocidadBase: Double,
           habilidadBase: Double,
           aprendizajes: List[Aprendizaje],
           override val criterio: Criterio,
           override val personalidad: Personalidad) extends Heroe(salud, nivel, fuerzaBase, velocidadBase, criterio, personalidad) {
  def sabeHechizo(hechizo: Hechizo): Boolean = aprendizajes.exists(_(nivel, hechizo))

  override def copy(salud: Double = salud, nivel: Int = nivel): Heroe = Mago(salud, nivel, fuerzaBase, velocidadBase, habilidadBase, aprendizajes, criterio, personalidad)
}

case class Aprendizaje(hechizo: Hechizo, nivel: Int) extends ((Int, Hechizo) => Boolean) {
  def apply(n: Int, h: Hechizo): Boolean = n >= nivel && h == hechizo
}
