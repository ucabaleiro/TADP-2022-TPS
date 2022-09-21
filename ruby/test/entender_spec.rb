require 'rspec'
require_relative '../lib/tadspec'

describe 'Entender' do

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

  context 'leandro entiende el mensaje viejo' do
    include Aserciones
    it 'pasa' do
      expect(leandro.deberia entender :viejo?).to eq(true)
    end
  end

  context 'leandro entiende el mensaje class' do
    include Aserciones
    it 'pasa, se hereda de object' do
      expect(leandro.deberia entender :class).to eq(true)
    end
  end

  context 'leandro deberia entender el mensaje nombre' do
    include Aserciones
    it 'no pasa' do
      expect(leandro.deberia entender :nombre).to eq(false )
    end
  end

end
