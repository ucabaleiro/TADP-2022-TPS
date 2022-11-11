package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class ObstaculoTest extends AnyFreeSpec {


  "Un obstaculo" - {
    "cuando esta cerrado" - {
      "puede ser superado por un ladron con habilidad de mas de 10" in {
        Cerrada(grupoCon(unLadron(habilidadBase = 11))) shouldBe true
      }

      "puede ser superado por un ladron con una ganzúa" in {
        Cerrada(grupoCon(unLadron(habilidadBase = 9), unCofreCon(Ganzua))) shouldBe true
      }

      "no puede ser superado por un ladron sin ganzúa ni habilidad mayor a 10" in {
        Cerrada(grupoCon(unLadron(habilidadBase = 9))) shouldBe false
      }

      "puede ser superado por cualquier heroe con una llave" in {
        Cerrada(grupoCon(unLadron(habilidadBase = 9), unCofreCon(Llave))) shouldBe true
      }

      "no puede ser superado por ningún héroe sin una llave" in {
        Cerrada(grupoCon(unGuerrero())) shouldBe false
      }

    }

    "cuando esta escondido" - {
      "puede ser superado por un mago que desbloqueo vislumbrar" in {
        val mago = unMago(aprendizaje = Aprendizaje(Vislumbrar, 0))

        Escondida(grupoCon(mago)) shouldBe true
      }


      "no puede ser superado por un mago que no desbloqueo vislumbrar" in {
        val mago = unMago(aprendizaje = Aprendizaje(Vislumbrar, 1000))

        Escondida(grupoCon(mago)) shouldBe false
      }

      "puede ser superado por un ladron con habilidad 6" in {
        val ladron = unLadron(habilidadBase = 6)

        Escondida(grupoCon(ladron)) shouldBe true
      }

      "no puede ser superado por un ladron con habilidad menor a 6" in {
        val ladron = unLadron(habilidadBase = 5)

        Escondida(grupoCon(ladron)) shouldBe false
      }

      "no puede ser superado por otro heroe" in {
        Escondida(grupoCon(unGuerrero())) shouldBe false
      }
    }

  }

  "cuando esta hechizado" - {
      "puede ser superado por un mago que conoce el hechizo" in {
        val mago = unMago(aprendizaje = Aprendizaje(Ibracadabra, 0))

        Encantada(Ibracadabra)(grupoCon(mago)) shouldBe true
      }

      "no puede ser superado por un mago que no desbloqueo el hechizo " in {
        val mago = unMago(aprendizaje = Aprendizaje(Ibracadabra, 1000))

        Encantada(Ibracadabra)(grupoCon(mago)) shouldBe false
      }

      "no lo puede superar un no mago" in {
        Encantada(Ibracadabra)(grupoCon(unGuerrero())) shouldBe false
      }
    }
}
