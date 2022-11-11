package calabozos

import calabozos.TestFactories.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class PuertaTest extends AnyFreeSpec {

  "Una puerta" - {
    "puede ser abierta si los héroes del grupo pueden atravesar cada uno de sus obstáculos" in {
      val puerta = unaPuerta(obstaculos = List(Cerrada, Encantada(Vislumbrar)))

      val mago = unMago(aprendizaje = Aprendizaje(Vislumbrar, 0))
      val ladron = unLadron(habilidadBase = 10)

      puerta(grupoCon(List(mago, ladron))) shouldBe true
    }

    "puede ser abierta si un solo héroe puede atravesar todos los obstáculos, aunque haya otro que no" in {
      val puerta = unaPuerta(obstaculos = List(Cerrada, Escondida))

      val ladron = unLadron(habilidadBase = 10)
      val guerrero = unGuerrero()

      puerta(grupoCon(List(ladron, guerrero))) shouldBe true
    }

    "no puede ser abierta si ninguno de los héroes puede atravesar todos sus obstáculos" in {
      val puerta = unaPuerta(obstaculos = List(Cerrada, Encantada(Vislumbrar)))

      val mago = unMago(aprendizaje = Aprendizaje(Vislumbrar, 0))
      val guerrero = unGuerrero()

      puerta(grupoCon(List(mago, guerrero))) shouldBe false
    }
  }

}
