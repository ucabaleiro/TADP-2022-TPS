require_relative './test'

class TestSuite
  def initialize(clase_suite)
    @clase_suite = clase_suite
  end

  def testear(*metodos)
    metodos.push(*metodos_test) if metodos.empty?
    resultados = metodos.map { |it| Test.new(it.to_s, @clase_suite).testear }
    ResultadoSuite.new(@clase_suite, resultados)
  end

  private
  def metodos_test
    @clase_suite.instance_methods(false)
                .filter { |metodo| metodo.to_s.start_with? "testear_que_" }
                .map { |metodo| metodo.to_s.delete_prefix("testear_que_").to_sym }
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