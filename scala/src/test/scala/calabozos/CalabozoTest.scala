package calabozos

import calabozos.TestFactories.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*
import scala.util.{Failure, Success}

class CalabozoTest extends AnyFreeSpec {
  "Un calabozo" - {
    "al hacer entrar a un grupo" - {
      "cuando su entrada es su salida el grupo puede salir" in {
        val calabozo = Calabozo(unaPuertaDeSalida())
        val grupo = grupoCon(unGuerrero(fuerzaBase = 20, salud = 100))

        calabozo.hacerEntrar(grupo) shouldBe Success(grupo)
      }

      "cuando no pueden abrir la puerta de entrada la aventura falla" in {
        val habitacion = unaHabitacion(situacion = MuchosDardos)
        val calabozo = Calabozo(unaPuerta(obstaculos = List(Cerrada), ubicacion = habitacion))
        val grupo = grupoCon(unGuerrero(fuerzaBase = 20, salud = 100))

        calabozo.hacerEntrar(grupo) shouldBe Failure(GrupoEncerradoException())
      }
    }

    "al consultar cuántos niveles necesita un grupo" - {
      "devuelve la cantidad de niveles que necesita un grupo para salir" in {
        val mago = unMago(aprendizaje = Aprendizaje(Vislumbrar, 10))
        val calabozo = Calabozo(unaPuertaDeSalida(obstaculo = Encantada(Vislumbrar)))

        calabozo.nivelesParaGrupo(grupoCon(mago)) shouldBe Success(10)
      }

      "devuelve None si el grupo no puede salir dándole 20 niveles" in {
        val mago = unMago(aprendizaje = Aprendizaje(Vislumbrar, 21))
        val calabozo = Calabozo(unaPuertaDeSalida(obstaculo = Encantada(Vislumbrar)))

        calabozo.nivelesParaGrupo(grupoCon(mago)) shouldBe Failure(GrupoEncerradoException())
      }
    }

    "al consultar el mejor grupo" - {
      val mago = unMago(aprendizaje = Aprendizaje(Vislumbrar, 0))
      val puerta = unaPuertaDeSalida(obstaculo = Encantada(Vislumbrar))
      val calabozo = Calabozo(puerta)

      "Si el grupo tiene mas heroes vivos gana" in {
        val grupo1 = grupoCon(mago)
        val grupo2 = grupoCon(List(mago, unGuerrero()))
        val grupo3 = grupoCon(List(mago, unGuerrero(), unGuerrero()))

        calabozo.mejorGrupo(List(grupo1, grupo2, grupo3)) shouldBe Some(grupo3)
      }

      "Si el grupo tiene menos heroes muertos gana" in {
        val grupo1 = grupoCon(mago)
        val grupo2 = grupoCon(List(mago, unGuerrero().morir()))
        val grupo3 = grupoCon(List(mago, unGuerrero().morir(), unGuerrero().morir()))

        calabozo.mejorGrupo(List(grupo1, grupo2, grupo3)) shouldBe Some(grupo1)
      }

      "Si tiene mas items gana" in {
        val grupo1 = grupoCon(mago, unCofreCon(Llave, Ganzua))
        val grupo2 = grupoCon(mago)

        calabozo.mejorGrupo(List(grupo1, grupo2)) shouldBe Some(grupo1)
      }

      "Si es nivel mas alto gana" in {
        val grupo1 = grupoCon(mago.subirNivel().subirNivel())
        val grupo2 = grupoCon(mago.subirNivel())
        val grupo3 = grupoCon(mago)

        calabozo.mejorGrupo(List(grupo1, grupo2, grupo3)) shouldBe Some(grupo1)
      }

      "Si ninguno puede superar el calabozo no hay mejor" in {
        val grupo1 = grupoCon(unGuerrero())
        val grupo2 = grupoCon(List(unGuerrero(), unGuerrero()))

        calabozo.mejorGrupo(List(grupo1, grupo2)) shouldBe None
      }
    }

  }
}
