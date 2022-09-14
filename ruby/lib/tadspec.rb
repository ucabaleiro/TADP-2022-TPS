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

def uno_de_estos(*valores)
  if valores.length == 1
    proc { |it| valores[0].include? it }
  else
    proc { |it| valores.include? it }
  end
end

class Object
  def deberia(asercion)
    asercion.call(self)
  end
end

def method_missing(symbol, *args, &block)
  if symbol.start_with? "ser_"
    asertar "#{symbol[4..]}?"
  else
    super(symbol, *args, &block)
  end
end

def respond_to_missing?(symbol, include_all)
  if symbol.start_with? "ser_"
    true
  else
    super(symbol, include_all)
  end
end
