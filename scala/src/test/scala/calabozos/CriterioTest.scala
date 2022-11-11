package calabozos

import calabozos.TestFactories.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class CriterioTest extends AnyFreeSpec {
  val grupo = grupoCon(List(unGuerrero(criterio = Heroico), unGuerrero(criterio = Heroico)))
  val puertas = List(unaPuerta(), unaPuerta())
  val puerta = unaPuerta()
  val unaPuertaConTesoro: Puerta = Puerta(List(), Habitacion(List.empty, TesoroPerdido(Ganzua)))
  
  "Un heroe" - {

    "heroico elige la ultima puerta encontrada" in {
      unGuerrero(criterio = Heroico).elegirPuerta(puertas ++ List(puerta), grupo).get shouldBe puerta
    }

    "ordenado elige la primera puerta encontrada" in {
      unGuerrero(criterio = Ordenado).elegirPuerta(List(puerta) ++ puertas, grupo).get shouldBe puerta
    }

    "vidente elige la puerta que mejor deje al grupo" in {
      unGuerrero(criterio = Vidente).elegirPuerta(puertas ++ List(unaPuertaConTesoro) ++ List(puerta), grupo).get shouldBe unaPuertaConTesoro
    }
  }
}
