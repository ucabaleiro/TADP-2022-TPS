require_relative './asercion'
require_relative './aserciones'
require_relative './asertable'
require_relative './resultado'

class TADsPec
  def self.testear (*arg)
    test_suites = arg.empty? ? [] : [arg]
    if test_suites.empty?
      test_suites = ObjectSpace.each_object(Class).map { |it| it }
    end

    test_metodos = arg[1..]
    if test_metodos == nil
      # TODO: Bindear a una instancia de la test suite
      @tests = test_suites.flat_map { |it| it.instance_methods(false).map { |sym| it.instance_method(sym) } }
                          .filter { |it| it.name.start_with? "testear_que_" }
    else
      @tests = test_metodos.flat_map { |metodo| test_suites.map { |suite| suite.method("testear_que_" + metodo.to_s) } }
    end

    Object.define_method(:deberia) do |asercion|
      asercion.call(self)
    end

    @tests.each { |it| it.call }

    Object.undef_method(:deberia)
  end
end
