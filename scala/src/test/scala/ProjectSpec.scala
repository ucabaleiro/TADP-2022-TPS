import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.*

case class Foo(bar: Option[Bar])

case class Bar(puntaje: Int)


class ProjectSpec extends AnyFreeSpec {

  "Este proyecto" - {

    "cuando está correctamente configurado" - {
      "debería resolver las dependencias y pasar este test" in {
        lista.maxByOption(_.puntaje)



        Prueba.materia shouldBe "tadp"
      }
    }
  }

}
