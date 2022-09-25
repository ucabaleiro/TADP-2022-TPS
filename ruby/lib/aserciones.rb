module Aserciones
  def ser(valor_o_asercion)
    if valor_o_asercion.is_a? Asercion
      valor_o_asercion
    else
      Asercion.new { |it| it.equal? valor_o_asercion }
    end
  end

  def ser_igual(valor)
    Asercion.new { |it| it == valor }
  end

  def menor_a(valor)
    Asercion.new { |it| it < valor }
  end

  def mayor_a(valor)
    Asercion.new { |it| it > valor }
  end

  def entender(simbolo)
    Asercion.new { |it| it.respond_to? simbolo }
  end

  def uno_de_estos(primer_valor, *otros_valores)
    if primer_valor.is_a? Array and otros_valores.empty?
      valores = primer_valor
    else
      valores = [primer_valor, *otros_valores]
    end
    Asercion.new { |it| valores.include? it }
  end

  def en(&bloque)
    bloque
  end

  def explotar_con(error)
    Asercion.new do |it|
      begin
        it.call
      rescue => error_recibido
        error_recibido.is_a? error
      else
        false
      end
    end
  end

  def method_missing(symbol, *args, &block)
    if symbol.start_with? "ser_"
      Asercion.new { |it| it.send("#{symbol.to_s.delete_prefix("ser_")}?") }
    elsif symbol.start_with? "tener_"
      ser(args[0]).desde do |it|
        it.instance_variable_get("@#{symbol.to_s.delete_prefix("tener_")}")
      end
    else
      super
    end
  end

  def respond_to_missing?(symbol, include_all = false)
    super or symbol.start_with?("ser_") or symbol.start_with?("tener_")
  end
end
