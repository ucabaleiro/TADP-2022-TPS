module Aserciones
  def ser(valor_o_asercion, texto = "ser")
    if valor_o_asercion.is_a? Asercion
      valor_o_asercion
    else
      Asercion.new(texto, valor_o_asercion) { |it| it.equal? valor_o_asercion }
    end
  end

  def ser_igual(valor)
    Asercion.new("ser igual a", valor) { |it| it == valor }
  end

  def menor_a(valor)
    Asercion.new("ser menor a", valor) { |it| it < valor }
  end

  def mayor_a(valor)
    Asercion.new("ser mayor a", valor) { |it| it > valor }
  end

  def entender(simbolo)
    Asercion.new("entender", simbolo) { |it| it.respond_to? simbolo }
  end

  def uno_de_estos(primer_valor, *otros_valores)
    if primer_valor.is_a? Array and otros_valores.empty?
      valores = primer_valor
    else
      valores = [primer_valor, *otros_valores]
    end
    Asercion.new("uno de estos", valores) { |it| valores.include? it }
  end

  def en(&bloque)
    bloque
  end

  def explotar_con(error_esperado)
    Asercion.new("explotar con", error_esperado) do |proc|
      begin
        proc.call
      rescue StandardError => error_recibido
        error_recibido.is_a? error_esperado
      else
        false
      end
    end
  end

  def method_missing(symbol, *args, &block)
    if symbol.start_with? "ser_"
      Asercion.new(symbol.to_s, true) { |it| it.send("#{symbol.to_s.delete_prefix("ser_")}?") }
    elsif symbol.start_with? "tener_"
      ser(args[0], symbol.to_s).desde do |it|
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
