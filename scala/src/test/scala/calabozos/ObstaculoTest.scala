package calabozos

import calabozos.Grupo.Cofre
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class ObstaculoTest extends AnyFreeSpec {
  def grupoCon(heroe: Heroe, cofre: Cofre = List()): Grupo = Grupo(List(heroe), cofre)
  def unLadron(habilidad: Int): Heroe = heroe(Ladron(habilidad))
  def unMago(aprendizaje: Aprendizaje): Heroe = heroe(Mago(List(aprendizaje)))
  def unCofreCon(item: Item): Cofre = List(item)
  def heroe(trabajo: Trabajo): Heroe = Heroe(stats, trabajo, Heroico)
  def stats: Stats = Stats(1,1,1,1)

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

    "cuando esta escondida" - {
      "puede ser superado por un mago que desbloqueo vislumbrar" in {
        val mago = unMago(Aprendizaje(Vislumbrar, 0))

        Escondida(grupoCon(mago)) shouldBe true
      }


      "no puede ser superado por un mago que no desbloqueo vislumbrar" in {
        val mago = unMago(Aprendizaje(Vislumbrar, 1000))

        Escondida(grupoCon(mago)) shouldBe false
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
