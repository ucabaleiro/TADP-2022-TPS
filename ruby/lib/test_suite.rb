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
