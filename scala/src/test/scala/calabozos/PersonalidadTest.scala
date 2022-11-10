package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class PersonalidadTest extends AnyFreeSpec {
  "Personalidades" - {
      "a los introvertidos" - {
        val ladron = unLadron(5)

        "les agradan un grupo de hasta 3 miembros" in {
          Introvertido(grupoCon(List(ladron))) shouldBe true
        }

        "no les agrada un grupo de más de 3 miembros" in {
          Introvertido(grupoCon(List(ladron, ladron, ladron, ladron))) shouldBe false
        }
      }

      "a los bigotes" - {
        val ladron = unLadron(5)
        val guerrero = heroe(Guerrero())

        "les cae bien un grupo donde no hay ladrones" in {
          Bigote(grupoCon(List(guerrero))) shouldBe true
        }

        "no les cae bien un grupo donde hay ladrones" in {
          Bigote(grupoCon(List(ladron, guerrero))) shouldBe false
        }
      }

      "a los interesados" - {
        "se suman a un grupo cuando tiene cierto objeto particular" in {
          Interesado(Llave)(Grupo(List(unLadron(8)), List(Llave))) shouldBe true
        }

        "no se suman a un grupo cuando no tiene cierto objeto particular" in {
          Interesado(Llave)(grupoCon(unLadron(8))) shouldBe false
        }
      }

      "a los loquitos siempre van a querer pelearse porque no les cae bien nadie" in {
          Loquito(grupoCon(unLadron(5))) shouldBe false
      }


  }

}
