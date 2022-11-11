package calabozos

import calabozos.TestFactories.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class UbicacionTest extends AnyFreeSpec {
  "Una ubicación" - {
    "cuando es habitación" - {
      "afecta al grupo que pasa por ella" in {
        val grupo = grupoCon(unGuerrero(salud = 100))
        val habitacion = unaHabitacion(situacion = MuchosDardos)

        habitacion.pasar(grupo).lider shouldBe Some(unGuerrero(salud = 90))
      }

      "puede ser recorrida exitosamente" in {
        val grupo = grupoCon(unGuerrero(salud = 100))
        val habitacion = unaHabitacionConSalida(situacion = MuchosDardos)

        habitacion(grupo) shouldBe Some(grupoCon(unGuerrero(salud = 90)))
      }

      "puede fallar porque el grupo se queda sin puertas atravesables" in {
        val grupo = grupoCon(unGuerrero())
        val habitacion = unaHabitacionConSalida(obstaculo = Cerrada)

        habitacion(grupo) shouldBe None
      }

      "puede fallar porque el grupo se queda sin héroes vivos" in {
        val grupo = grupoCon(unGuerrero(salud = 10))
        val habitacion = unaHabitacionConSalida(situacion = MuchosDardos)

        habitacion(grupo) shouldBe None
      }

    }

    "cuando es salida" - {
      "no modifica al grupo que pasa por ella" in {
        val grupo = grupoCon(unGuerrero())
        Salida.pasar(grupo) shouldBe grupo
      }

      "retorna un grupo exitoso al recorrerla" in {
        val grupo = grupoCon(unGuerrero())
        Salida(grupo) shouldBe Some(grupo)
      }
    }
  }
}
