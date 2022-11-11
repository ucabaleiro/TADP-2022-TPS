package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class PersonalidadTest extends AnyFreeSpec {
  "Personalidades" - {
      "a los introvertidos" - {
        "les agradan un grupo de hasta 3 miembros" in {
          Introvertido(grupoCon(List(unLadron(), unLadron(), unLadron()))) shouldBe true
        }

        "no les agrada un grupo de más de 3 miembros" in {
          Introvertido(grupoCon(List(unLadron(), unLadron(), unLadron(), unLadron()))) shouldBe false
        }
      }

      "a los bigotes" - {
        "les cae bien un grupo donde no hay ladrones" in {
          Bigote(grupoCon(List(unGuerrero()))) shouldBe true
        }

        "no les cae bien un grupo donde hay ladrones" in {
          Bigote(grupoCon(List(unLadron(), unGuerrero()))) shouldBe false
        }
      }

      "a los interesados" - {
        "se suman a un grupo cuando tiene cierto objeto particular" in {
          Interesado(Llave)(grupoCon(Llave)) shouldBe true
        }

        "no se suman a un grupo cuando no tiene cierto objeto particular" in {
          Interesado(Llave)(grupoCon(Ganzua)) shouldBe false
        }
      }

      "a los loquitos siempre van a querer pelearse porque no les cae bien nadie" in {
          Loquito(grupoCon(unGuerrero())) shouldBe false
      }

  }

}
