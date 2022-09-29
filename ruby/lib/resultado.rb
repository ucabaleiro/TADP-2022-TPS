require 'colorize'

class ResultadoTADsPec
  def initialize(resultados_suites)
    @resultados_suites = resultados_suites
  end

  def imprimir
    @resultados_suites .map { |resultado| resultado.imprimir }
    puts ""
    puts "==============="
    puts "REPORTE TADSPEC"
    puts "==============="
    puts ""
    puts "\t#{cantidad_exitosos}/#{cantidad} pasaron".green
    puts "\t#{cantidad_fallidos}/#{cantidad} fallaron".yellow
    puts "\t#{cantidad_explotados}/#{cantidad} explotaron".red
  end

  private
  def cantidad
    @resultados_suites.sum { |elem| elem.cantidad }
  end

  def cantidad_exitosos
    @resultados_suites.sum { |elem| elem.cantidad_exitosos }
  end

  def cantidad_fallidos
    @resultados_suites.sum { |elem| elem.cantidad_fallidos }
  end

  def cantidad_explotados
    @resultados_suites.sum { |elem| elem.cantidad_explotados }
  end
end

class ResultadoSuite
  def initialize(clase_suite, resultados_tests)
    @clase_suite = clase_suite
    @resultados_tests = resultados_tests
  end

  def imprimir
    puts "#{@clase_suite}".colorize(:color => :black, :background => bg_color)
    @resultados_tests.map { |resultado| resultado.imprimir }
    puts ""
  end

  def bg_color
    if @resultados_tests.any? {|ob|ob.is_a?ResultadoExplotado}
      :red
    elsif @resultados_tests.any? { |resultado| resultado.is_a? ResultadoFallido }
      :yellow
    elsif @resultados_tests.any? { |resultado| resultado.is_a? ResultadoExitoso }
      :green
    else
      :white
    end
  end

  def cantidad
    @resultados_tests.size
  end

  def cantidad_exitosos
    @resultados_tests.filter { |elem| elem.is_a?(ResultadoExitoso) }.size
  end

  def cantidad_fallidos
    @resultados_tests.filter { |elem| elem.is_a?(ResultadoFallido) }.size
  end

  def cantidad_explotados
    @resultados_tests.filter { |elem| elem.is_a?(ResultadoExplotado) }.size
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
    puts "\t\u{1f4a9} El test #{@clase_suite.to_s}:#{@nombre} explotó:".red
    puts "\t\t\t\t#{@error.backtrace.join("\n\t\t\t\t")}"
  end

end
