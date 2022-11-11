package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class GrupoTest extends AnyFreeSpec {
 "Un grupo" - {
     "tiene puntaje" in {
       val heroeMuerto = Heroe(nivel = 1, salud = 0, fuerzaBase = 1, velocidadBase = 1, Guerrero(), Heroico, Loquito)

       grupoCon(List(unLadron(3), heroeBuffeado(Guerrero())))
         .agregarItem(Ganzua)
         .agregarItem(Llave)
         .agregarHeroe(heroeMuerto)
         .puntaje shouldBe 37
     }
   }
}
