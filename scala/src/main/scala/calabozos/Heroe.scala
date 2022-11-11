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

  def perderSalud(cuanto: Double): Heroe = modificar(salud = salud - cuanto)
  def morir(): Heroe = modificar(salud = 0)
  def subirNivel(): Heroe = modificar(nivel = nivel + 1)

  protected def modificar(salud: Double = salud, nivel: Int = nivel): Heroe
}

case class Guerrero(override val salud: Double,
                    override val nivel: Int,
                    fuerzaBase: Double,
                    velocidadBase: Double,
                    override val criterio: Criterio,
                    override val personalidad: Personalidad) extends Heroe(salud, nivel, fuerzaBase, velocidadBase, criterio, personalidad) {
  override def fuerza: Double = fuerzaBase + fuerzaBase * nivel * 0.2

  override def modificar(salud: Double, nivel: Int): Heroe = this.copy(salud = salud, nivel = nivel)
}

case class Ladron(override val salud: Double,
                  override val nivel: Int,
                  fuerzaBase: Double,
                  velocidadBase: Double,
                  habilidadBase: Double,
                  override val criterio: Criterio,
                  override val personalidad: Personalidad) extends Heroe(salud, nivel, fuerzaBase, velocidadBase, criterio, personalidad) {
  def tieneHabilidad(habilidad: Int): Boolean = (habilidadBase + nivel * 3) >= habilidad

  override def modificar(salud: Double, nivel: Int): Heroe = this.copy(salud = salud, nivel = nivel)
}

case class Mago(override val salud: Double,
                override val nivel: Int,
                fuerzaBase: Double,
                velocidadBase: Double,
                aprendizajes: List[Aprendizaje],
                override val criterio: Criterio,
                override val personalidad: Personalidad) extends Heroe(salud, nivel, fuerzaBase, velocidadBase, criterio, personalidad) {
  def sabeHechizo(hechizo: Hechizo): Boolean = aprendizajes.exists(_(nivel, hechizo))

  override def modificar(salud: Double, nivel: Int): Heroe = this.copy(salud = salud, nivel = nivel)
}

case class Aprendizaje(hechizo: Hechizo, nivel: Int) extends ((Int, Hechizo) => Boolean) {
  def apply(n: Int, h: Hechizo): Boolean = n >= nivel && h == hechizo
}
