import org.scalatest.matchers.should.Matchers._
import org.scalatest.freespec.{AnyFreeSpec}
import calabozos.*

class ObstaculoTest extends AnyFreeSpec {
  "Un obstaculo" - {
    "cuando esta cerrado" - {
      "puede ser superado por un ladri con habilidad de mas de 10" in {
        val ladri = Ladron(1, 1, 1, 1, 11)
        val cofre = List()

        Cerrada.puedeSerSuperadoPor(ladri, cofre) shouldBe true
      }


      "puede ser superado por un ladri con una ganzúa" in {
        val ladri = Ladron(1, 1, 1, 1, 9)
        val cofre = List(Ganzua)

        Cerrada.puedeSerSuperadoPor(ladri, cofre) shouldBe true
      }

      "no puede ser superado por un ladri sin ganzúa ni habilidad mayor a 10" in {
        val ladri = Ladron(1, 1, 1, 1, 9)
        val cofre = List()

        Cerrada.puedeSerSuperadoPor(ladri, cofre) shouldBe false
      }

      "puede ser superado por cualquier heroe con una llave" in {
        val ladri = Ladron(1, 1, 1, 1, 9)
        val cofre = List(Llave)

        Cerrada.puedeSerSuperadoPor(ladri, cofre) shouldBe true
      }

      "no puede ser superado por ningún héroe sin una llave" in {
        val heroe = Guerrero(1, 1, 1, 1)
        val cofre = List()

        Cerrada.puedeSerSuperadoPor(heroe, cofre) shouldBe false
      }

    }
    "cuando esta escondida" - {
      "puede ser superado por un mago que desbloqueo vislumbrar" in {
        val vislumbrar = HechizoYNivel(Vislumbrar, 0)
        val mago = Mago(10, 10, 10, 10, List(vislumbrar))
        val cofre = List()

        Escondida.puedeSerSuperadoPor(mago, cofre) shouldBe true
      }


      "no puede ser superado por un mago que no desbloqueo vislumbrar" in {
        val mago = Mago(10, 10, 10, 10, List())
        val cofre = List()

        Escondida.puedeSerSuperadoPor(mago, cofre) shouldBe false
      }

      "puede ser superado por un ladron nivel 6" in {

      }
    }

  }
  "cuando esta hechizado" - {
      Encantada(Ibracadabra)

      "puede ser superado por un mago que conoce el hechizo" in {
        //val mago = Mago(1, 1, 4, 1, [ HechizoYNivel(Ibracadabra, 4) ])

      }


      "no puede ser superado por un mago que no desbloqueo el hechizo " in {

      }


    }
}
