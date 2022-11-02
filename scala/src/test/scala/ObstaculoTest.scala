import org.scalatest.matchers.should.Matchers._
import org.scalatest.freespec.{AnyFreeSpec}
import calabozos.*
import calabozos.Grupo.Cofre

class ObstaculoTest extends AnyFreeSpec {
  def grupoCon(heroe: Heroe, cofre: Cofre = List()) = Grupo(List(heroe), cofre)
  def ladron(habilidad: Int) = Ladron(1, 1, 1, 1, Ordenado, habilidad)

  "Un obstaculo" - {

    "cuando esta cerrado" - {
      "puede ser superado por un ladron con habilidad de mas de 10" in {
        Cerrada(grupoCon(ladron(11))) shouldBe true
      }

      "puede ser superado por un ladron con una ganzúa" in {
        Cerrada(grupoCon(ladron(9), List(Ganzua))) shouldBe true
      }

      "no puede ser superado por un ladron sin ganzúa ni habilidad mayor a 10" in {
        Cerrada(grupoCon(ladron(9))) shouldBe false
      }

      "puede ser superado por cualquier heroe con una llave" in {
        Cerrada(grupoCon(ladron(9), List(Llave))) shouldBe true
      }

      "no puede ser superado por ningún héroe sin una llave" in {
        Cerrada(grupoCon(Guerrero(1, 1, 1, 1, Heroico))) shouldBe false
      }

    }

    "cuando esta escondida" - {
      "puede ser superado por un mago que desbloqueo vislumbrar" in {
        val vislumbrar = Aprendizaje(Vislumbrar, 0)
        val mago = Mago(10, 10, 10, 10, Heroico, List(vislumbrar))
        val cofre = List()

        Escondida(grupoCon(mago, cofre)) shouldBe true
      }


      "no puede ser superado por un mago que no desbloqueo vislumbrar" in {
        val mago = Mago(10, 10, 10, 10, Heroico, List())
        val cofre = List()

        Escondida(grupoCon(mago, cofre)) shouldBe false
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
