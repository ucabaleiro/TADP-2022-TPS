require 'colorize'

require_relative './asercion'
require_relative './aserciones'
require_relative './asertable'
require_relative './no_paso_asercion'
require_relative './test'
require_relative './test_suite'
require_relative './resultado'
require_relative './mock'

class TADsPec
  class << self
    def testear(clase = nil, *metodos)
      Asertable.incluir_en Object
      suites = clase.nil? ? todas_las_test_suites : [TestSuite.new(clase)]
      resultados = suites.flat_map { |it| it.testear(*metodos) }
      Asertable.quitar_de Object
      ResultadoTADsPec.new(resultados).imprimir
    end

    def todas_las_test_suites
      ObjectSpace.each_object(Class)
                 .filter { |it| es_test_suite it }
                 .map { |it| TestSuite.new it }
    end

    def es_test_suite(clase)
      clase.instance_methods(false)
           .any? { |symbol| symbol.to_s.start_with? "testear_que" }
    end
  end
end
