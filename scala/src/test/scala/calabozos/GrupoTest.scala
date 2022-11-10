package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class GrupoTest extends AnyFreeSpec {
 "Un grupo" - {
     "tiene puntaje" in {
       val heroeMuerto = Heroe(Stats(0,0,0,0), Guerrero(), Heroico, Loquito)

       grupoCon(List(unLadron(3), heroeBuffeado(Guerrero())))
         .agregarItem(Ganzua)
         .agregarItem(Llave)
         .agregarHeroe(heroeMuerto)
         .puntaje shouldBe 37
     }
   }
}
