package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class CriterioTest extends AnyFreeSpec {

  def heroeConCriterio(criterio: Criterio): Heroe = Heroe(nivel = 1, salud = 1, Guerrero(fuerzaBase = 1, velocidadBase = 1), criterio, personalidad = Introvertido);
  def habitacionDondeNoPasaNada: Habitacion = Habitacion(List.empty, NoPasaNada)
  val grupo = grupoCon(List(heroeConCriterio(Heroico), heroeConCriterio(Heroico)))
  val puertas = List(Puerta(List.empty, habitacionDondeNoPasaNada), Puerta(List.empty, habitacionDondeNoPasaNada))
  val unaPuerta = Puerta(List(), Habitacion(List.empty, NoPasaNada))
  val unaPuertaConTesoro: Puerta = Puerta(List(), Habitacion(List.empty, TesoroPerdido(Ganzua)))


  "Un heroe" - {

    "heroico elige la ultima puerta encontrada" in {
      heroeConCriterio(Heroico).elegirPuerta(puertas ++ List(unaPuerta), grupo).get shouldBe unaPuerta
    }

    "ordenado elige la primera puerta encontrada" in {
      heroeConCriterio(Ordenado).elegirPuerta(List(unaPuerta) ++ puertas, grupo).get shouldBe unaPuerta
    }

    "vidente elige la puerta que mejor deje al grupo" in {
      heroeConCriterio(Vidente).elegirPuerta(puertas ++ List(unaPuertaConTesoro) ++ List(unaPuerta), grupo).get shouldBe unaPuertaConTesoro
    }
  }
}
