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
      rescue AsercionNoPasoError => no_paso_asercion_error
        resultado = ResultadoFallido.new(clase_suite, nombre, no_paso_asercion_error)
      rescue StandardError => error
        resultado = ResultadoExplotado.new(clase_suite,nombre,error)
      end
      Mockeable.restaurar
      resultado
    end
  end
end


class ResultadoExitoso
  def initialize(clase, nombre)
    @clase_suite = clase
    @nombre = nombre
  end

  def imprimir
    puts "\t\u{1F680} El test #{@nombre} pasó exitosamente.".green
  end
end

class ResultadoFallido
  def initialize(clase_suite, nombre, no_paso_asercion)
    @clase_suite = clase_suite
    @nombre = nombre
    @no_paso_asercion = no_paso_asercion
  end

  def imprimir
    puts "\t\u{1F921} El test #{@nombre} falló:".yellow
    puts "\t\tEsperaba #{@no_paso_asercion.nombre} <#{@no_paso_asercion.valor_esperado}> pero obtuvo <#{@no_paso_asercion.valor_obtenido}>"
  end
end

class ResultadoExplotado
  def initialize(clase_suite, nombre, error)
    @clase_suite = clase_suite
    @nombre = nombre
    @error = error
  end

  def imprimir
    puts "\t\u{1f4a9} El test #{@nombre} explotó con #{@error.class}:".red
    puts "\t\t#{@error.backtrace.join("\n\t\t")}"
  end
end
