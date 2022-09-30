require_relative './resultado'
require_relative './mockeable'

class Test
  def initialize(nombre, clase_suite)
    @nombre = nombre
    @clase_suite = clase_suite
  end

  def testear
    nombre = @nombre
    clase_suite = @clase_suite
    clase_suite.new.instance_eval do
      singleton_class.include Aserciones
      begin
        send("testear_que_#{nombre}")
        resultado = ResultadoExitoso.new(clase_suite, nombre)
      rescue NoPasoAsercion => no_paso_asercion_error
        resultado = ResultadoFallido.new(clase_suite, nombre, no_paso_asercion_error)
      rescue StandardError => error
        resultado = ResultadoExplotado.new(clase_suite,nombre,error)
      end
      Mockeable.restaurar
      resultado
    end
  end
end
