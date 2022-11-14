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

        habitacion.hacerPasar(grupo) shouldBe grupoCon(unGuerrero(salud = 90), habitacionesVisitadas = List(habitacion))
      }

      "puede ser recorrida exitosamente" in {
        val grupo = grupoCon(unGuerrero(salud = 100))
        val habitacion = unaHabitacionConSalida(situacion = MuchosDardos)

        habitacion.serRecorridaPor(grupo) shouldBe Some(grupoCon(unGuerrero(salud = 90), habitacionesVisitadas = List(habitacion)))
      }

      "puede fallar porque el grupo se queda sin puertas atravesables" in {
        val grupo = grupoCon(unGuerrero())
        val habitacion = unaHabitacionConSalida(obstaculo = Cerrada)

        habitacion.serRecorridaPor(grupo) shouldBe None
      }

      "puede fallar porque el grupo se queda sin héroes vivos" in {
        val grupo = grupoCon(unGuerrero(salud = 10))
        val habitacion = unaHabitacionConSalida(situacion = MuchosDardos)

        habitacion.serRecorridaPor(grupo) shouldBe None
      }

      "un grupo no atraviesa la misma situación dos veces" in {
        val grupo = grupoCon(unGuerrero(salud = 20))

        val habitacion = unaHabitacionConSalida(situacion = MuchosDardos)

        habitacion.hacerPasar( habitacion.hacerPasar(grupo) ) shouldBe grupoCon(unGuerrero(salud = 10), habitacionesVisitadas = List(habitacion))
      }
    }

    "cuando es salida" - {
      "no modifica al grupo que pasa por ella" in {
        val grupo = grupoCon(unGuerrero())
        Salida.hacerPasar(grupo) shouldBe grupoCon(unGuerrero())
      }

      "retorna un grupo exitoso al recorrerla" in {
        val grupo = grupoCon(unGuerrero())
        Salida.serRecorridaPor(grupo) shouldBe Some(grupoCon(unGuerrero()))
      }
    }
  }
}
