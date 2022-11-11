package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class SituacionTest extends AnyFreeSpec {
  "La situacion " -{
    "en la que no pasa nada devuelve el mismo grupo" in {
        NoPasaNada(grupoCon(unLadron(8))) shouldBe grupoCon(unLadron(8))
    }

    "donde hay un tesoro perdido se lo agrega al cofre del grupo" in {
      TesoroPerdido(Llave)(grupoCon(unLadron(8))) shouldBe Grupo(List(unLadron(8)), List(Llave))
    }

    "muchos dardos hace que todo el grupo pierda 10 de salud" in {
      val ladron = heroeBuffeado(Ladron(fuerzaBase = 20, velocidadBase = 20, habilidadBase = 5))

      MuchosDardos(grupoCon(ladron)).heroesVivos shouldBe List(ladron.perderSalud(10))
    }

    "trampa de leones mata al mas lento" in {
      val ladron = unLadron(5)
      val ladronBuffeado = heroeBuffeado(Ladron(velocidadBase = 2, fuerzaBase = 1, habilidadBase = 5))

      TrampaDeLeones(grupoCon(List(ladron, ladronBuffeado))).heroesVivos shouldBe List(ladronBuffeado)
    }

    "encuentro " - {
        val mago = Heroe(salud = 1, nivel = 1, Mago(fuerzaBase = 1, velocidadBase = 1, List(Aprendizaje(Vislumbrar, 0))), Heroico, Bigote)

        "cuando se agradan el heroe encontrado se suma al grupo" in {
          val grupo = grupoCon(mago)
          val bigote = heroe(personalidad = Bigote)

          Encuentro(bigote)(grupo).heroesVivos shouldBe grupoCon(List(mago, bigote)).heroesVivos
        }

        "cuando no se agradan pelean " - {
            "si el heroe encontrado es mas fuerte el grupo sufre daño" in {
              val guerrero = unGuerrero(fuerza = 2)
              val grupo = grupoCon(mago)

              Encuentro(guerrero)(grupo).heroesVivos shouldBe List()
            }

            "si el heroe encontrado es menos fuerte este escapa y el grupo sube un nivel " in {
              val guerrero = unGuerrero(fuerza = 2)
              val grupo = grupoCon(guerrero)

              Encuentro(mago)(grupo) shouldBe grupo.afectarHeroes(_.subirNivel())
            }
        }
    }
  }
}
