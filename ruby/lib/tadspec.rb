require 'colorize'

require_relative './asercion'
require_relative './aserciones'
require_relative './asertable'
require_relative './mockeable'
require_relative './no_paso_asercion'
require_relative './resultado'
require_relative './spy'
require_relative './test'
require_relative './test_suite'

class TADsPec
  class << self
    def testear(clase = nil, *metodos)
      suites = clase.nil? ? todas_las_test_suites : [TestSuite.new(clase)]

      incluir Asertable, Mockeable
      resultados = suites.map { |it| it.testear(*metodos) }
      excluir Asertable, Mockeable

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

    def incluir(*modulos)
      modulos.each do |modulo|
        modulo.instance_methods.each do |sym|
          Object.define_method(sym, modulo.instance_method(sym))
        end
      end
    end

    def excluir(*modulos)
      modulos.each do |modulo|
        modulo.instance_methods.each do |sym|
          Object.undef_method(sym)
        end
      end
    end
  end
end
