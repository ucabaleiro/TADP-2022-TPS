Object.define_method(:deberia) do |asercion|
  asercion.call(self)
end

module Aserciones

  def uno_de_estos(*valores)
    if valores.length == 1
      proc { |it| valores[0].include? it }
    else
      proc { |it| valores.include? it }
    end
  end

  def asertar(metodo, *args)
    proc { |it| it.send(metodo, *args) }
  end

  def ser(valor_o_asercion)
    if valor_o_asercion.class == Proc
      # devuelve el proc para que lo ejecute :deberia
      valor_o_asercion
    else
      # devuelve true si es el mismo objeto
      asertar :equal?,  valor_o_asercion
    end
  end

  def ser_igual(valor)
    # devuelve true si es un objeto equivalente
    asertar :==, valor
    # proc { |it| it == valor }
  end

  def menor_a(valor)
    asertar :<, valor
    # proc { |it| it < valor }
  end

  def mayor_a(valor)
    asertar :>, valor
    # proc { |it| it > valor }
  end

  def entender(simbolo)
    asertar :respond_to?, simbolo
    # proc { |it| it.respond_to? simbolo}
  end


  def explotar_con(excepcion)

  end
  def method_missing(symbol, *args, &block)
    if symbol.start_with? "ser_"
      asertar "#{symbol[4..]}?"
    elsif symbol.start_with? "tener_"
      if args[0].class == Proc
        proc { |it| args[0].call(it.instance_variable_get("@#{symbol[6..]}")) }
      else
        proc { |it| it.instance_variable_get("@#{symbol[6..]}") == args[0] }
      end
    else
      super
    end
  end

  def respond_to_missing?(symbol, include_all)
    super or symbol.start_with?("ser_") or symbol.start_with?("tener_")
  end
end


class TADsPec
  def self.testear (*arg)
    test_suites = arg.empty? ? [] : [arg]
    test_metodos = arg[1..]

    if test_suites.empty?
      test_suites = ObjectSpace.each_object(Class).map { |it| it }
    end

    if test_metodos == nil
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

    # suite = arg[0]
    # unless suite.is_a? Class
    #   raise "No se paso una suite de test"
    # end
    #
    # if arg.count == 1
    #   self.execute_tests self.test_methods suite.instance_methods(false)
    # else
    #   self.execute_tests self.test_methods arg[1..]
    # end
  end

  def execute_tests tests
    tests.each{|test| suite.send(test)}
  end

  def test_methods(metodos)
    metodos.filter{|metodo| metodo.to_s.start_with?("test_")}
    if metodos.empty?
      raise "No hay tests"
    end
  end
end
