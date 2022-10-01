class Asercion
  def initialize(&condicion_a_pasar)
    @condicion_a_pasar = condicion_a_pasar
    @pasos_anteriores = []
  end

  def desde(&bloque)
    @pasos_anteriores.push(bloque)
    self
  end

  def ejecutar_en(objeto)
    valor_obtenido = @pasos_anteriores.inject(objeto) { |o, proc| proc.call(o) }
    @condicion_a_pasar.call(valor_obtenido)
  end

end

class AsercionSobreSpy < Asercion
  def initialize(mensaje, &condicion_a_pasar)
    @mensaje = mensaje
    @aserciones_posteriores = []
    super(&condicion_a_pasar)
  end

  def veces(veces)
    @aserciones_posteriores << proc do |espia_obtenido|
      unless espia_obtenido.recibio_n_veces?(@mensaje, veces)
        raise AsercionNoPasoError.new(
          "recibir #{@mensaje} #{veces} veces", veces,
          espia_obtenido.cuantas_veces_recibio(@mensaje))
      end
    end
    self
  end

  def con_argumentos(*args)
    @aserciones_posteriores << proc do |espia_obtenido|
      unless espia_obtenido.recibio_con_argumentos?(@mensaje, *args)
        raise AsercionNoPasoError.new(
          "recibir #{@mensaje} con argumentos #{args}",
          true, false)
      end
    end
    self
  end

  def ejecutar_en(objeto)
    espia_obtenido = super
    @aserciones_posteriores.map { |proc| proc.call(espia_obtenido) }
  end
end
