module Espiable
  def espiar(objeto_espiado)
    spy = Spy.new(objeto_espiado)
    Cambios.guardar(spy)
    spy
  end
end

class Spy
  def initialize(objeto_espiado)
    @mensajes_recibidos = []
    @metodos_originales = {}

    objeto_espiado.methods.map do |sym|
      @metodos_originales[sym] = objeto_espiado.method(sym)
    end

    spy = self
    objeto_espiado.methods.each do |simbolo|
      if se_puede_espiar(simbolo)
        objeto_espiado.define_singleton_method(simbolo) do |*args, &bloque|
          spy.enviar_mensaje(simbolo, *args, &bloque)
        end
      end
    end
  end

  def method_missing(mensaje, *args, &bloque)
    @metodos_originales[:send].call(mensaje, *args, &bloque)
  end

  def respond_to_missing?(mensaje, include_private = false)
    @metodos_originales[:respond_to_missing?].call(mensaje, include_private)
  end

  def enviar_mensaje(mensaje, *args, &bloque)
    @mensajes_recibidos.push({ mensaje: mensaje, args: args })
    @metodos_originales[mensaje].call(*args, &bloque)
  end

  def recibio?(metodo)
    @mensajes_recibidos.any? { |mensaje| mensaje[:mensaje] == metodo }
  end

  def recibio_con_argumentos?(mensaje, *args)
    @mensajes_recibidos.any? { |m| m[:mensaje] == mensaje && m[:args] == args }
  end

  def recibio_n_veces?(mensaje, veces)
    @mensajes_recibidos.count { |m| m[:mensaje] == mensaje } == veces
  end

  def revertir
    @metodos_originales.each do |simbolo, metodo|
      if se_puede_espiar(simbolo)
        @metodos_originales[:singleton_class].call.remove_method(simbolo)
      end
      if metodo.owner == @metodos_originales[:singleton_class].call
        @metodos_originales[:singleton_class].call.define_method(simbolo, metodo)
      end
    end
  end

  private
  def se_puede_espiar(simbolo)
    simbolo != :object_id && simbolo != :__send__
  end
end
