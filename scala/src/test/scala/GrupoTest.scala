import org.scalatest.matchers.should.Matchers._
import org.scalatest.freespec.{AnyFreeSpec}
import calabozos.*

class GrupoTest extends AnyFreeSpec {
  "Un grupo" - {
      "tiene puntaje" in {
        val grupo = Grupo(
          List(Guerrero(1, 1, 3, 100, Ordenado), Ladron(1, 1, 1, 0, Ordenado, 12)),
          List(Llave, Ganzua, Llave, Llave),
          List()
        )
        grupo.puntaje shouldBe 12

      }
    }
  }