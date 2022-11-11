package calabozos

import calabozos.TestFactories._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

// TODO
class UbicacionTest extends AnyFreeSpec {
  "Una ubicación" - {
    "cuando es habitación" - {
      "afecta al grupo que pasa por ella" in {
        val grupo = grupoCon(unGuerrero(10, 100))
        val habitacion = Habitacion(List(), MuchosDardos)

        habitacion.pasar(grupo).lider.get shouldBe unGuerrero(10, 90)
      }

      "puede ser recorrida exitosamente" in {
        val grupo = grupoCon(unGuerrero(10, 100))
        val habitacion = Habitacion(List(puertaDeSalida()), MuchosDardos)

        habitacion(grupo).get.lider.get shouldBe unGuerrero(10, 90)
      }

      "puede fallar porque el grupo se queda sin puertas atravesables" in {
        val grupo = grupoCon(unGuerrero(10, 100))
        val habitacion = Habitacion(List(puertaDeSalida(List(Cerrada))), MuchosDardos)

        habitacion(grupo) shouldBe None
      }

      "puede fallar porque el grupo se queda sin héroes vivos" in {
        val grupo = grupoCon(unGuerrero(10, 0))
        val habitacion = Habitacion(List(puertaDeSalida()), MuchosDardos)

        habitacion(grupo) shouldBe None
      }

    }

    "cuando es salida" - {
      "no modifica al grupo que pasa por ella" in {
        val grupo = grupoCon(unGuerrero(10, 0))
        Salida.pasar(grupo) shouldBe grupo
      }

      "retorna un grupo exitoso al recorrerla" in {
        val grupo = grupoCon(unGuerrero(10, 0))
        Salida(grupo).get shouldBe grupo
      }
    }
  }
}
