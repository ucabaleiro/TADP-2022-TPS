package calabozos

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class GrupoTest extends AnyFreeSpec {
  "Un grupo" - {
      "tiene puntaje" in {
        val grupo = Grupo(
          List(
            Heroe(Stats(1,1,3,1), Guerrero(), Heroico),
            Heroe(Stats(1,1,1,0), Guerrero(), Heroico)
          ),
          List(Llave, Ganzua, Llave, Llave),
          List()
        )
        grupo.puntaje shouldBe 12
      }
    }
  }
