package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

// TODO
class TrabajoTest extends AnyFreeSpec {

    "Un heroe " - {

        "que es guerrero  " in {
            val heroe = Heroe(nivel = 5, salud = 2, Guerrero(fuerzaBase = 3, velocidadBase = 1), criterio = Ordenado, personalidad = Loquito);
            heroe.fuerza should be (6.0)
        }
    }


}
