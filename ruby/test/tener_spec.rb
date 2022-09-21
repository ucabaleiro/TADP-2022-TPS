require 'rspec'
require_relative '../lib/tadspec'

describe 'Tener' do

  class Docente
    def initialize(edad)
      @edad = edad
    end
    def edad
      @edad
    end
    def viejo?
      @edad > 29
    end
  end
  nico = Docente.new(30)
  leandro = Docente.new(22)
  docentes = [nico, leandro]


  before do
    # Do nothing
  end

  after do
    # Do nothing
  end

  context 'leandro deberia tener 22' do
    include Aserciones
    it 'pasa' do
      expect(leandro.deberia tener_edad 22).to eq(true )
    end
  end

  context 'leandro deberia tener nombre leandro' do
    include Aserciones
    it 'no pasa' do
      expect(leandro.deberia tener_nombre "leandro").to eq(false )
    end
  end

  context 'leandro deberia tener nombre nulo' do
    include Aserciones
    it 'pasa, leandro no tiene nombre' do
      expect(leandro.deberia tener_nombre nil).to eq(true )
    end
  end

  context 'leandro deberia tener edad mayor a 20' do
    include Aserciones
    it 'pasa' do
      expect(leandro.deberia tener_edad mayor_a 20).to eq(true )
    end
  end

  context 'leandro deberia tener edad menor a 25' do
    include Aserciones
    it 'pasa' do
      expect(leandro.deberia tener_edad menor_a 25).to eq(true )
    end
  end

  context 'leandro deberia tener edad incluida en la lista' do
    include Aserciones
    it 'pasa' do
      expect(leandro.deberia tener_edad uno_de_estos [7, 22, "hola"]).to eq(true )
    end
  end

end
