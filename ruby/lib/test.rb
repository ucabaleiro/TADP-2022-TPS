require_relative './resultado'
require_relative './mockeable'

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
        resultado = ResultadoExitoso.new(clase, nombre)
      rescue NoPasoAsercion => no_paso_asercion_error
        resultado = ResultadoFallido.new(clase, nombre, no_paso_asercion_error)
      rescue StandardError => error
        resultado = ResultadoExplotado.new(clase,nombre,error)
      end
      Mockeable.restaurar
      resultado
    end
  end
end
