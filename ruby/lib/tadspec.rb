Object.define_method(:deberia) do |asercion|
  asercion.call(self)
end

module Aserciones

  def uno_de_estos(*valores) # TODO: Recibir un parámetro que no sea array
    if valores.length == 1
      proc { |it| valores[0].include? it }
    else
      proc { |it| valores.include? it }
    end
  end

  def asertar(metodo, *args)
    proc { |it| it.send(metodo, *args) }
  end

  def ser(valor_o_asercion)  # TODO: Ver si se puede evitar recibir procs
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
      asertar symbol.to_s.delete_prefix("ser_")
    elsif symbol.start_with? "tener_"
      if args[0].class == Proc  # TODO: Evitar repetición de lógica con el ser
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
