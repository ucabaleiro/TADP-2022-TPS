import org.scalatest.matchers.should.Matchers._
import org.scalatest.freespec.{AnyFreeSpec}
import calabozos.*

class PuertaTest extends AnyFreeSpec{

    "Una puerta " - {

        "cuando esta cerrada"  - {
            val ladronHabilidoso = Ladron(1, 1, 1, 1, 11)
            val ladronNoHabilidoso = Ladron(1, 1, 1, 1, 9)
            val guerrero = Guerrero(1, 1, 1, 1)
            
            val puerta = new Puerta(List(Cerrada))
            
            "puede ser abierta por un grupo con un ladron con habilidad de mas de 10" in {
                val grupo = Grupo(List(ladronHabilidoso), List())

                puerta.puedeSerAbiertaPor(grupo) shouldBe true
            }

            "puede ser abierta por un grupo con un ladron con una ganzúa" in {
                val grupo = Grupo(List(ladronNoHabilidoso), List(Ganzua))

                puerta.puedeSerAbiertaPor(grupo) shouldBe true
            }

            "no puede ser abierta por un grupo con un ladron sin ganzúa ni habilidad mayor a 10" in {
                val grupo = Grupo(List(ladronNoHabilidoso), List())

                puerta.puedeSerAbiertaPor(grupo) shouldBe false
            }

            "puede ser abierta por un grupo con cualquier heroe con una llave" in {
                val grupo = Grupo(List(guerrero), List(Llave))


                puerta.puedeSerAbiertaPor(grupo) shouldBe true
            }

            "no puede ser abierta por un grupo con ningún héroe sin una llave" in {
                val grupo = Grupo(List(guerrero), List())

                puerta.puedeSerAbiertaPor(grupo) shouldBe false
            }

        }
        "cuando esta escondida" - {
            val ladronHabilidoso = Ladron(1, 1, 1, 1, 11)
            val ladronNoHabilidoso = Ladron(1, 1, 1, 1, 9)
            val guerrero = Guerrero(1, 1, 1, 1)
            val aprendizaje = Aprendizaje(Vislumbrar, 9)
            val magoRapido = Mago(10, 10, 10, 10, List(aprendizaje))
            val magoLento = Mago(10, 10, 2, 10, List(aprendizaje))
            
            val puerta = new Puerta(List(Escondida))

            "puede ser abierta por un grupo con un mago que desbloqueo vislumbrar" in {
                val grupo = Grupo(List(magoRapido,guerrero), List())

                puerta.puedeSerAbiertaPor(grupo) shouldBe true
            }

            "no puede ser abierta por un grupo con un mago que no desbloqueo vislumbrar" in {
                val grupo = Grupo(List(magoLento,guerrero), List())

                puerta.puedeSerAbiertaPor(grupo) shouldBe false
            }
            "puede ser abierta por un ladron con mas de 6 de habilidad" in {
                val grupo = Grupo(List(ladronHabilidoso,magoLento), List())

                puerta.puedeSerAbiertaPor(grupo) shouldBe true
            }
            "no puede ser abierta por un ladron con menos de 6 de habilidad" in {
                val grupo = Grupo(List(ladronNoHabilidoso,magoLento), List())

                puerta.puedeSerAbiertaPor(grupo) shouldBe false
            }
            

        }
        "cuando esta encantada" - {

                val aprendizaje = Aprendizaje(Ibracadabra, 1)
                val mago = Mago(10, 10, 10, 10, List(aprendizaje))
                
                val puerta = new Puerta(List(Encantada(Ibracadabra)))

            "puede ser abierta por un mago que sabe el hechizo" in {
                val grupo = Grupo(List(mago), List())

                puerta.puedeSerAbiertaPor(grupo) shouldBe true
            }
        }

    }


}
