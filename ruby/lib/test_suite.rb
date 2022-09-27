require_relative './test'

class TestSuite
  def initialize(clase)
    @clase = clase
  end

  def metodos_test
    @clase.instance_methods(false)
          .filter { |metodo| metodo.to_s.start_with? "testear_que_" }
          .map { |metodo| metodo.to_s.delete_prefix("testear_que_").to_sym }
  end

  def testear(*metodos)
    metodos.push(*metodos_test) if metodos.empty?
    metodos.each { |it| Test.new(it.to_s, @clase).testear }
  end
end
