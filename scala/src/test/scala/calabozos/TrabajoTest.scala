package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class TrabajoTest extends AnyFreeSpec {

    "Un heroe " - {

        "que es guerrero  " in {
            val heroe = Heroe(Stats(3.0, 1.0, 5, 2.0), Guerrero(), Ordenado, Loquito);
            heroe.stats.fuerza should be (6.0)
        }
    }


}
