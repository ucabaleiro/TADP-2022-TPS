package calabozos

import calabozos.TestFactories.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

// TODO
class CalabozoTest extends AnyFreeSpec {
  "Un calabozo" - {
    "al hacer entrar a un grupo" - {
      "cuando su entrada es su salida el grupo puede salir" in {
        val calabozo = Calabozo(puertaDeSalida())
        val grupo = grupoCon(unGuerrero(20, 100))

        calabozo.hacerEntrar(grupo).get shouldBe grupo
      }

      "cuando el grupo recorre las puertas hasta encontrar una salida la aventura termina" in {
        val calabozo = Calabozo(puertaCon(habitacionConSalida()))
        val grupo = grupoCon(unGuerrero(20, 100))

        calabozo.hacerEntrar(grupo).get shouldBe grupo
      }

      "cuando no pueden abrir la puerta de entrada la aventura falla" in {
        val habitacion = Habitacion(List(), MuchosDardos)
        val calabozo = Calabozo(Puerta(List(Cerrada), habitacion))
        val grupo = grupoCon(unGuerrero(20, 100))

        calabozo.hacerEntrar(grupo) shouldBe None
      }
    }

    "al consultar cuántos niveles necesita un grupo" - {
      "devuelve la cantidad de niveles que necesita un grupo para salir" in {
        val mago = unMago(Aprendizaje(Vislumbrar, 10))
        val calabozo = Calabozo(Puerta(List(Encantada(Vislumbrar)), habitacionConSalida()))

        calabozo.nivelesParaGrupo(grupoCon(mago)) shouldBe Some(10)
      }

      "devuelve None si el grupo no puede salir dándole 20 niveles" in {
        val mago = unMago(Aprendizaje(Vislumbrar, 21))
        val calabozo = Calabozo(Puerta(List(Encantada(Vislumbrar)), habitacionConSalida()))

        calabozo.nivelesParaGrupo(grupoCon(mago)) shouldBe None
      }
    }

    "al consultar el mejor grupo" in {
      val 
    }

  }
}
