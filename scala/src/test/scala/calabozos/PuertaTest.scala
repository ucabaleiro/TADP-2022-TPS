package calabozos

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class PuertaTest extends AnyFreeSpec {

  "Una puerta " - {
    val habitacionLoca = Habitacion(List.empty, NoPasaNada)
    def stats: Stats = Stats(1, 1, 1, 1)
    val ladronHabilidoso = Heroe(stats, Ladron(11), Heroico)
    val ladronNoHabilidoso = Heroe(stats, Ladron(9), Heroico)
    val guerrero = Heroe(stats, Guerrero(), Heroico)

    val puertaCerrada = Puerta(List(Cerrada), habitacionLoca, false)

    "cuando esta cerrada" - {

      "puede ser abierta por un grupo con un ladron con habilidad de mas de 10" in {
        val grupo = Grupo(List(ladronHabilidoso), List(), List())
        
        puertaCerrada(grupo) shouldBe true
      }

      "puede ser abierta por un grupo con un ladron con una ganzúa" in {
        val grupo = Grupo(List(ladronNoHabilidoso), List(Ganzua), List())

        puertaCerrada(grupo) shouldBe true
      }

      "no puede ser abierta por un grupo con un ladron sin ganzúa ni habilidad mayor a 10" in {
        val grupo = Grupo(List(ladronNoHabilidoso), List(), List())

        puertaCerrada(grupo) shouldBe false
      }

      "puede ser abierta por un grupo con cualquier heroe con una llave" in {
        val grupo = Grupo(List(guerrero), List(Llave), List())

        puertaCerrada(grupo) shouldBe true
      }

      "no puede ser abierta por un grupo con ningún héroe sin una llave" in {
        val grupo = Grupo(List(guerrero), List(), List())

        puertaCerrada(grupo) shouldBe false
      }

    }

    "cuando esta escondida" - {
      val aprendizaje = Aprendizaje(Vislumbrar, 9)
      val magoRapido = Heroe(Stats(10, 10, 10, 10), Mago(List(aprendizaje)), Heroico)
      val magoLento = Heroe(Stats(10, 10, 2, 10), Mago(List(aprendizaje)), Heroico)

      val puerta = new Puerta(List(Escondida), habitacionLoca, false)

      "puede ser abierta por un grupo con un mago que desbloqueo vislumbrar" in {
        val grupo = Grupo(List(magoRapido, guerrero), List(), List())

        puerta(grupo) shouldBe true
      }

      "no puede ser abierta por un grupo con un mago que no desbloqueo vislumbrar" in {
        val grupo = Grupo(List(magoLento, guerrero), List(), List())

        puerta(grupo) shouldBe false
      }
      "puede ser abierta por un ladron con mas de 6 de habilidad" in {
        val grupo = Grupo(List(ladronHabilidoso, magoLento), List(), List())

        puerta(grupo) shouldBe true
      }
      "no puede ser abierta por un ladron con menos de 6 de habilidad" in {
        val grupo = Grupo(List(ladronNoHabilidoso, magoLento), List(), List())

        puerta(grupo) shouldBe false
      }

    }
    "cuando esta encantada" - {
      val habitacionLoca = Habitacion(List.empty, NoPasaNada)

      val aprendizaje = Aprendizaje(Ibracadabra, 1)
      val mago = Heroe(Stats(10, 10, 10, 10), Mago(List(aprendizaje)), Heroico)

      val puerta =
        new Puerta(List(Encantada(Ibracadabra)), habitacionLoca, false)

      "puede ser abierta por un mago que sabe el hechizo" in {
        val grupo = Grupo(List(mago), List(), List())

        puerta(grupo) shouldBe true
      }
    }

  }

}
