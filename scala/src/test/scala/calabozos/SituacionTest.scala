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
        MuchosDardos(grupoCon(heroeBuffeado(Ladron(5)))).heroesVivos shouldBe List(heroeBuffeado(Ladron(5)).afectarStats(_.perderSalud(10)))
    }

    "trampa de leones mata al mas lento" in {
        TrampaDeLeones(grupoCon(List(unLadron(5),heroeBuffeado(Ladron(5))))).heroesVivos shouldBe List(heroeBuffeado(Ladron(5)))
    }

    "encuentro " - {
        val mago = Heroe(stats(), Mago(List(Aprendizaje(Vislumbrar, 0))), Heroico, Bigote)

        "cuando se agradan el heroe encontrado se suma al grupo" in {
          val grupo = grupoCon(mago)
          val guerrero = Heroe(stats(), Guerrero(), Heroico, Bigote)

          Encuentro(guerrero)(grupo).heroesVivos shouldBe grupoCon(List(mago,guerrero)).heroesVivos
        }

        "cuando no se agradan pelean " - {
            "si el heroe encontrado es mas fuerte el grupo sufre daño" in {
                val grupo = grupoCon(mago)
                val guerrero = Heroe(statsBuff,Guerrero(),Heroico,Loquito)

                Encuentro(guerrero)(grupo).heroesVivos shouldBe List()
            }

            "si el heroe encontrado es menos fuerte este escapa y el grupo sube un nivel " in {
                val guerrero = Heroe(statsBuff,Guerrero(),Heroico,Loquito)
                val grupo = grupoCon(guerrero)
                Encuentro(mago)(grupo) shouldBe grupo.afectarHeroes(_.subirNivel)
            }
        }
    }
  }
}
