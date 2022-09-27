class ResultadoAsercion
  def initialize(asercion, pasa)
    @asercion = asercion
    @pasa = pasa
  end

  def pasa?
    @pasa
  end
end
