class Test
  def initialize(nombre, clase)
    @nombre = nombre
    @clase = clase
  end

  def testear
    nombre = @nombre
    @clase.new.instance_eval do
      singleton_class.include Aserciones
      begin
        send("testear_que_#{nombre}")
        # TODO: El test pasó: clase, nombre
      rescue NoPasoAsercion => no_paso_asercion_error
        # TODO: El test falló: clase, nombre, no_paso_asercion_error(nombre, valor_esperado, valor_obtenido)
      rescue StandardError => error
        # TODO: El test explotó clase, nombre, error
      end
    end
  end
end
