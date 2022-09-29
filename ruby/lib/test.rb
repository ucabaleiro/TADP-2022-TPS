require_relative './resultado'

class Test
  def initialize(nombre, clase)
    @nombre = nombre
    @clase_suite = clase
  end

  def testear
    nombre = @nombre
    clase = @clase_suite
    @clase_suite.new.instance_eval do
      singleton_class.include Aserciones
      begin
        send("testear_que_#{nombre}")
        ResultadoExitoso.new(clase, nombre)
      rescue NoPasoAsercion => no_paso_asercion_error
        ResultadoFallido.new(clase, nombre, no_paso_asercion_error)
      rescue StandardError => error
        ResultadoExplotado.new(clase,nombre,error)
      end
    end
  end
end
