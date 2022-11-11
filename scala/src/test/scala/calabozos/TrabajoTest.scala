package calabozos

import calabozos.TestFactories.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

class TrabajoTest extends AnyFreeSpec {

  "Un heroe " - {

    "que es guerrero" - {
      "la fuerza arranca siendo la base" in {
        val heroe = unGuerrero(nivel = 0, fuerzaBase = 3)
        heroe.fuerza shouldEqual 3
      }

      "la fuerza aumenta un 20% por cada nivel" in {
        val heroe = unGuerrero(nivel = 5, fuerzaBase = 3)
        heroe.fuerza shouldEqual 3 + 3 * 0.2 * 5
      }
    }

    "que es ladron" - {
      "la habilidad de manos arranca siendo la base" in {
        val heroe = unLadron(nivel = 0, habilidadBase = 3)
        heroe.tieneHabilidad(3) shouldBe true
        heroe.tieneHabilidad(4) shouldBe false
      }

      "la habilidad de manos aumenta tres unidades por cada nivel" in {
        val heroe = unLadron(nivel = 5, habilidadBase = 3)
        heroe.tieneHabilidad(3 + 3 * 5) shouldBe true
        heroe.tieneHabilidad(3 + 3 * 5 + 1) shouldBe false
      }
    }

    "que es mago" - {
       "no tiene el nivel suficiente para aprender el hechizo" in {
         val heroe = unMago(nivel = 3, aprendizaje = Aprendizaje(Vislumbrar, 4))
         heroe.sabeHechizo(Vislumbrar) shouldBe false
       }

       "tiene el nivel suficiente para aprender el hechizo" in {
          val heroe = unMago(nivel = 4, aprendizaje = Aprendizaje(Vislumbrar, 4))
          heroe.sabeHechizo(Vislumbrar) shouldBe true
       }
    }
  }

}
