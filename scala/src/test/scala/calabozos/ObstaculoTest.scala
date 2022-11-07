package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class ObstaculoTest extends AnyFreeSpec {

  "Un obstaculo" - {
    "cuando esta cerrado" - {
      "puede ser superado por un ladron con habilidad de mas de 10" in {
        Cerrada(grupoCon(unLadron(11))) shouldBe true
      }

      "puede ser superado por un ladron con una ganzúa" in {
        Cerrada(grupoCon(unLadron(9), unCofreCon(Ganzua))) shouldBe true
      }

      "no puede ser superado por un ladron sin ganzúa ni habilidad mayor a 10" in {
        Cerrada(grupoCon(unLadron(9))) shouldBe false
      }

      "puede ser superado por cualquier heroe con una llave" in {
        Cerrada(grupoCon(unLadron(9), unCofreCon(Llave))) shouldBe true
      }

      "no puede ser superado por ningún héroe sin una llave" in {
        Cerrada(grupoCon(heroe(Guerrero()))) shouldBe false
      }

    }

    "cuando esta escondido" - {
      "puede ser superado por un mago que desbloqueo vislumbrar" in {
        val mago = unMago(Aprendizaje(Vislumbrar, 0))

        Escondida(grupoCon(mago)) shouldBe true
      }


      "no puede ser superado por un mago que no desbloqueo vislumbrar" in {
        val mago = unMago(Aprendizaje(Vislumbrar, 1000))

        Escondida(grupoCon(mago)) shouldBe false
      }

      "puede ser superado por un ladron con habilidad 6" in {
        val ladron = unLadron(6)

        Escondida(grupoCon(ladron)) shouldBe true
      }

      "no puede ser superado por un ladron con habilidad menor a 6" in {
        val ladron = unLadron(5)

        Escondida(grupoCon(ladron)) shouldBe false
      }

      "no puede ser superado por otro heroe" in {
        val guerrero = heroe(Guerrero())

        Escondida(grupoCon(guerrero)) shouldBe false
      }
    }

  }

  "cuando esta hechizado" - {
      "puede ser superado por un mago que conoce el hechizo" in {
        val mago = unMago(Aprendizaje(Ibracadabra, 0))

        Encantada(Ibracadabra)(grupoCon(mago)) shouldBe true
      }

      "no puede ser superado por un mago que no desbloqueo el hechizo " in {
        val mago = unMago(Aprendizaje(Ibracadabra, 1000))

        Encantada(Ibracadabra)(grupoCon(mago)) shouldBe false
      }
    }
}
