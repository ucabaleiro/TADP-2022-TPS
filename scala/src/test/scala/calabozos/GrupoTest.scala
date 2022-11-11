package calabozos

import calabozos.TestFactories.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class GrupoTest extends AnyFreeSpec {
 "Un grupo" - {
     "tiene puntaje" in {
       val heroeMuerto = unGuerrero(salud = 0)

       grupoCon(List(unLadron(habilidadBase = 3), unGuerrero(nivel = 20, salud = 20)))
         .agregarItem(Ganzua)
         .agregarItem(Llave)
         .agregarHeroe(heroeMuerto)
         .puntaje shouldBe 37
     }
   }
}
